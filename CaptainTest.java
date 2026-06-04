package captainship;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Captain class.
 * These tests verify that the captain can correctly manage ships,
 * send them on missions, and track fleet statistics.
 */
public class CaptainTest {

    private Captain captain;
    private Ship fastShip;
    private Ship cargoShip;

    /**
     * Initializes a captain and two ships before each test.
     */
    @Before
    public void setUp() {
        captain = new Captain();

        fastShip = new Ship("Falcon", 60, 100, 500, 100);
        cargoShip = new Ship("Titan", 30, 300, 500, 100);

        captain.registerShip(fastShip);
        captain.registerShip(cargoShip);
    }

    /**
     * Tests that registering a ship adds it to the fleet.
     */
    @Test
    public void testRegisterShip() {
        Captain c = new Captain();
        Ship s = new Ship("Explorer", 40, 200, 500, 100);

        c.registerShip(s);

        assertEquals(1, c.countOperationalShips());
    }

    /**
     * Tests that a ship can successfully complete a mission.
     */
    @Test
    public void testSendShipMissionSuccess() {
        boolean result = captain.sendShip(fastShip, "ScoutMission", 100, 300);

        assertTrue(result);
        assertEquals(100, fastShip.getTotalDistance(), 0.001);
        assertEquals(300, fastShip.getTotalValue(), 0.001);
    }

    /**
     * Tests that a mission fails if the distance exceeds the ship's range.
     */
    @Test
    public void testSendShipFailsIfDistanceTooLong() {
        boolean result = captain.sendShip(fastShip, "FarMission", 1000, 300);

        assertFalse(result);
    }

    /**
     * Tests that a ship's durability decreases after a mission.
     */
    @Test
    public void testDurabilityDecreasesAfterMission() {
        double before = fastShip.getDurability();

        captain.sendShip(fastShip, "Mission", 100, 200);

        assertTrue(fastShip.getDurability() < before);
    }

    /**
     * Tests that the captain can find the fastest ship in the fleet.
     */
    @Test
    public void testFindFastestShip() {
        Ship fastest = captain.findFastestShip();

        assertEquals(fastShip, fastest);
    }

    /**
     * Tests that the captain can find the ship with the largest cargo capacity.
     */
    @Test
    public void testFindLargestShip() {
        Ship largest = captain.findLargestShip();

        assertEquals(cargoShip, largest);
    }

    /**
     * Tests that the total distance traveled by all ships is tracked correctly.
     */
    @Test
    public void testTotalDistanceTracked() {
        captain.sendShip(fastShip, "Mission1", 100, 200);
        captain.sendShip(cargoShip, "Mission2", 50, 100);

        assertEquals(150, captain.calculateTotalDistance(), 0.001);
    }

    /**
     * Tests that the ship contributing the highest value can be identified.
     */
    @Test
    public void testFindShipWithHighestValue() {
        captain.sendShip(fastShip, "Mission1", 100, 200);
        captain.sendShip(cargoShip, "Mission2", 50, 500);

        Ship best = captain.findShipWithHighestValue();

        assertEquals(cargoShip, best);
    }

    /**
     * Tests that voyage history is recorded when ships are sent on missions.
     */
    @Test
    public void testVoyageHistoryRecorded() {
        captain.sendShip(fastShip, "ScoutMission", 100, 200);

        assertEquals(1, captain.getHistory().size());
    }

    /**
     * Tests that a ship becomes not operational when durability reaches zero.
     */
    @Test
    public void testShipBecomesNotOperational() {
        Ship weakShip = new Ship("OldShip", 20, 50, 200, 1);

        captain.registerShip(weakShip);
        captain.sendShip(weakShip, "HardMission", 20, 100);

        assertFalse(weakShip.isOperational());
    }

    /**
     * Tests that broken ships are removed from the fleet.
     */
    @Test
    public void testRetireBrokenShips() {
        Ship weakShip = new Ship("OldShip", 20, 50, 200, 1);

        captain.registerShip(weakShip);
        captain.sendShip(weakShip, "HardMission", 20, 100);

        captain.retireBrokenShips();

        assertEquals(2, captain.countOperationalShips());
    }
}
