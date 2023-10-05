import {ChangeDetectorRef, Component, NgZone, OnInit} from '@angular/core';
import {GooglebookService} from "../shared/googlebook.service";
import Swal from "sweetalert2";

@Component({
  selector: 'app-mybooks',
  templateUrl: './mybooks.component.html',
  styleUrls: ['./mybooks.component.css']
})
export class MybooksComponent implements OnInit {

  constructor(private googleBookService: GooglebookService, private zone: NgZone) {
  }

  ngOnInit(): void {
    this.getBookByUserId();
    const savedCardStates = localStorage.getItem('cardStates');
    if (savedCardStates) {
      this.cardStates = JSON.parse(savedCardStates);
    }
  }

  userbooks: Array<any>

  getBookByUserId() {
    this.googleBookService.getBookforUser()
      .subscribe((response) => {
        this.userbooks = response;

        // Retrieve ratings from localStorage and update the userbooks array
        this.userbooks.forEach(book => {
          const storedRating = localStorage.getItem(`bookRating-${book.id}`);
          if (storedRating) {
            book.rating = +storedRating; // Convert to number
          }
        });
      }, (error) => {
        console.error('Error getting book:', error);
      });
  }


  deleteBookByUserId(bookId) {
    this.googleBookService.deleteBookForUser(bookId)
      .subscribe((response) => {
        if (response)
          this.getBookByUserId()
        Swal.fire(
          'Success!',
          'The book has been successfully deleted!',
          'success'
        )
      }, (error) => {
        Swal.fire(
        'Error!',
        'Error in deleting the book!',
        'error'
      )
      });

  }

  rating;

  addRating(bookId: number, rating: number) {
    this.googleBookService.addRating(bookId, rating)
      .subscribe((response) => {
        if (response) {
          const bookToUpdate = this.userbooks.find(book => book.id === bookId);
          if (bookToUpdate) {
            bookToUpdate.rating = response;
            localStorage.setItem(`bookRating-${bookId}`, response.toString());
          }
        }
      }, (error) => {
        console.error('Error adding rating for book:', error);
      });
  }




  index: number;

  cardStates: string[] = [];  // Table to track the status of each card

  saveCardStates() {
    // Save the state of the cards in local storage
    localStorage.setItem('cardStates', JSON.stringify(this.cardStates));
  }

  readingNow(book: any, index: number) {
    this.cardStates[index] = 'Reading Now';
    this.saveCardStates();
  }

  toRead(book: any, index: number) {
    this.cardStates[index] = 'To Read';
    this.saveCardStates();
  }

  read(book: any, index: number) {
    this.cardStates[index] = 'Read';
    this.saveCardStates();
  }
}
