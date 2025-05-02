import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, retry, throwError } from 'rxjs';
import { Player } from '../../models/player/player.model';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {
  private apiUrl = 'http://localhost:8080/players';
  private hhtp = inject(HttpClient);

  constructor() {}

  getPlayers(): Observable<Player[]> {
    return this.hhtp
      .get<Player[]>(this.apiUrl)
      .pipe(retry(2), catchError(this.handleError));
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
