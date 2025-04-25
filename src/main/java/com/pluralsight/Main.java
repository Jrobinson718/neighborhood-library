package com.pluralsight;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {

    private static Console console = new Console();
    private static Book[] library;
    public static String fileName;

    public static void main(String[] args) {

        fileName = "books.txt";
        library = getCurrentLibrary();

        // Begin program by welcoming user and prompting for an input to explore the library
        ShowScreenHome();

    }

    // Initializes a library using the parameters made in my book class

    private static void saveAllDataToDisk(){


        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(fw);

            for (Book b : library){
                writer.write(b.getEncodedText() + "\n");

            }

            writer.close();
        }catch (Exception e){
            System.out.println("There was an ERROR saving the data.");

        }

    }

    private static Book[] getCurrentLibrary() {

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fr);

            Book[] booksTemp = new Book[1000];
            int size = 0;
            String dataString;

            while ((dataString = reader.readLine()) != null){

                booksTemp[size] = getBookFromEncodedString(dataString);

                size++;

            }

            reader.close();

            Book[] booksFinal =Arrays.copyOf(booksTemp, size);

            return booksFinal;
        }catch (IOException e) {
            throw new RuntimeException();

        }


    }

    public static void ShowScreenHome() {

        // Beginning of program prompt. Uses \n to print on a new line
        String homeScreenPrompt = "\nWelcome to the library\n" +
                "Please select an option from the following\n" +
                "    1 - Show available books\n" +
                "    2 - Show checked out books\n" +
                "    0 - exit application\n" +
                "(1,2,0): ";

        int option;
        // Locks the user into the home page until they press 0 and exit the program using a do/while loop
        do {
            option = console.promptForInt(homeScreenPrompt);


            switch (option) {
                case 1:
                    ShowScreenAvailableBooks();
                    break;

                case 2:
                    ShowScreenCheckedOutBooks();
                    break;

                case 0:
                    System.out.println("Exiting library, have a nice day!");
                    break;

                default:
                    System.out.println("Not a valid option, please try again.");

            }
        } while (option != 0);

    }

    private static void ShowScreenAvailableBooks() {
        // Print out each book that is not currently checked out.
        System.out.println("\nCurrently available books:");
        for (Book book : library) {

            if (!book.isCheckedOut()) {
                System.out.println(book);

            }
        }

        //Give the user the ability to pick a book to check out by id.
        int choice = console.promptForInt("\nEnter the book number to check out (0 to cancel): ");

        if (choice > 0 && choice <= library.length) {
            Book selectedBook = library[choice - 1];
            if (!selectedBook.isCheckedOut()) {
                String name = console.promptForString("\nWhat is your name: ");
                selectedBook.checkOut(name); //Assign the users name to getCheckedOutTo()
                System.out.printf("\n%s, You've successfully checked out %s%n", name, selectedBook.getTitle());
                saveAllDataToDisk();

            }
        } else if (choice != 0) {
            System.out.println("Not a valid option, please try again.");

        }

    }

    private static Book getBookFromEncodedString(String encodedBook){

        String[] temp = encodedBook.split(Pattern.quote("|"));

        int id = Integer.parseInt(temp[0]);
        String isbn = temp[1];
        String title = temp[2];

        Book result = new Book(id, isbn, title);

        if(temp.length > 3){
            result.checkOut(temp[3].trim());
        }
        ;
        return result;
    }

    private static void ShowScreenCheckedOutBooks() {
        //Creates variable checked out count to keep count of how many books are nto available
        int checkedOutCount = 0;
        System.out.println("\nChecked out books:");

        // Saves each book one at a time into the variable book to store its information
        for (Book book : library) {

            // Checks to see if the book is checked out and if it is increase checked out count by 1
            if (book.isCheckedOut()) {
                System.out.println(book + " (Checked out to: " + book.getCheckedOutTo() + ")\n");
                checkedOutCount++;

            }
        }

        // Checks to see if the checked out count is greater than 0 before printing an option menu for check in
        if (checkedOutCount > 0) {
            String input = console.promptForString("\nEnter \"C\" to check in a book." +
                    "\nEnter \"X\" to exit back to home screen.\n" +
                    "C  -  Check in\nX  -  Home\n").toUpperCase();
            // Based on user input (C to check in) allows the user to check back in their book and thanks them
            switch (input) {
                case "C":
                    int bookNumber = console.promptForInt("Enter the book number you'd like to check in: ");

                    // Checks to make sure the users input was not negative as well as not greater than the amount of books in the library
                    if (bookNumber > 0 && bookNumber <= library.length) {
                        Book checkedOutBook = library[bookNumber - 1];
                        if (checkedOutBook.isCheckedOut()) {
                            System.out.printf("\nThank you %s, %s has been checked back in.\n",
                                    checkedOutBook.getCheckedOutTo(), // Gets checkedOutTo before checking in the book in order to use the users name without asking for it
                                    checkedOutBook.getTitle());
                            checkedOutBook.checkIn();
                        }
                    }
                case "X":
                    return;

                // Error handling for invalid inputs ( bookNumber >= 21 or <= 0)
                default:
                    System.out.println("Invalid selection.");
            }
        } else {
            System.out.println("\nNo books currently checked out.");
        }
    }


}