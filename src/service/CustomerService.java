package service;

import model.Customer;

import java.util.Collection;
import java.util.HashSet;


public final class CustomerService {
    //Singleton declaration
    private static CustomerService CUSTOMERSERVICEINSTANCE;
    private CustomerService(){}
    public static CustomerService getInstance(){
        if (CUSTOMERSERVICEINSTANCE == null)
            CUSTOMERSERVICEINSTANCE = new CustomerService();
        return CUSTOMERSERVICEINSTANCE;
    }

    //Collection variable
    public Collection<Customer> customers = new HashSet<>();

    //Method implementation
    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName, email);
        if (customer.getEmail() == null)
            return;

        if (!customers.add(customer)){
            System.out.println("Email has been used");
        }
        else
            System.out.println("Account create successfully");
    }

    public Customer getCustomer(String customerEmail){
        if (!customers.isEmpty())
            for (Customer cus: customers) {
                if (cus.getEmail().equals(customerEmail))
                    return cus;
            }
        return null;
    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }

}
