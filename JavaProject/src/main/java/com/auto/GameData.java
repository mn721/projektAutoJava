package com.auto;

public class GameData {
    private float averageSpeed;
    private int driftPoints;
    private float distanceTraveled;

    public float getAverageSpeed() { return averageSpeed; }
    public void setAverageSpeed(float averageSpeed) { this.averageSpeed = averageSpeed; }
    public int getDriftPoints() { return driftPoints; }
    public void setDriftPoints(int driftPoints) { this.driftPoints = driftPoints; }
    public float getDistanceTraveled() { return distanceTraveled; }
    public void setDistanceTraveled(float distanceTraveled) { this.distanceTraveled = distanceTraveled; }

    @Override
    public String toString() {
        return String.format("Obecna prędkość: %.2f km/h, Punkty driftu: %d, Dystans: %.2f m",
                averageSpeed, driftPoints, distanceTraveled);
    }
}