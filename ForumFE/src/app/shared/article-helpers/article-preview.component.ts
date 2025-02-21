import { Component, Input, OnInit } from "@angular/core";

import { Article, User, UserService } from "../../core";
import { Post } from "../../core/models/post.model";
import { PostVote } from "../../core/models/PostVote.model";

@Component({
  selector: "app-article-preview",
  templateUrl: "./article-preview.component.html",
  styleUrls: ["./article-list.component.css"],
})
export class ArticlePreviewComponent implements OnInit {
  @Input() post: Post;
  upVote = 0;
  downVote = 0;

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.currentUser.subscribe((userData: User) => {
      const x: PostVote = this.post.postVotes.find(
        (item) => item.userId == userData.id
      );
      if (x) {
        this.post["upVote"] = x.upVote;
        this.post["downVote"] = !x.upVote;
      }
    });

    this.upVote = this.post.postVotes.filter(
      (vote) => vote.upVote == true
    ).length;
    this.downVote = this.post.postVotes.filter(
      (vote) => vote.upVote == false
    ).length;
  }

  onToggleFavorite(favorited: boolean) {
    this.post["upVote"] = favorited;

    if (favorited) {
      this.post["favoritesCount"]++;
    } else {
      this.post["favoritesCount"]--;
    }
  }

  onToggleUpVote(event: boolean) {
    this.post.upVote = event;
    if (this.post.downVote) {
      this.downVote--;
      this.post.downVote = false
    }
    if (event) {
      this.upVote++;
    } else {
      this.upVote--;
    }
  }

  onToggleDownVote(event: boolean) {
    this.post.downVote = event;
    if (this.post.upVote) {
      this.upVote--;
      this.post.upVote = false
    }
    if (event) {
      this.downVote++;
    } else {
      this.downVote--;
    }
  }
}
