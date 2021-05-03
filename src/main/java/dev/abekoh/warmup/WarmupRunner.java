package dev.abekoh.warmup;

import dev.abekoh.warmup.config.WarmupProperty;
import dev.abekoh.warmup.controllers.webapi.WebApiUserAddRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class WarmupRunner implements ApplicationRunner {

  private final WebClient webClient;

  private final WarmupProperty warmupProperty;

  public WarmupRunner(
      WebClient.Builder webClientBuilder,
      WarmupProperty warmupProperty,
      @Value("${server.port}") Integer port) {
    this.webClient =
        webClientBuilder.baseUrl(String.format("http://localhost:%d/api/users", port)).build();
    this.warmupProperty = warmupProperty;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (warmupProperty.getRequestCount() == null || warmupProperty.getRequestCount() <= 0) {
      log.info("skip warmup");
      return;
    }
    var request =
        WebApiUserAddRequest.builder()
            .firstName("Taro")
            .lastName("Yamada")
            .birthYear(1970)
            .birthMonth(1)
            .birthDate(1)
            .isDummy(true)
            .build();
    log.info("start warmup");
    webClient
        .post()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(request)
        .retrieve()
        .bodyToMono(Object.class) // 結果は使わないので適当なところにマッピング
        .repeat(warmupProperty.getRequestCount())
        .blockLast();
    log.info("finish warmup");
  }
}
