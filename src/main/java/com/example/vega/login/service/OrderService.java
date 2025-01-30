package com.example.vega.login.service;

import com.example.vega.login.entity.Orders;
import com.example.vega.login.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Saves a new order to the database.
     *
     * @param order the Orders object to be saved.
     * @return the saved Orders object.
     */
    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    /**
     * Deletes an order from the database by its ID.
     *
     * @param id the ID of the order to be deleted.
     */
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    /**
     * Updates the status of an order.
     *
     * @param id The ID of the order to update.
     * @param status The new status for the order.
     * @return The updated Orders object.
     */
    public Orders updateOrderStatus(Long id, String status) {
        Orders order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + id));
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }


}
