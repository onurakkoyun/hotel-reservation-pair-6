import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AppModule } from './app.module';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  imports: [RouterModule , AppModule]
})
export class AppComponent {
  title = 'hotel-reservation-frontend';
}
