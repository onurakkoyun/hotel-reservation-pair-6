import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HotelDetailComponent } from './hotel-detail/hotel-detail.component';
import { SharedModule } from '../../shared/shared.module';
import { ReviewComponent } from '../review/review.component';
import { RoomListComponent } from './room-list/room-list.component';
import { MultiplyPipe } from "../../pipes/multiply.pipe";

@NgModule({
    declarations: [HotelDetailComponent, ReviewComponent, RoomListComponent],
    imports: [CommonModule, SharedModule, MultiplyPipe]
})
export class HotelModule {}
