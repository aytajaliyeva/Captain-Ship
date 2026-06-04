package captainship;

import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Manages a fleet of ships and their missions.
 */
public class Captain {
    /** Ships in the fleet */
    private List<Ship> ships;
    /** Mission history */
    private List<String> history;
    
    /** Creates a captain with an empty fleet */
    public Captain() {
        ships = new ArrayList<>();
        history = new ArrayList<>();
    }

    /** Adds a ship to the fleet */
    public void registerShip(Ship s) {
        ships.add(s);
    }

    /** Sends a ship on a mission */
    public boolean sendShip(Ship s, String missionName, double distance, double value) {

        if (!ships.contains(s) || !s.isOperational()) {
            return false;
        }

        if (distance > s.getRange()) {
            return false;
        }

        s.addDistance(distance);
        s.addValue(value);
        s.decreaseDurability(distance * 0.1);

        history.add(s.getName() + " -> " + missionName);

        return true;
    }

    /** Finds the fastest ship */
    public Ship findFastestShip() {

        Ship max = ships.get(0);

        for (Ship s : ships) {
            if (s.getMaxSpeed() > max.getMaxSpeed()) {
                max = s;
            }
        }

        return max;
    }

    /** Finds the largest ship */
    public Ship findLargestShip() {

        Ship max = ships.get(0);

        for (Ship s : ships) {
            if (s.getCargoCapacity() > max.getCargoCapacity()) {
                max = s;
            }
        }

        return max;
    }

    /** Counts operational ships */
    public int countOperationalShips() {

        int count = 0;

        for (Ship s : ships) {
            if (s.isOperational()) {
                count++;
            }
        }

        return count;
    }

    /** Calculates total distance */
    public double calculateTotalDistance() {

        double sum = 0;

        for (Ship s : ships) {
            sum += s.getTotalDistance();
        }

        return sum;
    }

    /** Finds ship with highest value */
    public Ship findShipWithHighestValue() {

        Ship max = ships.get(0);

        for (Ship s : ships) {
            if (s.getTotalValue() > max.getTotalValue()) {
                max = s;
            }
        }

        return max;
    }

    /** Removes broken ships */
    public void retireBrokenShips() {
        ships.removeIf(s -> !s.isOperational());
    }

    /** Returns mission history */
    public List<String> getHistory() {
        return history;
    }
}
