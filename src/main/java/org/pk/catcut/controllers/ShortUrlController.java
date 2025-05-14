package org.pk.catcut.controllers;

import org.pk.catcut.entities.ShortUrl;
import org.pk.catcut.helpers.HashGenerator;
import org.pk.catcut.repositories.ShortUrlRepository;
import org.pk.catcut.requests.UrlRequest;
import org.pk.catcut.responses.UrlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/api")
public class ShortUrlController {
  private final ShortUrlRepository shortUrlRepository;

  private ShortUrlController(ShortUrlRepository shortUrlRepository) {
    this.shortUrlRepository = shortUrlRepository;
  }

  @PostMapping("/url")
  private ResponseEntity<ShortUrl> createShortUrl(@RequestBody UrlRequest urlRequest) {
    String url = urlRequest.getUrl();
    ShortUrl existingUrl = shortUrlRepository.findFirstByUrl(url).orElse(null);

    if (existingUrl != null) {
      return ResponseEntity.ok(existingUrl);
    }

      ShortUrl shortUrl = new ShortUrl.ShortUrlBuilder().setUrl(url).setShortUrl(HashGenerator.generateId(url)).build();
      shortUrlRepository.save(shortUrl);

      return ResponseEntity.ok(shortUrl);
  }

  @PostMapping("/get-redirect-url")
  private ResponseEntity<?> getRedirectUrl(@RequestBody UrlRequest urlRequest) {
    String shortUrl = urlRequest.getUrl();

    System.out.println(urlRequest);

    ShortUrl redirectUrl = shortUrlRepository.findFirstByShortUrl(shortUrl).orElse(null);

    if (redirectUrl == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UrlResponse("Not Found"));

    }

    return ResponseEntity.ok(redirectUrl);
  }

  @GetMapping("/redirect-to/{shortUrl}")
  private RedirectView redirectToUrl(@PathVariable String shortUrl) {
    ShortUrl redirectUrl = shortUrlRepository.findFirstByShortUrl(shortUrl).orElse(null);

    RedirectView redirectView = new RedirectView();
    redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);

    if (redirectUrl == null) {
       redirectView.setUrl("http://localhost:8080");
       return redirectView;
    }

    redirectView.setUrl(redirectUrl.getUrl());
    return redirectView;
  }
}
