import {  ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';


@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrl: './navbar.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavbarComponent implements OnInit {
    isLogged : boolean = false;
    displayEmail: string | null = null;

    constructor(private router: Router, @Inject(AuthService) private authService: AuthService,
        private change: ChangeDetectorRef) {}

    ngOnInit() {
        this.authService.isLogged.subscribe((isLogged) => {
            this.setLoggedState(isLogged);
            this.change.markForCheck(); // Signal change detection
        });
    }

    private setLoggedState(isLogged: boolean): void {
        this.isLogged = isLogged;
        this.displayEmail = this.authService.tokenPayload?.sub?? null;
    }

}
