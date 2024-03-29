package Encje;

import javax.persistence.*;


@Entity
public class uzytkownicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   private String login;
   private String haslo;
   private String mail;
   private int id_firmy;

   public uzytkownicy()
   {

   }
    public uzytkownicy(String login, String haslo, String mail, int id_firmy) {
        this.login = login;
        this.haslo = haslo;
        this.mail = mail;
        this.id_firmy = id_firmy;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId_firmy() {
        return id_firmy;
    }

    public void setId_firmy(int id_firmy) {
        this.id_firmy = id_firmy;
    }
}
