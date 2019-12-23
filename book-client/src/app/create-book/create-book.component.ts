import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, FormControl, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import { BookService } from '../book.service';
import { Book } from '../model/book';
import { Store } from '@ngrx/store';
import { AppState } from '../app.state';
import { AddBooks } from '../store/book.action';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit {

  minDate = new Date(1900, 0, 1);
  maxDate = new Date();
  book: Book = new Book();
  createBookForm: FormGroup;
  submitted: boolean = false;
  invalidLogin: boolean = false;

  books: Observable<Book[]>;

  constructor(private formBuilder: FormBuilder,
              private store: Store<AppState>,
             private bookService:BookService,
              private router: Router) { }

  onSubmit() {
    this.submitted = true;
    if (this.createBookForm.invalid) {
      return;
    }
   // this.save();
  }

  save() {
    
    this.bookService.createBook(this.book)
      .subscribe(data => console.log(data), error => console.log(error));
    this.book = new Book();
    this.router.navigate(['/books']);
    this.ngOnInit();
    this.reloadComponent();
  }


  get diagnostic() { return JSON.stringify(this.book); }

  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/books']);
}


  ngOnInit() {
    this.createBookForm = this.formBuilder.group({
      id: [],
      title: ['', Validators.required],
      author: ['', Validators.required],
      published:['',Validators.required],
      notes:['']
    });
  }

}
