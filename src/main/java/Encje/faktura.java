package Encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class faktura {
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
    private String kraj;
    private double kwota_zlecenia;
    private String stawka_vat;
    private String data_wystawienia;
    private String data_sprzedazy;
    private String nip;
    private String regon;
    private double kwota_netto;
    private double kwota_brutto;
    private String konto_pln;
    private String konto_eur;
    private String waluta;
    private String iban;
    private String swift;
    private String numer_faktury;
    private String nazwa_firmy;
    private int id_firmy;

    public faktura()
    {

    }

    public faktura(int id_zlecenia, String nazwa, String ulica, String numer_domu, String numer_mieszkania, String kod_pocztowy, String miasto, String kraj, double kwota_zlecenia, String stawka_vat, String data_wystawienia, String data_sprzedazy, String nip, String regon, double kwota_netto, double kwota_brutto, String konto_pln, String konto_eur, String waluta, String iban, String swift, String numer_faktury, String nazwa_firmy, int id_firmy) {
        this.id_zlecenia = id_zlecenia;
        this.nazwa = nazwa;
        this.ulica = ulica;
        this.numer_domu = numer_domu;
        this.numer_mieszkania = numer_mieszkania;
        this.kod_pocztowy = kod_pocztowy;
        this.miasto = miasto;
        this.kraj = kraj;
        this.kwota_zlecenia = kwota_zlecenia;
        this.stawka_vat = stawka_vat;
        this.data_wystawienia = data_wystawienia;
        this.data_sprzedazy = data_sprzedazy;
        this.nip = nip;
        this.regon = regon;
        this.kwota_netto = kwota_netto;
        this.kwota_brutto = kwota_brutto;
        this.konto_pln = konto_pln;
        this.konto_eur = konto_eur;
        this.waluta = waluta;
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

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public double getKwota_zlecenia() {
        return kwota_zlecenia;
    }

    public void setKwota_zlecenia(double kwota_zlecenia) {
        this.kwota_zlecenia = kwota_zlecenia;
    }

    public String getStawka_vat() {
        return stawka_vat;
    }

    public void setStawka_vat(String stawka_vat) {
        this.stawka_vat = stawka_vat;
    }

    public String getData_wystawienia() {
        return data_wystawienia;
    }

    public void setData_wystawienia(String data_wystawienia) {
        this.data_wystawienia = data_wystawienia;
    }

    public String getData_sprzedazy() {
        return data_sprzedazy;
    }

    public void setData_sprzedazy(String data_sprzedazy) {
        this.data_sprzedazy = data_sprzedazy;
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

    public double getKwota_netto() {
        return kwota_netto;
    }

    public void setKwota_netto(double kwota_netto) {
        this.kwota_netto = kwota_netto;
    }

    public double getKwota_brutto() {
        return kwota_brutto;
    }

    public void setKwota_brutto(double kwota_brutto) {
        this.kwota_brutto = kwota_brutto;
    }

    public String getKonto_pln() {
        return konto_pln;
    }

    public void setKonto_pln(String konto_pln) {
        this.konto_pln = konto_pln;
    }

    public String getKontro_eur() {
        return konto_eur;
    }

    public void setKontro_eur(String kontro_eur) {
        this.konto_eur = kontro_eur;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
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
