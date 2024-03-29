package GlowneOkno;

import Metody.Metody;
import Repozytoria.uzytkownicyRepozytorium;
import Repozytoria.firmaRepozytorium;
import org.springframework.mail.javamail.JavaMailSender;

import javax.swing.*;
import java.awt.*;

public class GlowneAdmin extends JFrame {
    private uzytkownicyRepozytorium uzytkownicyRepozytorium;
    private firmaRepozytorium firmaRepozytorium;
    Metody metody=new Metody();
    JButton bDodajUsera,bDodajFirme;
    JavaMailSender javaMailSender;
    public  GlowneAdmin(uzytkownicyRepozytorium uzytkownicyRepozytorium,firmaRepozytorium firmaRepozytorium,JavaMailSender javaMailSender)
    {
        this.uzytkownicyRepozytorium = uzytkownicyRepozytorium;
        this.firmaRepozytorium=firmaRepozytorium;
        this.javaMailSender=javaMailSender;

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setExtendedState(JFrame.MAXIMIZED_BOTH);

      setLayout(new BorderLayout());

        JPanel panelpolnocny=new JPanel();
      panelpolnocny.setLayout(new FlowLayout());

      ImageIcon icon =metody.StworzObrazIcone("dodajusera.jpg");
       ImageIcon icon1=metody.PrzeskalujObraz(icon,80,80);
       bDodajUsera=metody.StworzPrzyciskzObrazem(bDodajUsera,"Dodaj użytkownika",icon1,140,100);
        bDodajUsera.addActionListener(e -> {
            dispose();

            DodajUseraAdmin dodajUseraAdmin=new DodajUseraAdmin(uzytkownicyRepozytorium,firmaRepozytorium,javaMailSender);
            dodajUseraAdmin.setVisible(true);
        });

        ImageIcon DodajFirme=metody.StworzObrazIcone("firma.jpg");
        ImageIcon DodajFirme1=metody.PrzeskalujObraz(DodajFirme,80,80);
        bDodajFirme=metody.StworzPrzyciskzObrazem(bDodajFirme,"Dodaj firmę",DodajFirme1,100,100);

        bDodajFirme.addActionListener(e -> {
            DodajFirmeAdmin dodajFirmeAdmin=new DodajFirmeAdmin(firmaRepozytorium);
            dodajFirmeAdmin.setVisible(true);
        });

      panelpolnocny.add(bDodajUsera);
      panelpolnocny.add(bDodajFirme);



        add(panelpolnocny,BorderLayout.NORTH);

        setVisible(true);
    }
}
