/** 
 * Lab2 - Fun with Strings
 * 
 * A console program that receives user input to make alterations 
 * to a pre-determined string or to enter a new string. If the user 
 * enters an unrecognized command or does not provide the correct number
 * of command arguments, an error message is printed to the console
 * along with a description of each available command and associated
 * argument(s).
 *
 * 
 * Goals:
 * 1. Write a program that accepts text-based commands through the console.
 * 2. Entering the correct or incorrect command/argument combination 
 *    has an effect within the program.
 * 3. Explore various methods available within java.lang.String
 *    and conditional operators.
 * 4. Explore how to build and call a general method within other methods
 * 4. Test if the program works correctly 
 * 
 * @author Will Oprisko, 25501-OL1 
 * 
 * @version 2021-02-19
 * 
 * @experimenting -- 1) modifying and condensing starter code 
 *                   2) improving software engineering design and maintainence 
 *                      with StringBuilder
 *                   3) decreasing method variables by utilizing internal logic
 *                   4) learning how to search for and remove text within a 
 *                      String without using the <string>.removeAll() method.
 */

import java.util.Scanner;

public class StringFunLab
{
  public static final String START_STRING = "abc def";

  // Reusable error messages for multiple class methods with similar
  // command requirements.
  public static final String ERROR_MISSING_COMMAND = "ERROR: missing command(s)";
  public static final String ERROR_INDEX_RANGE     = "ERROR: index out of range";


  // Moved the main Method to the beginning of the program to clarify the 
  // program structure and simplify debugging logic structure or code implementation
  public static void main(String args[])
  {
    String theString  = START_STRING;
    boolean run       = true;

    // Main body of the program does not begin until a Scanner object is created
    // and initialized
    Scanner s = new Scanner(System.in);
    do
    {
      System.out.print("Enter Command: ");
      String line = s.nextLine();
      String[] commandWords = line.split(" ");

      /**
        * Replaces 'handleStringCommand' method in the starter code for the
        * following reasons:
        * 1. Relocates the core program logic with the main method
        * 2. Explicity displays the link between the conditional-loop and user input.
        * 3. Replaces repeated '<string>.equals(commandWords[0])' with the built-in
        *    switch statement feature for evaluating string expressions.
        * 4. Decreases code statement length and condenses total lines of code used 
        *    to implement the if-then-else conditional structure.
        * 5. Improves readability for designing and debugging the program structure. 
        */
      switch(commandWords[0])
      {
        case "searchText":
          theString = searchText(theString, commandWords);
          break;
        case "removeText":
          theString = removeText(theString, commandWords);
          break;
        case "addText":
          theString = addText(theString, commandWords);
          break;
        case "reverseText":
          theString = reverseText(theString, commandWords);
          break;
        case "reverseEachWord":
          theString = reverseEachWord(theString, commandWords);
          break;
        case "printString":
          theString = printString(theString, commandWords);
          break;
        case "enterNewString":
          theString = enterNewString(theString, commandWords);
          break;
        case "quit":
          run = false;
          break;
        default:
          printHelp("Unknown command: " + commandWords[0]);
      }

      if (theString == null) { theString = START_STRING; }

    } while(run == true);
  }
    

  public static String searchText(String oldString, String[] commandWords)
  {
    String searchString = new String("");
    boolean found = false;
    int indexEnd  = 0;

    // Check if the length of commandWords is less than 2
    if (commandWords.length < 2)
    {
      //print an error using printHelp and return null
      printHelp(ERROR_MISSING_COMMAND);
      return null;
    } 
    else
    {
      // Use combineWordsFrom(commandWords, 1) in order to obtain the text 
      // to search for within oldString
      searchString = combineWordsFrom(commandWords, 1);
      indexEnd = searchString.length();
    }


    for (int i = 0; i < oldString.length() && i+indexEnd <= oldString.length(); i++)
    {
      if(oldString.substring(i, i+indexEnd).equals(searchString))
      {        
        // Print out the index where the text is found in oldString
        System.out.println(i);
        found = true;
      }
    }

    // Print -1 if the text could not be found within oldString
    if (found == false) { System.out.println(-1); }

    // Return null since searchText does not change the current string.
    return null;
  }


