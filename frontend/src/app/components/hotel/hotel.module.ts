import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HotelDetailComponent } from './hotel-detail/hotel-detail.component';
import { SharedModule } from '../../shared/shared.module';
import { ReviewComponent } from '../review/review.component';
import { RoomListComponent } from './room-list/room-list.component';
import { MultiplyPipe } from '../../pipes/multiply.pipe';
import { RouterModule } from '@angular/router';
import { Util } from 'leaflet';
import { UtilitiesModule } from '../../utilities/utilities.module';


@NgModule({
  declarations: [HotelDetailComponent, ReviewComponent, RoomListComponent],
  imports: [CommonModule, SharedModule, RouterModule, MultiplyPipe],
})
export class HotelModule {}
