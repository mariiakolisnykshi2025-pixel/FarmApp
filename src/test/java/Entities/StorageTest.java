package Entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void testStorageCapacityAndLogic() {
        Storage storage = new Storage.StorageBuilder()
                .set_name("Central Storage")
                .set_capacity(10000)
                .set_workers(2)
                .set_salary(1500)
                .set_repair(200)
                .set_utilities(100)
                .set_units(0)
                .build();

        assertEquals(10000, storage.get_capacity());

        assertEquals(110.0, storage.costs(1), 0.001);

        assertEquals(0.0, storage.profit(10));

        assertEquals(-110.0, storage.final_profit(1), 0.001);
    }
}