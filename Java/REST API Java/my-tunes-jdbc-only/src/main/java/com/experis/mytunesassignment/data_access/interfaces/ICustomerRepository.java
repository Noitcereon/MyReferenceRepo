package com.experis.mytunesassignment.data_access.interfaces;

import com.experis.mytunesassignment.models.*;

import java.util.ArrayList;
import java.util.HashMap;

public interface ICustomerRepository extends ICrudRepository<CustomerPartial> {

    ArrayList<CustomerPartial> getAll(int limit, int offset); // overload method

    ArrayList<CustomerSpender> getHighestSpendingCustomersDescending();

    ArrayList<CustomerGenre> getCustomersMostPopularGenres(int customerId);

    ArrayList<CustomerCountry> getNumberOfCustomerPerCountry();

    CustomerPartial getCustomerByName(String name);
}
