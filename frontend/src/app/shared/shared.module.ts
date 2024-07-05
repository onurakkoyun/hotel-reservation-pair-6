import { NgModule } from '@angular/core';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { LayoutComponent } from './components/layout/layout.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { JsonPipe } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { SearchBoxComponent } from './components/search-box/search-box.component';
import { HomeComponent } from './components/home/home.component';
import { LangpickerComponent } from './components/langpicker/langpicker.component';
import { CounterComponent } from './components/counter/counter.component';
import { PaginationComponent } from './components/pagination/pagination.component';
import { CardComponent } from './components/card/card.component';
import { FilterComponent } from './components/filter/filter.component';
import { DropdownMenuComponent } from './components/dropdown-menu/dropdown-menu.component';
import { NewFilterComponent } from './components/new-filter/new-filter.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HotelListComponent } from '../components/hotel/hotel-list/hotel-list.component';
import { DatepickerComponent } from './components/datepicker/datepicker.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { HotelPhotoModalComponent } from './components/hotel-photo-modal/hotel-photo-modal.component';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { CUSTOM_DATE_FORMATS, CustomDateAdapter } from './components/datepicker/customdateadapter';



@NgModule({
  declarations: [
    NavbarComponent,
    FooterComponent,
    LayoutComponent,
    DatepickerComponent,
    SearchBoxComponent,
    HomeComponent,
    LangpickerComponent,
    CounterComponent,
    PaginationComponent,
    CardComponent,
    FilterComponent,
    NewFilterComponent,
    DropdownMenuComponent,
    HotelListComponent,
    HotelPhotoModalComponent,
  ],
  exports: [LayoutComponent, CardComponent, HotelPhotoModalComponent],
  imports: [
    RouterModule,
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatDatepickerModule,
    JsonPipe,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  providers: [
    { provide: DateAdapter, useClass: CustomDateAdapter },
    { provide: MAT_DATE_FORMATS, useValue: CUSTOM_DATE_FORMATS },
    { provide: MAT_DATE_LOCALE, useValue: 'tr-TR' } // Adjust the locale if needed
  ],

})
export class SharedModule {}
