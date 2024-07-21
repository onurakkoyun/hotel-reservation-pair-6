import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  Inject,
  OnInit,
} from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';
import { UserService } from '../../../services/user/user.service';
import { GuestDetails } from '../../../services/user/models/GuestDetails';
import { ManagerDetails } from '../../../services/user/models/ManagerDetails';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NavbarComponent implements OnInit {
  userDetails!: GuestDetails | ManagerDetails;
  isLogged: boolean = false;

  constructor(
    private router: Router,
    @Inject(AuthService) private authService: AuthService,
    @Inject(UserService) private userService: UserService,
    private change: ChangeDetectorRef
  ) { }

  ngOnInit() {
    this.authService.isLogged.subscribe((isLogged) => {
      this.isLogged = isLogged;
      if (isLogged) {
        this.setUserDetails();
      }
    });
  }

  private setUserDetails(): void {
    const displayEmail = this.authService.tokenPayload?.sub ?? '';
    if (displayEmail) {
      this.userService.getGuestDetailsByEmail(displayEmail).subscribe((userDetails) => {
        this.userDetails = userDetails;
        //console.log('User details:', this.userDetails);
        this.change.markForCheck(); // Signal change detection
      });
    }
  }
}
