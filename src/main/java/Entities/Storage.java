package Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "storages")
public class Storage extends Type {

    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL)
    private List<Field> fields;

    @Column(name = "capacity")
    private int capacity;

    public Storage() {
        super();
    }

    private Storage(StorageBuilder builder) {
        super(builder.name, builder.number_of_workers, builder.salary, builder.number_of_units_for_day, builder.price_for_product_unit, builder.fault, builder.equipment_repair, builder.utilities);
        this.capacity = builder.capacity;
    }

    public static class StorageBuilder {
        private String name;
        private int number_of_workers, salary, number_of_units_for_day, price_for_product_unit, equipment_repair, utilities, capacity;
        private double fault;

        public StorageBuilder set_name(String name) { this.name = name; return this; }
        public StorageBuilder set_workers(int workers) { this.number_of_workers = workers; return this; }
        public StorageBuilder set_salary(int salary) { this.salary = salary; return this; }
        public StorageBuilder set_units(int units) { this.number_of_units_for_day = units; return this; }
        public StorageBuilder set_price(int price) { this.price_for_product_unit = price; return this; }
        public StorageBuilder set_fault(double fault) { this.fault = fault; return this; }
        public StorageBuilder set_repair(int repair) { this.equipment_repair = repair; return this; }
        public StorageBuilder set_utilities(int utilities) { this.utilities = utilities; return this; }
        public StorageBuilder set_capacity(int capacity) { this.capacity = capacity; return this; }

        public Storage build() { return new Storage(this); }
    }

    @JsonProperty("capacity")
    public void set_capacity (int capacity) { this.capacity = capacity; }
    public int get_capacity () { return this.capacity; }

    @Override
    public double profit (int days) { return get_price_for_product_unit() * get_number_of_units_for_day() * days * (1 - get_fault()); }
    @Override
    public double costs (int days) { return ((get_salary() * get_number_of_workers()) + get_equipment_repair() + get_utilities()) / 30.0 * days; }
    @Override
    public double final_profit (int days) { return profit(days) - costs(days); }
    @Override
    public double final_profit(int days, int bonus) { return final_profit(days) + bonus; }
}