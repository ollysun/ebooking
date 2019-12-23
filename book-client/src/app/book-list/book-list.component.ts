import { Component, OnInit } from '@angular/core';
import { BookService } from '../book.service';
import { Book } from '../model/book';
import { Store } from '@ngrx/store';
import { AppState } from '../app.state';
import { AddBooks, ListBook } from '../store/book.action';
import { Observable } from 'rxjs';
import { getProducts } from '../store/book.selector';




@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  //books:Object;
  books: Book[] = new Array();

  //books:Object;
  bookData:Book[] = new Array();

  count:number;
  displayedColumns: string[] = ['id', 'author', 'title', 'published', 'notes'];
  constructor(private bookService: BookService, 
              private store: Store<AppState>) {}
               

  ngOnInit() {
    this.bookService.getBooksList().subscribe(result => {
       this.store.dispatch(new ListBook(result.data))
       Array.prototype.push.apply(this.books, result.data);
    });
   
  }

 

  


}
