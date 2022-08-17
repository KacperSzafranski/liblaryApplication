
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