  public static String removeText(String oldString, String[] commandWords)
  {
    String deleteString = new String("");
    String editedString = new String(oldString);

    int indexEnd = 0;
    int i = 0;

    // Check if the length of commandWords is less than 2
    if (commandWords.length < 2)
    {
      // Print an error using printHelp and return null
      printHelp("removeText - requires an argument");
      return null;
    } 
    else
    {
      // Use combineWordsFrom(commandWords, 1) in order to obtain the text 
      // to search for and delete within oldString
      deleteString = combineWordsFrom(commandWords, 1);
      indexEnd = deleteString.length();
    }

    // @experimenting: Learning how to search for and remove text within String by 
    // avoiding the use of <string>.replaceAll(<string>,"") in order to increase
    // understanding of the Java programming language, algorithm design, and 
    // software engineering techniques. 
    while (i < editedString.length() && i+indexEnd <= editedString.length())
    {
      if(editedString.substring(i, i+indexEnd).equals(deleteString))
      {        
        editedString = editedString.substring(0, i) + editedString.substring(i+indexEnd, editedString.length());
        i = 0;
      }
      else
      {
        i++;
      }
    }

    // Return the oldString along with any edits
    return editedString;
  }


  public static String addText(String oldString, String[] commandWords)
  {
    String insertString = new String("");
    String returnString = new String("");

    int index = 0;
    if (commandWords.length < 3)
    {
      printHelp(ERROR_MISSING_COMMAND);
      return null;
    }
    else 
    {
      index = Integer.parseInt(commandWords[1]);
    }
    
    if (index <= 0 || index > oldString.length())
    {
      printHelp("addText - invalid index (must be int between 0 and string length): -1");
      return null;
    }
    else 
    {
      insertString = combineWordsFrom(commandWords, 2);
      returnString = oldString.substring(0, index);
      returnString += insertString + oldString.substring(index); 
    }

    return returnString;
  }


  public static String reverseEachWord(String oldString, String[] commandWords)
  {
    String[] stringArray = oldString.split(" ");    
    String returnString = new String();
    
    for(String string : stringArray)
    {
      returnString += reverseString(string);
      returnString += " ";
    }   

    return returnString.strip();
  }


  public static String reverseText(String oldString, String[] commandWords)
  {
    // This function is already completed, as long as reverseString is correctly implemented.
    // Note: It was a design choice to not error out if arguments were erroneously included.
    return reverseString(oldString);
  }


  private static String reverseString(String s)
  {
    StringBuilder string = new StringBuilder(s);
    return string.reverse().toString();
  }

  

  // Implements the "printString" command.  Does not change theString, return what was passed.
  public static String printString(String oldString, String[] commandWords)
  {
    System.out.println(oldString);
    return oldString;
  }


  /**
    * Implements the "enterNewString" command.  You probably want to create a similar function 
    * for each string command.  Note that ‘oldString‘ is ignored by this particular 
    * command/function.
    */
  public static String enterNewString(String oldString, String[] commandWords)
  {
    if (commandWords.length < 2)
    {
        printHelp("enterNewString - requires an argument");
        return null;
    }
    return combineWordsFrom(commandWords, 1);
  }


  /**
    * Prints the provided ‘errorMessage‘, then reminds the  user what all the commands 
    * are and how to use them. You do not need to edit this function.
    */
  public static void printHelp(String errorMessage)
  {
    System.err.println(errorMessage);
    System.err.println("Usage - enter one of the following Commands:");
    System.err.println();
    System.err.println("searchText [text]: prints the index of [text] in "
                        + "the String, leaves it unchanged.");
    System.err.println("removeText [text] - deletes all occurences of "
                        + "[text] in the String.");
    System.err.println("addText [i] [text] - first argument is an integer "
                        + "between 0 and and the length of the string; "
                        + "adds [text] at that location in the string.");
    System.err.println("reverseText [no argument] - makes the string into "
                        + "its mirror image.");
    System.err.println("reverseEachWord [no argument] - like reverseText "
                        + "but applies to each command word individually.");
    System.err.println("printString [no argument] - prints the current "
                        + "value of the string.");
    System.err.println("enterNewString [text] - overwites whatever the "
                        + "string was with [text] instead.");
    System.err.println("quit [no argument] - exits the program.");
    System.err.println("");
  }
    

   
  /**
    * A useful function, combines all of the words in ‘words‘ starting from
    * ‘index‘.  You do not need to edit this function.
    */
  private static String combineWordsFrom(String[] words, int index)
  {
    String newString = "";
    for(int i = index; i < words.length; i+=1)
    {
      if(i > index)
      {
        newString += " ";
      }

      newString += words[i];
    }

    return newString;
  }
}











