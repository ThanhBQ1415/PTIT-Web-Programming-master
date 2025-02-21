import { Component, Input } from '@angular/core';

import { Errors } from '../core';

@Component({
  selector: 'app-list-errors',
  templateUrl: './list-errors.component.html'
})
export class ListErrorsComponent {
  formattedErrors: Array<string> = [];
  error = null

  @Input()
  set errors(errorList: any) {
    this.error = errorList
    // this.formattedErrors = Object.keys(errorList.errors || {})
    //   .map(key => `${key} ${errorList.errors[key]}`);
  }

  get errorList() { return this.formattedErrors; }


}
