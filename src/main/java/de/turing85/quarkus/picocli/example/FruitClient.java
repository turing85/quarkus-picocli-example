package de.turing85.quarkus.picocli.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "fruit")
@Path("api/fruit")
public interface FruitClient {
  @GET
  @Path("{fruitName}")
  @Produces(MediaType.APPLICATION_JSON)
  String getFruit(@PathParam("fruitName") String fruitName);
}
