package dev.abekoh.warmup.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "warmup")
public class WarmupProperty {
  private Integer warmupCount;
}
