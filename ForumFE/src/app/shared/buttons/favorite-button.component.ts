import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { Router } from "@angular/router";

import { Article, ArticlesService, UserService } from "../../core";
import { of } from "rxjs";
import { concatMap, tap } from "rxjs/operators";
import { Post } from "../../core/models/post.model";

@Component({
  selector: "app-favorite-button",
  templateUrl: "./favorite-button.component.html",
})
export class FavoriteButtonComponent {
  constructor(
    private articlesService: ArticlesService,
    private router: Router,
    private userService: UserService
  ) {}

  @Input() post: Post;
  @Output() toggle = new EventEmitter<boolean>();
  isSubmitting = false;

  // toggleFavorite() {
  //   this.isSubmitting = true;

  //   this.userService.isAuthenticated.pipe(concatMap(
  //     (authenticated) => {
  //       // Not authenticated? Push to login screen
  //       if (!authenticated) {
  //         this.router.navigateByUrl('/login');
  //         return of(null);
  //       }

  //       // Favorite the article if it isn't favorited yet
  //       if (!this.article.favorited) {
  //         return this.articlesService.favorite(this.article.slug)
  //         .pipe(tap(
  //           data => {
  //             this.isSubmitting = false;
  //             this.toggle.emit(true);
  //           },
  //           err => this.isSubmitting = false
  //         ));

  //       // Otherwise, unfavorite the article
  //       } else {
  //         return this.articlesService.unfavorite(this.article.slug)
  //         .pipe(tap(
  //           data => {
  //             this.isSubmitting = false;
  //             this.toggle.emit(false);
  //           },
  //           err => this.isSubmitting = false
  //         ));
  //       }

  //     }
  //   )).subscribe();
  // }

  toggleFavorite() {
    this.isSubmitting = true;
    this.userService.isAuthenticated
      .pipe(
        concatMap((authenticated) => {
          // Not authenticated? Push to login screen
          if (!authenticated) {
            this.router.navigateByUrl("/login");
            return of(null);
          }

          this.userService.currentUser.subscribe((user) => {
            if (!this.post.upVote && !this.post.downVote) {
              console.log(1);
              
              this.articlesService
                .createVote({
                  postId: this.post.id,
                  userId: user.id,
                  upVote: true,
                })
                .subscribe((data) => {
                  this.toggle.emit(true);
                  this.isSubmitting = false;
                });
            } else if (this.post.upVote) {
              console.log(2);
              this.articlesService
                .deleteVote({
                  postId: this.post.id,
                  userId: user.id,
                })
                .subscribe((data) => {
                  this.toggle.emit(false);
                  this.isSubmitting = false;
                });
            } else {
              console.log(3);
              this.articlesService
                .updateVote({
                  postId: this.post.id,
                  userId: user.id,
                })
                .subscribe((data) => {
                  this.toggle.emit(true);
                  this.isSubmitting = false;
                });
            }
          }).unsubscribe();
          return of(null);
        })
      )
      .subscribe().unsubscribe();
  }
}
