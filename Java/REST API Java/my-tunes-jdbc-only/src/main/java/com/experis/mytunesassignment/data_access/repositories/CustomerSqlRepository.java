package com.experis.mytunesassignment.data_access.repositories;

import com.experis.mytunesassignment.data_access.ConnectionManager;
import com.experis.mytunesassignment.models.*;
import com.experis.mytunesassignment.data_access.interfaces.ICustomerRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class CustomerSqlRepository implements ICustomerRepository {

    private Connection conn;

    // region ICrudRepo
    @Override
    public ArrayList<CustomerPartial> getAll() {
        ArrayList<CustomerPartial> customers = new ArrayList<>();
        try {
            conn = ConnectionManager.getInstance().getConnection();
            System.out.println("Connection to SQLite has been established.");

            String sql = "SELECT CustomerId, FirstName, LastName, Country,PostalCode,Phone,Email FROM Customer";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerPartial customer = readCustomerPartial(resultSet);
                customers.add(customer);
            }
            return customers; // success
        } catch (SQLException sqe) {
            System.out.println("Something went wrong...");
            System.out.println(sqe.getMessage());
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }
        }
        return null; // failed
    }


    @Override
    public CustomerPartial getById(int id) {
        CustomerPartial customer = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            System.out.println("Connection to SQLite has been established.");

            String sql = "SELECT CustomerId, FirstName, LastName, Country," +
                    "PostalCode,Phone,Email FROM Customer WHERE CustomerId = ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = readCustomerPartial(resultSet);
            }
            return customer; // success
        } catch (SQLException sqe) {
            System.out.println("Something went wrong...");
            System.out.println(sqe.getMessage());
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }
        }
        return null; // failed
    }

    @Override
    public CustomerPartial create(CustomerPartial customer) {
        try {
            //Open Connection
            conn = ConnectionManager.getInstance().getConnection();
            System.out.println("Connection to SQLite has been established.");
            String sql = "INSERT INTO Customer (FirstName,LastName,Country,PostalCode,Phone,Email) VALUES(?,?,?,?,?,?)";
            //Prepare statement
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            setCustomer(customer, preparedStatement);
            //Execute statement
            preparedStatement.executeUpdate();
            var resultset = preparedStatement.getGeneratedKeys();//.getRowId("CustomerId");
            while (resultset.next()) {
                customer.setId(resultset.getInt(1));
            }
            return customer;
        } catch (SQLException sqe) {
            System.out.println("Something went wrong...");
            System.out.println(sqe.getMessage());
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }
        }
        return null;
    }


    //    /**
