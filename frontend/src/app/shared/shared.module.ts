import { NgModule } from "@angular/core";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { FooterComponent } from "./components/footer/footer.component";
import { LayoutComponent } from "./components/layout/layout.component";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { DatepickerComponent } from "./components/datepicker/datepicker.component";
import { SearchBoxComponent } from "./components/search-box/search-box.component";
import { HomeComponent } from "./components/home/home.component";
import { LangpickerComponent } from "./components/langpicker/langpicker.component";
import { CounterComponent } from "./components/counter/counter.component";




@NgModule({
    declarations: [NavbarComponent, FooterComponent, LayoutComponent, DatepickerComponent, SearchBoxComponent, HomeComponent, LangpickerComponent, CounterComponent],
    exports: [LayoutComponent],
    imports: [
        RouterModule,
        CommonModule,
    ],
  })
  export class SharedModule { }
  
