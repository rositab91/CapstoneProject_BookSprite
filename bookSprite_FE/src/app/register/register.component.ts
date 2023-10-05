import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  errorMessage: string;
  loggedUser: string;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.registerForm = this.fb.group({
      firstname: [null, Validators.required],
      lastname: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      password: [null, Validators.required]
    });
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const registrationData = this.registerForm.value;
      this.authService.register(registrationData).subscribe(
        response => {
          this.loggedUser = registrationData.firstname;
          this.authService.setFirstname(this.loggedUser);
          this.router.navigate(['/googlebooks']);
        },
        error => {
          this.errorMessage = 'Registration failed. Please try again.';
        }
      );
    }
  }

  ngOnInit(): void {
  }

}
