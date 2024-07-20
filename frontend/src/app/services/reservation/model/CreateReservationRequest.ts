export interface CreateReservationRequest {
    roomId: number;
    guestId: number;
    cardNumber: string;
    cardHolderName: string;
    expirationMonth: string;
    expirationYear: string;
    cvc: string;
    currency: string;
    checkInDate: string;
    checkOutDate: string;
  }
