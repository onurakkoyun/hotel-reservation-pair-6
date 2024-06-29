import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-counter',
    templateUrl: './counter.component.html',
    styleUrl: './counter.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CounterComponent { 

    @Input() initialValue: number = 1;
    @Output() countChange = new EventEmitter<number>();
  
    guestCount = new FormControl(this.initialValue, Validators.required); // Validators eklendi
  
    constructor() {
      this.guestCount.valueChanges.subscribe((value) => {
        if (value !== null) {
          this.countChange.emit(value);
        }
      });
    }
  
    decrement() {
      const currentValue = this.guestCount.value;
      if (currentValue !== null && currentValue > 1) {
        this.guestCount.setValue(currentValue - 1);
      }
    }
  
    increment() {
      const currentValue = this.guestCount.value;
      if (currentValue !== null && currentValue < 10) { // maksimum değeri 10 olarak belirleyelim
        this.guestCount.setValue(currentValue + 1);
      }
    }
}
