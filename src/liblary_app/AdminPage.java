/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liblary_app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminPage extends JFrame {
    
    private final Container kontener = this.getContentPane();
    private final JPanel panelMenu = new JPanel();
    private final MenuButton menu1 = new MenuButton("Add User");
    private final MenuButton menu2 = new MenuButton("Add Book");
    private final MenuButton menu3 = new MenuButton("Show Users");
    private final MenuButton menu4 = new MenuButton("Show Books");
    private final MenuButton menu5 = new MenuButton("Show Issues");
    
    public AdminPage()
    {
        initComponents();
    }
    public void initComponents()
    {
        int szer = Toolkit.getDefaultToolkit().getScreenSize().width; 
        int wys = Toolkit.getDefaultToolkit().getScreenSize().height; 
        int szerRamki = this.getSize().width;
        int wysRamki = this.getSize().height;
        this.setLocation((szer-szerRamki)/2, (wys-wysRamki)/2);
        this.setDefaultCloseOperation(3);
        this.setTitle("AdminPage");
        
        panelMenu.setLayout(new GridLayout(1,5));
        
        panelMenu.add(menu1);
        panelMenu.add(menu2);
        panelMenu.add(menu3);
        panelMenu.add(menu4);
        panelMenu.add(menu5);
        
        kontener.add(panelMenu);
        
        pack();
        
        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddUser().setVisible(true);
            }
        });
        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddBook().setVisible(true);
            }
        });
        menu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.datasource.open();
                Main.datasource.showUsers();
                Main.datasource.close();
            }
        });
        menu4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.datasource.open();
                Main.datasource.showBooks(2);
                Main.datasource.close();
            }
        });
        menu5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.datasource.open();
                Main.datasource.showBorrowedBook();
                Main.datasource.close();
            }
        });
        
    }
    public static class MenuButton extends JButton 
    {
        public MenuButton(String nazwa)
        {
            super(nazwa);
            this.setBackground(Color.LIGHT_GRAY);
        }

       
    }
    public static void main(String[] args) {
        new AdminPage().setVisible(true);
    }
}
