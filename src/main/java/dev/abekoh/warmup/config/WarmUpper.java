package dev.abekoh.warmup.config;

import dev.abekoh.warmup.controllers.webapi.WebApiUserAddRequest;
import dev.abekoh.warmup.controllers.webapi.WebApiUserAddResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class WarmUpper implements InitializingBean {

  private final WebClient webClient;

  public WarmUpper(WebClient webClient) {
    this.webClient = webClient;
  }

  @Override
  @EventListener(ApplicationReadyEvent.class)
  public void afterPropertiesSet() throws Exception {
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
