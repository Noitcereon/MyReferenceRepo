package com.experis.mytunesassignment.data_access.repositories;

import com.experis.mytunesassignment.data_access.interfaces.ICustomerRepository;
import com.experis.mytunesassignment.models.CustomerCountry;
import com.experis.mytunesassignment.models.CustomerGenre;
import com.experis.mytunesassignment.models.CustomerPartial;
import com.experis.mytunesassignment.models.CustomerSpender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerSqlRepositoryTest {

    private ICustomerRepository customerRepo;

    @BeforeEach
    void setUp() {
        customerRepo = new CustomerSqlRepository();
    }

    @Test
    void getAll_Valid_ReturnsArrayListCustomerPartial() {
        var expected = ArrayList.class;
        ArrayList<CustomerPartial> actual = customerRepo.getAll();
        assertEquals(expected, actual.getClass());
    }

    @Test
    void getById_validId_GetId4() {
        CustomerPartial customer = customerRepo.getById(4);
        int expectedID = 4;
        int actualID = customer.getId();
        assertEquals(expectedID, actualID);
    }

    @Test
    void GetById_invalidId_customerIsNull() {
        CustomerPartial customer = customerRepo.getById(-1);
        assertNull(customer);
    }


    @Test
    void create_validInput_returnsCustomer() {
        int countBeforeCreate = customerRepo.getAll().size();
        CustomerPartial customer = new CustomerPartial(0, "Hans", "Nielsen", "USA", "2860", "+45 20204030", "hans@gmail.com");
        customerRepo.create(customer);
        int countAfterCreate = customerRepo.getAll().size();
        assertEquals(countBeforeCreate + 1, countAfterCreate);
    }

    @Test
    void update_validInput_returnsOne() {
        int expected = 1; // affectedRows

        CustomerPartial customer = customerRepo.getCustomerByName("Kara");
        customer.setLastName("the Benevolent");

        int actual = customerRepo.update(customer);

        assertEquals(expected, actual);
    }

    @Test
    void delete() {
    }

    @Test
    void getHighestSpendingCustomersDescendingOrder_success_returnsArrayList() {
        ArrayList<CustomerSpender> actualCustomers = customerRepo.getHighestSpendingCustomersDescending();
        var expected = ArrayList.class;

        assertEquals(expected, actualCustomers.getClass());
    }

    @Test
    void getCustomersMostPopularGenres_success_returnsArrayList() {
        ArrayList<CustomerGenre> actualGenres = customerRepo.getCustomersMostPopularGenres(1);
        var expected = ArrayList.class;
        assertEquals(expected,actualGenres.getClass());
    }

    @Test
    void getNumberOfCustomersPerCountry_testDataWithUsa_returnsTrue() {
        ArrayList<CustomerCountry> data = customerRepo.getNumberOfCustomerPerCountry();
        String expectedFirstCountry = "USA";
        Optional<CustomerCountry> first = data.stream().findFirst();
        String actualFirstCountry = first.get().getCountry();
        assertEquals(expectedFirstCountry, actualFirstCountry);
    }


    @Test
    void getAllLimitOverload_validLimitAndOffset_listOfSizeLimitAndFirstIDisOffsetPlusOne() {
        // arrange
        ArrayList<CustomerPartial> customers = customerRepo.getAll(10, 30);
        int expectedLimit = 10;
        int expectedFirstID = 31;

        // act
        int actualLimit = customers.size();
        int actualFirstID = customers.get(0).getId();

        // assert
        assertEquals(expectedLimit, actualLimit);
        assertEquals(expectedFirstID, actualFirstID);
    }


    @Test
    void getCustomerByName_aName_returnsSingularCustomerWithNameKara() {
        String expectedCustomersName = "Kara";
        CustomerPartial actualCustomer = customerRepo.getCustomerByName("Kara");
        assertEquals(expectedCustomersName, actualCustomer.getFirstName());
    }

}