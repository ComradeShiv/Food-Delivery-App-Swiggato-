package com.example.Swiggato.Service;

import com.example.Swiggato.DTOs.RequestDTO.CustomerRequest;
import com.example.Swiggato.DTOs.ResponseDTO.CustomerResponse;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerRequest customerRequest);

    CustomerResponse getCustomer(String mobNo);

    CustomerResponse findCustomerWithMostOrders();

    CustomerResponse femaleCustomerWithLeastOrders();
}
