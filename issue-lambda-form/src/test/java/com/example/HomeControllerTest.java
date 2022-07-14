package com.example;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.model.Headers;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import io.micronaut.http.MediaType;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {

    private static MicronautLambdaHandler handler;
    private static Context lambdaContext = new MockLambdaContext();

    @BeforeAll
    public static void setupSpec() {
        try {
            handler = new MicronautLambdaHandler();
        } catch (ContainerInitializationException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanupSpec() {
        handler.getApplicationContext().close();
    }

    @Test
    void testHandler() {
        AwsProxyRequest request = new AwsProxyRequest();
        request.setHttpMethod("POST");
        Headers headers = new Headers();
        headers.put(HttpHeaders.CONTENT_TYPE, Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
        request.setMultiValueHeaders(headers);
        request.setBody("message=World");
        request.setPath("/");
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"message\":\"Hello World\"}",  response.getBody());
    }
}
