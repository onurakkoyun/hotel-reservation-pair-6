import {  ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user/user.service';


@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrl: './navbar.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavbarComponent implements OnInit {
    isLogged : boolean = false;
    displayEmail: string = '';
    id : number = -1;
    firstName: string = '';
    lastName: string = '';
    isManager: boolean = false;
    phoneNumber: string = '';

    constructor(private router: Router, @Inject(AuthService) private authService: AuthService,
       @Inject(UserService) private userService: UserService, private change: ChangeDetectorRef) {}

    ngOnInit() {
        this.authService.isLogged.subscribe((isLogged) => {
            this.setLoggedState(isLogged);
            if (isLogged) {
                this.setInfo(this.displayEmail);
            }
        });
    }

    private setLoggedState(isLogged: boolean): void {
        this.isLogged = isLogged;
        this.displayEmail = this.authService.tokenPayload?.sub?? '';
    }

    private setInfo(email: string): void {
        const token = this.authService.token ?? '';
        this.userService.getGuestDetailsByEmail(token, email).subscribe((user) => {
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.isManager = user.role == 'MANAGER';
            this.id = user.id;
            this.change.markForCheck(); // Signal change detection
        });
    }

}
