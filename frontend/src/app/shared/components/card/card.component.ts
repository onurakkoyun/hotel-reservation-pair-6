import { CommonModule } from '@angular/common';
import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Carousel, CarouselInterface, CarouselItem, CarouselOptions } from 'flowbite';

@Component({
    selector: 'app-card',
    templateUrl: './card.component.html',
    styleUrl: './card.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CardComponent implements AfterViewInit { 
    @Input() title: string = '';
    @Input() ImageSrc: string = '';
    @Input() ImageAlt: string = '';
    @Input() description: string = '';
    @Input() buttonLabel: string = '';
    @Input() ImageWidth: number = 500;
    @Input() ImageHeight: number = 400;
    @Output() buttonClick = new EventEmitter<void>();

    @ViewChild('carousel') carouselElementRef: ElementRef | undefined;
    carousel: CarouselInterface | undefined;

    // Did not use in template <button (click)="onButtonClick()">{{ buttonLabel }}</button>
    onButtonClick() {
        this.buttonClick.emit();
    }

    get imageStyles() {
        return {
            'width.px': this.ImageWidth,
            'height.px': this.ImageHeight,
        };
    }

    ngAfterViewInit() {
        const carouselElement = this.carouselElementRef?.nativeElement;

        const items: CarouselItem[] = Array.from(carouselElement.querySelectorAll('[data-carousel-item]')).map((el, index) => ({
            position: index,
            el: el as HTMLElement,
        }));

        const options: CarouselOptions = {
            defaultPosition: 0,
            interval: 3000,
            indicators: {
                activeClasses: 'bg-white dark:bg-gray-800',
                inactiveClasses: 'bg-white/50 dark:bg-gray-800/50 hover:bg-white dark:hover:bg-gray-800',
                items: Array.from(carouselElement.querySelectorAll('[data-carousel-indicator]')).map((el, index) => ({
                    position: index,
                    el: el as HTMLElement,
                })),
            },
            onNext: () => {
                //console.log('next slider item is shown');
            },
            onPrev: () => {
                //console.log('previous slider item is shown'); // this does not work
            },
            onChange: () => {
                // console.log('new slider item has been shown');
            },
        };

        this.carousel = new Carousel(carouselElement, items, options);

        this.carousel.cycle();

        const prevButton = carouselElement.querySelector('[data-carousel-prev]');
        const nextButton = carouselElement.querySelector('[data-carousel-next]');

        if (!prevButton || !nextButton) {
            return;
        }
        
        prevButton.addEventListener('click', () => {
            this.carousel?.prev();
        });

        nextButton.addEventListener('click', () => {
            this.carousel?.next();
        });
    }

}
