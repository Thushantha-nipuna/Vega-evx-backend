package com.example.vega.login.controller;

import com.example.vega.login.entity.Orders;
import com.example.vega.login.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Orders> getAllOrders() {
        return orderService.getAllOrders(); // Calls the service to fetch all orders
    }

    /**
     * Handles POST requests to create a new order.
     *
     * @param order The Orders object sent in the request body.
     * @return The saved Orders object after persisting it in the database.
     */
    @PostMapping
    public Orders createOrder(@RequestBody Orders order) {
        return orderService.createOrder(order); // Calls the service to save the new order
    }

    /**
     * Handles PATCH requests to update the status of an order by its ID.
     *
     * @param id The ID of the order to update.
     * @param status The new status for the order.
     * @return The updated Orders object.
     */
    @PatchMapping("/{id}/status")
    public Orders updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            return orderService.updateOrderStatus(id, status); // Calls the service to update the status
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    /**
     * Handles DELETE requests to remove an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id); // Calls the service to delete the specified order
    }

    /*
    * package com.example.vega.login.controller;

import com.example.vega.login.entity.Order;
import com.example.vega.login.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from React app
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }
}
* */


}
