package Service;

import Entities.Type;

public interface StatisticsFunctions <T extends Type> {

    default void print_statistics (T item, int k) {
        System.out.printf("%-15s %-25d %-20d %-15.2f грн%n", item.get_name(), item.get_number_of_units_for_day() * k, item.get_price_for_product_unit(), item.final_profit(k));
    }

    default void print_profits (T item, int k) {
        System.out.printf("%-15s %-15.2f %-15.2f%n", item.get_name(), item.final_profit(k), item.final_profit(k, 2000));
    }

    default void print_costs (T item, int k) {
        System.out.printf("%-15s %-15.2f грн%n", item.get_name(), item.costs(k));
    }

}
