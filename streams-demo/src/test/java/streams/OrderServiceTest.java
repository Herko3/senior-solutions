package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    OrderService ordersService = new OrderService();


    @BeforeEach
    public void init() {


        Product p1 = new Product("Tv", "IT", 2000);
        Product p2 = new Product("Laptop", "IT", 2400);
        Product p3 = new Product("Phone", "IT", 400);
        Product p4 = new Product("Lord of The Rings", "Book", 20);
        Product p5 = new Product("Harry Potter Collection", "Book", 120);

        Order o1 = new Order("pending", LocalDate.of(2021, 06, 07));
        o1.addProduct(p1);
        o1.addProduct(p2);
        o1.addProduct(p5);

        Order o2 = new Order("on delivery", LocalDate.of(2021, 06, 01));
        o2.addProduct(p3);
        o2.addProduct(p1);
        o2.addProduct(p2);

        ordersService.saveOrder(o1);
        ordersService.saveOrder(o2);

    }

    @Test
    void testCountByStatus() {
        assertEquals(1, ordersService.countOrdersByStatus("Pending"));
    }

    @Test
    void testCountByStatusWithZero() {
        assertEquals(0, ordersService.countOrdersByStatus("Pendisdng"));
    }

    @Test
    void testOrdersByProductCategoryWithResult2() {
        List<Order> result = ordersService.collectOrdersWithProductCategory("it");

        assertThat(result)
                .hasSize(2)
                .extracting(Order::getStatus)
                .contains("on delivery");
    }

    @Test
    void testOrdersByProductCategoryWithResult1() {
        List<Order> result = ordersService.collectOrdersWithProductCategory("book");

        assertThat(result)
                .hasSize(1)
                .extracting(Order::getStatus)
                .contains("pending");
    }

    @Test
    void testOrdersByProductCategoryWithoutResult() {
        List<Order> result = ordersService.collectOrdersWithProductCategory("zoo");

        assertThat(result)
                .hasSize(0);
    }

    @Test
    void testProductsOverPrice(){
        List<Product> result = ordersService.productsOverAmountPrice(1999);

        assertEquals(4,result.size());
    }

    @Test
    void testRevenueBetweenDates(){
        double revenue = ordersService.revenueBetweenDates(LocalDate.of(2021,5,30),LocalDate.of(2021,6,5));

        assertEquals(4800, revenue);
    }

    @Test
    void findProductByName(){
        Product product = ordersService.getProductByName("laptop");

        assertEquals(2400, product.getPrice());
    }

    @Test
    void findProductByNameWithNoResult(){
        assertThrows(IllegalArgumentException.class,()->ordersService.getProductByName("Nincs"));
    }


}