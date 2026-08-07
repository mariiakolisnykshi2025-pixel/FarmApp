package Entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void testFieldWithStorage() {
        Storage storage = new Storage();
        storage.set_id(10);

        Field field = new Field.FieldBuilder()
                .set_name("Wheat Field")
                .set_area(100)
                .set_workers(2)
                .set_salary(500)
                .set_units(1000)
                .set_price(5)
                .set_fault(0.1)
                .set_repair(100)
                .set_utilities(100)
                .build();

        field.setStorage(storage);

        assertEquals(100, field.get_area());
        assertEquals(10, field.getStorageId());
        assertEquals(storage, field.getStorage());

        assertEquals(4500.0, field.profit(1), 0.001);
    }

    @Test
    void testStorageIdNullSafe() {
        Field field = new Field();
        assertNull(field.getStorageId());
    }

    @Test
    void testFieldCosts() {
        Field field = new Field.FieldBuilder()
                .set_workers(1)
                .set_salary(3000)
                .set_repair(0)
                .set_utilities(0)
                .build();

        assertEquals(100.0, field.costs(1), 0.001);
    }
}