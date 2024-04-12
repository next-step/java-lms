package nextstep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

@Configuration
public class BeanConfig {
  @Bean
  public SimpleJdbcInsert simpleJdbcInsert(@Autowired JdbcTemplate jdbcTemplate) {
    return new SimpleJdbcInsert(jdbcTemplate);
  }
}
