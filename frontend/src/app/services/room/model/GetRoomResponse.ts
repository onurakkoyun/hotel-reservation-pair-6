export interface GetRoomResponse {
    id: number;
    hotelId: number;
    roomTypeId: number;
    roomTypeName: string;
    quantity: number;
    dailyPrice: number;
    currency: string;
    capacity: number;
    squareMeterSize: number;
    roomImages: GetRoomImageResponse[];
    roomBeds: GetRoomBedResponse[];
    roomFeatures: GetRoomFeatureResponse[];
  }
  
interface GetRoomImageResponse {
    id: number;
    url: string;
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
