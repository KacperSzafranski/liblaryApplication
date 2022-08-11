/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liblary_app;

public class User {
    private int id;
    private String login;
    private String password;
    public User()
    {
        
    }
    public User(String login, String password, int id)
    {
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }
    public int getId()
    {
        return id;
    }

    public String getPassword() {
        return password;
    }

}
