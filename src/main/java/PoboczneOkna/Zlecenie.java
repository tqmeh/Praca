package PoboczneOkna;

import Metody.Metody;
import Repozytoria.*;
import javax.swing.*;
import java.awt.*;

public class Zlecenie extends JFrame {

    JPanel panelZachodni;
    Metody metody=new Metody();
    JButton bDodaj;
    krajRepozytorium KrajRepozytorium;
    uzytkownicyRepozytorium UzytkownicyRepozytorium;
    zleceniodawcaRepozytorium ZleceniodawcaRepozytorium;
    przewoznikRepozytorium PrzewoznikRepozytorium;
    private int userID;
    public Zlecenie(krajRepozytorium KrajRepozytorium, uzytkownicyRepozytorium UytkownicyRepozytorium,zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,
                    int userID,przewoznikRepozytorium PrzewoznikRepozytorium)
    {
        this.KrajRepozytorium=KrajRepozytorium;
        this.UzytkownicyRepozytorium=UytkownicyRepozytorium;
        this.ZleceniodawcaRepozytorium=ZleceniodawcaRepozytorium;
        this.PrzewoznikRepozytorium=PrzewoznikRepozytorium;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        this.userID=userID;

        panelZachodni =new JPanel();
        panelZachodni.setLayout(new BoxLayout(panelZachodni,BoxLayout.Y_AXIS));
        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);

        bDodaj=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Dodaj",Plus1,100,20);
        bDodaj.addActionListener(e -> {
            NoweZlecenie noweZlecenie=new NoweZlecenie(KrajRepozytorium,UzytkownicyRepozytorium,ZleceniodawcaRepozytorium,userID,PrzewoznikRepozytorium);
            noweZlecenie.setVisible(true);
        });
        panelZachodni.add(bDodaj);
        add(panelZachodni);
        setVisible(true);
        add(panelZachodni,BorderLayout.WEST);


    }
}
