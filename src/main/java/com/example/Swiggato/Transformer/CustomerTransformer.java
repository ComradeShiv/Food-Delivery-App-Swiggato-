package com.example.Swiggato.Transformer;

import com.example.Swiggato.DTOs.RequestDTO.CustomerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.CartResponse;
import com.example.Swiggato.DTOs.ResponseDTO.CustomerResponse;
import com.example.Swiggato.Model.Customer;

import javax.xml.transform.Transformer;
import java.net.CacheResponse;
import java.util.ArrayList;

public class CustomerTransformer {

    public static Customer customerRequestToCustomer(CustomerRequest customerRequest) {
        return Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .email(customerRequest.getEmail())
                .mobileNo(customerRequest.getMobileNo())
                .address(customerRequest.getAddress())
                .gender(customerRequest.getGender())
                .transaction(new ArrayList<>()) // initialised again coz builder class initialise with null if not given by us
                .order(new ArrayList<>()) // initialised again coz builder class initialise with null if not given by us
                .cardList(new ArrayList<>()) // initialised again coz builder class initialise with null if not given by us
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer) {

        CartResponse cartResponse = CartTransformer.cartToCartResponse(customer.getCart());
        return CustomerResponse.builder()
                .customerName(customer.getCustomerName())
                .address(customer.getAddress())
                .email(customer.getEmail())
                .mobileNo(customer.getMobileNo())
                .cartResponse(cartResponse)
                .build();
    }
}
