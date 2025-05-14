import {FormControl} from '@angular/forms';

export interface IUrlForm {
  url: FormControl<string | null>;
}

export interface IUrlResponse {
  shortUrl: string;
  url: string;
  message?: string;
}
