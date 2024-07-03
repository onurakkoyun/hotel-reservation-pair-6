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
import type { InstanceOptions } from 'flowbite';
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
  @ViewChild('carousel') carousel: ElementRef | undefined;
  @ViewChild('prev') prev: ElementRef | undefined;
  @ViewChild('next') next: ElementRef | undefined;
  @Input() carouselId: string = '';
  @Output() buttonClick = new EventEmitter<void>();

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private ngZone: NgZone, private cdr: ChangeDetectorRef) {
    
  }

  ngOnInit(): void {

  }

  onButtonClick() {
    this.buttonClick.emit();
  }

  ngAfterViewInit() {
    if (isPlatformBrowser(this.platformId)) {
      //initCarousels();
      const items: CarouselItem[] = Array.from(this.carousel?.nativeElement.querySelectorAll('[data-carousel-item]'))
      .filter((node): node is HTMLElement => node instanceof HTMLElement)
      .map((el: HTMLElement, index: number) => ({
        position: index,
        el: el
      }));
  
      const options: CarouselOptions = {};
      const instanceOptions: InstanceOptions = {
        id: 'controls-carousel-' + this.carouselId,
        override: true
      };
      
      const carousel: CarouselInterface = new Carousel(this.carousel?.nativeElement as HTMLElement, items, options, instanceOptions);

      this.prev?.nativeElement.addEventListener('click', () => {
        carousel.prev();
      });

      this.next?.nativeElement.addEventListener('click', () => {
        carousel.next();
      });
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

