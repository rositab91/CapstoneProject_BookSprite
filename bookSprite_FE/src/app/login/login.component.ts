import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../shared/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  loginForm: FormGroup;
  errorMessage: string;
  loggedUser: string;

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      password: [null, Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const loginData = this.loginForm.value;
      this.authService.login(loginData).subscribe(
        response => {
          this.loggedUser = response.firstname;
          const token = this.authService.getToken();
          this.authService.setFirstname(this.loggedUser);

          this.router.navigate(['/googlebooks'], { queryParams: { token } });
        },
        error => {
          this.errorMessage = 'Login failed. Please try again.';
        }
      );
    }
  }

ngOnInit(): void {
  }
}
