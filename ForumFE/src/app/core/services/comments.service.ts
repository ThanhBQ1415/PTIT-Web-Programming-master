import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

import { ApiService } from "./api.service";
import { Comment } from "../models";
import { map } from "rxjs/operators";
import { DataResponse } from "../models/DataResponse.model";
import { CommentVote } from "../models/CommentVote.model";

@Injectable()
export class CommentsService {
  constructor(private apiService: ApiService) {}

  add(body): Observable<Comment> {
    return this.apiService.post(`/comment`, body);
  }

  getAll(id): Observable<DataResponse<Comment>> {
    return this.apiService.get(`/comments-by-post/${id}`);
  }

  destroy(commentId, articleSlug) {
    return this.apiService.delete(
      `/articles/${articleSlug}/comments/${commentId}`
    );
  }

  createVote(body): Observable<CommentVote> {
    return this.apiService.post("/comment-vote", body);
  }

  deleteVote(body) {
    return this.apiService.post(
      "/comment-vote/delete-by-user-and-comment",
      body
    );
  }

  updateVote(body) {
    return this.apiService.put(
      "/comment-vote/update-by-user-and-comment",
      body
    );
  }
}
