package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("ru.example.news")
@SpringBootApplication(scanBasePackages = {
        "ru.example.repository", "ru.example.news",
        "ru.example.controller", "ru.example"}, exclude = {DataSourceAutoConfiguration.class})
public class Main {

            public static void main(String[] args) {
                SpringApplication.run(Main.class, args);
            }

        }