package liblary_app;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.util.List;
import java.sql.SQLException;

public class Main extends JFrame {
    
    JLabel login = new JLabel("login");
    JLabel title = new JLabel("LiblaryApp");
    JLabel password = new JLabel("password");
    JPasswordField passwordField = new JPasswordField("Password",20);
    JTextField loginField = new JTextField("Login", 20);
    JButton logButton = new JButton("Login");
    static Datasource datasource = new Datasource();
    static int userLoginId;
    
    public Main()
    {
        super("liblaryApp");
        
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
        
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING).addComponent(login)
                        .addComponent(password)               
                ).addGroup(layout.createParallelGroup(LEADING).addComponent(title)                 
                .addComponent(loginField,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200)
                .addComponent(passwordField,GroupLayout.DEFAULT_SIZE,GroupLayout.DEFAULT_SIZE,200))
                .addComponent(logButton)
            );
        layout.setVerticalGroup(
        layout.createSequentialGroup().addComponent(title).addGroup(layout.createParallelGroup(BASELINE)
        .addComponent(login)
        .addComponent(loginField))
         .addGroup(layout.createParallelGroup(BASELINE)
          .addComponent(password)
          .addComponent(passwordField))
          .addComponent(logButton)
            
        );
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                int length = String.valueOf(passwordField.getPassword()).length();
                passwordField.select(0, length);
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        //Ustawienie zaznaczenia po kliknięciu 
        loginField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                int length = (loginField.getText()).length();
                loginField.select(0, length);               
            }

            @Override
            public void focusLost(FocusEvent e) {
                
            }
        });
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] pass = passwordField.getPassword();
                String passAdmin = String.valueOf(pass);
                //System.out.println(passAdmin);
                String log = loginField.getText();
                //System.out.println(log);
                if(passAdmin.equals(new String("admin")) && log.equals(new String("admin")))
                    new AdminPage().setVisible(true);
                else{    
                   datasource.open();
                   try{
                       List<User> users = datasource.queryLoginUsers(passAdmin, log);
                       System.out.println(users.size());
                       if(users.isEmpty()){
                           JOptionPane.showMessageDialog(rootPane, "Brak użytkownika");
                       }else{
                           for(User user : users)
                               userLoginId = user.getId();
                           
                           new UserPage().setVisible(true);
                       }
                   }catch(SQLException ex){
                       System.out.println("Something went wrong" + ex.getMessage());
                   }
                    
                    datasource.close(); 
                    
                }
                dispose();                
            }
        });
        pack();
    }
    
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
    
}

