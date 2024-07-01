import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoomDetailComponent } from './room-detail/room-detail.component';
import { HotelDetailComponent } from './hotel-detail/hotel-detail.component';
import { SharedModule } from '../../shared/shared.module';
import { ReviewComponent } from "../review/review.component";



@NgModule({
    declarations: [RoomDetailComponent, HotelDetailComponent, ReviewComponent],
    imports: [
        CommonModule,
        SharedModule,
    ]
})
export class HotelModule { }
