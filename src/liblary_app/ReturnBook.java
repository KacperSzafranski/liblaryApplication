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
import java.util.List;
import java.sql.SQLException;
        

public class ReturnBook extends JFrame {
    
    JLabel title = new JLabel("title");
    JLabel id = new JLabel("id");  
    JComboBox titleBox = new JComboBox();
    JComboBox idBox = new JComboBox() ;
    JButton returnButton = new JButton("Return");
    
    
    
    
    public ReturnBook()
    {
        initComponent();
    }
     public void initComponent()
    {
        this.setTitle("Return Book");
        int szer = Toolkit.getDefaultToolkit().getScreenSize().width; 
        int wys = Toolkit.getDefaultToolkit().getScreenSize().height; 
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
        .addComponent(title)
        .addComponent(id))
        .addGroup(layout.createParallelGroup(LEADING)
        .addComponent(titleBox,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200)
        .addComponent(idBox,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200))
        .addComponent(returnButton));
        
         layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(title)
        .addComponent(titleBox))
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(id)
        .addComponent(idBox))
        .addComponent(returnButton));
         
         titleBox.setPrototypeDisplayValue("Choose Book Title");
         
         int w = titleBox.getItemCount();
         if( w == 0){
             titleBox.setEnabled(false);
             idBox.setEnabled(false);
         }
         else{
             titleBox.setEnabled(true);
             idBox.setEnabled(true);
         }
         
         
         
         pack();
         
         try{
             Main.datasource.open();
             List<String> result = Main.datasource.getBorrowedTitle(Main.userLoginId);
             Main.datasource.close();
             if(result.isEmpty())
                System.out.println("Brak książek");
            else{
                titleBox.setEnabled(true);
                idBox.setEnabled(true);
                for(String var : result)
                    titleBox.addItem(var);}
         } catch(SQLException ex){
             System.out.println("Query " + ex.getMessage());
         }
         titleBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idBox.removeAllItems();
                String searchT = (String)titleBox.getSelectedItem();
                 try{
                    Main.datasource.open();
                    List<Integer> titleId = Main.datasource.getBorrowedID(searchT, Main.userLoginId);
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
         returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int bookID = (int)idBox.getSelectedItem();
                    Main.datasource.open();
                    Main.datasource.returnBook(bookID);
                    Main.datasource.close();
                    dispose();
                }catch(SQLException ex){
                    System.out.println("Error" + ex.getMessage());
                }
            }
         });
         
        
    }
     public static void main(String[] args) {
        new ReturnBook().setVisible(true);
        
        
    }
    
}
