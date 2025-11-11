package BD2;

import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CarParkingTest {

    private CarParking parking;

    @BeforeEach
    void setUp() {
        parking = new CarParking();
    }

    @Test
    void testArrivalWhenGarageHasSpace() {
        parking.handleArrival("UB11-11");
        parking.handleArrival("UB22-22");

        // Expect 2 cars in the garage
        assertEquals(2, CarParking.garage.size());

        // Check messages
        List<String> msgs = CarParking.results;
        assertTrue(msgs.get(0).contains("There is room"));
        assertTrue(msgs.get(1).contains("There is room"));
    }

    @Test
    void testArrivalWhenGarageFull() {
        for (int i = 1; i <= 10; i++) {
            parking.handleArrival("CAR" + i);
        }
        parking.handleArrival("CAR11");

        List<String> msgs = CarParking.results;
        assertTrue(msgs.get(msgs.size() - 1).contains("Garage full"));
        assertEquals(10, CarParking.garage.size());
    }

}
