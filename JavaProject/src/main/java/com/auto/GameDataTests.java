package com.auto;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

class GameDataTest {

    @Test
    void testGettersAndSetters()
    {
        GameData data = new GameData();
        data.setCurrentSpeed(120.5f);
        data.setAverageSpeed(85.3f);
        data.setDistanceTraveled(2500.7f);
        data.setCurrentGear("Fourth");

        assertEquals(120.5f, data.getCurrentSpeed(), 0.001f);
        assertEquals(85.3f, data.getAverageSpeed(), 0.001f);
        assertEquals(2500.7f, data.getDistanceTraveled(), 0.001f);
        assertEquals("Fourth", data.getCurrentGear());
    }

    @Test
    void testToStringFormatting()
    {
        GameData data = new GameData();
        data.setCurrentSpeed(100.0f);
        data.setAverageSpeed(75.5f);
        data.setDistanceTraveled(1500.0f);
        data.setCurrentGear("Third");

        String result = data.toString();

        String expected = "Obecny bieg: Third, Obecna prędkość: 100.00 km/h, Średnia prędkość: 75.50 km/h, Dystans: 1500.00 m";
        assertEquals(expected, result);
    }
}