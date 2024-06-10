import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
    selector: 'app-navbar',
    templateUrl: './navbar.component.html',
    styleUrl: './navbar.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavbarComponent implements OnInit { 
    isLogged: boolean = false;
    displayEmail: string | null = null;

    constructor(private authService: AuthService,
        private change: ChangeDetectorRef) { }

    ngOnInit() { 
        this.authService.isLogged.subscribe((isLogged) =>
            this.setLoggedState(isLogged)
          );
    }

    private setLoggedState(isLogged: boolean): void {
        this.isLogged = isLogged;
        this.displayEmail = this.authService.tokenPayload?.email?? null;
        this.change.markForCheck();
    }

    onLogoutClick(): void {
        this.authService.logout();
    }
 
}
