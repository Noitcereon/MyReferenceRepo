package com.experis.mytunesassignment.controllers;

import com.experis.mytunesassignment.models.*;
import com.experis.mytunesassignment.data_access.repositories.CustomerSqlRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api")
public class CustomerController {
    private final CustomerSqlRepository customerRepo = new CustomerSqlRepository();

    @GetMapping("customer")
    public ArrayList<CustomerPartial> getAll(@RequestParam(required = false, defaultValue = "0") String limit, @RequestParam(required = false, defaultValue = "0") String offset) {
        if (limit.equals("0")) {
            return customerRepo.getAll();
        }
        return customerRepo.getAll(Integer.parseInt(limit), Integer.parseInt(offset));
    }

    @GetMapping("customer/{customerId}")
    public CustomerPartial getById(@PathVariable String customerId) {
        return customerRepo.getById(Integer.parseInt(customerId));
    }

    @GetMapping("customer/search")
    public CustomerPartial getCustomerByName(@RequestParam String name) {
        return customerRepo.getCustomerByName(name);
    }

    @PostMapping("customer")
    public CustomerPartial create(@RequestBody CustomerPartial customer){
        return customerRepo.create(customer);
    }
    @PutMapping("customer/{customerId}")
    public int update(@RequestBody CustomerPartial customer, @PathVariable String customerId){
        customer.setId(Integer.parseInt(customerId));
        return customerRepo.update(customer);
    }

    @GetMapping("customer/count/country")
    public ArrayList<CustomerCountry> getNumberOfCustomerPerCountry(){
        return customerRepo.getNumberOfCustomerPerCountry();
    }
    @GetMapping("customer/count/spendings")
    public ArrayList<CustomerSpender> getHighestSpendingCustomersDescending(){
        return customerRepo.getHighestSpendingCustomersDescending();
    }

    @GetMapping("customer/{customerId}/popular/genre")
    public ArrayList<CustomerGenre> getCustomersMostPopularGenres(@PathVariable int customerId){
        return customerRepo.getCustomersMostPopularGenres(customerId);
    }

}
