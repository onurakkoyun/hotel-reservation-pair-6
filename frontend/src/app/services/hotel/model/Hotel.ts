export interface Hotel {
    id: number;
    managerId: number;
    hotelName: string;
    isBreakfastAvailable: boolean;
    IsBreakfastIncludedInPrice: boolean;
    breakfastPricePerPerson: number;
    starCount: number;
    ratingAverage: number;
    firstAddressLine: string;
    secondAddressLine: string;
    city: string;
    postalCode: string;
    province: string;
    country: string;
    hotelFeatures: GetHotelFeatureResponse[];
    hotelImages: GetHotelImageResponse[];
    reviews: GetReviewResponse[];
    rooms: GetRoomResponse[];
    lowestRoomPrice?: number;
    currency?: string;
  }
  
interface GetHotelFeatureResponse {
    id: number;
    hotelFeatureName: string;
  }
  
interface GetReviewResponse {
    id: number;
    comment: string;
    creationDate: Date;
    rating: number;
    guestId: number;
    hotelId: number;
  }
  
interface GetRoomResponse {
    id: number;
    quantity: number;
    dailyPrice: number;
    currency: string;
    capacity: number;
    squareMeterSize: number;
    hotelId: number;
    roomTypeId: number;
    roomTypeName: string;
    roomBeds: GetRoomBedResponse[];
    roomFeatures: GetRoomFeatureResponse[];
  }
  
interface GetRoomBedResponse {
    id: number;
    quantity: number;
    bedName: string;
  }
  
interface GetRoomFeatureResponse {
    id: number;
    featureName: string;
  }
  
interface GetHotelImageResponse {
    id: number;
    url: string;
    hotelId: number;
  }