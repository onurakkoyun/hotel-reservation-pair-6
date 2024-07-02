import { CommonModule } from '@angular/common';
import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  OnInit,
  Output,
  Inject,
} from '@angular/core';
import { HotelService } from './../../../services/hotel/hotel.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HomeComponent implements OnInit {

  ngOnInit(): void {
  }

  constructor(@Inject(HotelService) private hotelService: HotelService
    //private change: ChangeDetectorRef
  ) {}

}
