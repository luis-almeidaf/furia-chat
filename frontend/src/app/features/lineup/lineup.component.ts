import { Component, inject, OnInit } from '@angular/core';
import { CardData } from '../../models/card.model';
import { PlayerService } from '../../services/player/player.service';
import { CardComponent } from '../../shared/components/card/card.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lineup',
  imports: [CardComponent, CommonModule],
  templateUrl: './lineup.component.html',
  styleUrl: './lineup.component.css',
})
export class LineupComponent implements OnInit {
  cardItems: CardData[] = [];
  error: string = '';
  private playerService = inject(PlayerService);

  ngOnInit(): void {
    this.loadPlayers();
  }

  private loadPlayers(): void {
    this.playerService.getPlayers().subscribe({
      next: (data) => {
        this.cardItems = data;
      },
      error: (err: any) => {
        console.error('Erro ao carregar dados: ', err);
        this.error = 'Não foi possível carregar dados';
      },
    });
  }
}
