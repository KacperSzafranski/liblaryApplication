/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liblary_app;

import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
//import javax.swing.table.DefaultTableModel;

public class Datasource {
    
    public static final String DB_NAME = "LiblaryUserdb.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:/C:\\Users\\kacpe\\Documents\\NetBeansProjects\\liblaryApplication" + DB_NAME;
    
    public static final String TABLE_USERS = "LiblaryUser";
    public static final String COLUMN_USERS_ID = "_id";
    public static final String COLUMN_USERS_LOGIN = "login";
    public static final String COLUMN_USERS_PASSWORD= "password";
    public static final String COLUMN_USERS_NAME= "name";
    public static final String COLUMN_USERS_SURNAME= "surname";
    
    public static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " (" + COLUMN_USERS_ID + " INTEGER, " + COLUMN_USERS_LOGIN + " TEXT, " + COLUMN_USERS_PASSWORD + " TEXT, " 
            + COLUMN_USERS_NAME + " TEXT, " + COLUMN_USERS_SURNAME + " TEXT, PRIMARY KEY(" + COLUMN_USERS_ID + "))";
    
    public static final int INDEX_USERS_ID = 1;
    public static final int INDEX_USERS_LOGIN = 2;
    public static final int INDEX_USERS_PASSWORD = 3;
    
    public static final String TABLE_BOOKS = "Books";
    public static final String COLUMN_BOOKS_ID = "_id";
    public static final String COLUMN_BOOKS_TITLE = "title";
    public static final String COLUMN_BOOKS_AUTHOR = "author";
    public static final String COLUMN_BOOKS_STATUS = "status";
    
    public static final String CREATE_BOOKS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BOOKS + " (" + COLUMN_BOOKS_ID + " INTEGER, " + COLUMN_BOOKS_TITLE + " TEXT, " + COLUMN_BOOKS_AUTHOR + " TEXT, " 
            + COLUMN_BOOKS_STATUS + " INTEGER, PRIMARY KEY(" + COLUMN_BOOKS_ID + "))";
    
    public static final String TABLE_BORROWED = "Borrowed";
    public static final String COLUMN_BORROWED_ID = "_id";
    public static final String COLUMN_BOOK_ID = "bookID";
    public static final String COLUMN_USER_ID = "userID";
    
    public static final String CREATE_BORROWED_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BORROWED + "( " + COLUMN_BORROWED_ID + " INTEGER, " + COLUMN_BOOK_ID + " INTEGER, " + COLUMN_USER_ID + " INTEGER, "
            + "PRIMARY KEY(" + COLUMN_BORROWED_ID + "))";
    
