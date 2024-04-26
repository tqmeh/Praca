package Encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class zlecenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String wykonawca;
    private String zlecemiodawca_nazwa_skrocona;
    private String numer_zlecenia;
    private String rodzaj_towaru;
    private int ilosc;
    private int waga;
    private String kraj_zaladunku;
    private String data_zaladunku;
    private String miejscowosc_zaladunku;
    private String kod_pocztowy_zaladunku;
    private String ulica_zaladunku;
    private String firma_zaladunek;
    private String przewoznik_nazwa_skrocona;
    private String rodzaj_samochodu;
    private String kierowca;
    private String numery_rejestracyjne;
    private String kraj_rozladunku;
    private String data_rozladunku;
    private String miejscowosc_rozladunku;
    private String kod_pocztowy_rozladunku;
    private String ulica_rozladunku;
    private String firma_rozladunek;
    private  Double kwota_frachtu;
    private Double kwota_zlecenia;
    private String waluta;
    private String nazwa_firmy;
    private int id_firmy;
    private String nazwa_uzytkownika;
    private String warunku_zlecenia;

    public zlecenie()
    {

    }

    public zlecenie(String wykonawca, String zlecemiodawca_nazwa_skrocona, String numer_zlecenia, String rodzaj_towaru, int ilosc, int waga, String kraj_zaladunku, String data_zaladunku, String miejscowosc_zaladunku, String kod_pocztowy_zaladunku,String ulica_zaladunku, String firma_zaladunek, String przewoznik_nazwa_skrocona, String rodzaj_samochodu, String kierowca, String numery_rejestracyjne, String kraj_rozladunku, String data_rozladunku, String miejscowosc_rozladunku, String kod_pocztowy_rozladunku,String ulica_rozladunku, String firma_rozladunek,Double kwota_frachtu,Double kwota_zlecenia, String waluta, String warunku_zlecenia, String nazwa_firmy, int id_firmy, String nazwa_uzytkownika) {
        this.wykonawca = wykonawca;
        this.zlecemiodawca_nazwa_skrocona = zlecemiodawca_nazwa_skrocona;
        this.numer_zlecenia = numer_zlecenia;
        this.rodzaj_towaru = rodzaj_towaru;
        this.ilosc = ilosc;
        this.waga = waga;
        this.kraj_zaladunku = kraj_zaladunku;
        this.data_zaladunku = data_zaladunku;
        this.miejscowosc_zaladunku = miejscowosc_zaladunku;
        this.kod_pocztowy_zaladunku = kod_pocztowy_zaladunku;
        this.firma_zaladunek = firma_zaladunek;
        this.przewoznik_nazwa_skrocona = przewoznik_nazwa_skrocona;
        this.rodzaj_samochodu = rodzaj_samochodu;
        this.kierowca = kierowca;
        this.numery_rejestracyjne = numery_rejestracyjne;
        this.kraj_rozladunku = kraj_rozladunku;
        this.data_rozladunku = data_rozladunku;
        this.miejscowosc_rozladunku = miejscowosc_rozladunku;
        this.kod_pocztowy_rozladunku = kod_pocztowy_rozladunku;
        this.firma_rozladunek = firma_rozladunek;
        this.nazwa_firmy = nazwa_firmy;
        this.id_firmy = id_firmy;
        this.nazwa_uzytkownika = nazwa_uzytkownika;
        this.ulica_zaladunku=ulica_zaladunku;
        this.ulica_rozladunku=ulica_rozladunku;
        this.kwota_frachtu=kwota_frachtu;
        this.kwota_zlecenia=kwota_zlecenia;
        this.waluta=waluta;
        this.warunku_zlecenia=warunku_zlecenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWykonawca() {
        return wykonawca;
    }

    public void setWykonawca(String wykonawca) {
        this.wykonawca = wykonawca;
    }

    public String getZlecemiodawca_nazwa_skrocona() {
        return zlecemiodawca_nazwa_skrocona;
    }

    public void setZlecemiodawca_nazwa_skrocona(String zlecemiodawca_nazwa_skrocona) {
        this.zlecemiodawca_nazwa_skrocona = zlecemiodawca_nazwa_skrocona;
    }

    public String getNumer_zlecenia() {
        return numer_zlecenia;
    }

    public void setNumer_zlecenia(String numer_zlecenia) {
        this.numer_zlecenia = numer_zlecenia;
    }

    public String getRodzaj_towaru() {
        return rodzaj_towaru;
    }

    public void setRodzaj_towaru(String rodzaj_towaru) {
        this.rodzaj_towaru = rodzaj_towaru;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public String getKraj_zaladunku() {
        return kraj_zaladunku;
    }

    public void setKraj_zaladunku(String kraj_zaladunku) {
        this.kraj_zaladunku = kraj_zaladunku;
    }

    public String getData_zaladunku() {
        return data_zaladunku;
    }

    public void setData_zaladunku(String data_zaladunku) {
        this.data_zaladunku = data_zaladunku;
    }

    public String getMiejscowosc_zaladunku() {
        return miejscowosc_zaladunku;
    }

    public void setMiejscowosc_zaladunku(String miejscowosc_zaladunku) {
        this.miejscowosc_zaladunku = miejscowosc_zaladunku;
    }

    public String getKod_pocztowy_zaladunku() {
        return kod_pocztowy_zaladunku;
    }

    public void setKod_pocztowy_zaladunku(String kod_pocztowy_zaladunku) {
        this.kod_pocztowy_zaladunku = kod_pocztowy_zaladunku;
    }

    public String getFirma_zaladunek() {
        return firma_zaladunek;
    }

    public void setFirma_zaladunek(String firma_zaladunek) {
        this.firma_zaladunek = firma_zaladunek;
    }

    public String getPrzewoznik_nazwa_skrocona() {
        return przewoznik_nazwa_skrocona;
    }

    public void setPrzewoznik_nazwa_skrocona(String przewoznik_nazwa_skrocona) {
        this.przewoznik_nazwa_skrocona = przewoznik_nazwa_skrocona;
    }

    public String getRodzaj_samochodu() {
        return rodzaj_samochodu;
    }

    public void setRodzaj_samochodu(String rodzaj_samochodu) {
        this.rodzaj_samochodu = rodzaj_samochodu;
    }

    public String getKierowca() {
        return kierowca;
    }

    public void setKierowca(String kierowca) {
        this.kierowca = kierowca;
    }

    public String getNumery_rejestracyjne() {
        return numery_rejestracyjne;
    }

    public void setNumery_rejestracyjne(String numery_rejestracyjne) {
        this.numery_rejestracyjne = numery_rejestracyjne;
    }

    public String getKraj_rozladunku() {
        return kraj_rozladunku;
    }

    public void setKraj_rozladunku(String kraj_rozladunku) {
        this.kraj_rozladunku = kraj_rozladunku;
    }

    public String getData_rozladunku() {
        return data_rozladunku;
    }

    public void setData_rozladunku(String data_rozladunku) {
        this.data_rozladunku = data_rozladunku;
    }

    public String getMiejscowosc_rozladunku() {
        return miejscowosc_rozladunku;
    }

    public void setMiejscowosc_rozladunku(String miejscowosc_rozladunku) {
        this.miejscowosc_rozladunku = miejscowosc_rozladunku;
    }

    public String getKod_pocztowy_rozladunku() {
        return kod_pocztowy_rozladunku;
    }

    public void setKod_pocztowy_rozladunku(String kod_pocztowy_rozladunku) {
        this.kod_pocztowy_rozladunku = kod_pocztowy_rozladunku;
    }

    public String getFirma_rozladunek() {
        return firma_rozladunek;
    }

    public void setFirma_rozladunek(String firma_rozladunek) {
        this.firma_rozladunek = firma_rozladunek;
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

    public String getNazwa_uzytkownika() {
        return nazwa_uzytkownika;
    }

    public void setNazwa_uzytkownika(String nazwa_uzytkownika) {
        this.nazwa_uzytkownika = nazwa_uzytkownika;
    }

    public String getUlica_zaladunku() {
        return ulica_zaladunku;
    }

    public void setUlica_zaladunku(String ulica_zaladunku) {
        this.ulica_zaladunku = ulica_zaladunku;
    }

    public String getUlica_rozladunku() {
        return ulica_rozladunku;
    }

    public void setUlica_rozladunku(String ulica_rozladunku) {
        this.ulica_rozladunku = ulica_rozladunku;
    }

    public Double getKwota_frachtu() {
        return kwota_frachtu;
    }

    public void setKwota_frachtu(Double kwota_frachtu) {
        this.kwota_frachtu = kwota_frachtu;
    }

    public Double getKwota_zlecenia() {
        return kwota_zlecenia;
    }

    public void setKwota_zlecenia(Double kwota_zlecenia) {
        this.kwota_zlecenia = kwota_zlecenia;
    }

    public String getWaluta() {
        return waluta;
    }

    public void setWaluta(String waluta) {
        this.waluta = waluta;
    }

    public String getWarunku_zlecenia() {
        return warunku_zlecenia;
    }

    public void setWarunku_zlecenia(String warunku_zlecenia) {
        this.warunku_zlecenia = warunku_zlecenia;
    }
}
