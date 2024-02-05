package com.example.Swiggato.Controller;

import com.example.Swiggato.DTOs.RequestDTO.CustomerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.CustomerResponse;
import com.example.Swiggato.Service.CustomerService;
import com.example.Swiggato.Service.Impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    final CustomerService customerService;   // Constructor Injection

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {
        try {
            CustomerResponse response = customerService.addCustomer(customerRequest);
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get customer details
    @GetMapping("/getCustomer")
    public ResponseEntity getCustomer(@RequestParam("mobNo") String mobNo) {
        try {
            CustomerResponse response = customerService.getCustomer(mobNo);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // get the customer with most number of orders
    @GetMapping("/customerWithMostOrders")
    public ResponseEntity findCustomerWithMostOrders() {
        try {
            CustomerResponse customerResponse = customerService.findCustomerWithMostOrders();
            return new ResponseEntity(customerResponse, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // get the female customer with least number of orders
    @GetMapping("/femaleWithLeastOrders")
    public ResponseEntity femaleCustomerWithLeastOrders() {
        try {
            CustomerResponse customerResponse = customerService.femaleCustomerWithLeastOrders();
            return new ResponseEntity<>(customerResponse, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
