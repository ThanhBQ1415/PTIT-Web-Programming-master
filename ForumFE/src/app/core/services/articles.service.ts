import { Injectable } from "@angular/core";
import { HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";

import { ApiService } from "./api.service";
import { Article, ArticleListConfig } from "../models";
import { map } from "rxjs/operators";
import { Post } from "../models/post.model";
import { DataResponse } from "../models/DataResponse.model";
import { PostVote } from "../models/PostVote.model";

@Injectable()
export class ArticlesService {
  constructor(private apiService: ApiService) {}

  getAll(): Observable<DataResponse<Post>> {
    return this.apiService.get("/posts");
  }

  save(post: Post, type): Observable<Post> {
    console.log(type, type === "create");

    if (type === "create") return this.apiService.post("/post", post);
    return this.apiService.put(`/post/${post.id}`, post);
  }

  query(
    config: ArticleListConfig
  ): Observable<{ articles: Article[]; articlesCount: number }> {
    // Convert any filters over to Angular's URLSearchParams
    const params = {};

    Object.keys(config.filters).forEach((key) => {
      params[key] = config.filters[key];
    });

    return this.apiService.get(
      "/articles" + (config.type === "feed" ? "/feed" : ""),
      new HttpParams({ fromObject: params })
    );
  }

  get(id): Observable<Post> {
    return this.apiService.get("/post/" + id);
  }

  getByUserId(id): Observable<DataResponse<Post>> {
    return this.apiService.get("/post-by-user/" + id);
  }

  destroy(id) {
    return this.apiService.delete("/post/" + id);
  }

  // save(article): Observable<Article> {
  //   // If we're updating an existing article
  //   if (article.slug) {
  //     return this.apiService.put('/articles/' + article.slug, {article: article})
  //       .pipe(map(data => data.article));

  //   // Otherwise, create a new article
  //   } else {
  //     return this.apiService.post('/articles/', {article: article})
  //       .pipe(map(data => data.article));
  //   }
  // }

  favorite(slug): Observable<Article> {
    return this.apiService.post("/articles/" + slug + "/favorite");
  }

  unfavorite(slug): Observable<Article> {
    return this.apiService.delete("/articles/" + slug + "/favorite");
  }

  createVote(body): Observable<PostVote> {
    return this.apiService.post("/vote", body);
  }

  updateVote(body): Observable<PostVote> {
    return this.apiService.put(`/vote/update-by-post-and-user`, body);
  }

  deleteVote(body) {
    return this.apiService.post(`/vote/delete-by-post-and-user`, body)
  }
}
