import { NgModule } from "@angular/core";
import { NavbarComponent } from "./components/navbar/navbar.component";
import { FooterComponent } from "./components/footer/footer.component";
import { LayoutComponent } from "./components/layout/layout.component";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { HttpClientModule } from "@angular/common/http";
import { DatepickerComponent } from "./components/datepicker/datepicker.component";
import { SearchBoxComponent } from "./components/search-box/search-box.component";




@NgModule({
    declarations: [NavbarComponent, FooterComponent, LayoutComponent, DatepickerComponent, SearchBoxComponent],
    exports: [LayoutComponent],
    imports: [
        RouterModule,
    ],
  })
  export class SharedModule { }
  