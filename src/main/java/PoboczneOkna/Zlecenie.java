package PoboczneOkna;

import Metody.Metody;

import javax.swing.*;
import java.awt.*;

public class Zlecenie extends JFrame {

    JPanel panelZachodni;
    Metody metody=new Metody();
    JButton bDodaj;
    public Zlecenie()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        panelZachodni =new JPanel();
        panelZachodni.setLayout(new BoxLayout(panelZachodni,BoxLayout.Y_AXIS));
        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);

        bDodaj=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Dodaj",Plus1,100,20);
        bDodaj.addActionListener(e -> {
            NoweZlecenie noweZlecenie=new NoweZlecenie();
            noweZlecenie.setVisible(true);
        });
        panelZachodni.add(bDodaj);
        add(panelZachodni);
        setVisible(true);
        add(panelZachodni,BorderLayout.WEST);


    }
}
