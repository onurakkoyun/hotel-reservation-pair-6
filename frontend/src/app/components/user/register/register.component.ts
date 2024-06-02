import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../../../services/auth/auth.service';
import { RegisterCredentials } from '../../../models/register-credentials';
import { TermsConditionsComponent } from '../../gdpr/terms-conditions/terms-conditions.component';
import { PrivacyPolicyComponent } from '../../gdpr/privacy-policy/privacy-policy.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
    now = new Date();
    registerFormGroup: FormGroup;
    registerSuccess = new EventEmitter<void>();


  constructor(public dialog: MatDialog, private formBuilder: FormBuilder, private router: Router, @Inject(AuthService)private authService: AuthService) {
    this.registerFormGroup = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required]],
      phoneNumber: ['', [Validators.required]],
      password: ['', [Validators.required]],
      passwordConfirmation: ['', [Validators.required]]
    });
      
  }
  
  openTermsConditions() {
    this.dialog.open(TermsConditionsComponent);
  }
  
  openPrivacyPolicy() {
    this.dialog.open(PrivacyPolicyComponent);
  }

  register() {
    const registerCredentials: RegisterCredentials = {
      firstName: this.registerFormGroup.value.firstName,
      lastName: this.registerFormGroup.value.lastName,
      phoneNumber: this.registerFormGroup.value.phoneNumber,
      email: this.registerFormGroup.value.email,
      password: this.registerFormGroup.value.password,
      passwordConfirmation: this.registerFormGroup.value.passwordConfirmation
    };
    this.authService.register(registerCredentials).subscribe({
      complete: () => {
        this.registerSuccess.emit();
        console.log('Registration successful');
        this.router.navigate(['/']);
      },
    });
  }

  onRegisterFormSubmit() {
    if (this.registerFormGroup.invalid) {
      console.error('Invalid form');
      return;
    }

    this.register();
  }

}
