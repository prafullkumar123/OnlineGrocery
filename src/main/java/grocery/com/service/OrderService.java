package grocery.com.service;

import grocery.com.model.Customer;
import grocery.com.model.GroceryItem;
import grocery.com.model.Order;
import grocery.com.repository.CustomerRepository;
import grocery.com.repository.GroceryItemRepository;
import grocery.com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GroceryItemRepository groceryItemRepository;

    public Order createOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<GroceryItem> fullGroceryItems = order.getGroceryItems().stream()
                .map(item -> groceryItemRepository.findById(item.getId())
                        .orElseThrow(() -> new RuntimeException("GroceryItem not found: id = " + item.getId())))
                .toList();

        double totalPrice = fullGroceryItems.stream()
                .mapToDouble(GroceryItem::getPrice)
                .sum();

        order.setCustomer(customer);
        order.setGroceryItems(fullGroceryItems);
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
