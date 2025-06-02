package com.auto;

public class GameData {
    private float CurrentSpeed;
    private float AverageSpeed;
    private float DistanceTraveled;
    private String CurrentGear;

    public float getCurrentSpeed() {
        return CurrentSpeed;
    }

    public void setCurrentSpeed(float currentSpeed) {
        this.CurrentSpeed = currentSpeed;
    }
    public float getAverageSpeed() {
        return AverageSpeed;
    }

    public void setAverageSpeed(float averageSpeed) {
        this.AverageSpeed = averageSpeed;
    }

    public float getDistanceTraveled() {
        return DistanceTraveled;
    }

    public void setDistanceTraveled(float distanceTraveled) {
        this.DistanceTraveled = distanceTraveled;
    }

    public String getCurrentGear() {
        return CurrentGear;
    }

    public void setCurrentGear(String CurrentGear) {
        this.CurrentGear = CurrentGear;
    }

    @Override
    public String toString() {
        return String.format("Obecny bieg: %s, Obecna prędkość: %.2f km/h, Średnia prędkość: %.2f km/h, Dystans: %.2f m",
                CurrentGear, CurrentSpeed, AverageSpeed, DistanceTraveled);
    }
}