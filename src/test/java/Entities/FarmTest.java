package Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FarmTest {

    private Farm farm;

    @BeforeEach
    void setUp() {
        farm = new Farm.FarmBuilder()
                .set_name("Dairy Farm")
                .set_workers(5)
                .set_salary(1000)
                .set_units(100)
                .set_price(20)
                .set_fault(0.1)
                .set_repair(500)
                .set_utilities(300)
                .set_animal_count(50)
                .build();
    }

    @Test
    void testProfitCalculation() {
        assertEquals(1800.0, farm.profit(1), 0.001);
    }

    @Test
    void testCostsCalculation() {
        double expectedDailyCosts = 5800.0 / 30.0;
        assertEquals(expectedDailyCosts, farm.costs(1), 0.001);
    }

    @Test
    void testFinalProfitWithBonus() {
        double baseProfit = farm.final_profit(1);
        double profitWithBonus = farm.final_profit(1, 500);
        assertEquals(baseProfit + 500, profitWithBonus, 0.001);
    }

    @Test
    void testGettersAndSetters() {
        farm.set_animal_count(60);
        assertEquals(60, farm.get_animal_count());
        assertEquals("Dairy Farm", farm.get_name());
    }
}