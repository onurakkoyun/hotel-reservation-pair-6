import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
    selector: 'app-datepicker',
    templateUrl: './datepicker.component.html',
    styleUrl: './datepicker.component.css',
    changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DatepickerComponent implements OnInit { 
    @ViewChild('dateInput') dateInput: ElementRef | undefined;
    @ViewChild('datepicker') datepicker: ElementRef | undefined;

    currentDate = new Date();
    selectedDates: Date[] = [];
    currentMonth: number = this.currentDate.getMonth();
    currentYear: number = this.currentDate.getFullYear();
    daysOfMonth: Date[] = [];
    
    constructor() { }

    ngOnInit() {
        this.generateDaysOfMonth();
     }

    generateDaysOfMonth(): void {
    const startDate = new Date(this.currentYear, this.currentMonth, 1);
    const endDate = new Date(this.currentYear, this.currentMonth + 1, 0);

    this.daysOfMonth = [];
    for (let day = startDate.getDate(); day <= endDate.getDate(); day++) {
        this.daysOfMonth.push(new Date(this.currentYear, this.currentMonth, day));
    }
    }

    prevMonth(): void {
    if (this.currentMonth > 0) {
        this.currentMonth--;
    } else {
        this.currentMonth = 11;
        this.currentYear--;
    }
    this.generateDaysOfMonth();
    }

    nextMonth(): void {
    if (this.currentMonth < 11) {
        this.currentMonth++;
    } else {
        this.currentMonth = 0;
        this.currentYear++;
    }
    this.generateDaysOfMonth();
    }

    selectDate(date: Date): void {
    this.selectedDates.push(date);
    this.selectedDates.sort((a, b) => a.getTime() - b.getTime());

    if (this.dateInput) {
        this.dateInput.nativeElement.value = this.selectedDates.map(date => date.toDateString()).join(' - ');
    }
    }

    ngAfterViewInit(): void {
    this.dateInput?.nativeElement.addEventListener('click', () => {
        if (this.datepicker && this.datepicker.nativeElement.style.display === 'none') {
        this.datepicker.nativeElement.style.display = 'block';
        } else if (this.datepicker) {
        this.datepicker.nativeElement.style.display = 'none';
        }
    });
    }
}
