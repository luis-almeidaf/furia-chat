import { inject, Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, map, Observable, retry, throwError } from 'rxjs';
import { CardData } from '../../models/card.model';
import { ApiResponse } from '../../models/match.model';

@Injectable({
  providedIn: 'root',
})
export class MatchService {
  private apiUrl = environment.apiurl;
  private http = inject(HttpClient);

  constructor() {}

  getMatches(): Observable<CardData[]> {
    return this.http.get<ApiResponse>(`${this.apiUrl}matches`).pipe(
      map((response) =>
        response.content.map((match) => ({
          opponent: match.opponent,
          gameDate: match.gameDate,
          result: match.result,
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
