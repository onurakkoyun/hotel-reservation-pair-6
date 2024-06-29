import { Component, AfterViewInit, ElementRef, ViewChild, ChangeDetectionStrategy, Inject, OnInit, ChangeDetectorRef, PLATFORM_ID, NgZone, Input } from '@angular/core';
import { Router } from '@angular/router';
import { initDropdowns} from 'flowbite';
import { AuthService } from '../../../services/auth/auth.service';
import { isPlatformBrowser } from '@angular/common';

@Component({
    selector: 'app-dropdown-menu',
    templateUrl: './dropdown-menu.component.html',
    styleUrls: ['./dropdown-menu.component.css'],
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DropdownMenuComponent implements OnInit {
    @Input() displayEmail: string | null = null;
    @Input() firstName: string | null = null;
    @Input() lastName: string | null = null;
    
    constructor(@Inject(AuthService) private authService: AuthService, private router: Router, @Inject(PLATFORM_ID) private platformId: Object, private ngZone: NgZone) { }

    ngOnInit() {
        if (isPlatformBrowser(this.platformId)) {
            this.ngZone.runOutsideAngular(() => {
              initDropdowns();
            });
          }
    }

    onLogoutClick(): void {
        this.authService.logout().subscribe({
            complete: () => {
                console.log('Logout successful');
                this.router.navigate(['/']);
            }
        });
    }

}