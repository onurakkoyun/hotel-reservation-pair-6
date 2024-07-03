import { Component, Inject, OnInit, Output } from '@angular/core';
import { HotelService } from '../../../services/hotel/hotel.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {

  @Output() managerId: string = '';
  constructor(@Inject(HotelService) private hotelService: HotelService) {}
  ngOnInit() {
/*     this.hotelService.getHotelById( */
  }

}
