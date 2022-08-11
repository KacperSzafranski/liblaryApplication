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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class AddUser extends JFrame implements KeyListener  {
    JLabel login = new JLabel("login");
    JLabel password = new JLabel("password");
    JLabel name = new JLabel("name");
    JLabel surname = new JLabel("surname");
    
    JTextField loginText = new JTextField(20);
    JTextField passwordText = new JTextField(20);
    JTextField nameText = new JTextField(20);
    JTextField surnameText = new JTextField(20);
    
    JButton createButton = new JButton("Create User");
    
     public AddUser()
    {
        initComponent();
    }
     public void initComponent()
    {
        this.setTitle("AdminPage");
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
        .addComponent(name)
        .addComponent(surname)
        .addComponent(login)
        .addComponent(password))
        .addGroup(layout.createParallelGroup(LEADING)
        .addComponent(nameText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200)
        .addComponent(surnameText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200)
        .addComponent(loginText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200)
        .addComponent(passwordText,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200))
        .addComponent(createButton));
        
        layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(name)
        .addComponent(nameText))
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(surname)
        .addComponent(surnameText))
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(login)
        .addComponent(loginText))
        .addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(password)
        .addComponent(passwordText))
        .addComponent(createButton));
        
        pack();
        
        loginText.addKeyListener(this);
        passwordText.addKeyListener(this);
        nameText.addKeyListener(this);
        surnameText.addKeyListener(this);
        
        createButton.addActionListener((ActionEvent e) -> {
            String l = loginText.getText();
            String p = passwordText.getText();
            String n = nameText.getText();
            String s = surnameText.getText();
            

            try {
                Main.datasource.open();
                Main.datasource.insertUser(l, p, n, s);
                Main.datasource.close();
            } catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
            loginText.setText("");
            nameText.setText("");
            surnameText.setText("");
            passwordText.setText("");
           
                    });
    }
    public static void main(String[] args) {
        new AddUser().setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == ' ')
                    e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

     
}
