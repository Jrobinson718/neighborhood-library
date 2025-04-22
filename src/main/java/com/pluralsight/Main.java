package com.pluralsight;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Book[] library = getCurrentLibrary();

    public static void main(String[] args) {
        ShowScreenHome();

    }


    private static Book[] getCurrentLibrary() {
        Book[] library = new Book[20];

        library[0] = new Book(1, "ISBN 978-1-78862-355-1", "The Misadventures of Lauryn Hill");
        library[1] = new Book(2, "ISBN 978-0-316-76948-0", "To Kill a Mockingbird");
        library[2] = new Book(3, "ISBN 978-0-06-112008-4", "1984");
        library[3] = new Book(4, "ISBN 978-0-7432-7356-5", "The Great Gatsby");
        library[4] = new Book(5, "ISBN 978-0-452-28423-4", "Animal Farm");
        library[5] = new Book(6, "ISBN 978-0-553-21311-0", "The Hobbit");
        library[6] = new Book(7, "ISBN 978-0-395-19395-7", "The Catcher in the Rye");
        library[7] = new Book(8, "ISBN 978-0-679-72325-5", "Brave New World");
        library[8] = new Book(9, "ISBN 978-0-141-43957-6", "Lord of the Flies");
        library[9] = new Book(10, "ISBN 978-0-7475-5100-8", "Harry Potter and the Philosopher's Stone");
        library[10] = new Book(11, "ISBN 978-0-545-01022-1", "The Hunger Games");
        library[11] = new Book(12, "ISBN 978-0-316-76948-1", "To Kill a Mockingbird (Special Edition)");
        library[12] = new Book(13, "ISBN 978-0-06-112008-5", "1984 (Anniversary Edition)");
        library[13] = new Book(14, "ISBN 978-0-7432-7356-6", "The Great Gatsby (Illustrated)");
        library[14] = new Book(15, "ISBN 978-0-452-28423-5", "Animal Farm (Revised Edition)");
        library[15] = new Book(16, "ISBN 978-0-553-21311-1", "The Hobbit (Collector's Edition)");
        library[16] = new Book(17, "ISBN 978-0-395-19395-8", "The Catcher in the Rye (Reissue)");
        library[17] = new Book(18, "ISBN 978-0-679-72325-6", "Brave New World (Updated Version)");
        library[18] = new Book(19, "ISBN 978-0-141-43957-7", "Lord of the Flies (School Edition)");
        library[19] = new Book(20, "ISBN 978-0-7475-5100-9", "Harry Potter and the Philosopher's Stone (10th Anniversary)");

        return library;
    }

    public static void ShowScreenHome() {

        String homeScreenPrompt = "Welcome to the library\n" +
                "Please select an option from the following\n" +
                "    1 - Show available books\n" +
                "    2 - Show checked out books\n" +
                "    0 - exit application\n" +
                "(1,2,0): ";

        int option;
        do {
            System.out.print(homeScreenPrompt);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
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
                    System.out.println("Not a valid option, please try again.\n");

            }
        } while (option != 0);

    }

    public static void ShowScreenAvailableBooks(){
        System.out.println("\nCurrently available books:");
        for (Book book : library){

            if(!book.isCheckedOut()){
                System.out.println(book);

            }
        }
    }

    public static void ShowScreenCheckedOutBooks(){
        System.out.println("\nChecked out books:");
        for (Book book : library){

            if (book.isCheckedOut()){
                System.out.println(book +" (Checked out to: " + book.getCheckedOutTo() + ")");

            }
        }
    }
}