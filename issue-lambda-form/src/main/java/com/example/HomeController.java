package com.example;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;

@Controller
public class HomeController {

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post
    public Map<String, Object> save(@NonNull @NotNull @Body MessageCreate messageCreate) {
        return Collections.singletonMap("message", "Hello "+ messageCreate.getMessage());
    }
}
