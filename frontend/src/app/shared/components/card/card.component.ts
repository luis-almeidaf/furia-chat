import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { CardData } from '../../../models/card.model';

@Component({
  selector: 'app-card',
  imports: [CommonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css',
})
export class CardComponent {
  @Input() data: CardData | undefined;

  formatDate(dateString: string): string {
    if (!dateString) {
      return '';
    }

    try {
      const date = new Date(dateString);
      const dia = date.getDate().toString().padStart(2, '0');
      const mes = (date.getMonth() + 1).toString().padStart(2, '0');
      const ano = date.getFullYear().toString().slice(-2);
      const hora = date.getHours().toString().padStart(2, '0');
      const minuto = date.getMinutes().toString().padStart(2, '0');

      return `${dia}/${mes}/${ano} ${hora}:${minuto}`;
    } catch (error) {
      console.error('Erro ao formatar data:', error);
      return '';
    }
  }
}
