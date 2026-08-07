package Entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@MappedSuperclass //це шоб додати поля у таблиці спадкоємців
public abstract class Type implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "workers")
    private int number_of_workers;

    @Column(name = "salary")
    private int salary;

    @Column(name = "units")
    private int number_of_units_for_day;

    @Column(name = "price")
    private int price_for_product_unit;

    @Column(name = "fault")
    private double fault;

    @Column(name = "repair")
    private int equipment_repair;

    @Column(name = "utilities")
    private int utilities;

    public Type(String name, int number_of_workers, int salary, int number_of_units_for_day, int price_for_product_unit, double fault, int equipment_repair, int utilities) {
        this.name = name;
        this.number_of_workers = number_of_workers;
        this.salary = salary;
        this.number_of_units_for_day = number_of_units_for_day;
        this.price_for_product_unit = price_for_product_unit;
        this.fault = fault;
        this.equipment_repair = equipment_repair;
        this.utilities = utilities;
    }

    public Type() {}

    @JsonProperty("id")
    public void set_id(int id) { this.id = id; }
    public int get_id() { return id; }

    @JsonProperty("number_of_workers")
    public void set_number_of_workers (int number_of_workers) {
        this.number_of_workers = number_of_workers;
    }
    public int get_number_of_workers () {
        return this.number_of_workers;
    }

    @JsonProperty("salary")
    public void set_salary (int salary) {
        this.salary = salary;
    }
    public int get_salary () {
        return this.salary;
    }

    @JsonProperty("number_of_units_for_day")
    public void set_number_of_units_for_day (int number_of_units_for_day) {
        this.number_of_units_for_day = number_of_units_for_day;
    }
    public int get_number_of_units_for_day () {
        return this.number_of_units_for_day;
    }

    @JsonProperty("price_for_product_unit")
    public void set_price_for_product_unit (int price_for_product_unit) {
        this.price_for_product_unit = price_for_product_unit;
    }
    public int get_price_for_product_unit () {
        return this.price_for_product_unit;
    }

    @JsonProperty("fault")
    public void set_fault (double fault) {
        this.fault = fault;
    }
    public double get_fault () {
        return this.fault;
    }

    @JsonProperty("equipment_repair")
    public void set_equipment_repair (int equipment_repair) {
        this.equipment_repair = equipment_repair;
    }
    public int get_equipment_repair () {
        return this.equipment_repair;
    }

    @JsonProperty("utilities")
    public void set_utilities (int utilities) {
        this.utilities = utilities;
    }
    public int get_utilities () {
        return this.utilities;
    }

    @JsonProperty("name")
    public String get_name() {
        return name;
    }
    public void set_name (String name) {
        this.name = name;
    }

    public abstract double profit (int days);
    public abstract double costs (int days);
    public abstract double final_profit (int days);
    public abstract double final_profit(int days, int bonus);
}