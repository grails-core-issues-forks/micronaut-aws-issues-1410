package com.example;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class HomeControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void bindingWorksForForm() {
        HttpResponse<Map> response = httpClient.toBlocking().exchange(HttpRequest.POST("/", "message=World")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE), Map.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Hello World", response.getBody().get().get("message").toString());
    }

}