import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AuthService } from './auth/auth.service';
import { LoginComponent } from '../components/user/login/login.component';
import { UserService } from './user/user.service';
import { ReservationService } from './reservation/reservation.service';

@NgModule({
  declarations: [],
  imports: [CommonModule, HttpClientModule],
  providers: [AuthService, UserService, ReservationService],
})
export class ServicesModule {}
