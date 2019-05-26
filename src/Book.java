public class Book {

    public String bookname;
    public String author;

    Book(String bookName, String authorName)
    {
        this.bookname = bookName;
        this.author = authorName;
    }


    public void display() {

        System.out.println("Book name: " + bookname);
        System.out.println("Author: " + author);
    }
}