    public static final String QUERY_LOGIN_USERS = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_LOGIN + " = ? AND " + COLUMN_USERS_PASSWORD + " = ?";
    public static final String INSERT_USER = "INSERT INTO " + TABLE_USERS +
            '(' + COLUMN_USERS_LOGIN + ", " + COLUMN_USERS_PASSWORD + ", " + COLUMN_USERS_NAME + ", " + COLUMN_USERS_SURNAME +  ") VALUES(?,?,?,?)";
    public static final String INSERT_BOOK = "INSERT INTO " + TABLE_BOOKS +
            '(' + COLUMN_BOOKS_TITLE + ", " + COLUMN_BOOKS_AUTHOR + ", " + COLUMN_BOOKS_STATUS +  ") VALUES(?,?,1)";
    public static final String QUERY_BOOKS = "SELECT DISTINCT " + COLUMN_BOOKS_TITLE + " FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOKS_TITLE + " LIKE ?" + " AND " + COLUMN_BOOKS_STATUS + " = ?";
    public static final String QUERY_BOOKS_ID = "SELECT DISTINCT " + COLUMN_BOOKS_ID + " FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOKS_TITLE + " LIKE ? AND " + COLUMN_BOOKS_STATUS + " = 1" ;
    public static final String INSERT_BORROWED_BOOK = "INSERT INTO " + TABLE_BORROWED +" (" + COLUMN_BOOK_ID + ", " + COLUMN_USER_ID + ") VALUES (?,?)";
    public static final String UPDATE_BOOK_STATUS = "UPDATE " + TABLE_BOOKS + " SET " + COLUMN_BOOKS_STATUS + " = ? WHERE " + COLUMN_BOOKS_ID + " = ?";
    public static final String USER_BOOKS_TITLE = "SELECT " + TABLE_BOOKS + "." + COLUMN_BOOKS_TITLE + " , " + TABLE_BOOKS + "." + COLUMN_BOOKS_ID + " FROM ((" + TABLE_BOOKS + " INNER JOIN " + TABLE_BORROWED + " ON " 
            + TABLE_BOOKS + "." + COLUMN_BOOKS_ID + " = " + TABLE_BORROWED + "." + COLUMN_BOOK_ID + ") INNER JOIN " + TABLE_USERS + " ON " + TABLE_USERS + "." + COLUMN_USERS_ID + " = " + TABLE_BORROWED + "." 
            + COLUMN_USER_ID + ") WHERE " + TABLE_BORROWED + "." + COLUMN_USER_ID + " = ?";
    public static final String QUERY_BORROWED_BOOK = "SELECT " + TABLE_USERS + "." + COLUMN_USERS_NAME + ", " + TABLE_USERS + "." + COLUMN_USERS_SURNAME + " , " + TABLE_BOOKS + "." + COLUMN_BOOKS_TITLE + " FROM ((" + TABLE_USERS
            + " INNER JOIN " + TABLE_BORROWED + " ON " + TABLE_USERS + "." + COLUMN_USERS_ID + " = " + TABLE_BORROWED + "." + COLUMN_USER_ID + ") INNER JOIN " + TABLE_BOOKS + " ON " + TABLE_BOOKS + "." + COLUMN_BOOKS_ID
            + " = " + TABLE_BORROWED + "." + COLUMN_BOOK_ID + ")";
    public static final String SELECT_BORROWED_TITLE = "SELECT " + TABLE_BOOKS + "." + COLUMN_BOOKS_TITLE + " FROM " + TABLE_BOOKS + " INNER JOIN " + TABLE_BORROWED + " ON " + TABLE_BORROWED + "." + COLUMN_BOOK_ID + " = " + TABLE_BOOKS + "." + COLUMN_BOOKS_ID
            + " AND " + TABLE_BORROWED + "." + COLUMN_USER_ID + " =  ?";
    public static final String SELECT_BORROWED_ID = "SELECT " + TABLE_BOOKS + "." + COLUMN_BOOKS_ID + " FROM " + TABLE_BOOKS + " INNER JOIN " + TABLE_BORROWED + " ON " + TABLE_BOOKS + "." + COLUMN_BOOKS_ID + " = " + TABLE_BORROWED + "." + COLUMN_BOOK_ID + " WHERE "
            + TABLE_BORROWED + "." + COLUMN_USER_ID + " = ? AND " + TABLE_BOOKS + "." + COLUMN_BOOKS_TITLE + " = ?";
    public static final String DELETE_BORROWED_BOOK = "DELETE FROM " + TABLE_BORROWED + " WHERE " + TABLE_BORROWED + "." + COLUMN_BOOK_ID + " = ?";
    
    private PreparedStatement queryLoginUsers;
    private PreparedStatement insertIntoUsers;
    private PreparedStatement insertIntoBooks;
    private PreparedStatement querySBooks;
    private PreparedStatement querySBooksID;
    private PreparedStatement insertIntoBorrowed;
    private PreparedStatement updateStatus;
    private PreparedStatement queryUserBooks;
    private PreparedStatement borrowedTitle;
    private PreparedStatement borrowedID;
    private PreparedStatement returnBook;
    
    private Connection conn;
    
    
    
    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            
            Statement statement = conn.createStatement();
            statement.execute(CREATE_USERS_TABLE);
            statement.execute(CREATE_BOOKS_TABLE);
            statement.execute(CREATE_BORROWED_TABLE);
            
            
            
