/** 
 * - Website Chat Simulator -- Message.java
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

public class Message
{
    // Refactoring required to implment 'final' keyword
    private Person sender;
    private Person receiver;
    private String message;
    private long   timestamp;    //changed variable name from 'whenSent'


    // Removed timestamp argument to prevent receiving an invalid or inaccurate
    // time value
    public Message(Person sender, Person receiver, String message)
    {
        try
        {
            setSender(sender);
            setReceiver(receiver);

            preventSelfMessage(sender, receiver);

            setMessage(message);
            setTimestamp(timestamp);
        }
        catch (IllegalArgumentException e)
        {
            // Message objects are created with default values when an exception is thrown.
            // This allows for a program and/or user to make a mistake without causing a 
            // fatal error AND preventing the creation of an Message object with partially 
            // correct information
            this.sender    = null;
            this.receiver  = null;
            this.message   = null;
            this.timestamp = 0;

            System.out.println(e);
        }
    }

    private void setSender (Person sender) 
    { 
        if (sender == null)
            { throw new IllegalArgumentException("message sender cannot be null"); }
        else
            { this.sender = sender; }
    }

    private void setReceiver (Person receiver)
    {
        if (receiver == null)
            { throw new IllegalArgumentException("message receiver cannot be null"); }
        else
            { this.receiver = receiver; }
    }

    private void setMessage(String message)
    {
        if (message == null || message.equals(""))
            { throw new IllegalArgumentException("cannot send an empty message"); }
        else
            { this.message = message; }
    }

    private void setTimestamp(long timestamp)
    {
        this.timestamp = System.currentTimeMillis();
    }

    private void preventSelfMessage(Person sender, Person receiver)
    {
        if (sender.getUID() == receiver.getUID())
        {
            throw new IllegalArgumentException("sender and receiver cannot share the same UID");
        }
    }

    public Person getSender()   { return this.sender;   }

    public Person getReceiver() { return this.receiver; }

    public String getMessage()  { return this.message;  }

    public long getTimestamp()  { return this.timestamp;}


    public String covertEpochToDate()       
    {
    // This is a fancy way of printing the day and time using java.time.* classes;
    // We have not seen this in class.
        return (java.time.LocalDateTime.ofEpochSecond(this.getTimestamp()/1000L, 0, 
            java.time.ZoneOffset.UTC).format(java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss"))); 
    }
}
