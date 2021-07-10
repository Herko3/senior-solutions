package streams;

import java.time.LocalDate;
import java.util.*;

public class OrderService {

    private List<Order> orders = new ArrayList<>();


    public void saveOrder(Order order) {
        orders.add(order);
    }

    public long countOrdersByStatus(String status) {
        return orders.stream()
                .filter(o -> o.getStatus().equalsIgnoreCase(status))
                .count();
    }

    public List<Order> collectOrdersWithProductCategory(String category) {
        return orders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> p.getCategory().equalsIgnoreCase(category)))
                .toList();
    }

    public List<Product> productsOverAmountPrice(int amount) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getPrice() > amount)
                .toList();
    }

    public double revenueBetweenDates(LocalDate after, LocalDate before) {
        return orders.stream()
                .filter(o -> o.getOrderDate().isAfter(after) && o.getOrderDate().isBefore(before))
                .flatMapToDouble(o -> o.getProducts().stream()
                        .mapToDouble(Product::getPrice))
                .sum();
    }

    public Product getProductByName(String name) {
        return orders.stream()
                .flatMap(o -> o.getProducts().stream())
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No product with name: " + name));
    }



}
