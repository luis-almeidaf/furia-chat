import { CommonModule, NgClass } from '@angular/common';
import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, NgClass],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  isNavbarOpen = signal(false);

  toggleNavbar() {
    console.log('oi');
    this.isNavbarOpen.set(!this.isNavbarOpen());
  }
}
