package Entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "farms")

public class Farm extends Type implements java.io.Serializable{
    private int animal_count;

    private Farm(FarmBuilder builder) {
        super(builder.name, builder.number_of_workers, builder.salary, builder.number_of_units_for_day, builder.price_for_product_unit, builder.fault, builder.equipment_repair, builder.utilities);
        this.animal_count = builder.animal_count;
    }
    public Farm() {
    }

    public static class FarmBuilder {
        private String name;
        private int number_of_workers, salary, number_of_units_for_day, price_for_product_unit, equipment_repair, utilities, animal_count;
        private double fault;

        public FarmBuilder set_name(String name) { this.name = name; return this; }
        public FarmBuilder set_workers(int workers) { this.number_of_workers = workers; return this; }
        public FarmBuilder set_salary(int salary) { this.salary = salary; return this; }
        public FarmBuilder set_units(int units) { this.number_of_units_for_day = units; return this; }
        public FarmBuilder set_price(int price) { this.price_for_product_unit = price; return this; }
        public FarmBuilder set_fault(double fault) { this.fault = fault; return this; }
        public FarmBuilder set_repair(int repair) { this.equipment_repair = repair; return this; }
        public FarmBuilder set_utilities(int utilities) { this.utilities = utilities; return this; }
        public FarmBuilder set_animal_count(int count) { this.animal_count = count; return this; }
        public Farm build() {
            return new Farm(this);
        }
    }

    @JsonProperty("animal_count")
    public void set_animal_count (int animal_count) {
        this.animal_count = animal_count;
    }

    public int get_animal_count () {
        return this.animal_count;
    }


    public double profit (int days) {
        return get_price_for_product_unit() * get_number_of_units_for_day() * days * (1 - get_fault());
    }

    public double costs (int days) {
        double monthly_costs = (get_salary() * get_number_of_workers()) + get_equipment_repair() + get_utilities();
        double daily_costs = monthly_costs / 30.0;

        return daily_costs * days;
    }

    public double final_profit (int days) {
        return profit(days) - costs(days);
    }

    public double final_profit(int days, int bonus) {
        return final_profit(days) + bonus;
    }
}
