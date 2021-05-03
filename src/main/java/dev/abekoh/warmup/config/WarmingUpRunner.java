package dev.abekoh.warmup.config;

import com.fasterxml.jackson.databind.JsonNode;
import dev.abekoh.warmup.controllers.webapi.WebApiUserAddRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class WarmingUpRunner implements ApplicationRunner {

  private final WebClient webClient;

  public WarmingUpRunner(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.baseUrl("http://localhost:8080/api/users").build();
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    webClient
        .post()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(
            WebApiUserAddRequest.builder()
                .firstName("Kotaro")
                .lastName("Abe")
                .birthYear(1993)
                .birthMonth(6)
                .birthDate(25)
                .isDummy(true)
                .build())
        .retrieve()
        .bodyToMono(JsonNode.class)
        .repeat(30000)
        .blockLast();
  }
}
