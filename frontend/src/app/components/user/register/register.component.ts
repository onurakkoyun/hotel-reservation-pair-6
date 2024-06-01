import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
    registerForm = FormGroup;


  constructor(private http: HttpClient, private formBuilder: FormBuilder, private router: Router) {}

  ngOnInit() {
/*     this.registerForm = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      phoneNumber: [''],
      password: [''],
      confirmPassword: ['']
    }); */
  }

/*   onSubmit() {
    this.http.post('api/guests/register', { firstName: this.firstName, lastName: this.lastName, email: this.email, phoneNumber: this.phoneNumber, password: this.password, confirmPassword: this.confirmPassword})
      .subscribe(() => this.router.navigate(['/']));
  } */
}

