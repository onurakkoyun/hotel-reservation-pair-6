import { Injectable } from '@angular/core';
import { NativeDateAdapter, MatDateFormats } from '@angular/material/core';

@Injectable()
export class CustomDateAdapter extends NativeDateAdapter {
  override format(date: Date, displayFormat: Object): string {
	if (displayFormat === 'input') {
	  let day: string = date.getDate().toString();
	  day = +day < 10 ? '0' + day : day;
	  let month: string = (date.getMonth() + 1).toString();
	  month = +month < 10 ? '0' + month : month;
	  const year = date.getFullYear();
	  return `${day}/${month}/${year}`;
	} else {
	  return date.toDateString();
	}
  }
}

export const CUSTOM_DATE_FORMATS: MatDateFormats = {
  parse: {
	dateInput: 'DD/MM/YYYY',
  },
  display: {
	dateInput: 'input',
	monthYearLabel: 'MMM YYYY',
	dateA11yLabel: 'LL',
	monthYearA11yLabel: 'MMMM YYYY',
  },
};