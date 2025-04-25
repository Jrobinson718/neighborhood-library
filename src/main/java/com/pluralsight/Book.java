package com.pluralsight;

public class Book {

    private int id;
    private String isbn;
    private String title;
    private boolean isCheckedOut;
    private String checkedOutTo;

    public Book(int id, String isbn, String title) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.isCheckedOut = false;
        this.checkedOutTo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public String getCheckedOutTo() {
        return checkedOutTo;
    }



    public void checkOut(String name){
        this.isCheckedOut = true;
        this.checkedOutTo = name;

    }

    public void checkIn(){
        this.isCheckedOut = false;
        this.checkedOutTo = "";

    }

    @Override
    public String toString(){
        return String.format("%-5d %-60s %21s", id, title, isbn);

    }

    public String getEncodedText(){
//        String result;
//        if(this.isCheckedOut){
//            result = ;
//        }else {
//            result = this.id + "|" + this.isbn + "|" + this.title;
//        }
        return this.id + "|" + this.isbn + "|" + this.title + (this.isCheckedOut ? "|" + this.checkedOutTo : "");
    }

}