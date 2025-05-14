package org.pk.catcut;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("dev")
class CatcutApplicationTests {
  @Test
  void contextLoads() {
    assertTrue(true);
  }
}