            queryLoginUsers = conn.prepareStatement(QUERY_LOGIN_USERS);
            insertIntoUsers = conn.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);       
            insertIntoBooks = conn.prepareStatement(INSERT_BOOK,Statement.RETURN_GENERATED_KEYS);
            querySBooks = conn.prepareStatement(QUERY_BOOKS);
            querySBooksID = conn.prepareStatement(QUERY_BOOKS_ID);
            insertIntoBorrowed = conn.prepareStatement(INSERT_BORROWED_BOOK,Statement.RETURN_GENERATED_KEYS);
            updateStatus = conn.prepareStatement(UPDATE_BOOK_STATUS);
            returnBook = conn.prepareStatement(DELETE_BORROWED_BOOK);
            queryUserBooks = conn.prepareStatement(USER_BOOKS_TITLE);
            borrowedTitle = conn.prepareStatement(SELECT_BORROWED_TITLE);
            borrowedID = conn.prepareStatement(SELECT_BORROWED_ID);
            
            return true;
        } catch(SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }
    public void close() {
        try {
            if(insertIntoBooks != null) {
                insertIntoUsers.close();
            }
            if(querySBooks != null) {
                querySBooks.close();
            }
            if(querySBooksID != null) {
                querySBooksID.close();
            }
            if(queryLoginUsers != null){
                queryLoginUsers.close();
            }
            if(insertIntoBorrowed != null) {
                insertIntoBorrowed.close();
            }
            if(updateStatus != null){
                updateStatus.close();
            }
            if(borrowedTitle != null){
                borrowedTitle.close();
            }
            if(borrowedID != null){
                borrowedID.close();
            }
            if(returnBook != null){
                returnBook.close();
            }
          //  if(queryUserBooks != null){
          //      queryUserBooks.close();
          //  }
            
            if(conn != null) {
                conn.close();
                System.out.println("Close");
             
            }
        } catch(SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }
    public List<User> queryLoginUsers(String password, String login) throws SQLException {
        
        queryLoginUsers.setString(1, login);
        queryLoginUsers.setString(2, password);
        System.out.println(queryLoginUsers);
        
        
        ResultSet results = queryLoginUsers.executeQuery();
            
        List<User> liblaryUsers = new ArrayList<>();
        while(results.next())
            {
                User liblaryUser = new User(results.getString(2),results.getString(3),results.getInt(1));
                liblaryUsers.add(liblaryUser); 
            }
            
            return liblaryUsers;
        
    
    }
    public List<String> getBorrowedTitle(int userId) throws SQLException
    {
        borrowedTitle.setInt(1,userId);
        
        ResultSet results = borrowedTitle.executeQuery();
        List<String> titles = new ArrayList<>();
        while(results.next())
        {
            String title = results.getString(1);
            titles.add(title);
        }
        return titles;
    }
    public List<Integer> getBorrowedID(String title, int userID) throws SQLException{
        borrowedID.setString(2, title);
        borrowedID.setInt(1,userID);
        
        ResultSet results = borrowedID.executeQuery();
        List<Integer> listId = new ArrayList<>();
        while(results.next())
        {
            int id = results.getInt(1);
            listId.add(id);
        }
        return listId;
    }
    public int insertUser(String login,String password,String name, String surname) throws SQLException
    {
        List<User> results = this.queryLoginUsers(password, login);
        if(!(results.isEmpty())){
            System.out.println("Użytkownik już występuje");
            return 0;
        }else {
            insertIntoUsers.setString(1,login);
            insertIntoUsers.setString(2,password);
            insertIntoUsers.setString(3,name);
            insertIntoUsers.setString(4,surname);
            int affectedRows = insertIntoUsers.executeUpdate();
            
            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert artist!");
            }
            ResultSet generatedKeys = insertIntoUsers.getGeneratedKeys();
            
            if(generatedKeys.next()) {
                System.out.println("The id: " + generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
        }
    }
    public int insertBook(String title,String author) throws SQLException
    {
        
	insertIntoBooks.setString(1,title);
	insertIntoBooks.setString(2,author);

	int affectedRows = insertIntoBooks.executeUpdate();
      System.out.println("Rows: " + affectedRows);
            
      if(affectedRows != 1) {
          throw new SQLException("Couldn't insert artist!");
      }
      ResultSet generatedKeys = insertIntoBooks.getGeneratedKeys();

	if(generatedKeys.next()) {
         System.out.println(insertIntoUsers);
         System.out.println("The id: " + generatedKeys.getInt(1));
         return generatedKeys.getInt(1);
         } else {
             throw new SQLException("Couldn't get _id for artist");
         }
    }   
    public void showUsers()
    {
        String columns[] = {COLUMN_USERS_ID,COLUMN_USERS_LOGIN,COLUMN_USERS_PASSWORD,COLUMN_USERS_NAME,COLUMN_USERS_SURNAME};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_USERS)){
            int i = 0;
            while(results.next())
            {
               int id = results.getInt(COLUMN_USERS_ID);
               String login = results.getString(COLUMN_USERS_LOGIN);
               String password = results.getString(COLUMN_USERS_PASSWORD);
               String name = results.getString(COLUMN_USERS_NAME);
               String surname = results.getString(COLUMN_USERS_SURNAME);
               model.addRow(new Object[]{id,login,password,name,surname});
               i++;
            }
            
           
            JFrame f = new JFrame("Populate JTable from Database");
            JPanel panel = new JPanel();
            panel.add(pane);
            f.add(panel);
            f.setSize(500, 250);
            f.setDefaultCloseOperation(2);
            f.setVisible(true);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
    }
    public void showBooks(int bookStatus)
    {
        String columns[] = {COLUMN_BOOKS_ID,COLUMN_BOOKS_TITLE,COLUMN_BOOKS_AUTHOR,COLUMN_BOOKS_STATUS};
        String query;
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        if(bookStatus == 1)
        {
            query = "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOKS_STATUS + " = 1";
        }else if(bookStatus == 0){
            query = "SELECT * FROM " + TABLE_BOOKS + " WHERE " + COLUMN_BOOKS_STATUS + " = 0";
        }else{
            query = "SELECT * FROM " + TABLE_BOOKS;
        }
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(query)){
            int i = 0;
            while(results.next())
            {
               int id = results.getInt(COLUMN_BOOKS_ID);
               String title = results.getString(COLUMN_BOOKS_TITLE);
               String author = results.getString(COLUMN_BOOKS_AUTHOR);
               int status = results.getInt(COLUMN_BOOKS_STATUS);
               
               model.addRow(new Object[]{id,title,author,status});
               i++;
            }
            
           
            JFrame f = new JFrame("Populate JTable from Database");
            JPanel panel = new JPanel();
            panel.add(pane);
            f.add(panel);
            f.setSize(500, 250);
            f.setDefaultCloseOperation(2);
            f.setVisible(true);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void showBorrowedBook()
    {
        String columns[] = {COLUMN_USERS_NAME, COLUMN_USERS_SURNAME, COLUMN_BOOKS_TITLE};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(QUERY_BORROWED_BOOK)){
            while(results.next())
            {
               String name = results.getString(1);
               String surname = results.getString(2);
               String title = results.getString(3);
               
               model.addRow(new Object[]{name,surname,title});
            }
            
           
            JFrame f = new JFrame("Populate JTable from Database");
            JPanel panel = new JPanel();
            panel.add(pane);
            f.add(panel);
            f.setSize(500, 250);
            f.setDefaultCloseOperation(2);
            f.setVisible(true);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
        
    public void showUserBooks(int userID) throws SQLException
    {
        String column[] = {"title", "ID"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(column);
        JTable table = new JTable(model);
        table.setShowGrid(true);
        table.setShowVerticalLines(true);
        JScrollPane pane = new JScrollPane(table);
        queryUserBooks.setInt(1,userID);
        ResultSet result = queryUserBooks.executeQuery();
        while(result.next())
        {
            String title = result.getString(1);
            int id = result.getInt(2);
            System.out.println(title);
            model.addRow(new Object[]{title,id});
        }
        JFrame f = new JFrame("Populate JTable from Database");
            JPanel panel = new JPanel();
            panel.add(pane);
            f.add(panel);
            f.setSize(500, 250);
            f.setDefaultCloseOperation(2);
            f.setVisible(true);
    }
    public List<String> queryBooks(String title) throws SQLException {           
           querySBooks.setString(1, "%" + title + "%");
           querySBooks.setInt(2,1);
                     
           ResultSet results = querySBooks.executeQuery();
           List<String> books = new ArrayList<>();
           
           while(results.next())
            {
                String book = results.getString(1);
                books.add(book); 
            }
            
            return books;
           
        }
    
    public List<Integer> queryBooksID(String title) throws SQLException {
           querySBooksID.setString(1, "%" + title + "%");
           
           ResultSet results = querySBooksID.executeQuery();
           List<Integer> booksID = new ArrayList<>();
           
           while(results.next())
            {
                int book = results.getInt(1);
                booksID.add(book); 
            }
            
            return booksID;
           
        }
    public int borrowBook(int userId, int bookId) throws SQLException
    {
        updateStatus.setInt(1, 0);
        updateStatus.setInt(2,bookId);
        insertIntoBorrowed.setInt(1, bookId);
        insertIntoBorrowed.setInt(2, userId);
        
        int affectedRows = insertIntoBorrowed.executeUpdate();
        updateStatus.executeUpdate();
            
            if(affectedRows != 1) {
                throw new SQLException("Couldn't insert book!");
            }
            ResultSet generatedKeys = insertIntoBorrowed.getGeneratedKeys();
            
            if(generatedKeys.next()) {
                System.out.println("The id: " + generatedKeys.getInt(1));
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Couldn't get _id for artist");
            }
            
            
    }
    public void returnBook(int bookID) throws SQLException 
    {
        updateStatus.setInt(1,1);
        updateStatus.setInt(2, bookID);
        returnBook.setInt(1, bookID);
        
        updateStatus.executeUpdate();
        int affectedRows = returnBook.executeUpdate();
        
        if(affectedRows != 1) {
                throw new SQLException("Couldn't delete book!");
         }
        
    }
    
}

