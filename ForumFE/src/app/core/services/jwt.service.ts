import { Injectable } from '@angular/core';


@Injectable()
export class JwtService {

  getToken(): String {
    return window.localStorage['jwtToken'];
  }

  saveToken(token: String) {
    window.localStorage['jwtToken'] = token;
  }

  destroyToken() {
    window.localStorage.removeItem('jwtToken');
    window.localStorage.removeItem('user');
  }

  saveUser(user) {
    window.localStorage['user'] = JSON.stringify(user);
  }

  getUser() {
    return JSON.parse(window.localStorage['user']);
  }

}
