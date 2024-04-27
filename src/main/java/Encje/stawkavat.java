package Encje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class stawkavat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stawka;
    public stawkavat()
    {

    }

    public stawkavat(String stawka) {
        this.stawka = stawka;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStawka() {
        return stawka;
    }

    public void setStawka(String stawka) {
        this.stawka = stawka;
    }
}
