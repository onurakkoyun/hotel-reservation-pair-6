import {
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  ElementRef,
  EventEmitter,
  Inject,
  Input,
  NgZone,
  OnInit,
  Output,
  PLATFORM_ID,
  ViewChild,
} from '@angular/core';
import {
  initCarousels,
  Carousel,
  CarouselInterface,
  CarouselItem,
  CarouselOptions,
} from 'flowbite';
import { Hotel, HotelService } from '../../../services/hotel/hotel.service';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CardComponent implements OnInit, AfterViewInit {
  @Input() hotel: Hotel = {} as Hotel;
  @Input() carouselId: string = '';
  @ViewChild('carousel') carousel: ElementRef | undefined;
  @Output() buttonClick = new EventEmitter<void>();

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
  }

  onButtonClick() {
    this.buttonClick.emit();
  }

  ngAfterViewInit() {
    if (isPlatformBrowser(this.platformId)) {
      initCarousels();
    }
  }

  getRatingText(ratingAverage: number): string {
    if (ratingAverage > 9) return 'Exceptional';
    if (ratingAverage > 8) return 'Wonderful';
    if (ratingAverage > 7) return 'Good';
    if (ratingAverage > 6) return 'Normal';
    if (ratingAverage > 4) return 'Bad';
    return 'Not rated';
  }

  getRatingClass(ratingAverage: number): string {
    if (ratingAverage > 9) return 'bg-green-700';
    if (ratingAverage > 8) return 'bg-green-600';
    if (ratingAverage > 7) return 'bg-yellow-600';
    if (ratingAverage > 6) return 'bg-orange-600';
    if (ratingAverage > 4) return 'bg-red-600';
    return 'bg-gray-600';
  }
  
  getCurrencySymbol(currency: string | undefined): string {
    switch (currency) {
      case 'USD':
        return '$';
      case 'TRY':
        return '₺';
      case 'EUR':
        return '€';
      default:
        return '';
    }
  }
}

