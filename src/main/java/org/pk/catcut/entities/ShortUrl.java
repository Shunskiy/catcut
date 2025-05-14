package org.pk.catcut.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ShortUrl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Override
  public String toString() {
    return "ShortUrl{" +
        "id=" + id +
        ", shortUrl='" + shortUrl + '\'' +
        ", url='" + url + '\'' +
        '}';
  }

  private String shortUrl;

  private String url;

  private ShortUrl(ShortUrlBuilder builder) {
    this.shortUrl = builder.shortUrl;
    this.url = builder.url;
  }

  public ShortUrl(Long id, String shortUrl, String url) {
    this.id = id;
    this.shortUrl = shortUrl;
    this.url = url;
  }

  public ShortUrl() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public static class ShortUrlBuilder {
    private String shortUrl;
    private String url;

    public ShortUrlBuilder setShortUrl(String shortUrl) {
      this.shortUrl = shortUrl;
      return this;
    }

    public ShortUrlBuilder setUrl(String url) {
      this.url = url;
      return this;
    }

    public ShortUrl build() {
      return new ShortUrl(this);
    }
  }
}
