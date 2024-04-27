package Encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class fakturykosztowe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_zlecenia;
    private String nazwa;
    private String ulica;
    private String numer_domu;
    private String numer_mieszkania;
    private String kod_pocztowy;
    private String miasto;
    private String nip;
    private String regon;
    private double kwota;
    private String waluta;
    private String konto_PLN;
    private String konto_EUR;
    private String iban;
    private String swift;
    private String numer_faktury;
    private String nazwa_firmy;
    private int id_firmy;
    public fakturykosztowe()
    {

    }

    public fakturykosztowe(int id_zlecenia, String nazwa, String ulica, String numer_domu, String numer_mieszkania, String kod_pocztowy, String miasto, String nip, String regon, double kwota, String waluta, String konto_PLN, String konto_EUR, String iban, String swift, String numer_faktury, String nazwa_firmy, int id_firmy) {
        this.id_zlecenia = id_zlecenia;
        this.nazwa = nazwa;
        this.ulica = ulica;
        this.numer_domu = numer_domu;
        this.numer_mieszkania = numer_mieszkania;
        this.kod_pocztowy = kod_pocztowy;
        this.miasto = miasto;
        this.nip = nip;
        this.regon = regon;
        this.kwota = kwota;
        this.waluta = waluta;
        this.konto_PLN = konto_PLN;
        this.konto_EUR = konto_EUR;
        this.iban = iban;
        this.swift = swift;
        this.numer_faktury = numer_faktury;
        this.nazwa_firmy = nazwa_firmy;
        this.id_firmy = id_firmy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_zlecenia() {
        return id_zlecenia;
    }

    public void setId_zlecenia(int id_zlecenia) {
        this.id_zlecenia = id_zlecenia;
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

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = kwota;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public String getKonto_PLN() {
        return konto_PLN;
    }

    public void setKonto_PLN(String konto_PLN) {
        this.konto_PLN = konto_PLN;
    }

    public String getKonto_EUR() {
        return konto_EUR;
    }

    public void setKonto_EUR(String konto_EUR) {
        this.konto_EUR = konto_EUR;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public String getNumer_faktury() {
        return numer_faktury;
    }

    public void setNumer_faktury(String numer_faktury) {
        this.numer_faktury = numer_faktury;
    }

    public String getNazwa_firmy() {
        return nazwa_firmy;
    }

    public void setNazwa_firmy(String nazwa_firmy) {
        this.nazwa_firmy = nazwa_firmy;
    }

    public int getId_firmy() {
        return id_firmy;
    }

    public void setId_firmy(int id_firmy) {
        this.id_firmy = id_firmy;
    }
}
