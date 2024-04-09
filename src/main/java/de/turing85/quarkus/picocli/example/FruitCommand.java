package de.turing85.quarkus.picocli.example;

import java.util.Objects;

import jakarta.enterprise.context.Dependent;
import jakarta.ws.rs.WebApplicationException;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import picocli.CommandLine;

@CommandLine.Command(description = "Get infos of a fruit")
@Dependent
@Slf4j
public class FruitCommand implements Runnable {
  @CommandLine.Option(names = {"--name", "-n"}, description = "Which fruit will we get?",
      required = true)
  String fruitToGet;

  @RestClient
  FruitClient fruitClient;

  @Override
  public void run() {
    try {
      log.info(Objects.requireNonNull(fruitClient.getFruit(fruitToGet)));
    } catch (WebApplicationException e) {
      if (log.isTraceEnabled()) {
        log.trace("Unable to get information about \"{}\" :(", fruitToGet, e);
      } else {
        log.warn("Unable to get information about \"{}\" :(", fruitToGet);
      }
    }
  }
}
