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
import java.sql.SQLException;
import java.awt.event.KeyListener;


public class AddBook extends JFrame {
    
    JLabel title = new JLabel("title");
    JLabel author = new JLabel("author");
    
    JTextField titleText = new JTextField(20);
    JTextField authorText = new JTextField(20);
    
    JButton createButton = new JButton("Add Book");
    
    public AddBook()
    {
        initComponent();
    }
    
    public void initComponent()
    {
        this.setTitle("AdminPage");
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
        .addComponent(title)
        .addComponent(author))
        .addGroup(layout.createParallelGroup(LEADING)
        .addComponent(titleText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200)
        .addComponent(authorText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200))
        .addComponent(createButton));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(title)
        .addComponent(titleText))
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(author)
        .addComponent(authorText))
        .addComponent(createButton));
        
        pack();

        
        createButton.addActionListener((ActionEvent e) -> {
            String a = authorText.getText();
            String t = titleText.getText();
            
            try {
                Main.datasource.open();
                Main.datasource.insertBook(t,a);
                Main.datasource.close();
            } catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
            
            authorText.setText("");
            titleText.setText("");
            
            
                    });
         
    }
     public static void main(String[] args) {
        new AddBook().setVisible(true);
        }

     
    
}

