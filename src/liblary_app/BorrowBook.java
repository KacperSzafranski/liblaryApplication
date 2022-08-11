/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liblary_app;

import java.awt.Toolkit;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.sql.SQLException;
        

public class BorrowBook extends JFrame {
    JLabel title = new JLabel("title");
    JLabel id = new JLabel("id");  
    JComboBox titleBox = new JComboBox();
    JComboBox idBox = new JComboBox() ;
    JTextField searchText = new JTextField("Search book", 20);
    JButton search = new JButton("Search");
    JButton borrow = new JButton("Borrow");
    private List<String> result;
    
     public BorrowBook()
    {
        initComponent();
    }
     public void initComponent()
    {
        this.setTitle("BorrowBook");
        int szer = Toolkit.getDefaultToolkit().getScreenSize().width; 
        int wys = Toolkit.getDefaultToolkit().getScreenSize().height; 
        //this.setSize(szer/4, wys/4);
//        this.setLocation(szer/4, wys/4);
        int szerRamki = this.getSize().width;
        int wysRamki = this.getSize().height;
        this.setLocation((szer-szerRamki)/2, (wys-wysRamki)/2);
        this.setDefaultCloseOperation(2);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(LEADING)
        .addComponent(searchText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200)
        .addComponent(title)
        .addComponent(id))
        .addGroup(layout.createParallelGroup(LEADING)
        .addComponent(search)
        .addComponent(titleBox)
        .addComponent(idBox))
        .addComponent(borrow));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(searchText)
        .addComponent(search))
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(title)
        .addComponent(titleBox))
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(id)
        .addComponent(idBox))
        .addComponent(borrow));
        
        pack();
        
        try {
                Main.datasource.open();
                result = Main.datasource.queryBooks("");
                titleBox.removeAllItems();
                Main.datasource.close();
                if(result.isEmpty()){
                    System.out.println("Brak książek");
                    titleBox.setEnabled(false);
                    idBox.setEnabled(false);
                }else{
                    titleBox.setEnabled(true);
                    idBox.setEnabled(true);
                    for(String title : result)
                        titleBox.addItem(title);}
                }catch(SQLException ex){
                    System.out.println("Query " + ex.getMessage());
                }
        
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String searchT = searchText.getText();
                Main.datasource.open();
                result = Main.datasource.queryBooks(searchT);
                searchText.setText("");
                titleBox.removeAllItems();
                Main.datasource.close();
                if(result.isEmpty()){
                    System.out.println("Brak książek");
                    titleBox.setEnabled(false);
                    idBox.setEnabled(false);
                }else{
                    titleBox.setEnabled(true);
                    idBox.setEnabled(true);
                    for(String title : result)
                        titleBox.addItem(title);}
                }catch(SQLException ex){
                    System.out.println("Query " + ex.getMessage());
                }
                
            }
        });
        borrow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookId = (Integer)idBox.getSelectedItem();
                int userId = Main.userLoginId;
                try{
                    Main.datasource.open();
                    Main.datasource.borrowBook(userId, bookId);
                    Main.datasource.close();
                    dispose();
                }catch(SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
                
                
                
            }
        });
        titleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                 idBox.removeAllItems();
                 String searchT = (String)titleBox.getSelectedItem();
                 try{
                    Main.datasource.open();
                    List<Integer> titleId = Main.datasource.queryBooksID(searchT);
                     Main.datasource.close();
                  for(int id : titleId)
                    {
                        idBox.addItem(id);
                    }
                 }catch(SQLException ex){
                     ex.getMessage();
                 }
                 
            }
            
        });
        searchText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                int length = (searchText.getText()).length();
                searchText.select(0, length); 
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        
        
    }
     
     public static void main(String[] args) {
        new BorrowBook().setVisible(true);
        
        
    }
}
