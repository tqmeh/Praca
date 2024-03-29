package PoboczneOkna;

import Metody.Metody;

import javax.swing.*;
import java.awt.*;
import Ograniczenia.*;
public class NoweZlecenie extends JFrame {
    Metody metody=new Metody();
    JPanel PanelZachodni,PanelPolnocny,PanelCentralny;


    public NoweZlecenie()
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        PanelZachodni=StworzPanelZachodni();
        PanelPolnocny=StworzPanelPolnocny();
        PanelCentralny=StworzPanelCentralny();

        this.add(PanelCentralny, BorderLayout.CENTER);

    }
    public JPanel StworzPanelZachodni()
    {
        JPanel panel=new JPanel();
        panel.setBackground(Color.red);
        panel.setPreferredSize(new Dimension(100,100));
        add(panel,BorderLayout.WEST);
        return panel;
    }
    public JPanel StworzPanelPolnocny()
    {
        JPanel panel=new JPanel();
        panel.setBackground(Color.blue);
        panel.setPreferredSize(new Dimension(100, 100));
        add(panel, BorderLayout.NORTH);
        return panel;
    }
    public JPanel StworzPanelCentralny()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        panel.setLayout(null);

        JLabel lTransport,lZleceniodawca,lNumerZlecenia,lDataZaladunku,lMiejscowoscZaladunku,lKodPocztowyZaladunku,lZaladunek,
        lFirmaZaladunku,lPrzewoznik,lTowar,lRodzaj,lIlosc,lWaga,lGodzinaZaladunku,lUwagiZaladunek,lRodzajSamochodu,lKierowca,lNumeryRejestracyjne,
        lRozladunek,lDataRozladunku,lGodzinaRozladunku,lMiejscowoscRozladunku,lKodPocztowyRozladunku,lFirmaRozladunku,lUwagiRozladunek;

        LimitowanyText tTransport;
        tTransport=new LimitowanyText(20,false);

        lTransport=new JLabel();
        lZleceniodawca=new JLabel();
        lNumerZlecenia=new JLabel();
        lDataZaladunku=new JLabel();
        lMiejscowoscZaladunku=new JLabel();
        lKodPocztowyZaladunku=new JLabel();
        lZaladunek=new JLabel();
        lFirmaZaladunku=new JLabel();
        lPrzewoznik=new JLabel();
        lTowar=new JLabel();
        lRodzaj=new JLabel();
        lIlosc=new JLabel();
        lWaga=new JLabel();
        lGodzinaZaladunku=new JLabel();
        lUwagiZaladunek=new JLabel();
        lRodzajSamochodu=new JLabel();
        lKierowca=new JLabel();
        lNumeryRejestracyjne=new JLabel();
        lRozladunek=new JLabel();
        lDataRozladunku=new JLabel();
        lGodzinaRozladunku=new JLabel();
        lMiejscowoscRozladunku=new JLabel();
        lKodPocztowyRozladunku=new JLabel();
        lFirmaRozladunku=new JLabel();
        lUwagiRozladunek=new JLabel();
       metody.StworzNapisPanel(lTransport,"Transport",10,10,100,20,panel);
       metody.StworzNapisPanel(lZleceniodawca,"Zleceniodawca",10,40,100,20,panel);
       metody.StworzNapisPanel(lNumerZlecenia,"Numer zlecenia",10,70,100,20,panel);
       metody.StworzNapisPanel(lTowar,"Towar",10,180,100,20,panel);
        lTowar.setFont(lTowar.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisPanel(lRodzaj,"Rodzaj",10,210,100,20,panel);
        metody.StworzNapisPanel(lIlosc,"Ilosc",10,240,100,20,panel);
        metody.StworzNapisPanel(lWaga,"Waga",140,240,100,20,panel);
        metody.StworzNapisPanel(lZaladunek,"Zaladunek",10,310,100,20,panel);
       lZaladunek.setFont(lZaladunek.getFont().deriveFont(Font.BOLD));//pogrubienie
       metody.StworzNapisPanel(lDataZaladunku,"Data załadunku",10,340,100,20,panel);
       metody.StworzNapisPanel(lGodzinaZaladunku,"Godzina",140,340,100,20,panel);
        metody.StworzNapisPanel(lMiejscowoscZaladunku,"Miejscowość ",10,370,100,20,panel);
        metody.StworzNapisPanel(lKodPocztowyZaladunku,"Kod pocztowy",10,400,100,20,panel);
        metody.StworzNapisPanel(lFirmaZaladunku,"Firma",10,430,100,20,panel);
        metody.StworzNapisPanel(lUwagiZaladunek,"Uwagi",10,500,100,20,panel);
        metody.StworzNapisPanel(lPrzewoznik,"Przewoźnik",600,10,100,20,panel);
        metody.StworzNapisPanel(lRodzajSamochodu,"Rodzaj samochodu",600,40,140,20,panel);
        metody.StworzNapisPanel(lKierowca,"Kierowca",600,70,100,20,panel);
        metody.StworzNapisPanel(lNumeryRejestracyjne,"Numery rejestracyjne",600,100,150,20,panel);
        metody.StworzNapisPanel(lRozladunek,"Rozladunek",600,310,100,20,panel);
        lRozladunek.setFont(lRozladunek.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisPanel(lDataRozladunku,"Data rozładunku",600,340,100,20,panel);
        metody.StworzNapisPanel(lGodzinaRozladunku,"Godzina",730,340,100,20,panel);
        metody.StworzNapisPanel(lMiejscowoscRozladunku,"Miejscowosc",600,370,100,20,panel);
        metody.StworzNapisPanel(lKodPocztowyRozladunku,"Kod pocztowy",600,400,100,20,panel);
        metody.StworzNapisPanel(lFirmaRozladunku,"Firma",600,430,100,20,panel);
        metody.StworzNapisPanel(lUwagiRozladunek,"Uwagi",600,500,100,20,panel);

        metody.StworzLimitowanyText(tTransport,120,40,100,20,panel);

        return panel;
    }
}
