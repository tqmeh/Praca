package Encje;

import javax.persistence.*;


@Entity
public class uzytkownicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String haslo;

    public uzytkownicy()
    {

    }
   public uzytkownicy( String login,String haslo)
   {

       this.login=login;
       this.haslo=haslo;

   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }
}
