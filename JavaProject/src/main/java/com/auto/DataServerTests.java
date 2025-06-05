package com.auto;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DataServerTest
{
    @Test
    void shouldHandleValidPostRequest() throws IOException {
        Gson gson = new Gson();
        HttpExchange mockExchange = mock(HttpExchange.class);

        GameData testData = new GameData();
        testData.setCurrentSpeed(120.5f);
        testData.setAverageSpeed(85.3f);
        testData.setDistanceTraveled(2500.7f);
        testData.setCurrentGear("Fifth");
        String json = gson.toJson(testData);

        InputStream inputStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
        when(mockExchange.getRequestMethod()).thenReturn("POST");
        when(mockExchange.getRequestBody()).thenReturn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(mockExchange.getResponseBody()).thenReturn(outputStream);

        DataServer.DataHandler handler = new DataServer.DataHandler();
        handler.handle(mockExchange);

        String expectedResponse = "Dane odebrano pomyślnie";
        int expectedByteLength = expectedResponse.getBytes(StandardCharsets.UTF_8).length;

        verify(mockExchange, times(1)).sendResponseHeaders(200, expectedByteLength);

        String actualResponse = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldRejectNonPostMethods() throws IOException {
        HttpExchange mockExchange = mock(HttpExchange.class);
        when(mockExchange.getRequestMethod()).thenReturn("GET");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(mockExchange.getResponseBody()).thenReturn(outputStream);

        DataServer.DataHandler handler = new DataServer.DataHandler();
        handler.handle(mockExchange);

        String expectedResponse = "Metoda nieobsługiwana";
        int expectedByteLength = expectedResponse.getBytes(StandardCharsets.UTF_8).length;

        verify(mockExchange, times(1)).sendResponseHeaders(405, expectedByteLength);

        String actualResponse = new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
        assertEquals(expectedResponse, actualResponse);
    }
}
