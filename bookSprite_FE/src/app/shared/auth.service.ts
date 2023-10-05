import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = environment.apiUrl;
  private token: string;
  private firstname: string;

  constructor(private http: HttpClient) {
    this.firstname = localStorage.getItem('firstname');
  }

  register(data: any) {
    return this.http.post(`${this.apiUrl}/register`, data);
  }

  login(data: any) {
    return this.http.post<any>(`${this.apiUrl}/authenticate`, data).pipe(
      tap(response => {
        if (response && response.access_token) {
          this.token = response.access_token;
          this.firstname = response.firstname;
          // Store the token in local storage
          localStorage.setItem('token', this.token);
          localStorage.setItem('firstname', this.firstname);
        }
      })
    );
  }

  getToken(): string {
    return this.token || localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    if (token) {
      return true;
    }
    return false;
  }

  logout() {
    localStorage.removeItem('token');
    this.token = null;
  }

  getFirstname(): string {
    return this.firstname;
  }

  setFirstname(firstName: string) {
    this.firstname = firstName;
  }
}
