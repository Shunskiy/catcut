package org.pk.catcut.responses;

public class UrlResponse {
  public String url;
  public String message;

  public UrlResponse(String url, String message) {
    this.url = url;
    this.message = message;
  }

  public UrlResponse(String message) {
    this.message = message;
  }

  public UrlResponse() {

  }
}
