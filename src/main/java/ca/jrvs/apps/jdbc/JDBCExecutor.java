package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {

    public static void main(String[] args) {
        ApacheDataSource dcm = new ApacheDataSource("localhost", "hplussport", "postgres", "password");

        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);


            //........create
            //Customer customer = new Customer();
            //customer.setFirstName("George"); customer.setLastName("Washington"); customer.setEmail("george@wh.com"); customer.setPhone("(123) 456-7890");
            //customer.setState("Washington"); customer.setCity("DC"); customer.setZipcode("123321"); customer.setAddress("1 random st");
            //customerDAO.create(customer);

            //........read
            //Customer customer = customerDAO.findById(1000);
            //System.out.println(customer.getFirstName() + " " + customer.getLastName());

            //........update
            Customer customer = customerDAO.findById(10000);
            System.out.println(customer.getFirstName()+" "+ customer.getLastName() + " " + customer.getEmail());
            customer.setEmail("gwash@wh.gov");
            customer = customerDAO.update(customer);
            System.out.println(customer.getFirstName()+" "+ customer.getLastName() + " " + customer.getEmail());



        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
