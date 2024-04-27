package Encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class numer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numer;
    private String numer_zlecenia;
    private String nazwa_firmy;
    private int id_firmy;

    public numer()
    {

    }

    public numer(int numer, String numer_zlecenia, String nazwa_firmy, int id_firmy) {
        this.numer = numer;
        this.numer_zlecenia = numer_zlecenia;
        this.nazwa_firmy = nazwa_firmy;
        this.id_firmy = id_firmy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public String getNumer_zlecenia() {
        return numer_zlecenia;
    }

    public void setNumer_zlecenia(String numer_zlecenia) {
        this.numer_zlecenia = numer_zlecenia;
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
