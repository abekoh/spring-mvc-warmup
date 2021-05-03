package dev.abekoh.warmup.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;

@Configuration
public class JacksonConfig {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
    return builder ->
        builder
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .visibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
            .visibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
  }

  @Bean
  Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
    return new Jackson2JsonEncoder(mapper);
  }

  @Bean
  Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
    return new Jackson2JsonDecoder(mapper);
  }

  @Bean
  ExchangeStrategies exchangeStrategies(Jackson2JsonEncoder encoder, Jackson2JsonDecoder decoder) {
    return ExchangeStrategies.builder()
        .codecs(
            clientDefaultCodecsConfigurer -> {
              clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonEncoder(encoder);
              clientDefaultCodecsConfigurer.defaultCodecs().jackson2JsonDecoder(decoder);
            })
        .build();
  }
}
