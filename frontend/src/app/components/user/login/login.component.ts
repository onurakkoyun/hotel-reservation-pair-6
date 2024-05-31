import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email: string | undefined;
  password: string | undefined;

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    this.http.post('api/auth/authenticate', { email: this.email, password: this.password })
      .subscribe(() => this.router.navigate(['/']));
  }
}
