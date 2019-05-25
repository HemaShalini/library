public class book {
    int bookid;
    public String bookname;
    public String author;

    public void createbookdetails(int id, String book, String authorname) {
        this.bookid = id;
        this.bookname = book;
        this.author = authorname;
    }

    public void display() {
        System.out.println("Book id: " + bookid);

        System.out.println("Book name: " + bookname);
        System.out.println("Author: " + author);
    }
}
