package com.example.Swiggato.Repository;

import com.example.Swiggato.Enum.Gender;
import com.example.Swiggato.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByMobileNo(String mobile);

    List<Customer> findByGender(Gender female);
}
