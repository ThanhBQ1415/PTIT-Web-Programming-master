import { Component, Input, OnInit } from "@angular/core";

import { Article, UserService } from "../../core";
import { Post } from "../../core/models/post.model";

@Component({
  selector: "app-article-meta",
  templateUrl: "./article-meta.component.html",
})
export class ArticleMetaComponent implements OnInit {
  @Input() post: Post;

  defaultImage =
    "https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Avatar_icon_green.svg/1024px-Avatar_icon_green.svg.png";

  urlImg = "";
  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getUserById(this.post.userId).subscribe((data) => {
      this.urlImg = data.avatar;
    });
  }
}
