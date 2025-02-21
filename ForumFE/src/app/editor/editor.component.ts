import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, FormControl } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Article, ArticlesService, User, UserService } from "../core";
import { Post } from "../core/models/post.model";

@Component({
  selector: "app-editor-page",
  templateUrl: "./editor.component.html",
})
export class EditorComponent implements OnInit {
  article: Post = {} as Post;
  articleForm: FormGroup;
  tagField = new FormControl();
  errors: Object = {};
  isSubmitting = false;
  type: string = 'create';

  constructor(
    private articlesService: ArticlesService,
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private userService: UserService
  ) {
    // use the FormBuilder to create a form group
    this.articleForm = this.fb.group({
      id: null,
      title: "",
      // description: '',
      body: "",
      open: true,
      userId: [],
      tags: [[]],
    });

    // Initialized tagList as empty array
    // this.article.tagList = [];

    // Optional: subscribe to value changes on the form
    // this.articleForm.valueChanges.subscribe(value => this.updateArticle(value));
  }

  ngOnInit() {
    // If there's an article prefetched, load it
    this.route.data.subscribe((data) => {
      if (Object.keys(data).length > 0) {
        console.log(data);
        this.type = 'update'
        this.article = data.data;
        this.articleForm.patchValue(this.article);
      }
    });

    this.userService.currentUser.subscribe((userData: User) => {
      this.articleForm.patchValue({ userId: userData.id });
    });
  }

  addTag() {
    // // retrieve tag control
    // const tag = this.tagField.value;
    // // only add tag if it does not exist yet
    // if (this.article.tagList.indexOf(tag) < 0) {
    //   this.article.tagList.push(tag);
    // }
    // // clear the input
    // this.tagField.reset("");
  }

  removeTag(tagName: string) {
    // this.article.tagList = this.article.tagList.filter(
    //   (tag) => tag !== tagName
    // );
  }

  submitForm() {
    this.isSubmitting = true;

    console.log(this.articleForm.value);
    
    // post the changes
    this.articlesService.save(this.articleForm.value, this.type).subscribe(
      (article) => {
        console.log(article);

        this.router.navigateByUrl("/article/" + article.id);
      },
      (err) => {
        console.log(err);

        this.errors = err;
        this.isSubmitting = false;
      }
    );
  }

}
