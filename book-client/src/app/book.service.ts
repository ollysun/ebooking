import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from './model/book';
import { Response } from './model/response';


@Injectable({
  providedIn: 'root'
})
export class BookService {

  private baseUrl = 'http://localhost:8090/books';

  response: Response = new Response();
  

  constructor(private http: HttpClient) { }

  getBook(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
 
  createBook(book: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, book);
  }

  getBooksList(): Observable<Response> {
    return this.http.get<Response>(`${this.baseUrl}`);
  }
}
