import { Routes } from '@angular/router';
import {UrlComponent} from './components/url/url.component';
import {RedirectComponent} from './components/redirect/redirect.component';

export const routes: Routes = [
  {path: '', component: UrlComponent},
  {path: '**', component: RedirectComponent},
];
