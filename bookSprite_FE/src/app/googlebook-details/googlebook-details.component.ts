import { Component, OnInit } from '@angular/core';
import {GooglebookService} from "../shared/googlebook.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {GoogleBook} from "../models/GoogleBook";
import Swal from 'sweetalert2'

@Component({
  selector: 'app-googlebook-details',
  templateUrl: './googlebook-details.component.html',
  styleUrls: ['./googlebook-details.component.css']
})
export class GooglebookDetailsComponent implements OnInit {
  books: Array<any>;
  isLoading=true;
  currentPage = 1;
  disabledBookIds: string[] = [];
  constructor(private googlebookService: GooglebookService,
              private route: ActivatedRoute,
              private location: Location) { }

  ngOnInit() {
    const title = this.route.snapshot.params['title'];
    this.getBooks(title);
    const storedDisabledBookIds = localStorage.getItem('disabledBookIds');
    if (storedDisabledBookIds) {
      this.disabledBookIds = JSON.parse(storedDisabledBookIds);
    }

    this.getBookByUserId();
  }

  getBooks(title: string) {
    this.isLoading = true;

    this.googlebookService.getAllBooks(title)
      .subscribe(data => {
        this.books = data.items
        this.isLoading = false;
        this.books = this.books.filter(book => !this.userbooks.some(userBook => userBook?.title === book?.volumeInfo?.title));
      });
  }


  get paginatedBooks(): any[] {
    // Check if books is undefined or empty
    if (!this.books || this.books.length === 0) {
      return [];
    }

    const startIndex = (this.currentPage - 1) * this.selectedPageSize;
    const endIndex = startIndex + this.selectedPageSize;

    // Ensure endIndex does not exceed the length of the books array
    const slicedBooks = this.books.slice(startIndex, endIndex);

    return slicedBooks;
  }


  pageSizeOptions = [5, 10, 25, 50, 100];  // Options for rows per page
  selectedPageSize = this.pageSizeOptions[0];  // Default selected page size

  onPageSizeChange() {
    this.currentPage = 1;  // Reset to first page when changing page size
    this.scrollToTop();
  }

  nextPage() {
    this.currentPage++;
    this.scrollToTop();
  }

  previousPage() {
    this.currentPage--;
    this.scrollToTop();
  }

  scrollToTop() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  goBack() {
    this.location.back();
  }

  userbooks: Array<any>;

  getBookByUserId() {
    this.googlebookService.getBookforUser()
      .subscribe((response) => {
        if (response) this.userbooks = response;
      }, (error) => {
        console.error('Error getting book:', error);
      });
  }

  addbook(book, i) {
    let googleBook = new GoogleBook();
    if(book.volumeInfo?.imageLinks?.smallThumbnail) googleBook.smallThumbnail = book.volumeInfo.imageLinks.smallThumbnail ;
    if(book.volumeInfo?.imageLinks?.thumbnail) googleBook.thumbnail = book.volumeInfo.imageLinks.thumbnail;
    googleBook.authors = " ";
    if(book.volumeInfo.authors) book.volumeInfo.authors.forEach(author => googleBook.authors.concat(" - " + author))
    googleBook.description = book.volumeInfo.description
    googleBook.averageRating = book.volumeInfo.averageRating
    googleBook.language = book.volumeInfo.language
    googleBook.pageCount = book.volumeInfo.pageCount
    googleBook.printType = book.volumeInfo.printType
    googleBook.publisher = book.volumeInfo.publisher
    googleBook.publishedDate = book.volumeInfo.publishedDate
    googleBook.ratingsCount = book.volumeInfo.ratingsCount
    googleBook.title = book.volumeInfo.title

    this.disabledBookIds.push(book.id);
    localStorage.setItem('disabledBookIds', JSON.stringify(this.disabledBookIds));

    this.googlebookService.addBook(googleBook)
      .subscribe((response) => {
        Swal.fire(
          'Success!',
          'The book has been successfully added!',
          'success'
        )
      }, (error) => {
        Swal.fire(
          'Error',
          'Error in adding the book!',
          'error'
        )
      });


    this.books.splice(i, 1);
    this.books = this.books.slice();

  }


}
