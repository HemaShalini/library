import java.util.Scanner;



    public class libraryclass {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            admin Admin = new admin();
            int bookindex = 0;
            String continuechoice;
            book[] books = new book[4];
            for(int index=0;index<4;index++){
                books[bookindex]=new book();
            }
            do {
                System.out.println("Enter your choice");
                System.out.println("1.Add book");
                System.out.println("2.Display book");
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter admin name: ");
                String adminname = sc.nextLine();
                System.out.println("Enter admin password");
                String pwd = sc.nextLine();

                if (Admin.ValidateAdmin(adminname, pwd)) {
                    if (choice == 1) {
                        System.out.println("Enter book id:");
                        int id=Integer.valueOf(sc.nextLine());
                        System.out.println("Enter book name");
                        String book=sc.nextLine();
                        System.out.println("Enter author name");
                        String author=sc.nextLine();
                        books[bookindex].createbookdetails(id,book,author);
                        bookindex++;
                    } else if (choice == 2) {
                        System.out.println("Enter book id");
                        int id = sc.nextInt();
                        boolean bookfound = false;
                        for (int index = 0; index < bookindex; index++) {
                            if (books[bookindex].bookid != id) {
                                continue;
                            }
                            System.out.println("Book name is");
                            System.out.println(books[bookindex].bookname);
                            System.out.println("Author of the book is");
                            System.out.println(books[bookindex].author);
                            bookfound = true;
                            break;
                        }
                        if (!bookfound) {
                            System.out.println("No such book found");
                        }

                    } else {
                        System.out.println("Enter a valid choice");
                    }
                } else {
                    System.out.println("Enter correct name and password");
}               System.out.println("Do you want to continue yes or no");
                continuechoice = sc.nextLine();
            } while (continuechoice.equals("yes"));
        }
    }


