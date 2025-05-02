import { CommonModule } from '@angular/common';
import { Component, inject, input, Input, OnInit } from '@angular/core';
import { CardData } from '../../../../models/card.model';

@Component({
  selector: 'app-card',
  imports: [CommonModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.css',
})
export class CardComponent {
  @Input() data: CardData | undefined;
}
