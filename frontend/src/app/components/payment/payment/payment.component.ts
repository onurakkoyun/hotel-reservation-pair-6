import {
  ChangeDetectorRef,
  Component,
  Inject,
  Input,
  Renderer2,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { UserService } from '../../../services/user/user.service';
import {
  RoomService,
} from '../../../services/room/room.service';
import {
  ReservationService,
} from '../../../services/reservation/reservation.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ManagerDetails } from '../../../services/user/models/ManagerDetails';
import { GuestDetails } from '../../../services/user/models/GuestDetails';
import { GetRoomResponse } from '../../../services/room/model/GetRoomResponse';
import { CreateReservationRequest } from '../../../services/reservation/model/CreateReservationRequest';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.scss',
})
export class PaymentComponent {
  @Input() checkInDate: string = '';
  @Input() checkOutDate: string = '';
  @Input() roomId: number = 0;

  userDetails!: GuestDetails | ManagerDetails;
  room: GetRoomResponse | undefined;
  paymentFormGroup: FormGroup;

  expirationError: string | null = null;
  cvcError: string | null = null;
  days: number = 0;

  constructor(
    private route: ActivatedRoute,
    @Inject(RoomService) private roomService: RoomService,
    @Inject(AuthService) private authService: AuthService,
    @Inject(UserService) private userService: UserService,
    @Inject(ReservationService) private reservationService: ReservationService,

    private formBuilder: FormBuilder,
    private change: ChangeDetectorRef
  ) {
    this.paymentFormGroup = this.formBuilder.group({
      cardHolderName: ['', [Validators.required]],
      cardNumber: ['', [Validators.required]],
      expirationMonth: ['', [Validators.required]],
      expirationYear: ['', [Validators.required]],
      cvc: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params) => {
      this.roomId = params['selectedRoomId'];
      this.checkInDate = params['checkIn'];
      this.checkOutDate = params['checkOut'];
    });
    this.days = this.daysBetweenDates(this.checkInDate, this.checkOutDate);
    
    this.setUserDetails();

    this.roomService.getById(this.roomId).subscribe((room) => {
      this.room = room;
      this.change.detectChanges();
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

  calculateTotalPrice(): number {
    if (!this.room) {
      return 0;
    }
    return this.room.dailyPrice * this.days;
  }

  daysBetweenDates(checkInDate: string, checkOutDate: string): number {
    const checkIn = new Date(checkInDate);
    const checkOut = new Date(checkOutDate);
    const diffTime = Math.abs(checkOut.getTime() - checkIn.getTime());
    const days = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    return days;
  }

  formatCardNumber(event: Event): void {
    let input = event.target as HTMLInputElement;
    let value = input.value.replace(/\D/g, ''); // Sadece rakam karakterlerini al

    if (value.length > 16) {
      value = value.slice(0, 16); // En fazla 16 karaktere sınırla
    }

    let formattedValue = '';
    for (let i = 0; i < value.length; i++) {
      if (i > 0 && i % 4 === 0) {
        formattedValue += ' ';
      }
      formattedValue += value.charAt(i);
    }

    this.paymentFormGroup
      .get('cardNumber')
      ?.setValue(value, { emitEvent: false });
    input.value = formattedValue; // Input değerini güncelle
    console.log(input.value);
  }

  formatExpiration(event: Event): void {
    const input = event.target as HTMLInputElement;
    let value = input.value.replace(/\D/g, ''); // Remove non-numeric characters

    if (value.length > 4) {
      value = value.slice(0, 4); // Limit to MMYY format
    }

    let formattedValue = '';
    for (let i = 0; i < value.length; i++) {
      if (i === 2) {
        formattedValue += ' / '; // Add slash between MM and YY
      }
      formattedValue += value.charAt(i);
    }

    input.value = formattedValue; // Update input value

    // Validate expiration date
    const parts = formattedValue.split(' / ');
    if (parts.length === 2) {
      const month = parseInt(parts[0], 10);
      const year = parseInt(parts[1], 10); // Assume YY format

      const today = new Date();
      const currentYear = today.getFullYear() % 100; // Get last two digits of the year
      const currentMonth = today.getMonth() + 1; // getMonth is zero-based

      // Check if expiration date is valid
      if (
        year < currentYear ||
        (year === currentYear && month < currentMonth)
      ) {
        this.expirationError = 'Expiration date cannot be earlier than today.';
      } else if (month > 12) {
        this.expirationError = 'Invalid month entered.';
      } else {
        this.expirationError = null; // Reset error message if valid
      }

      this.paymentFormGroup
        .get('expirationMonth')
        ?.setValue(month.toString(), { emitEvent: false });
      this.paymentFormGroup
        .get('expirationYear')
        ?.setValue(year.toString(), { emitEvent: false });
    }
  }

  validateCvc(event: Event): void {
    const input = event.target as HTMLInputElement;
    let value = input.value.trim(); // Boşlukları kaldır

    if (value.length > 3) {
      value = value.slice(0, 3); // En fazla 3 karaktere sınırla
    }

    if (value.length === 0) {
      this.cvcError = 'CVC is required.';
    } else if (value.length < 3) {
      this.cvcError = 'CVC must be at least 3 digits.';
    } else if (value.length > 3) {
      this.cvcError = 'CVC must be exactly 3 digits.';
    } else {
      this.cvcError = null; // Hata mesajını sıfırla, geçerli girdi
    }

    this.paymentFormGroup.get('cvc')?.setValue(value, { emitEvent: false });
    input.value = value; // Input değerini güncelle
  }
  onSubmit(): void {
    if (this.expirationError || this.cvcError) {
      return; // Prevent submission if there are validation errors
    }

    const reservationRequest: CreateReservationRequest = {
      roomId: this.roomId,
      guestId: this.userDetails.id,
      cardHolderName: this.paymentFormGroup.value.cardHolderName,
      cardNumber: this.paymentFormGroup.value.cardNumber.replace(/\s+/g, ''), // Remove spaces
      expirationMonth: this.paymentFormGroup.value.expirationMonth,
      expirationYear: this.paymentFormGroup.value.expirationYear,
      cvc: this.paymentFormGroup.value.cvc,
      currency: this.room?.currency ?? 'USD', // Default to USD if room currency is not defined
      checkInDate: this.checkInDate,
      checkOutDate: this.checkOutDate,
    };
    console.log(reservationRequest);

    this.reservationService.createReservation(reservationRequest).subscribe(
      (reservation) => {
        console.log('Reservation created:', reservation);
        // Redirect to reservation details page
      },
    );
  }
}
