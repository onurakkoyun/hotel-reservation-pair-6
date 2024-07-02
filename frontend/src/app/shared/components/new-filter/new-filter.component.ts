import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'app-new-filter',
  templateUrl: './new-filter.component.html',
  styleUrl: './new-filter.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NewFilterComponent { 
  mobileFiltersOpen = true;

  toggleMobileFilters() {
    this.mobileFiltersOpen = !this.mobileFiltersOpen;
  }
}
