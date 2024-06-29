import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, EventEmitter, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-search-box',
    templateUrl: './search-box.component.html',
    styleUrl: './search-box.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SearchBoxComponent { 
  searchForm: FormGroup; // FormGroup tanımlandı

  @Output() search = new EventEmitter<{ query: string, guestCount: number }>();

  constructor(private fb: FormBuilder) {
    this.searchForm = this.fb.group({
      query: [''],
      guestCount: [2]
    });
  }

  onSearch() {
    const query = this.searchForm.get('query')?.value || '';
    const guestCount = this.searchForm.get('guestCount')?.value || 2;
    this.search.emit({ query, guestCount });
  }
}
