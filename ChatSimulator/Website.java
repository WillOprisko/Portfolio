/** 
 * - Website Chat Simulator -- Website.java
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

import java.util.ArrayList;

public class Website
{
    private Person []    users;
    private Message[] messages;

    public Website()
    {
        users    = new  Person[0];
        messages = new Message[0];
    }

    public void addPerson(Person p)
    {        
        // Prevent null Person objects
        // Prevent adding the same Person more than once        
        if (p != null && this.getPersonByUID(p.getUID()) == null) // Consider creating a checkPerson() method
        {
            Person tempArray[] = new Person[users.length + 1];

            for(int i = 0; i < users.length; i++)
            {
                tempArray[i] = users[i];
            }
        
            tempArray[tempArray.length - 1] = p;
            users = tempArray;
        }
        else if (p == null)
        {
            System.out.println("Error: Person object is null. Cannot add to Website.");
        }
        else if (this.getPersonByUID(p.getUID()) != null)
        {
            System.out.println("Error: user id already assigned");
        }
    }


    // Access permission set to 'private' to prevent external call to add message to website
    // adding the message should be controlled by the website
    private void addMessage(Message m)
    {
        Message tempArray[] = new Message [messages.length + 1];
        // TODO - decide how to store m, so that you can later retrieve the
        // right ones in getMessagesFor().

        for (int i = 0; i < messages.length; i++)
        {
            tempArray[i] = messages[i];
        }

        tempArray[tempArray.length - 1] = m;
        messages = tempArray;
    }

    // overload sendMessage(Message m)
    // Not recommended
    public void sendMessage(Message m)
    {
        if (validateMessage(m))     { this.addMessage(m); }
    }

    // Moved 'sendMessageTo' method from class Person to class Website
    // Renamed 'sendMessage'
    // calculate time within message method
    public void sendMessage(Person sender, Person receiver, String message)
    {
        Message m = new Message(sender, receiver, message);
        //long timestamp = System.currentTimeMillis();
        //Message m = new Message(sender, receiver, message, timestamp);
        
        if (validateMessage(m))     { this.addMessage(m); }               
    }


    private boolean validateMessage(Message m)
    {
        boolean validated = false;
        Person  sender    = m.getSender();
        Person  receiver  = m.getReceiver();
        String  message   = m.getMessage();

        // If message is null, then sender and receive is null
        
        // Prevent calling methods on null messages
        if (message == null || message.equals(""))
        {
            // Error message handled within the Message class
        }
        else if (this.getPersonByUID(sender.getUID()) == null)
        {
            System.out.println("Error: sender is not registered for this website");
        }
        else if (this.getPersonByUID(receiver.getUID()) == null)
        {
            System.out.println("Error: receiver is not registered for this website");
        }
        else
        {
            validated = true;
        }

        return validated;
    }

    public Person getPersonByUID(int uid)
    {
        Person queriedUser = null;

        for (Person p : users)
        {
            if (p.getUID() == uid)
            {
                queriedUser = p;
                break;
            }
        }
        return queriedUser;
    }


    // Renamed 'getMessagesUID'
    public Message[] getMessageHistory(int uid)
    {
        // Default constructor creates 10 elements
        // Specified 0 elements
        Message[] messageHistory;
        ArrayList<Message> arrayList = new ArrayList<Message> (0);
        for (Message m : messages)
        {
            if (m.getSender().getUID() == uid || m.getReceiver().getUID() == uid)
            {
                arrayList.add(m);
            }
        }


        messageHistory = new Message[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            messageHistory[i] = arrayList.get(i);
        }

        return messageHistory;
    }


    // Renamed 'getMessagesUID'
    public Message[] getChatHistory(int uid1, int uid2)
    {
        Message[] chatHistory;

        // Default ArrayList constructor creates an object with 10 elements
        // Specified 0 to ensure the ArrayList object size matches the number
        // of stored Message objects
        ArrayList<Message> arrayList = new ArrayList<Message> (0);
        for (Message m : messages)
        {
            if ((m.getSender().getUID() == uid1 && m.getReceiver().getUID() == uid2) ||
                (m.getSender().getUID() == uid2 && m.getReceiver().getUID() == uid1))
            {
                arrayList.add(m);
            }
        }

        chatHistory = new Message[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++)
        {
            chatHistory[i] = arrayList.get(i);
        }

        return chatHistory;
    }

    /** For Testing and Debugging Purposes Only //
    public void printAllMessages()
    {
        for(Message m : messages)
        {
            System.out.println(m.getSender().getFirstName() + " sent " + m.getReceiver().getFirstName() + " " + m.getMessage());
        }
    }
    // Remove code for implementation /**/


    /** For Testing and Debugging Purposes Only //
    public void printAllUsers()
    {
        for(Person p : users)
        {
            System.out.println(p.getUID() + "  " + p.getFirstName() + " " + p.getLastName());
        }
    }
    // Remove code for implementation /**/

}
