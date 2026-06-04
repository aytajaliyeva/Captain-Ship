/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package captainship;

/**
 * This class represents a ship.
 */
public class Ship {
    private String name;
    private double maxSpeed;
    private double cargoCapacity;
    private double range;
    private double durability;
    
    private double totalDistance;
    private double totalValue;
    private boolean operational;
    
    public Ship(String name, double maxSpeed, double cargoCapacity, double range, double durability) {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.cargoCapacity = cargoCapacity;
        this.range = range;
        this.durability = durability;
        
        this.operational = true;
        this.totalDistance = 0;
        this.totalValue = 0;
    }
    public String getName() {
        return name;
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
    public double getCargoCapacity() {
        return cargoCapacity;
    }
    public double getRange() {
        return range;
    }
    public double getDurability() {
        return durability;
    }
    public boolean isOperational() {
        return operational;
    }
    public double getTotalDistance() {
        return totalDistance;
    }
    public double getTotalValue() {
        return totalValue;
    }
    public void addDistance(double distance) {
        totalDistance += distance;
    }
    public void addValue(double value) {
        totalValue += value;
    }
    public void decreaseDurability(double amount) {
        durability -= amount;
        if (durability <= 0) {
            operational = false;
        }
    }
}
