import { CommonModule } from '@angular/common';
import { AfterViewChecked, AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, ElementRef, Inject, Input, PLATFORM_ID, QueryList, ViewChild, ViewChildren } from '@angular/core';
import {
  GetHotelImageResponse,
  Hotel,
} from '../../../services/hotel/hotel.service';
import { isPlatformBrowser } from '@angular/common';
import { Carousel, CarouselInterface, CarouselItem, CarouselOptions, InstanceOptions, Modal, ModalOptions, ModalInterface } from 'flowbite';




@Component({
  selector: 'app-hotel-photo-modal',
  templateUrl: './hotel-photo-modal.component.html',
  styleUrls: ['./hotel-photo-modal.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HotelPhotoModalComponent implements AfterViewChecked {
  @Input() hotel: Hotel = {} as Hotel;
  @ViewChild('modalEl') modalEl: ElementRef | undefined;
  modalcomponent: ModalInterface | undefined;
  @ViewChild('carousel') carousel: ElementRef | undefined;
  @ViewChildren('carouselItem') carouselItems: QueryList<ElementRef> | undefined;
  carouselcomponent: CarouselInterface | undefined;
  carouselItemsProcessed = false;

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private change: ChangeDetectorRef) { }



  ngAfterViewChecked() {

    if (isPlatformBrowser(this.platformId)) {
      if (!this.carouselItemsProcessed) {
        this.processCarouselItems();
      }
    }
  }

  private processCarouselItems() {
    //initCarousels();
    const items: CarouselItem[] = this.carouselItems!.toArray()
    .map((elRef: ElementRef, index: number) => ({
      position: index,
      el: elRef.nativeElement
    }));

    if (items.length > 0) {
      this.carouselItemsProcessed = true;
      //console.log(items);
    }

    const carouselOptions: CarouselOptions = {};
    const instanceOptions: InstanceOptions = {
      id: 'controls-carousel',
      override: true
    };

    this.carouselcomponent = new Carousel(this.carousel?.nativeElement as HTMLElement, items, carouselOptions, instanceOptions);

    //initModals();
    const modalOptions: ModalOptions = {};
    const modalInstanceOptions: InstanceOptions = {
      id: "extralarge-modal",
      override: true
    };

    this.modalcomponent = new Modal(this.modalEl?.nativeElement, modalOptions, modalInstanceOptions);
  }

  onPrev() {
    this.carouselcomponent?.prev();
  }

  onNext() {
    this.carouselcomponent?.next();
  }

  openModal() {
    this.modalcomponent?.toggle();
  }

  hideModal() {
    this.modalcomponent?.hide();
  }

}
