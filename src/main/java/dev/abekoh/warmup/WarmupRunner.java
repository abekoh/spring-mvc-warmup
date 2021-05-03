package dev.abekoh.warmup;

import com.fasterxml.jackson.databind.JsonNode;
import dev.abekoh.warmup.config.WarmupProperty;
import dev.abekoh.warmup.controllers.webapi.WebApiUserAddRequest;
import lombok.extern.slf4j.Slf4j;
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

  public WarmupRunner(WebClient.Builder webClientBuilder, WarmupProperty warmupProperty) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/users").build();
    this.warmupProperty = warmupProperty;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (warmupProperty.getWarmupCount() == null || warmupProperty.getWarmupCount() <= 0) {
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
        .bodyToMono(JsonNode.class)
        .repeat(warmupProperty.getWarmupCount())
        .blockLast();
    log.info("finish warmup");
  }
}
