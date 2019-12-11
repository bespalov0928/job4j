package ru.job4j.pojo;

public class Library {

    public static void main(String[] args) {
        Book[] library = new Book[]{
                new Book("Book_1", 10),
                new Book("Book_2", 15),
                new Book("Book_3", 20),
                new Book("Clean Code", 325)};

        for (int index = 0; index < library.length; index++) {
            System.out.println("Title: " + library[index].getTitle() + ". Pages count: " + library[index].getPagesCount());
        }

        Book temp = library[0];
        library[0] = library[3];
        library[3] = temp;

        System.out.println();
        for (int index = 0; index < library.length; index++) {
            System.out.println("Title: " + library[index].getTitle() + ". Pages count: " + library[index].getPagesCount());
        }

        System.out.println();
        for (Book book : library) {
            if (book.getTitle().equals("Clean Code")) {
                System.out.println("Title: " + book.getTitle() + ". Pages count: " + book.getPagesCount());
            }
        }
    }
}
