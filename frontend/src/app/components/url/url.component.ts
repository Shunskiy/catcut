import { Component } from '@angular/core';
import {UrlService} from '../../services/url.service';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {IUrlForm} from '../../services/url.model';
import {BehaviorSubject} from 'rxjs';
import {AsyncPipe, NgIf} from '@angular/common';
import {UrlHelpers} from '../../helpers/UrlHelpers';

@Component({
  selector: 'app-url',
  imports: [
    ReactiveFormsModule,
    NgIf,
    AsyncPipe
  ],
  templateUrl: './url.component.html',
  styleUrl: './url.component.scss'
})
export class UrlComponent {
  public generatedUrl$ = new BehaviorSubject<string | null>(null);
  public urlForm: FormGroup<IUrlForm>;

  public formError$ = new BehaviorSubject<string>('');

  constructor(private urlService: UrlService, private fb: FormBuilder) {
    this.urlForm = this.fb.group({
      url: ['', [Validators.required, Validators.pattern('https?://.+')]]
    })
  }

  public handleInput() {
    this.formError$.next('');
  }

  public submitForm(): void {
    if (this.urlForm.valid && this.urlForm.value.url) {
      console.log(this.urlForm.value.url);
      this.urlService.handleUrl(this.urlForm.value.url).subscribe(res => this.generatedUrl$.next(UrlHelpers.generateUrl(res.shortUrl)));
    } else {
      this.formError$.next('Wrong URL');
    }
  }

  public resetForm(): void {
    this.generatedUrl$.next(null);
  }
}
