package GlowneOkno;

import Logowanie.Logowanie;
import Metody.Metody;
import PoboczneOkna.*;
import Repozytoria.*;
import org.springframework.mail.javamail.JavaMailSender;
import javax.swing.*;
import java.awt.*;

public class OknoGlowneProgramu extends JFrame {

    JavaMailSender javaMailSender;
     Metody metody=new Metody();
        private int userID;
        uzytkownicyRepozytorium UzytkownicyRepozytorium;
     JButton bUstawienia,bZlecenie;
    public OknoGlowneProgramu(int userID, uzytkownicyRepozytorium UzytkownicyRepozytorium,JavaMailSender javaMailSender)
    {

        this.userID=userID;
        this.UzytkownicyRepozytorium=UzytkownicyRepozytorium;
        this.javaMailSender=javaMailSender;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        JPanel panelPolnocny=new JPanel();
        panelPolnocny.setLayout(new FlowLayout());
        ImageIcon icon =metody.StworzObrazIcone("ustawienia.jpg");
        ImageIcon icon1=metody.PrzeskalujObraz(icon,80,80);
        ImageIcon iconZlecenie =metody.StworzObrazIcone("DodajZlecenie.jpg");
        ImageIcon iconZlecenie1=metody.PrzeskalujObraz(iconZlecenie,80,80);
        bUstawienia=metody.StworzPrzyciskzObrazem(bUstawienia,"Ustawienia",icon1,140,100);
        bZlecenie=metody.StworzPrzyciskzObrazem(bZlecenie,"Zlecenia",iconZlecenie1,140,100);
        bUstawienia.setVisible(false);



        Integer wlasciciel=UzytkownicyRepozytorium.findWlascicielById(userID);//wykorzystywanie repozytoria do wyszukiwania danych
        if(wlasciciel==1)
        {
            bUstawienia.setVisible(true);
        }

        bUstawienia.addActionListener(e -> {
            UstawieniaWlasciciel ustawieniaWlasciciel=new UstawieniaWlasciciel(userID,UzytkownicyRepozytorium,javaMailSender);
            ustawieniaWlasciciel.setVisible(true);

        });
        bZlecenie.addActionListener(e -> {
            Zlecenie zlecenie=new Zlecenie();
            zlecenie.setVisible(true);
        });

        panelPolnocny.add(bUstawienia);
        panelPolnocny.add(bZlecenie);
        add(panelPolnocny,BorderLayout.NORTH);
    }
}