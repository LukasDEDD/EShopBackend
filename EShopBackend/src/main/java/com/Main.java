package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

  private final ConsoleController controller;


  public Main(ConsoleController controller) {
    this.controller = controller;
  }

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(String... args) {
    try {
      System.out.println("--- Welcome to the E-Shop System 2026 ---");
      controller.start();
    } catch (Exception e) {
      System.err.println("An unexpected system error occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }
}