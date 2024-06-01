import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { first } from 'rxjs';

@Component({
    selector: 'app-register-manager',
    templateUrl: './registerManager.component.html',
    styleUrl: './registerManager.component.scss'
})
export class RegisterManagerComponent {
    firstName: string | undefined;
    lastName: string | undefined;
    email: string | undefined;
    phoneNumber: string | undefined;
    hotelName: string | undefined;
    companyName: string | undefined;
    password: string | undefined;
    confirmPassword: string | undefined;


    constructor(private http: HttpClient, private router: Router) { }

    onSubmit() {
        this.http.post('api/managers/register', { firstName: this.firstName, lastName: this.lastName, email: this.email, phoneNumber: this.phoneNumber, hotelName: this.hotelName, companyName: this.companyName, password: this.password, confirmPassword: this.confirmPassword })
            .subscribe(() => this.router.navigate(['/']));
    }
}

