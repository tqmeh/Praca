package Encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class firma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nazwa;
    private String ulica;
    private String numer_domu;

    private String numer_mieszkania;
    private String kod_pocztowy;
    private String miasto;
    private double nip;
    private double regon;
    public firma()
    {

    }
    public firma( String nazwa, String ulica, String numer_domu, String numer_mieszkania, String kod_pocztowy, String miasto, double nip, double regon) {

        this.nazwa = nazwa;
        this.ulica = ulica;
        this.numer_domu = numer_domu;
        this.numer_mieszkania = numer_mieszkania;
        this.kod_pocztowy = kod_pocztowy;
        this.miasto = miasto;
        this.nip = nip;
        this.regon = regon;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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

    public double getNip() {
        return nip;
    }

    public void setNip(double nip) {
        this.nip = nip;
    }

    public double getRegon() {
        return regon;
    }

    public void setRegon(double regon) {
        this.regon = regon;
    }
}
