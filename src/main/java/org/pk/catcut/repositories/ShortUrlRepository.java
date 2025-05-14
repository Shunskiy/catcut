package org.pk.catcut.repositories;

import java.util.Optional;
import org.pk.catcut.entities.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
  Optional<ShortUrl> findFirstByUrl(String url);

  Optional<ShortUrl> findFirstByShortUrl(String shortUrl);
}
