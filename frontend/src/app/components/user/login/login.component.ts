import {
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Inject,
} from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { LoginCredentials } from '../../../models/login-credentials';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginComponent {
  now = new Date();
  loginFormGroup: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    @Inject(AuthService) private authService: AuthService
  ) {
    this.loginFormGroup = this.formBuilder.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  login() {
    const loginCredentials: LoginCredentials = {
      email: this.loginFormGroup.value.email,
      password: this.loginFormGroup.value.password,
    };
    this.authService.login(loginCredentials).subscribe({
      complete: () => {
        this.authService.emitLoginSuccess();
        console.log('Login successful');
        this.router.navigate(['/']);
      },
    });
  }

  onLoginFormSubmit() {
    if (this.loginFormGroup.invalid) {
      console.error('Invalid form');
      return;
    }

    this.login();
  }
}
