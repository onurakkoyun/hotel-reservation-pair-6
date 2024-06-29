import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  Output,
} from '@angular/core';
import { Hotel } from '../../../services/hotel/hotel.service';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CardComponent {

  @Input() set hotels(value: Hotel[]) {
    this._hotels = value;
    this.cdr.detectChanges();
    console.log('Hotels in CardComponent:', this._hotels);
  }

  get hotels(): Hotel[] {
    return this._hotels;
  }

  private _hotels: Hotel[] = [];

  @Output() buttonClick = new EventEmitter<void>();

  constructor(private cdr: ChangeDetectorRef) {}

  onButtonClick() {
    this.buttonClick.emit();
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
    if (ratingAverage > 9) return 'bg-green-600';
    if (ratingAverage > 8) return 'bg-green-600';
    if (ratingAverage > 7) return 'bg-green-600';
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
