/** 
 * - Website Chat Simulator-- Driver.java
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
 *               -Public Static Methods-
 *               1. the <main> method does not utilize any command-line arguments and 
 *                  begins the program for this package.
 *               2. <sendChatMessage> receives a specific Website class object as argument 'w' 
 *                  and calls w.getPersonByUID() to confirm the UIDs received from the user
 *                  represent Person objects contained within 'w'. Class method w.sendMessage()
 *                  is called if the UID for both the sender AND receiver is not null.                  
 *               3. <addPeopleTo> automated process for adding hard coded Person class objects 
 *                  to a specified instance of Website. 
 *               4. <printChatHistory> prompts the user to enter the UID for messages sent 
 *                  between a sender and receiver and stored within on a specific Website object.
 *               5. <handlePrintMessages> prints out all the chat messages stored on a specific
 *                  Website object between two Person objects.
 *
 *
 * 
 * @author Will Oprisko
 * 
 * @version 2021-03-07
 * 
 * @experimenting -- 1) utilizing static variables to improve readibility, 
 *                      debugging, and code maintainence.
 */

/**/ package chatsim;  /**/
/**  import chatsim.*; /**/

import java.util.Scanner;


public class Driver
{
    public static final String UID_NOT_FOUND = "Person with UID == %d not found%n";

    public static void main (String[] args)
    {        
        boolean run  = true;
        String  userInput = "";

        Scanner s = new Scanner(System.in);
        Website w = new Website();
        
        // Add a predetermined list of Person objects to a specified Website object
        addPeopleTo(w);                     
        
        // User input is stored as String to prevent the program from a fatal exception error 
        // if the user makes a mistake when typing the UID
        do
        {
            System.out.print("Type 'quit' to exit, anything else to keep going: ");
            userInput = s.nextLine();

            if (!userInput.equals("quit"))
            {
                System.out.print("Enter the sender's UID, or -1 to view chat history: ");
                userInput = s.nextLine();
            }
    
            switch(userInput)
            {
                case "quit":
                    run = false;
                    break;
                case "-1":
                    printChatHistory(s, w);
                    break;
                default:
                    sendChatMessage(userInput, s, w);                                     
                    break;                
            }
        } while (run);
    }

    // UI for sending a creating and storing a Message object within a specific
    // Website object, if the Website object contains two Person objects
    // associated with the IUDs entered by the user
    public static void sendChatMessage(String userInput, Scanner s, Website w)
    {
        int uid1 = 0;
        int uid2 = 0;
        
        Person sender   = null;
        Person receiver = null;
        String message  = null;
        
        try 
        { 
            uid1   = Integer.parseInt(userInput);                     
            sender = w.getPersonByUID(uid1);            

            if (sender == null)     
                { System.out.printf(UID_NOT_FOUND, uid1); }
            else
            {        
                System.out.print("Enter the receiver's UID: ");
                userInput = s.nextLine();
                uid2 = Integer.parseInt(userInput);
                receiver = w.getPersonByUID(uid2);                
            }
                  

            if (receiver == null && sender != null)   
                { System.out.printf(UID_NOT_FOUND, uid2); }
            else if (receiver != null)
            {
                System.out.print("Enter the message text being sent: ");
                message = s.nextLine();
                w.sendMessage(sender, receiver, message); 
            }
        }
        catch (Exception e) { System.out.println(e); }
    }


    public static void addPeopleTo(Website w)
    {
        Person TA = new Person(1001, "255", "TA");
        w.addPerson(TA);

        // Add several other people to the website.
        w.addPerson(new Person(1002, "Jane", "Smith"));
        w.addPerson(new Person(1003, "Tyrion", "Lannister"));
        w.addPerson(new Person(1004, "Frodo", "Baggins"));
    }


    public static void printChatHistory(Scanner s, Website w)
    {
        System.out.print("Enter the first person's UID: ");
        int firstUID = s.nextInt();

        s.nextLine();  // clear the carriage return

        System.out.print("Enter the second person's UID: ");
        int secondUID = s.nextInt();

        s.nextLine();  // clear the carriage return

        handlePrintMessages(w, firstUID, secondUID);
    } 

    // Website.getChatHistory() returns a Message array containing the messages between
    // the Person with firstUID and the Person with secondUID.
    // Messages are store in sequential order based upon the time they were sent by the website.
    public static void handlePrintMessages(Website w, int firstUID, int secondUID)
    {        
        Person a = w.getPersonByUID(firstUID);
        Person b = w.getPersonByUID(secondUID);
        Message[] messages = w.getChatHistory(firstUID, secondUID);
        
        if (a == null)
        {
            System.out.println("Person with uid "+firstUID+" not found, can't print message history");
        }
        else if (b == null)
        {
            System.out.println("Person with uid "+secondUID+" not found, can't print message history");
        }
        else if (messages.length == 0)
        {
            System.out.println("NO MESSAGE HISTORY FOUND FOR THESE TWO USERS.");
        }        
        else
        {
            System.out.println("Showing chat history between ["+a.getFullName()+"] and ["+b.getFullName()+"]");
            for(int i = 0; i < messages.length; i+=1)
            {
                System.out.println("At (" + messages[i].covertEpochToDate()+"), "+
                                    messages[i].getSender().getFullName()+" said: \"" +
                                    messages[i].getMessage() + "\"");
            }
        }
    }   
}
