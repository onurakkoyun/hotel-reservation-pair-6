import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component } from '@angular/core';

@Component({
  selector: 'app-app',
  standalone: true,
  imports: [
    CommonModule,
  ],
  template: `<p>app works!</p>`,
  styleUrl: './app.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppComponent { }
