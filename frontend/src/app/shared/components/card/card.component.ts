import {
  AfterViewInit,
  ChangeDetectionStrategy,
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
export class CardComponent implements OnInit {
  @Input() title: string = '';
  @Input() ImageSrc: string = '';
  @Input() ImageAlt: string = '';
  @Input() description: string = '';
  @Input() buttonLabel: string = '';
  @Input() ImageWidth: number = 500;
  @Input() ImageHeight: number = 400;
  @Output() buttonClick = new EventEmitter<void>();

/*   @ViewChild('carousel', { static: false }) carouselElementRef:
    | ElementRef
    | undefined;

  carousel: CarouselInterface | undefined; */

  hotels: Hotel[] = [];

  constructor(private hotelService: HotelService,@Inject(PLATFORM_ID) private platformId: Object, private ngZone: NgZone) {}

  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.ngZone.runOutsideAngular(() => {
        initCarousels();
      });
    }
    this.hotelService.getAllHotels().subscribe((data: Hotel[]) => {
      this.hotels = data;
    });
  }

  onButtonClick() {
    this.buttonClick.emit();
  }

  get imageStyles() {
    return {
      'width.px': this.ImageWidth,
      'height.px': this.ImageHeight,
    };
  }

  /* ngAfterViewInit() {


    setTimeout(() => {
      if (this.carouselElementRef && this.carouselElementRef.nativeElement) {
        this.initializeCarousel();
      } else {
        console.error(
          'carouselElementRef is not defined or its native element is missing'
        );
      }
    }, 100);
  }

  initializeCarousel() {
    const carouselElement = this.carouselElementRef?.nativeElement;
    if (!carouselElement) {
      console.error('carouselElement is not found');
      return;
    }

    const items: CarouselItem[] = Array.from(
      carouselElement.querySelectorAll('[data-carousel-item]')
    ).map((el, index) => ({
      position: index,
      el: el as HTMLElement,
    }));

    if (items.length === 0) {
      console.error('No carousel items found');
      return;
    }

    const options: CarouselOptions = {
      defaultPosition: 0,
      interval: 100, // Disable auto interval
      indicators: {
        activeClasses: 'bg-white dark:bg-gray-800',
        inactiveClasses:
          'bg-white/50 dark:bg-gray-800/50 hover:bg-white dark:hover:bg-gray-800',
        items: Array.from(
          carouselElement.querySelectorAll('[data-carousel-indicator]')
        ).map((el, index) => ({
          position: index,
          el: el as HTMLElement,
        })),
      },
      onNext: () => {
        console.log('next slider item is shown');
      },
      onPrev: () => {
        console.log('previous slider item is shown');
      },
      onChange: () => {
        console.log('new slider item has been shown');
      },
    };

    this.carousel = new Carousel(carouselElement, items, options);
    //this.carousel.cycle();

    const prevButton = carouselElement.querySelector('[data-carousel-prev]');
    const nextButton = carouselElement.querySelector('[data-carousel-next]');

    if (!prevButton || !nextButton) {
      console.error('Prev or Next button not found');
      return;
    }

    prevButton.addEventListener('click', (event: MouseEvent) => {
      event.preventDefault();
      this.carousel?.prev();
    });

    nextButton.addEventListener('click', (event: MouseEvent) => {
      event.preventDefault();
      this.carousel?.next();
    });
  } */

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
