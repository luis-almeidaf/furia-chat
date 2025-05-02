import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, map, Observable, retry, throwError } from 'rxjs';
import { ApiResponse, Player } from '../../models/player.model';
import { CardData } from '../../models/card.model';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {
  private apiUrl = 'http://localhost:8080/players';
  private http = inject(HttpClient);

  constructor() {}

  getPlayers(): Observable<CardData[]> {
    return this.http.get<ApiResponse>(this.apiUrl).pipe(
      map((response) =>
        response.content.map((player) => ({
          nickname: player.nickname,
          role: player.role,
          img: player.img,
        }))
      ),
      retry(2),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.error(
      `Código do erro: ${error.status},` + `Mensagem: ${error.error}`
    );
    return throwError(
      () => new Error('Algo deu errado. Por favor, tente novamente.')
    );
  }
}
