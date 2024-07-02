import { Component, EventEmitter, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { TermsConditionsComponent } from '../../gdpr/terms-conditions/terms-conditions.component';
import { PrivacyPolicyComponent } from '../../gdpr/privacy-policy/privacy-policy.component';
import { RegisterManagerCredentials } from '../../../models/register-manager-credentials';

@Component({
    selector: 'app-register-manager',
    templateUrl: './registerManager.component.html',
    styleUrl: './registerManager.component.scss'
})
export class RegisterManagerComponent {
    now = new Date();
    registerManagerFormGroup: FormGroup;
    registerManagerSuccess = new EventEmitter<void>();


    constructor(public dialog: MatDialog, private formBuilder: FormBuilder, private router: Router, @Inject(AuthService)private authService: AuthService) {
        this.registerManagerFormGroup = this.formBuilder.group({
          firstName: ['', [Validators.required]],
          lastName: ['', [Validators.required]],
          companyName: ['', [Validators.required]],
          email: ['', [Validators.required]],
          phoneNumber: ['', [Validators.required]],
          password: ['', [Validators.required]],
          passwordConfirm: ['', [Validators.required]]
        });
          
      }

      openTermsConditions() {
        this.dialog.open(TermsConditionsComponent);
      }
      
      openPrivacyPolicy() {
        this.dialog.open(PrivacyPolicyComponent);
      }

      registerManager() {
        const registerManagerCredentials: RegisterManagerCredentials = {
          firstName: this.registerManagerFormGroup.value.firstName,
          lastName: this.registerManagerFormGroup.value.lastName,
          companyName: this.registerManagerFormGroup.value.companyName,
          phoneNumber: this.registerManagerFormGroup.value.phoneNumber,
          email: this.registerManagerFormGroup.value.email,
          password: this.registerManagerFormGroup.value.password,
          passwordConfirm: this.registerManagerFormGroup.value.passwordConfirm
        };
        this.authService.registerManager(registerManagerCredentials).subscribe({
          complete: () => {
            this.registerManagerSuccess.emit();
            console.log('Registration of Manager successful');
            this.router.navigate(['/']);
          },
        });
      }
    
      onRegisterManagerFormSubmit() {
        if (this.registerManagerFormGroup.invalid) {
          console.error('Invalid form');
          return;
        }
    
        this.registerManager();
      }
}

