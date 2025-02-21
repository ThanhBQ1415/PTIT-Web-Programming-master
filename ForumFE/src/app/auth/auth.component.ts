import { Component, OnInit } from "@angular/core";
import {
  FormBuilder,
  FormGroup,
  FormControl,
  Validators,
} from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";

import { Errors, UserService } from "../core";

@Component({
  selector: "app-auth-page",
  templateUrl: "./auth.component.html",
})
export class AuthComponent implements OnInit {
  authType: String = "";
  title: String = "";
  errors: string = null
  isSubmitting = false;
  authForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder
  ) {
    // use FormBuilder to create a form group
    this.authForm = this.fb.group({
      password: ["", Validators.required],
      username: ["", Validators.required],
    });
  }

  ngOnInit() {
    this.route.url.subscribe((data) => {
      // Get the last piece of the URL (it's either 'login' or 'register')
      this.authType = data[data.length - 1].path;
      // Set a title for the page accordingly
      this.title = this.authType === "login" ? "Đăng nhập" : "Đăng kí";
      // add form control for username if this is the register page
      if (this.authType === "register") {
        this.authForm.addControl("email", new FormControl());
        this.authForm.addControl("role", new FormControl(["user"]));
      }
    });
  }

  submitForm() {
    this.isSubmitting = true;
    this.errors = null;

    const credentials = this.authForm.value;

    console.log(credentials);

    this.userService.attemptAuth(this.authType, credentials).subscribe(
      (data) => {
        if (this.authType === "register") this.router.navigateByUrl("/login");
        else this.router.navigateByUrl("/");
      },
      (err) => {
        this.errors = err.message;
        console.log(this.errors);
        
        this.isSubmitting = false;
      }
    );
  }
}
