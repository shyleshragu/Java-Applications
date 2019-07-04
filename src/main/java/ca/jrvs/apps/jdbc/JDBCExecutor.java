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
            //CustomerDAO customerDAO = new CustomerDAO(connection);
            OrderDAO orderDAO = new OrderDAO(connection);


            //........create
            //Customer customer = new Customer();
            //customer.setFirstName("George"); customer.setLastName("Washington"); customer.setEmail("george@wh.com"); customer.setPhone("(123) 456-7890");
            //customer.setState("Washington"); customer.setCity("DC"); customer.setZipcode("123321"); customer.setAddress("1 random st");
            //customerDAO.create(customer);

            //........read
            //Customer customer = customerDAO.findById(1000);
            //System.out.println(customer.getFirstName() + " " + customer.getLastName());
            Order order = orderDAO.findById(1000);
            System.out.println(order);

            //........update
            //Customer customer = customerDAO.findById(10000);
            //System.out.println(customer.getFirstName()+" "+ customer.getLastName() + " " + customer.getEmail());
            //customer.setEmail("gwash@wh.gov");
            //customer = customerDAO.update(customer);
            //System.out.println(customer.getFirstName()+" "+ customer.getLastName() + " " + customer.getEmail());

            //.........delete
            //Customer customer = new Customer();
            //customer.setFirstName("Delete"); customer.setLastName("Man"); customer.setEmail("deleteman@wh.com"); customer.setPhone("(123) 010-0000");
            //customer.setState("Atlantis"); customer.setCity("Atlantis"); customer.setZipcode("000000"); customer.setAddress("fallen world");
            //Customer dbCustomer = customerDAO.create(customer);
            //System.out.println(dbCustomer);
            //dbCustomer = customerDAO.findById(dbCustomer.getId());
            //System.out.println(dbCustomer);
            //dbCustomer.setEmail("deleddname@sea.com");
            //dbCustomer = customerDAO.update(dbCustomer);
            //System.out.println(dbCustomer);
            //customerDAO.delete(dbCustomer.getId());



        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
