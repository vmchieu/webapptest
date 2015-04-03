package com.proquest.interview.phonebook;

public class Person {
	public String name;
	public String phoneNumber;
	public String address;
        
        // Constructor
        Person (String firstName, String lastName, String phoneNumber, String address) {
            this.name = firstName + ' ' + lastName;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }
        
        @Override
        public String toString() {
            return name + ", " + phoneNumber + ", " + address;
        }
}
