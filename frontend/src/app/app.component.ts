import { Component } from '@angular/core';
import { MainComponent } from './shared/components/main/main.component';

@Component({
  selector: 'app-root',
  imports: [MainComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'frontend';
}
