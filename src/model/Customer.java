package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName, lastName, email;
    private final String emailRegEx = "^(.+)@(.+)\\.com$";


    public Customer (String firstName, String lastName, String email) {
        try{
            if (!Pattern.compile(emailRegEx).matcher(email).matches()) {
                throw new IllegalArgumentException("Error, Invalid Email");
            }
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;

        } catch(IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email==null)? 0 : email.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass()!=obj.getClass()) return false;

        Customer customer = (Customer) obj;
        return email.equals(customer.getEmail());

    }

    @Override
    public String toString(){
        return "First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email + "\t";
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
