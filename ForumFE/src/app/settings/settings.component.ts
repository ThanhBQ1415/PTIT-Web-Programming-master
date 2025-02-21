import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";

import { User, UserService } from "../core";

@Component({
  selector: "app-settings-page",
  templateUrl: "./settings.component.html",
})
export class SettingsComponent implements OnInit {
  user: User = {} as User;
  settingsForm: FormGroup;
  errors: string = null;
  isSubmitting = false;

  constructor(
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder
  ) {
    // create form group using the form builder
    this.settingsForm = this.fb.group({
      aboutMe: "",
      avatar: ""
    });
    // Optional: subscribe to changes on the form
    // this.settingsForm.valueChanges.subscribe(values => this.updateUser(values));
  }

  ngOnInit() {
    // Make a fresh copy of the current user's object to place in editable form fields
    this.userService
      .getUserById(this.userService.getCurrentUser().id)
      .subscribe((data) => {
        Object.assign(this.user, data);
        console.log(this.user);

        // Fill the form
        this.settingsForm.patchValue(this.user);
        console.log(this.settingsForm.value);

      });
  }

  logout() {
    this.userService.purgeAuth();
    this.router.navigateByUrl("/");
  }

  submitForm() {
    this.isSubmitting = true;

    // // update the model
    // this.updateUser(this.settingsForm.value);
    console.log(this.settingsForm.value);
    

    this.userService.update(this.user.id ,this.settingsForm.value).subscribe(
      (updatedUser) =>
        this.router.navigateByUrl("/profile/" + updatedUser.id),
      (err) => {
        this.errors = err.message;
        this.isSubmitting = false;
      }
    );
  }

  updateUser(values: Object) {
    Object.assign(this.user, values);
  }
}
