package Encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class zleceniodawca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nazwa_skrocona;
    private String nazwa_pelna;
    private String kraj;
    private String ulica;
    private String numer_domu;
    private String numer_mieszkania;
    private String kod_pocztowy;
    private String miasto;
    private String nip;
    private double regon;
    private String telefon_stacjonarny;
    private String telefon_komorkowy;
    private String mail;
    private String skype;
    private int id_firmy;
    private String nazwa_firmy;
    private int id_uzytkownika;
    private String nazwa_uzytkownika;

    public zleceniodawca()
    {

    }

    public zleceniodawca(String nazwa_skrocona, String nazwa_pelna, String kraj, String ulica, String numer_domu, String numer_mieszkania, String kod_pocztowy, String miasto, String nip, double regon, String telefon_stacjonarny, String telefon_komorkowy, String mail, String skype, int id_firmy, String nazwa_firmy, int id_uzytkownika, String nazwa_uzytkownika) {
        this.nazwa_skrocona = nazwa_skrocona;
        this.nazwa_pelna = nazwa_pelna;
        this.kraj = kraj;
        this.ulica = ulica;
        this.numer_domu = numer_domu;
        this.numer_mieszkania = numer_mieszkania;
        this.kod_pocztowy = kod_pocztowy;
        this.miasto = miasto;
        this.nip = nip;
        this.regon = regon;
        this.telefon_stacjonarny = telefon_stacjonarny;
        this.telefon_komorkowy = telefon_komorkowy;
        this.mail = mail;
        this.skype = skype;
        this.id_firmy = id_firmy;
        this.nazwa_firmy = nazwa_firmy;
        this.id_uzytkownika = id_uzytkownika;
        this.nazwa_uzytkownika = nazwa_uzytkownika;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa_skrocona() {
        return nazwa_skrocona;
    }

    public void setNazwa_skrocona(String nazwa_skrocona) {
        this.nazwa_skrocona = nazwa_skrocona;
    }

    public String getNazwa_pelna() {
        return nazwa_pelna;
    }

    public void setNazwa_pelna(String nazwa_pelna) {
        this.nazwa_pelna = nazwa_pelna;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNumer_domu() {
        return numer_domu;
    }

    public void setNumer_domu(String numer_domu) {
        this.numer_domu = numer_domu;
    }

    public String getNumer_mieszkania() {
        return numer_mieszkania;
    }

    public void setNumer_mieszkania(String numer_mieszkania) {
        this.numer_mieszkania = numer_mieszkania;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public double getRegon() {
        return regon;
    }

    public void setRegon(double regon) {
        this.regon = regon;
    }

    public String getTelefon_stacjonarny() {
        return telefon_stacjonarny;
    }

    public void setTelefon_stacjonarny(String telefon_stacjonarny) {
        this.telefon_stacjonarny = telefon_stacjonarny;
    }

    public String getTelefon_komorkowy() {
        return telefon_komorkowy;
    }

    public void setTelefon_komorkowy(String telefon_komorkowy) {
        this.telefon_komorkowy = telefon_komorkowy;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
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

    public int getId_uzytkownika() {
        return id_uzytkownika;
    }

    public void setId_uzytkownika(int id_uzytkownika) {
        this.id_uzytkownika = id_uzytkownika;
    }

    public String getNazwa_uzytkownika() {
        return nazwa_uzytkownika;
    }

    public void setNazwa_uzytkownika(String nazwa_uzytkownika) {
        this.nazwa_uzytkownika = nazwa_uzytkownika;
    }
}
