import { Component } from '@angular/core';
import { CardData } from '../../models/card.model';
import { CommonModule } from '@angular/common';
import { CardComponent } from '../../shared/components/card/card.component';

@Component({
  selector: 'app-home',
  imports: [CommonModule, CardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  cardItems: CardData[] = [
    {
      title: 'Participe do chat',
      description: 'Mostre a força da torcida e interaja durante os jogos',
    },
    {
      title: 'Proximos jogos',
      description: 'Fnatic, 05/05/2025 ás 20horas',
    },
  ];
}
