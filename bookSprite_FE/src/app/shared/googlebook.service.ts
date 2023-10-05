import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GooglebookService {
  url = "http://localhost:8080/api/v1/googlebooks/";
  constructor(private http: HttpClient) { }

  getAllBooks(title: string): Observable<any> {
    return this.http.get(this.url + 'name/' + title);
  }

   getHttpHeader = () => {
    return {
      headers: new HttpHeaders({
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      })
    };
  };

  addBook(book: any) : Observable<any>{
    const token = localStorage.getItem('token');
    return this.http.post(this.url, book, {headers: this.getHttpHeader().headers});
  }
  getBookforUser() : Observable<any>{
    return this.http.get(this.url + 'user', {headers: this.getHttpHeader().headers});
  }

  deleteBookForUser(bookId: number): Observable<any>{
    return this.http.delete(this.url + bookId, {headers: this.getHttpHeader().headers})
  }

  addRating(bookId: number, rating: number): Observable<any> {
    const ratingValue = rating.toString(); // Convert rating to string
    return this.http.post(`${this.url}rating/${bookId}`, ratingValue, {headers: this.getHttpHeader().headers});
  }

}
