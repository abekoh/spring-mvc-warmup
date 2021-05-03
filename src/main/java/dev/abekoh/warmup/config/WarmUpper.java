package dev.abekoh.warmup.config;

import dev.abekoh.warmup.controllers.webapi.WebApiUserAddRequest;
import dev.abekoh.warmup.controllers.webapi.WebApiUserAddResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class WarmUpper {

  private final WebClient webClient;

  public WarmUpper(WebClient.Builder webClientBuilder) {
    this.webClient = webClientBuilder.build();
  }

  @EventListener(ApplicationReadyEvent.class)
  public void warmUp() {
    var resp =
        webClient
            .post()
            .uri("http://localhost:8080/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(
                WebApiUserAddRequest.builder()
                    .firstName("Kotaro")
                    .lastName("Abe")
                    .birthYear(1993)
                    .birthMonth(6)
                    .birthDate(25)
                    .build())
            .retrieve()
            .bodyToMono(WebApiUserAddResponse.class)
            .block();
    log.info("resp: {}", resp);
  }
}
