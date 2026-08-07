package Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "fields")
public class Field extends Type {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @Column(name = "area")
    private int area;

    public Field() {
        super();
    }

    private Field(FieldBuilder builder) {
        super(builder.name, builder.number_of_workers, builder.salary, builder.number_of_units_for_day, builder.price_for_product_unit, builder.fault, builder.equipment_repair, builder.utilities);
        this.area = builder.area;
    }

    public static class FieldBuilder {
        private String name;
        private int number_of_workers, salary, number_of_units_for_day, price_for_product_unit, equipment_repair, utilities, area;
        private double fault;

        public FieldBuilder set_name(String name) { this.name = name; return this; }
        public FieldBuilder set_workers(int workers) { this.number_of_workers = workers; return this; }
        public FieldBuilder set_salary(int salary) { this.salary = salary; return this; }
        public FieldBuilder set_units(int units) { this.number_of_units_for_day = units; return this; }
        public FieldBuilder set_price(int price) { this.price_for_product_unit = price; return this; }
        public FieldBuilder set_fault(double fault) { this.fault = fault; return this; }
        public FieldBuilder set_repair(int repair) { this.equipment_repair = repair; return this; }
        public FieldBuilder set_utilities(int utilities) { this.utilities = utilities; return this; }
        public FieldBuilder set_area(int area) { this.area = area; return this; }


        public Field build() { return new Field(this); }
    }

    @JsonProperty("area")
    public void set_area(int area) { this.area = area; }
    public int get_area() { return this.area; }


    @Override
    public double profit (int days) { return get_price_for_product_unit() * get_number_of_units_for_day() * days * (1 - get_fault()); }
    @Override
    public double costs (int days) { return ((get_salary() * get_number_of_workers()) + get_equipment_repair() + get_utilities()) / 30.0 * days; }
    @Override
    public double final_profit (int days) { return profit(days) - costs(days); }
    @Override
    public double final_profit(int days, int bonus) { return final_profit(days) + bonus; }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    @JsonProperty("storageId")
    public Integer getStorageId() {
        return storage != null ? storage.get_id() : null;
    }
}