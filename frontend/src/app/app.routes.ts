import { Routes } from '@angular/router';
import { HomeComponent } from './features/home/home.component';
import { LineupComponent } from './features/lineup/lineup.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'lineup', component: LineupComponent },
];
