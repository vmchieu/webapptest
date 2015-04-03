package com.proquest.interview.phonebook;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
        // List of people in the phone book
	public List people;
        // Index of people by name, assuming that the combination of first name and last name is unique
        // If the combination of first name and last name is not unique then using a list of Person for the value of the map
        // Since name seems to be relatively random, the use of HashMap will help find person if the size of the people list is large
        private Map<String, Person> nameIndex;
        
        // Constructor
        PhoneBookImpl () {
             people = new ArrayList();
             nameIndex = new HashMap<>();
        }
	
	@Override
	public void addPerson(Person newPerson) {
		//TODO: write this method
            people.add(newPerson);
            nameIndex.put(newPerson.name, newPerson);
	}
	
	@Override
	public Person findPerson(String firstName, String lastName) {
		//TODO: write this method
             return nameIndex.get(firstName + ' ' + lastName);
	}
        
        @Override
        public String toString() {
            String res = "";
            for (Object p : people) {
                res += p.toString() + "\n";
            }
            return res;
        }
	
	public static void main(String[] args) {
		DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

		/* TODO: create person objects and put them in the PhoneBook and database
		 * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/
                PhoneBookImpl pb = new PhoneBookImpl();
                Person p1 = new Person("John", "Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI");
                Person p2 = new Person("Cynthia", "Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI");
                pb.addPerson(p1);
                pb.addPerson(p2);
		// TODO: print the phone book out to System.out
                System.out.println(pb.toString());
		// TODO: find Cynthia Smith and print out just her entry
                System.out.println(pb.findPerson("Cynthia", "Smith").toString());
		// TODO: insert the new person objects into the database
                try {
			Connection cn = DatabaseUtil.getConnection();
			Statement stmt = cn.createStatement();
                        for (Object p : pb.people) {
                           stmt.execute("INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES('" + ((Person) p).name + "','" + ((Person) p).phoneNumber + "', '" + ((Person) p).address + "')");
                        }
			cn.commit();
			cn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
                
	}
}
