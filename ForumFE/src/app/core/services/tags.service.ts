import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { ApiService } from "./api.service";
import { map } from "rxjs/operators";

@Injectable()
export class TagsService {
  constructor(private apiService: ApiService) {}

  getAll(): Observable<any> {
    return this.apiService.get("/tags");
  }
}
