import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GooglebookService} from "../shared/googlebook.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../shared/auth.service";
@Component({
  selector: 'app-googlebooks',
  templateUrl: './googlebooks.component.html',
  styleUrls: ['./googlebooks.component.css']
})
export class GooglebooksComponent implements OnInit {
  bookForm: FormGroup;
  navLinks: any[];
  activeLinkIndex: number;
  user: any;
  constructor(private router: Router, private formBuilder: FormBuilder, private authService: AuthService,) {
    this.navLinks = [
      {
        label: 'Search Google Book',
        link: './googlebooks',
        index: 1
      },
    ];
  }

  ngOnInit() {
    this.user = this.authService.getFirstname();
    this.bookForm = this.formBuilder.group({
      title: [null, Validators.required]
    });
  }
  loading = false
  onSubmit() {
    this.loading = true
    let title = this.bookForm.get('title').value;
    if (title) {
      this.router.navigate(['googlebookdetails', title]);
    }
  }
}
