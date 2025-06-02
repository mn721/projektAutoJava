package com.auto;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.testng.AssertJUnit.assertEquals;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.mockito.Mockito.*;

class DataServerTest {

    @Test
    void shouldHandleValidPostRequest() throws IOException
    {
        Gson gson = new Gson();
        HttpExchange mockExchange = Mockito.mock(HttpExchange.class);

        GameData testData = new GameData();
        testData.setCurrentSpeed(120.5f);
        testData.setAverageSpeed(85.3f);
        testData.setDistanceTraveled(2500.7f);
        testData.setCurrentGear("Fifth");

        String json = gson.toJson(testData);
        InputStream inputStream = new ByteArrayInputStream(json.getBytes());

        when(mockExchange.getRequestMethod()).thenReturn("POST");
        when(mockExchange.getRequestBody()).thenReturn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(mockExchange.getResponseBody()).thenReturn(outputStream);

        DataServer.DataHandler handler = new DataServer.DataHandler();
        handler.handle(mockExchange);

        verify(mockExchange).sendResponseHeaders(200, "Dane odebrano pomyślnie".length());
        assertEquals("Dane odebrano pomyślnie", outputStream.toString());
    }

    @Test
    void shouldRejectNonPostMethods() throws IOException
    {
        HttpExchange mockExchange = Mockito.mock(HttpExchange.class);
        when(mockExchange.getRequestMethod()).thenReturn("GET");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        when(mockExchange.getResponseBody()).thenReturn(outputStream);

        DataServer.DataHandler handler = new DataServer.DataHandler();
        handler.handle(mockExchange);

        verify(mockExchange).sendResponseHeaders(405, "Metoda nieobsługiwana".length());
        assertEquals("Metoda nieobsługiwana", outputStream.toString());
    }
}