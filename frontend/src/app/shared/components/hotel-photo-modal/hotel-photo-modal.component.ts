import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import {
  GetHotelImageResponse,
  Hotel,
} from '../../../services/hotel/hotel.service';

@Component({
  selector: 'app-hotel-photo-modal',
  templateUrl: './hotel-photo-modal.component.html',
  styleUrls: ['./hotel-photo-modal.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HotelPhotoModalComponent {
  @Input() hotel: Hotel = {} as Hotel;
}
