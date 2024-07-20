import {
  AfterViewInit,
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  ElementRef,
  Inject,
  Input,
  OnInit,
  PLATFORM_ID,
  QueryList,
  ViewChildren,
} from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import {
  Carousel,
  CarouselInterface,
  CarouselItem,
  CarouselOptions,
  InstanceOptions,
} from 'flowbite';
import { Router } from '@angular/router';
import { GetRoomResponse } from '../../../services/room/model/GetRoomResponse';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RoomListComponent implements OnInit, AfterViewInit {
  @Input() availableRooms: GetRoomResponse[] = [];
  @Input() checkInDate: string = '';
  @Input() checkOutDate: string = '';
  @Input() selectedRoomId = 0;
  @Input() days = 0;

  @ViewChildren('carousel') carousels: QueryList<ElementRef> | undefined;
  @ViewChildren('carouselItem') carouselItems:
    | QueryList<ElementRef>
    | undefined;

  carouselsMap: Map<string, CarouselInterface> = new Map();

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private cdr: ChangeDetectorRef,
    private router: Router
  ) {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    if (isPlatformBrowser(this.platformId) && this.carousels) {
      this.initializeCarousels();
    }
  }

  reserveRoom(roomId: number) {
    const queryParams = {
      selectedRoomId: roomId,
      checkIn: this.checkInDate,
      checkOut: this.checkOutDate,
    };
    this.router.navigate(['/payments'], { queryParams }); //TODO: This doesnt work
  }

  private initializeCarousels(): void {
    this.carousels?.forEach((carouselEl: ElementRef, carouselIndex: number) => {
      const items: CarouselItem[] = this.carouselItems!.toArray()
        .filter(
          (item, index) =>
            Math.floor(index / this.availableRooms.length) === carouselIndex
        )
        .map((elRef: ElementRef, index: number) => ({
          position: index,
          el: elRef.nativeElement,
        }));

      const options: CarouselOptions = {};
      const instanceOptions: InstanceOptions = {
        id: 'controls-carousel-' + carouselIndex,
        override: true,
      };

      const carouselComponent = new Carousel(
        carouselEl.nativeElement as HTMLElement,
        items,
        options,
        instanceOptions
      );

      this.carouselsMap.set(
        'controls-carousel-' + carouselIndex,
        carouselComponent
      );
    });
  }

  onPrev(carouselId: string) {
    this.carouselsMap.get(carouselId)?.prev();
  }

  onNext(carouselId: string) {
    this.carouselsMap.get(carouselId)?.next();
  }
}
