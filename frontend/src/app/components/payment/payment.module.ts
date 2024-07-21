import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PaymentComponent } from './payment/payment.component';
import { SharedModule } from '../../shared/shared.module';
import { MultiplyPipe } from '../../pipes/multiply.pipe';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [PaymentComponent],
  imports: [CommonModule, SharedModule, MultiplyPipe, FormsModule, ReactiveFormsModule],
})
export class PaymentModule {}
