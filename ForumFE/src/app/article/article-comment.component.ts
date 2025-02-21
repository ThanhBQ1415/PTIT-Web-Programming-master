import { Component, EventEmitter, Input, Output, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { of } from "rxjs";
import { concatMap } from "rxjs/operators";

import { Comment, CommentsService, User, UserService } from "../core";

@Component({
  selector: "app-article-comment",
  templateUrl: "./article-comment.component.html",
})
export class ArticleCommentComponent implements OnInit {
  isUpVote = false;
  isDownVote = false;
  upVote = 0;
  downVote = 0;
  constructor(
    private userService: UserService,
    private router: Router,
    private commentService: CommentsService
  ) {}

  @Input() comment: Comment;
  @Output() deleteComment = new EventEmitter<boolean>();

  canModify: boolean;

  ngOnInit() {
    // Load the current user's data

    this.userService.currentUser.subscribe((userData: User) => {
      this.canModify = userData.id === this.comment.userId;
      const x = this.comment.commentVotes.find(
        (item) => item.userId == userData.id
      );
      if (x) {
        this.comment.upVote = x.upVote;
        this.comment.downVote = !x.upVote;
      }
    });

    this.upVote = this.comment.commentVotes.filter(
      (vote) => vote.upVote == true
    ).length;
    this.downVote = this.comment.commentVotes.filter(
      (vote) => vote.upVote == false
    ).length;
  }

  deleteClicked() {
    this.deleteComment.emit(true);
  }

  toggleUpVote() {
    this.isUpVote = true;
    this.userService.isAuthenticated
      .pipe(
        concatMap((authenticated) => {
          // Not authenticated? Push to login screen
          if (!authenticated) {
            this.router.navigateByUrl("/login");
            return of(null);
          }

          this.userService.currentUser
            .subscribe((user) => {
              if (!this.comment.upVote && !this.comment.downVote) {
                this.commentService
                  .createVote({
                    commentId: this.comment.id,
                    userId: user.id,
                    upVote: true,
                  })
                  .subscribe((data) => {
                    this.comment.upVote = true;
                    this.isUpVote = false;
                    this.upVote++;
                  });
              } else if (this.comment.upVote) {
                this.commentService
                  .deleteVote({
                    commentId: this.comment.id,
                    userId: user.id,
                  })
                  .subscribe((data) => {
                    this.comment.upVote = false;
                    this.isUpVote = false;
                    this.upVote--;
                  });
              } else {
                this.commentService
                  .updateVote({
                    commentId: this.comment.id,
                    userId: user.id,
                  })
                  .subscribe((data) => {
                    this.comment.upVote = true;
                    this.comment.downVote = false;
                    this.isUpVote = false;
                    this.upVote++;
                    this.downVote--;
                  });
              }
            })
            .unsubscribe();
          return of(null);
        })
      )
      .subscribe()
      .unsubscribe();
  }

  toggleDownVote() {
    this.isDownVote = true;
    this.userService.isAuthenticated
      .pipe(
        concatMap((authenticated) => {
          // Not authenticated? Push to login screen
          if (!authenticated) {
            this.router.navigateByUrl("/login");
            return of(null);
          }

          this.userService.currentUser
            .subscribe((user) => {
              if (!this.comment.upVote && !this.comment.downVote) {
                this.commentService
                  .createVote({
                    commentId: this.comment.id,
                    userId: user.id,
                    upVote: false,
                  })
                  .subscribe((data) => {
                    this.comment.downVote = true;
                    this.isDownVote = false;
                    this.downVote++;
                  });
              } else if (this.comment.downVote) {
                this.commentService
                  .deleteVote({
                    commentId: this.comment.id,
                    userId: user.id,
                  })
                  .subscribe((data) => {
                    this.comment.downVote = false;
                    this.isDownVote = false;
                    this.downVote--;
                  });
              } else {
                this.commentService
                  .updateVote({
                    commentId: this.comment.id,
                    userId: user.id,
                  })
                  .subscribe((data) => {
                    this.comment.downVote = true;
                    this.comment.upVote = false;
                    this.isDownVote = false;
                    this.downVote++;
                    this.upVote--;
                  });
              }
            })
            .unsubscribe();
          return of(null);
        })
      )
      .subscribe()
      .unsubscribe();
  }
}
