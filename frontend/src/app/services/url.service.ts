import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {IUrlResponse} from './url.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UrlService {

  constructor(private http: HttpClient) {
  }

  public handleUrl(url: string): Observable<IUrlResponse> {
    return this.http.post<IUrlResponse>('/api/url', { url });
  }

  public getInitUrl(url: string): Observable<IUrlResponse> {
    return this.http.post<IUrlResponse>('/api/get-redirect-url', { url })
  }
}
