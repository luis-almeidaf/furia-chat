import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { catchError, map, Observable, retry, throwError } from 'rxjs';
import { ApiResponse } from '../../models/player.model';
import { CardData } from '../../models/card.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {
  private apiUrl = environment.apiurl;
  private http = inject(HttpClient);

  constructor() {}

  getPlayers(): Observable<CardData[]> {
    return this.http.get<ApiResponse>(`${this.apiUrl}players`).pipe(
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
