
package liblary_app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.SQLException;

public class UserPage extends JFrame {
    
    private final Container kontener = this.getContentPane();
    private final JPanel panelMenu = new JPanel();
    private final AdminPage.MenuButton menu1 = new AdminPage.MenuButton("Show Books");
    private final AdminPage.MenuButton menu2 = new AdminPage.MenuButton("Issue Book");
    private final AdminPage.MenuButton menu3 = new AdminPage.MenuButton("Return Book");
    //String is = Main.logInUser;
    
     public UserPage()
    {
        initComponent();
    }
    public void initComponent()
    {
        System.out.println(Main.userLoginId);
        this.setTitle("UserPage");
        int szer = Toolkit.getDefaultToolkit().getScreenSize().width; 
        int wys = Toolkit.getDefaultToolkit().getScreenSize().height; 
        //this.setSize(szer/4, wys/4);
//        this.setLocation(szer/4, wys/4);
        int szerRamki = this.getSize().width;
        int wysRamki = this.getSize().height;
        this.setLocation((szer-szerRamki)/2, (wys-wysRamki)/2);
        this.setDefaultCloseOperation(2);
        
        panelMenu.setLayout(new GridLayout(1,3));
        
        panelMenu.add(menu1);
        panelMenu.add(menu2);
        panelMenu.add(menu3);
        
        
         menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Main.datasource.open();
                    Main.datasource.showUserBooks(Main.userLoginId);
                    Main.datasource.close();
                }catch(SQLException ex){
                    System.out.println("Error" + ex.getMessage());
                }
             
            }
        });
        menu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BorrowBook().setVisible(true);
            }
        });
        menu3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReturnBook().setVisible(true);
            }
        });
        
        
        kontener.add(panelMenu);
        //System.out.println(Main.logInUser);
        
        pack();
    }
    
    
    public static void main(String[] args) {
        new UserPage().setVisible(true);
    }
}
