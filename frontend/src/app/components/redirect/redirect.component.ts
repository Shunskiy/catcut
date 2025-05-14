import {Component, OnInit} from '@angular/core';
import {BehaviorSubject, Observable, takeWhile, timer} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {IUrlResponse} from '../../services/url.model';
import {UrlService} from '../../services/url.service';
import {AsyncPipe, NgIf} from '@angular/common';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-redirect',
  imports: [
    AsyncPipe,
    NgIf,
    RouterLink
  ],
  templateUrl: './redirect.component.html',
  styleUrl: './redirect.component.scss'
})
export class RedirectComponent implements OnInit {
  public currentUrl$ = new BehaviorSubject<string | null>(null);
  public redirectTimer$ = new BehaviorSubject<number | null>(null);

  public error$ = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient, private urlService: UrlService, private router: Router) {
  }

  private redirect(url: string) {
    window.location.replace(url);
  }

  public startRedirect(): void {
    const countdownFrom = 3;

    timer(0, 1000).pipe(
      takeWhile((count) => {
        console.log(count);
        return count <= countdownFrom
      }),
    )
    .subscribe(
      {
        next: (count): void => {
          this.redirectTimer$.next(countdownFrom - count)
        },
        complete: () => {
          if (this.currentUrl$.value) {
            const hashUrl = window.location.pathname.substring(1);
            window.location.replace("/api/redirect-to/" + hashUrl);
          }
        }
      }
    )
  }

  public ngOnInit(): void {
    const hashUrl = window.location.pathname.substring(1);

    if (hashUrl.includes("/")) {
      this.router.navigate(['/']);
    }

    this.urlService.getInitUrl(hashUrl).subscribe({
      next: (res) => {
        this.currentUrl$.next(res.url)
        this.startRedirect()
      },
      error: ({error}: { error: IUrlResponse }) => {

        if (error.message) {
          this.error$.next(error.message)
        } else {
          throw new Error(error.message);
        }
      }
    })
  }

}
