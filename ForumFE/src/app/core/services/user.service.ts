import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, BehaviorSubject, ReplaySubject } from "rxjs";

import { ApiService } from "./api.service";
import { JwtService } from "./jwt.service";
import { Profile, User } from "../models";
import { map, distinctUntilChanged } from "rxjs/operators";

@Injectable()
export class UserService {
  private currentUserSubject = new BehaviorSubject<User>({} as User);
  public currentUser = this.currentUserSubject
    .asObservable()
    .pipe(distinctUntilChanged());

  private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
  public isAuthenticated = this.isAuthenticatedSubject.asObservable();

  constructor(
    private apiService: ApiService,
    private http: HttpClient,
    private jwtService: JwtService
  ) {}

  // Verify JWT in localstorage with server & load user's info.
  // This runs once on application startup.
  populate() {
    // If JWT detected, attempt to get & store user's info
    if (this.jwtService.getToken()) {
      // this.apiService.get("/user").subscribe(
      //   (data) => this.setAuth(data.user),
      //   (err) => this.purgeAuth()
      // );
      this.setAuth(this.jwtService.getUser());
    } else {
      // Remove any potential remnants of previous auth states
      this.purgeAuth();
    }
  }

  setAuth(user: User) {
    this.jwtService.saveUser(user);
    // Save JWT sent from server in localstorage
    this.jwtService.saveToken(`Bearer ${user.accessToken}`);
    // Set current user data into observable
    this.currentUserSubject.next(user);
    // Set isAuthenticated to true
    this.isAuthenticatedSubject.next(true);
  }

  purgeAuth() {
    // Remove JWT from localstorage
    this.jwtService.destroyToken();
    // Set current user to an empty object
    this.currentUserSubject.next({} as User);
    // Set auth status to false
    this.isAuthenticatedSubject.next(false);
  }

  attemptAuth(type, credentials): Observable<User> {
    const route = type === "login" ? "/signin" : "/signup";
    return this.apiService.post("/auth" + route, credentials).pipe(
      map((data) => {
        if (route === "/signin") this.setAuth(data);
        return data;
      })
    );
  }

  getCurrentUser(): User {
    return this.currentUserSubject.value;
  }

  // Update the user on the server (email, pass, etc)
  update(id, user): Observable<User> {
    return this.apiService.put("/user/" + id, user).pipe(
      map((data) => {
        // Update the currentUser observable
        this.currentUserSubject.next(data);
        return data;
      })
    );
  }

  getUserById(id): Observable<Profile> {
    return this.apiService.get(`/user/${id}`);
  }
}
