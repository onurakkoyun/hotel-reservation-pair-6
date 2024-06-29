import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, Input } from '@angular/core';

@Component({
    selector: 'app-filter',
    templateUrl: './filter.component.html',
    styleUrl: './filter.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class FilterComponent { 
    filters = {
        star1: false,
        star2: false,
        star3: false,
        star4: false,
        star5: false,
        priceFrom: null,
        priceTo: null
      };

      selectedStar = 0;
    
      updateSelectedStar() {
        this.selectedStar = (this.filters.star1 ? 1 : 0) +
                             (this.filters.star2 ? 1 : 0) +
                             (this.filters.star3 ? 1 : 0) +
                             (this.filters.star4 ? 1 : 0) +
                             (this.filters.star5 ? 1 : 0);
      }
    
      resetFilters() {
        this.filters.star1 = false;
        this.filters.star2 = false;
        this.filters.star3 = false;
        this.filters.star4 = false;
        this.filters.star5 = false;
        this.updateSelectedStar();
      }
    
      resetPriceFilters() {
        this.filters.priceFrom = null;
        this.filters.priceTo = null;
      }
}
