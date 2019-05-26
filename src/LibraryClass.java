import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;


public class LibraryClass {


        public static JSONObject readJSON(String fileName)
        {
            JSONParser parser = new JSONParser();

            JSONObject jsonObject = null;
            try (Reader reader = new FileReader(fileName)) {

                jsonObject = (JSONObject) parser.parse(reader);
                System.out.println("Read JSON"+jsonObject);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

        public static void writeJSON(JSONObject jsonObject,String fileName)
        {
            try (FileWriter file = new FileWriter(fileName)) {
                file.write(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Admin admin = new Admin();
            int bookindex = 0;
            String continuechoice;
            //Map<String, Book> books = new HashMap<>();
         //   Map<String,Visitor> visitors=new HashMap<>();
            JSONObject books = readJSON("books.json");
            JSONObject writeJson = new JSONObject();
            JSONObject visitors= readJSON("visitor.json");
            JSONObject writeJsonVisitors=new JSONObject();
            JSONArray jsonArray = (JSONArray) books.get("books");
            JSONArray jsonArrayVisitors=(JSONArray) visitors.get("visitors");
            System.out.println("Enter admin name: ");
            String adminname = sc.nextLine();
            System.out.println("Enter admin password");
            String pwd = sc.nextLine();
            if (admin.ValidateAdmin(adminname, pwd)) {
                do {
                    System.out.println("Enter your choice");
                    System.out.println("1.Add book");
                    System.out.println("2.Display book");
                    System.out.println("3.Lend book");
                    System.out.println("4.Display all the books");
                    System.out.println("5.Books issued list");
                    int choice = sc.nextInt();
                    sc.nextLine();


                    if (choice == 1) {
                        System.out.println("Enter book id:");
                        String id = sc.nextLine();
                        System.out.println("Enter book name");
                        String bookname = sc.nextLine();
                        System.out.println("Enter author name");
                        String author = sc.nextLine();
                        //books.put(id, new Book(bookname, author));
                        JSONObject book = new JSONObject();
//                        book.put("id",id);
//                        book.put("bookName",bookname);
//                        book.put("author",author);
//
//                        jsonArray.add(book);
//
//                        writeJson.put("books",jsonArray);
//
//                        writeJSON(writeJson,"books.json");
                        Statement stmt = null;
                        try {

                            Connection connection = JDBCExample.getInstance().getConnection();
                            connection.setAutoCommit(false);

                            stmt = connection.createStatement();

                            String sql = "INSERT INTO book (id,name,author) "
                                    + "VALUES ('"+id+"', '"+bookname+"','"+author+"');";
                            stmt.executeUpdate(sql);
                            stmt.close();
                            connection.commit();
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        bookindex++;
                    } else if (choice == 2) {
                        System.out.println("Enter book id");
                        String bookid = sc.nextLine();

//
//                        System.out.println("Book name is");
//                        System.out.println(books.getOrDefault(id, new Book(null, null)).bookname);
//                        System.out.println("Author of the book is");
//                        System.out.println(books.getOrDefault(id,new Book(null,null)).author);
                        System.out.println(String.format("%10s %10s %10s", "Book id", "  BookName", "BookAuthor"));
                        /*JSONObject json;
                        for (Object jsonObj : jsonArray ) {
                            json = (JSONObject) jsonObj;
                            if (json.get("id").equals(id)) {
                                System.out.println(String.format("%10s %10s %10s", json.get("id"), json.get("bookName"), json.get("author")));
                            }
                        }*/
                        Statement statement;
                        Connection connection = JDBCExample.getInstance().getConnection();
                        try {
                            connection.setAutoCommit(false);

                            statement = connection.createStatement();
                            String sql = "SELECT * from book where id='"+bookid+"'";

                            ResultSet resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
                                System.out.println(String.format("%10s %10s %10s", resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("author")));
                            }

                            statement.close();
                            connection.commit();
                            connection.close();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                    }


                    else if(choice==3){
                        System.out.println("Enter your name");
                        String visitorname=sc.nextLine();
                        System.out.println("Enter your id");
                        String visitorid=sc.nextLine();
                        System.out.println("Enter the book id");
                        String booktolend=sc.nextLine();
                       // visitors.put(booktolend,new Visitor(visitorid,visitorname));
                       /* JSONObject visitor = new JSONObject();
                        visitor.put("id",visitorid);
                        visitor.put("visitorName",visitorname);
                        visitor.put("booklent",booktolend);*/
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        //Getting current date
                        Calendar cal = Calendar.getInstance();
                        //Displaying current date in the desired format
                        System.out.println("Current Date: "+sdf.format(cal.getTime()));

                        //Number of Days to add
                        cal.add(Calendar.DAY_OF_MONTH, 20);
                        //Date after adding the days to the current date
                        String newDate = sdf.format(cal.getTime());
                        //Displaying the new Date after addition of Days to current date
                        System.out.println("Return Date "+newDate);
                        Statement stmt = null;
                        try {

                            Connection connection = JDBCExample.getInstance().getConnection();
                            connection.setAutoCommit(false);

                            stmt = connection.createStatement();

                            String sql = "INSERT INTO visitor (id,name,bookid,returndate) "
                                    + "VALUES ('"+visitorid+"', '"+visitorname+"','"+booktolend+"','"+newDate+"');";
                            stmt.executeUpdate(sql);
                            stmt.close();
                            connection.commit();
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                     /*   visitor.put("returndate",newDate);
                        jsonArrayVisitors.add(visitor);

                        writeJson.put("visitors",jsonArrayVisitors);

                        writeJSON(writeJson,"visitor.json");*/

                    }
                    else if(choice==4) {
                        System.out.println(String.format("%10s %10s %10s", "Book id", "  BookName", "BookAuthor"));
                   //     JSONObject json;

                        Statement statement;
                        Connection connection = JDBCExample.getInstance().getConnection();
                        try {
                            connection.setAutoCommit(false);

                            statement = connection.createStatement();
                            String sql = "SELECT * from book";

                            ResultSet resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
                                System.out.println(String.format("%10s %10s %10s", resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("author")));
                            }

                            statement.close();
                            connection.commit();
                            connection.close();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


//                        for (String id : books.keySet()) {
//                            System.out.println(String.format("%10s %10s %10s", id, books.get(id).bookname, books.get(id).author));
//                        }
//                    }
         //           for (Object jsonObj : jsonArray ) {
//                        json = (JSONObject) jsonObj;
//                        System.out.println(String.format("%10s %10s %10s", json.get("id"),json.get("bookName"),json.get("author")));
          //          }
                }
                    else if(choice==5){
                        System.out.println(String.format("%10s %10s %10s%10s", "Visitor id", "  Visitor Name", "Book id","Return Date"));
                        Statement statement;
                        Connection connection = JDBCExample.getInstance().getConnection();
                        try {
                            connection.setAutoCommit(false);

                            statement = connection.createStatement();
                            String sql = "SELECT * from visitor";

                            ResultSet resultSet = statement.executeQuery(sql);
                            while (resultSet.next()) {
                                System.out.println(String.format("%10s %10s %10s %10s", resultSet.getString("id"), resultSet.getString("name"), resultSet.getString("bookid"),resultSet.getString("" +
                                        "returndate")));
                            }

                            statement.close();
                            connection.commit();
                            connection.close();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
               /*         JSONObject json;
                        for (Object jsonObj : jsonArrayVisitors) {
                            json = (JSONObject) jsonObj;
                            System.out.println(String.format("%10s %10s %10s %10s", json.get("id"),json.get("visitorName"),json.get("booklent"),json.get("returndate")));
                        }*/
                    }

                System.out.println("Do you want to continue yes or no");
                continuechoice = sc.nextLine();
            } while (continuechoice.equals("yes")) ;
        }
            else {
                System.out.println("Enter correct name and password");
            }



        }


}

