import { Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { Router, RouterModule} from '@angular/router';
import { AppModule } from './app.module';
import { initFlowbite } from 'flowbite';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  imports: [RouterModule , AppModule]
})
export class AppComponent implements OnInit {
  title = 'hotel-reservation-frontend';

  constructor(private router: Router, @Inject(PLATFORM_ID) private platformId: Object) {}
  
  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      initFlowbite();
    }
  }
}
