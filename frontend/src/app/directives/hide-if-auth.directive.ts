import { Directive, ElementRef, OnInit } from '@angular/core';
import { AuthService } from '../services/auth/auth.service';

@Directive({
  selector: '[appHideIfAuth]',
  standalone: true,
})
export class HideIfAuthDirective implements OnInit { 
  constructor(private el: ElementRef, private authService: AuthService) {}

  ngOnInit() {
      if (this.authService.isAuthenticated) {
        // Hide the element if the user is authenticated
        this.el.nativeElement.style.display = 'none';
      } else {
        // Show the element if the user is not authenticated
        this.el.nativeElement.style.display = '';
      }
  }
}
