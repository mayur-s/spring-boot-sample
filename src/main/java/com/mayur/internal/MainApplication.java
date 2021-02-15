package com.mayur.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication(scanBasePackages = {"com"})
public class MainApplication implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Bean
  ProtobufHttpMessageConverter protobufHttpMessageConverter() {
    return new ProtobufHttpMessageConverter();
  }

  public static void main(String[] args) {
    SpringApplication.run(MainApplication.class, args);
  }

  @Override
  public void run(String... args) {
    log.info("Start app info");
    log.trace("trace start app");
    createTable();
  }

  private void createTable() {
    String expr = "CREATE TABLE EMPLOYEE (id INT NOT NULL, name VARCHAR(50), phone DOUBLE,  " +
      "created_on TIMESTAMP, modified_on TIMESTAMP, metadata BLOB, PRIMARY KEY (id))";

    jdbcTemplate.execute(expr);
    log.info("table created..");
  }

}
