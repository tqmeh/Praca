package PoboczneOkna;

import Metody.Metody;
import Repozytoria.*;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import Ograniczenia.*;
import com.toedter.calendar.JDateChooser;

public class NoweZlecenie extends JFrame {
    Metody metody=new Metody();
    JPanel PanelZachodni,PanelPolnocny,PanelCentralny;
    LimitowanyText tZleceniodawca,tNumerZlecenia,tIlosc,tWaga,tGodzinaZaladunku,tGodzinaZaladunku1,tMiejscoscZaladunku,tKodPocztowy,
    tPrzewzonik,tKierowca,tNumeryRejestracyjne,tGodzinaRozladunku,tGodzinaRozladunku1,tMiastoRozladunku,tKodPocztowyRozladunku;
    JComboBox cZlecenie,cRodzajTowaru,cKrajZaladunku,cRodzajSamochodu,cKrajRozladunku;
    JDateChooser DataZaladunku,DataRozladunku;
    JTextField tDataZaladunku,tDataRozladunku;
    JButton bDodajZleceniodawce,bDodajPrzewoznika;

    LimitowanyTextArea aFirmaZaladunku,aFirmaRozladunku;
    krajRepozytorium KrajRepozytorium;
    uzytkownicyRepozytorium UzytkownicyRepozytorium;
    zleceniodawcaRepozytorium ZleceniodawcaRepozytorium;
    private int userID;
    public NoweZlecenie(krajRepozytorium KrajRepozytorium,uzytkownicyRepozytorium UzytkownicyRepozytorium,zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,
                        int userID)
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        this.KrajRepozytorium=KrajRepozytorium;
        this.UzytkownicyRepozytorium=UzytkownicyRepozytorium;
        this.ZleceniodawcaRepozytorium=ZleceniodawcaRepozytorium;
        this.userID=userID;
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
        lFirmaZaladunku,lPrzewoznik,lTowar,lRodzaj,lIlosc,lWaga,lGodzinaZaladunku,lRodzajSamochodu,lKierowca,lNumeryRejestracyjne,
        lRozladunek,lDataRozladunku,lGodzinaRozladunku,lMiejscowoscRozladunku,lKodPocztowyRozladunku,lFirmaRozladunku,lKilogramy,
                lGodzinaZaladunku1,lKrajZaladunku,lKrajRozladunku,lGodzinaRozladunku1;




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
        lRodzajSamochodu=new JLabel();
        lKierowca=new JLabel();
        lNumeryRejestracyjne=new JLabel();
        lRozladunek=new JLabel();
        lDataRozladunku=new JLabel();
        lGodzinaRozladunku=new JLabel();
        lMiejscowoscRozladunku=new JLabel();
        lKodPocztowyRozladunku=new JLabel();
        lFirmaRozladunku=new JLabel();
        lKilogramy=new JLabel();
        lGodzinaRozladunku1=new JLabel();
        lKrajZaladunku=new JLabel();
        lKrajRozladunku=new JLabel();
        lGodzinaZaladunku1=new JLabel();
        DataZaladunku=new JDateChooser();
        tZleceniodawca=new LimitowanyText(20,false);
        tNumerZlecenia=new LimitowanyText(30,false);
        cZlecenie=new JComboBox();
        cRodzajTowaru=new JComboBox();
        cKrajZaladunku=new JComboBox();
        cKrajRozladunku=new JComboBox();
        tIlosc=new LimitowanyText(5,true);
        tWaga=new LimitowanyText(5,true);
        tGodzinaZaladunku=new LimitowanyText(2,true);
        tGodzinaZaladunku1=new LimitowanyText(2,true);
        tDataZaladunku=new JTextField();
        tMiejscoscZaladunku=new LimitowanyText(40,false);
        tKodPocztowy=new LimitowanyText(10,true);
        tPrzewzonik=new LimitowanyText(30,false);
        aFirmaZaladunku=new LimitowanyTextArea(150);
        cRodzajSamochodu=new JComboBox();
        tKierowca=new LimitowanyText(30,false);
        tNumeryRejestracyjne=new LimitowanyText(20,false);
        DataRozladunku=new JDateChooser();
        tDataRozladunku=new JTextField();
        tGodzinaRozladunku=new LimitowanyText(2,true);
        tGodzinaRozladunku1=new LimitowanyText(2,true);
        tMiastoRozladunku=new LimitowanyText(40,false);
        tKodPocztowyRozladunku=new LimitowanyText(10,true);
        aFirmaRozladunku=new LimitowanyTextArea(150);
        bDodajZleceniodawce=new JButton();
        bDodajPrzewoznika=new JButton();

        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);
        bDodajZleceniodawce=metody.StworzPrzyciskzObrazembezTekstuzWyboremUmiejscowienia(Plus1,290,40,20,20,panel);
        bDodajZleceniodawce.addActionListener(e -> {
            DodajZleceniodawce dodajZleceniodawce=new DodajZleceniodawce(KrajRepozytorium,UzytkownicyRepozytorium,ZleceniodawcaRepozytorium,userID);
            dodajZleceniodawce.setVisible(true);
        });


      bDodajPrzewoznika= metody.StworzPrzyciskzObrazembezTekstuzWyboremUmiejscowienia(Plus1,910,10,20,20,panel);




       metody.StworzNapisPanel(lTransport,"Transport",10,10,100,20,panel);
       metody.StworzNapisPanel(lZleceniodawca,"Zleceniodawca",10,40,100,20,panel);
       metody.StworzNapisPanel(lNumerZlecenia,"Numer zlecenia",10,70,100,20,panel);
       metody.StworzNapisPanel(lTowar,"Towar",10,180,100,20,panel);
        lTowar.setFont(lTowar.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisPanel(lRodzaj,"Rodzaj",10,210,100,20,panel);
        metody.StworzNapisPanel(lIlosc,"Ilość",10,240,100,20,panel);
        metody.StworzNapisPanel(lWaga,"Waga",140,240,100,20,panel);
        metody.StworzNapisPanel(lZaladunek,"Zaladunek",10,310,100,20,panel);
       lZaladunek.setFont(lZaladunek.getFont().deriveFont(Font.BOLD));//pogrubienie
        metody.StworzNapisPanel(lKrajZaladunku,"Kraj",10,340,100,20,panel);
       metody.StworzNapisPanel(lDataZaladunku,"Data załadunku",10,370,100,20,panel);
       metody.StworzNapisPanel(lGodzinaZaladunku,"Godzina",240,370,100,20,panel);
       metody.StworzNapisPanel(lGodzinaZaladunku1,":",325,370,100,20,panel);
        metody.StworzNapisPanel(lMiejscowoscZaladunku,"Miejscowość ",10,400,100,20,panel);
        metody.StworzNapisPanel(lKodPocztowyZaladunku,"Kod pocztowy",10,430,100,20,panel);
        metody.StworzNapisPanel(lFirmaZaladunku,"Firma",10,460,100,20,panel);
        metody.StworzNapisPanel(lPrzewoznik,"Przewoźnik",600,10,100,20,panel);
        metody.StworzNapisPanel(lRodzajSamochodu,"Rodzaj samochodu",600,40,140,20,panel);
        metody.StworzNapisPanel(lKierowca,"Kierowca",600,70,100,20,panel);
        metody.StworzNapisPanel(lNumeryRejestracyjne,"Numery rejestracyjne",600,100,150,20,panel);
        metody.StworzNapisPanel(lRozladunek,"Rozladunek",600,310,100,20,panel);
        lRozladunek.setFont(lRozladunek.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisPanel(lKrajRozladunku,"Kraj",600,340,100,20,panel);
        cKrajRozladunku.setBounds(700,340,160,20);
        metody.StworzNapisPanel(lDataRozladunku,"Data rozładunku",600,370,100,20,panel);
        DataRozladunku.setBounds(700,370,20,20);
        tDataRozladunku.setBounds(725,370,100,20);
        metody.StworzNapisPanel(lGodzinaRozladunku,"Godzina",840,370,100,20,panel);
        metody.StworzNapisPanel(lMiejscowoscRozladunku,"Miejscowosc",600,400,100,20,panel);
        metody.StworzNapisPanel(lKodPocztowyRozladunku,"Kod pocztowy",600,430,100,20,panel);
        metody.StworzNapisPanel(lFirmaRozladunku,"Firma",600,460,100,20,panel);
        metody.StworzNapisPanel(lKilogramy,"Kg",255,240,20,20,panel);
        cZlecenie.setBounds(120,10,160,20);
        metody.StworzLimitowanyText(tZleceniodawca,120,40,160,20,panel);
        metody.StworzLimitowanyText(tNumerZlecenia,120,70,160,20,panel);
        cRodzajTowaru.setBounds(60,210,160,20);
        metody.StworzLimitowanyText(tIlosc,60,240,60,20,panel);
        metody.StworzLimitowanyText(tWaga,190,240,60,20,panel);
        cKrajZaladunku.setBounds(100,340,160,20);
        metody.StworzLimitowanyText(tGodzinaZaladunku,290,370,30,20,panel);
        metody.StworzLimitowanyText(tGodzinaZaladunku1,332,370,30,20,panel);
        DataZaladunku.setBounds(100,370,20,20);
        tDataZaladunku.setBounds(125,370,100,20);
        metody.StworzLimitowanyText(tMiejscoscZaladunku,100,400,150,20,panel);
        metody.StworzLimitowanyText(tKodPocztowy,100,430,100,20,panel);
        aFirmaZaladunku.setBounds(100,460,250,70);
        aFirmaZaladunku.setWrapStyleWord(true);
        aFirmaZaladunku.setLineWrap(true);
        aFirmaZaladunku.setCaretPosition(0);
        metody.StworzLimitowanyText(tPrzewzonik,720,10,180,20,panel);
        cRodzajSamochodu.setBounds(720,40,180,20);
        metody.StworzLimitowanyText(tKierowca,720,70,180,20,panel);
        metody.StworzLimitowanyText(tNumeryRejestracyjne,720,100,150,20,panel);
        metody.StworzLimitowanyText(tGodzinaRozladunku,890,370,30,20,panel);
        metody.StworzNapisPanel(lGodzinaRozladunku1,":",925,370,100,20,panel);
        metody.StworzLimitowanyText(tGodzinaRozladunku1,932,370,30,20,panel);
        metody.StworzLimitowanyText(tMiastoRozladunku,700,400,150,20,panel);
        metody.StworzLimitowanyText(tKodPocztowyRozladunku,700,430,100,20,panel);
        aFirmaRozladunku.setBounds(700,460,250,70);
        aFirmaRozladunku.setWrapStyleWord(true);
        aFirmaRozladunku.setLineWrap(true);
        aFirmaRozladunku.setCaretPosition(0);
        panel.add(cKrajRozladunku);
        panel.add(cKrajZaladunku);
        panel.add(aFirmaZaladunku);
        panel.add(DataZaladunku);
        panel.add(cZlecenie);
        panel.add(cRodzajTowaru);
        panel.add(tDataZaladunku);
        panel.add(cRodzajSamochodu);
        panel.add(DataRozladunku);
        panel.add(tDataRozladunku);
        panel.add(aFirmaRozladunku);
        return panel;
    }
}
