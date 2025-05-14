export class UrlHelpers {
  public static generateUrl(url: string): string {
    return window.location.origin + "/" + url;
  }
}
