/** 
 * - Website Chat Simulator -- Person.java
 * 
 * A console program that simulates a simple chat engine allowing two website 
 * users to privately send and receive text messages. The user is prompted 
 * to enter a command to exit or continue, provide their user id, the id of the
 * specific user they would like to message, and the message to send. Users can
 * also enter a command to print the chat history in between any two users
 *
 * 
 * Goals:
 * 1. Design and build multiple classes that will call and interact with one another. 
 * 2. Implement OOP principles of encapsulation in each class by privitizing 
 *    class fields and properties, and including the associated getter and setter.
 * 3. Implement OOP principles of inheritance, composition, and constructor and/or 
 *    method overloading when appropriate. 
 * 4. Design class methods around a single-purpose for unit testing, debugging, and 
 *    code readibility.
 *
 * 
 * @author Will Oprisko
 * 
 * @version 2021-03-07
 * 
 */
package chatsim;

public class Person
{
    private int    uid       =  0;
    private String firstName = "UNKNOWN USER";
    private String lastName  = "";

    // Constructor if no name is provided; not recommended for implementation
    // Provided if the application allows for annoymous users
    public Person(int uid)
    {
        setUID(uid);
    }

    // Constructor if only one name is provided; for applications permitting
    // users to enter their name as a single string
    public Person(int uid, String first)
    {
        setUID(uid);
        setFirstName(first);
    }

    // Preferred constructor; full implementation of the class
    // Associates a User ID with a First and Last name
    public Person(int uid, String first, String last)
    {
        setUID(uid);
        setFirstName(first);
        setLastName(last);
    }


    // Prevent the User ID from being changed by an external source
    // If an application needs to change the UID associated with a user,
    // then a new Person object must be created.

    // IMPORTANT: Method does not prevent assigning the same User ID to
    //            multiple Person objects. Error checking must be done 
    //            by the invoking class or class method.
    private void setUID(int uid)
    {
        if (uid > 0) { this.uid = uid; }
        else { throw new IllegalArgumentException("UID cannot be less than 1"); }
    }

    public void setFirstName(String first) { this.firstName = first; }

    public void setLastName (String last)  { this.lastName  = last; }


    public String getFirstName() { return firstName; }

    public String getLastName()  { return lastName; }

    public int    getUID()       { return uid; }

    public String getFullName()  { return firstName + " " + lastName; }
   
}

// Moved 'sendMessageTo' method to class Website


