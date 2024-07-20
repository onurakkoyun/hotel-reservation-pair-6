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
  QueryList,
  ViewChild,
  ViewChildren,
} from '@angular/core';
import {
  initCarousels,
  Carousel,
  CarouselInterface,
  CarouselItem,
  CarouselOptions,
} from 'flowbite';
import type { InstanceOptions } from 'flowbite';
import { isPlatformBrowser } from '@angular/common';
import { Hotel } from '../../../services/hotel/model/Hotel';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CardComponent implements OnInit, AfterViewInit {
  @Input() hotel: Hotel = {} as Hotel;
  @Input()
  searchQuery!: {
    query: string;
    checkIn: string;
    checkOut: string;
    guestCount: number;
  };
  @Input() carouselId: string = '';
  @ViewChild('carousel') carousel: ElementRef | undefined;
  @ViewChildren('carouselItem') carouselItems:
    | QueryList<ElementRef>
    | undefined;
  carouselcomponent: CarouselInterface | undefined;

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {}

  ngAfterViewInit() {
    if (isPlatformBrowser(this.platformId)) {
      //initCarousels();
      const items: CarouselItem[] = this.carouselItems!.toArray().map(
        (elRef: ElementRef, index: number) => ({
          position: index,
          el: elRef.nativeElement,
        })
      );

      const options: CarouselOptions = {};
      const instanceOptions: InstanceOptions = {
        id: 'controls-carousel-' + this.carouselId,
        override: true,
      };

      this.carouselcomponent = new Carousel(
        this.carousel?.nativeElement as HTMLElement,
        items,
        options,
        instanceOptions
      );
    }
  }

  onPrev() {
    this.carouselcomponent?.prev();
  }

  onNext() {
    this.carouselcomponent?.next();
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
