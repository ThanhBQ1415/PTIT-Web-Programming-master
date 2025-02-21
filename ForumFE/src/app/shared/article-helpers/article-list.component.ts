import { Component, Input } from "@angular/core";

import { Article, ArticleListConfig, ArticlesService } from "../../core";
import { Post } from "../../core/models/post.model";
@Component({
  selector: "app-article-list",
  styleUrls: ["article-list.component.css"],
  templateUrl: "./article-list.component.html",
})
export class ArticleListComponent {
  constructor(private articlesService: ArticlesService) {}

  @Input() limit: number;
  @Input()
  set config(config: ArticleListConfig) {
    if (config) {
      this.query = config;
      this.currentPage = 1;
      this.runQuery();
    }
  }

  query: ArticleListConfig;
  results: Post[];
  loading = false;
  currentPage = 1;
  totalPages: Array<number> = [1];

  setPageTo(pageNumber) {
    this.currentPage = pageNumber;
    this.runQuery();
  }

  runQuery() {
    this.loading = true;
    this.results = [];

    // Create limit and offset filter (if necessary)
    if (this.limit) {
      this.query.filters.limit = this.limit;
      this.query.filters.offset = this.limit * (this.currentPage - 1);
    }
    console.log(this.query);
    
    if(this.query.filters.userId != undefined) {
      this.articlesService.getByUserId(this.query.filters.userId).subscribe((data) => { 
        console.log(data);
             
        this.loading = false;
        this.results = data.data;
  
        // this.totalPages = Array.from(
        //   new Array(Math.ceil(data.totalItems / this.limit)),
        //   (val, index) => index + 1
        // );
      });
    }
    else {
      this.articlesService.getAll().subscribe((data) => {      
        this.loading = false;
        this.results = data.data;
  
        // this.totalPages = Array.from(
        //   new Array(Math.ceil(data.totalItems / this.limit)),
        //   (val, index) => index + 1
        // );
      });
    }


    // this.articlesService.query(this.query).subscribe((data) => {
    //   this.loading = false;
    //   this.results = data.articles;

    //   // Used from http://www.jstips.co/en/create-range-0...n-easily-using-one-line/
    //   this.totalPages = Array.from(
    //     new Array(Math.ceil(data.articlesCount / this.limit)),
    //     (val, index) => index + 1
    //   );
    // });
  }
}