//     * @param updatedCustomer Customer object the database should update to (must have the Id specified)
//     * @return true if successful and the db was updated, otherwise false.
//     */
    @Override
    public int update(CustomerPartial updatedCustomer) {
        conn = ConnectionManager.getInstance().getConnection();

        String sql = "UPDATE Customer SET FirstName = ?, LastName = ?, " +
                " Country = ?, PostalCode = ?, Phone = ?," +
                " Email=?" +
                " WHERE Customer.CustomerId = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            setCustomer(updatedCustomer, statement);
            statement.setInt(7, updatedCustomer.getId());

            return statement.executeUpdate(); // returns no. of affected rows
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // failure
    }

    private void setCustomer(CustomerPartial updatedCustomer, PreparedStatement statement) throws SQLException {
        statement.setString(1, updatedCustomer.getFirstName());
        statement.setString(2, updatedCustomer.getLastName());
        statement.setString(3, updatedCustomer.getCountry());
        statement.setString(4, updatedCustomer.getPostalCode());
        statement.setString(5, updatedCustomer.getPhoneNo());
        statement.setString(6, updatedCustomer.getEmail());
    }

    @Override
    public int delete(int id) {
        return 0;
    }
    // endregion ICrudRepo

    // region ICustomerRepo specific
    @Override
    public ArrayList<CustomerSpender> getHighestSpendingCustomersDescending() {
        Connection conn = ConnectionManager.getInstance().getConnection();
        String sql = "SELECT SUM(I.Total) Spendings, FirstName, LastName FROM Customer " +
                "INNER JOIN Invoice I on Customer.CustomerId = I.CustomerId " +
                "GROUP BY Customer.CustomerId " +
                "ORDER BY Spendings DESC";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();

            ArrayList<CustomerSpender> customers = new ArrayList<>();
            while (result.next()) {
                String firstName = result.getString("FirstName");
                String lastName = result.getString("LastName");
                double total = result.getDouble("Spendings");
                CustomerSpender customer = new CustomerSpender(firstName, lastName, total);
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        return null;
    }

    @Override
    public ArrayList<CustomerGenre> getCustomersMostPopularGenres(int customerId) {
        try {
            //Open Connection
            conn = ConnectionManager.getInstance().getConnection();
            System.out.println("Connection to SQLite has been established.");

            //Prepare statement
            String sql = """
                    SELECT G.Name, COUNT(G.GenreId) AS Count FROM Customer
                    INNER JOIN Invoice I on Customer.CustomerId = I.CustomerId
                    INNER JOIN InvoiceLine IL on I.InvoiceId = IL.InvoiceId
                    INNER JOIN Track T on IL.TrackId = T.TrackId
                    INNER JOIN Genre G on T.GenreId = G.GenreId
                    WHERE I.CustomerId = ?
                    GROUP BY G.GenreId
                    ORDER BY Count DESC""";

            PreparedStatement preparedStatement =
                    conn.prepareStatement(sql);

            preparedStatement.setInt(1, customerId);
            //Execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            //Process results
            ArrayList<CustomerGenre> popularGenres = new ArrayList<CustomerGenre>();
            int top_count = resultSet.getInt("Count");
            while (resultSet.next()) {
                if (resultSet.getInt("Count") == top_count) {
                    popularGenres.add(new CustomerGenre(resultSet.getString("Name")));
                }
            }
            return popularGenres;

        } catch (SQLException sqe) {
            System.out.println("Something went wrong...");
            System.out.println(sqe.getMessage());
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }

        }
        return null; //failure
    }


    @Override
    public ArrayList<CustomerCountry> getNumberOfCustomerPerCountry() {
        conn = ConnectionManager.getInstance().getConnection();

        try {
            String sql = "SELECT Country, COUNT(CustomerId) AS CustomerCount FROM Customer GROUP BY Country ORDER BY CustomerCount DESC";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();
            ArrayList<CustomerCountry> customersPerCountry = new ArrayList<>();
            while (result.next()) {
                int noOfCustomersInCountry = result.getInt("CustomerCount");
                String country = result.getString("Country");
                CustomerCountry customerCountry = new CustomerCountry(noOfCustomersInCountry, country);
                customersPerCountry.add(customerCountry);
            }
            return customersPerCountry;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.exit(-1);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        return null;

    }

    @Override
    public ArrayList<CustomerPartial> getAll(int limit, int offset) {
        ArrayList<CustomerPartial> customers = new ArrayList<>();
        try {
            conn = ConnectionManager.getInstance().getConnection();
            System.out.println("Connection to SQLite has been established.");

            String sql = "SELECT CustomerId, FirstName, LastName, Country,PostalCode,Phone,Email FROM Customer LIMIT ? OFFSET ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerPartial customer = readCustomerPartial(resultSet);
                customers.add(customer);
            }
            return customers; // success
        } catch (SQLException sqe) {
            System.out.println("Something went wrong...");
            System.out.println(sqe.getMessage());
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }
        }
        return null; // failed
    }

    @Override
    public CustomerPartial getCustomerByName(String name) {

        try {
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM Customer WHERE Customer.FirstName LIKE ? OR Customer.LastName LIKE ? LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
            ResultSet result = statement.executeQuery();

            CustomerPartial customer = null;
            // while Reader.read equivalent
            while (result.next()) {
                customer = readCustomerPartial(result);
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //Close connection
                conn.close();
            } catch (SQLException sqe) {
                System.out.println("Something went wrong while closing the connection.");
                System.out.println(sqe.getMessage());
            }
        }
        return null;
    }
    // endregion ICustomerRepo specific


    /**
     * @param result A ResultSet with data
     * @return A customer read from the provided ResultSet
     */
    private CustomerFull readCustomerFull(ResultSet result) throws SQLException {
        int id = result.getInt("CustomerId");
        String firstName = result.getString("FirstName");
        String lastName = result.getString("LastName");
        String company = result.getString("Company");
        String address = result.getString("Address");
        String city = result.getString("City");
        String state = result.getString("State");
        String country = result.getString("Country");
        String postalCode = result.getString("PostalCode");
        String phoneNo = result.getString("Phone");
        String fax = result.getString("Fax");
        String email = result.getString("Email");
        int supportRepId = result.getInt("SupportRepId");

        return new CustomerFull(id, firstName, lastName, company,
                address, city, state, country, postalCode,
                phoneNo, fax, email, supportRepId);
    }

    /**
     * @param result A ResultSet with data
     * @return A customer read from the provided ResultSet
     */
    private CustomerPartial readCustomerPartial(ResultSet result) throws SQLException {
        int id = result.getInt("CustomerId");
        String firstName = result.getString("FirstName");
        String lastName = result.getString("LastName");
        String country = result.getString("Country");
        String postalCode = result.getString("PostalCode");
        String phoneNo = result.getString("Phone");
        String email = result.getString("Email");

        return new CustomerPartial(id, firstName, lastName,
                country, postalCode,
                phoneNo, email);
    }
}
