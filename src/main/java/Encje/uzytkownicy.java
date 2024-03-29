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
   private String nazwa_firmy;
   private int wlasciciel;
   private int administrator;
   private int zmiana_hasla;

   public uzytkownicy()
   {

   }
    public uzytkownicy(String login, String haslo, String mail, int id_firmy,String nazwa_firmy,int wlasciciel,int administrator,int zmiana_hasla) {
        this.login = login;
        this.haslo = haslo;
        this.mail = mail;
        this.id_firmy = id_firmy;
        this.nazwa_firmy=nazwa_firmy;
        this.wlasciciel=wlasciciel;
        this.administrator=administrator;
        this.zmiana_hasla=zmiana_hasla;
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

    public String getNazwa_firmy() {
        return nazwa_firmy;
    }

    public void setNazwa_firmy(String nazwa_firmy) {
        this.nazwa_firmy = nazwa_firmy;
    }

    public int getWlasciciel() {
        return wlasciciel;
    }

    public void setWlasciciel(int wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    public int getAdministrator() {
        return administrator;
    }

    public void setAdministrator(int administrator) {
        this.administrator = administrator;
    }

    public int getZmiana_hasla() {
        return zmiana_hasla;
    }

    public void setZmiana_hasla(int zmiana_hasla) {
        this.zmiana_hasla = zmiana_hasla;
    }
}
