import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AuthService } from '../../services/auth/auth.service';
import { RouterLink } from '@angular/router';
import { RegisterManagerComponent } from './registerManager/registerManager.component';



@NgModule({
  declarations: [LoginComponent, RegisterComponent, RegisterManagerComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink
  ],
})
export class UserModule { }
