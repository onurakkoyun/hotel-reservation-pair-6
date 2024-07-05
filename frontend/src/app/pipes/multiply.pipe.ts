import { Pipe, type PipeTransform } from '@angular/core';

@Pipe({
  name: 'multiply',
  standalone: true,
})
export class MultiplyPipe implements PipeTransform {

  transform(value: number, multiplier: number): number {
    return value * multiplier;
  }

}
