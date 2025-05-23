package com.auto;

public class GameData {
    private float CurrentSpeed;
    private float AverageSpeed;
    private int DriftPoints;
    private float DistanceTraveled;

    public float getCurrentSpeed() { return CurrentSpeed; }
    public void setCurrentSpeed(float currentSpeed) { this.CurrentSpeed = currentSpeed; }
    public int getDriftPoints() { return DriftPoints; }
    public void setDriftPoints(int driftPoints) { this.DriftPoints = driftPoints; }
    public float getDistanceTraveled() { return DistanceTraveled; }
    public void setDistanceTraveled(float distanceTraveled) { this.DistanceTraveled = distanceTraveled; }

    @Override
    public String toString() {
        return String.format("Obecna prędkość: %.2f km/h, Średnia prędkość: %2f km/h, Punkty driftu: %d, Dystans: %.2f m",
                CurrentSpeed, AverageSpeed, DriftPoints, DistanceTraveled);
    }
}