package PoboczneOkna;

import Metody.Metody;
import Repozytoria.*;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import Ograniczenia.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.toedter.calendar.JDateChooser;
import Encje.*;
public class NoweZlecenie extends JFrame {
    Metody metody=new Metody();
    JPanel PanelZachodni,PanelPolnocny,PanelCentralny,PanelCentralny1;
    LimitowanyText tZleceniodawca, tNumerZlecenia,tIlosc,tWaga,tGodzinaZaladunku,tGodzinaZaladunku1,tMiejscoscZaladunku,tKodPocztowy,
    tPrzewzonik,tKierowca,tNumeryRejestracyjne,tGodzinaRozladunku,tGodzinaRozladunku1,tMiastoRozladunku,tKodPocztowyRozladunku,
    tKwotaFrachtu,tKwotaZlecenia,tUlicaZaladunku,tUlicaRozladunku;

    JComboBox cZlecenie,cRodzajTowaru,cKrajZaladunku,cRodzajSamochodu,cKrajRozladunku,cWaluta;
    JDateChooser DataZaladunku,DataRozladunku;
    JTextField tDataZaladunku,tDataRozladunku;
    JButton bDodajZleceniodawce,bDodajPrzewoznika;

    LimitowanyTextArea aFirmaZaladunku,aFirmaRozladunku,aWarunkiZlecenia;
    krajRepozytorium KrajRepozytorium;
    uzytkownicyRepozytorium UzytkownicyRepozytorium;
    zleceniodawcaRepozytorium ZleceniodawcaRepozytorium;
    przewoznikRepozytorium PrzewoznikRepozytorium;
    wykonawcaRepozytorium WykonawcaRepozytorium;

    samochodRepozytorium SamochodRepozytorium;

    towarRepozytorium TowarRepozytorium;
    zlecenieRepozytorium ZlecenieRepozytorium;

    walutaRepozytorium WalutaRepozytorium;


    String sZleceniodawca,sNumerZlecenia,sTowarRodzaj,sIlosc,sWaga,sKrajZaladunku,sGodzinaZaladunku,sGodzinaZaladunku1,
    sMiejscowoscZaladunku,sKodPocztowyZaladunku,sFirmaZaladunku,sPrzewoznik,sRodzajSamochodu,sKierowca,sNumerRejestracyjny,sKrajRozladunku,
    sGodzinaRozladunku,sGodzinaRozladunku1,sMiejscowoscRozladunku,sKodPocztowyRozladunku,sFirmaRozladunku,NazwaFirmy,NazwaUzytkownika,
    sWaluta,sUlicaZaladunku,sUlicaRozladunku,sKwotaFrachtu,sKwotaZlecenia,sWarunkuZlecenia;
    //Dane do wysylki do bazydanych
    String DataRozladunku1,DataZaladunku1,sTransport;// nie zapomniec o sTransport wybor z cWlasnyTransport
    int iIlosc,iWaga,IdFirmy;

    JButton bZapisz,bWyjdz;
    JTabbedPane zakladka1,zakladka2;

    double dKwotaZlecenia,dKwotaFrachtu;
    private int userID;
    public NoweZlecenie(krajRepozytorium KrajRepozytorium,uzytkownicyRepozytorium UzytkownicyRepozytorium,zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,
                        int userID,przewoznikRepozytorium PrzewoznikRepozytorium,wykonawcaRepozytorium WykonawcaRepozytorium,samochodRepozytorium SamochodRepozytorium,
                        towarRepozytorium TowarRepozytorium,zlecenieRepozytorium ZlecenieRepozytorium,walutaRepozytorium WalutaRepozytorium)
    {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        NazwaFirmy=UzytkownicyRepozytorium.findNazwaFirmyByUserId(userID);
        NazwaUzytkownika=UzytkownicyRepozytorium.findNazwaUzytkownikaById(userID);
        IdFirmy=UzytkownicyRepozytorium.findFirmaIdByUserId(userID);
        this.KrajRepozytorium=KrajRepozytorium;
        this.UzytkownicyRepozytorium=UzytkownicyRepozytorium;
        this.ZleceniodawcaRepozytorium=ZleceniodawcaRepozytorium;
        this.userID=userID;
        this.PrzewoznikRepozytorium=PrzewoznikRepozytorium;
        this.WykonawcaRepozytorium=WykonawcaRepozytorium;
        this.SamochodRepozytorium=SamochodRepozytorium;
        this.TowarRepozytorium=TowarRepozytorium;
        this.ZlecenieRepozytorium=ZlecenieRepozytorium;
        this.WalutaRepozytorium=WalutaRepozytorium;
        PanelZachodni=StworzPanelZachodni();
        PanelPolnocny=StworzPanelPolnocny();
        PanelCentralny=StworzPanelCentralny();
        PanelCentralny1=StworzPanelDodawaniaInformacjiDoZlecenia();

        zakladka1=new JTabbedPane();
        zakladka2=new JTabbedPane();
        zakladka1.addTab("Dodaj zlecenie",StworzPanelCentralny());
        zakladka1.addTab("Warunku zlecenia",StworzPanelDodawaniaInformacjiDoZlecenia());

        this.add(PanelCentralny, BorderLayout.CENTER);
        add(zakladka1,BorderLayout.CENTER);
        zakladka1.addChangeListener(e -> {

        });
        SprawdzWykonawceNaPoczatku();
        tZleceniodawca.setEditable(false);
    }
    public JPanel StworzPanelZachodni()
    {
        JPanel panel=new JPanel();
        panel.setBackground(Color.red);
        panel.setPreferredSize(new Dimension(100,100));
        bZapisz=new JButton();
        ImageIcon Zapisz = metody.StworzObrazIcone("Akceptuj.png");
        ImageIcon Zapisz1 = metody.PrzeskalujObraz(Zapisz, 20, 20);
        bZapisz = metody.StworzPrzyciskzObrazemzTekstemObok(bZapisz, "Zapisz", Zapisz1, 100, 20);
        bZapisz.addActionListener(e -> {
            System.out.println("sTransport to "+sTransport);
            Pobierz();
            SprawdzCzyWszystkoWypelnione();
        });
        ImageIcon Wyjdz=metody.StworzObrazIcone("wyjdz.jpg");
        ImageIcon Wyjdz1=metody.PrzeskalujObraz(Wyjdz,20,20);
        bWyjdz=metody.StworzPrzyciskzObrazemzTekstemObok(bWyjdz,"Wyjdź",Wyjdz1,100,20);
        bWyjdz.addActionListener(e -> {
            dispose();
        });

        panel.add(bZapisz);
        panel.add(bWyjdz);
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
    public JPanel StworzPanelDodawaniaInformacjiDoZlecenia()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setLayout(null);

        JLabel lDodajWarunkuZlecenia;
        aWarunkiZlecenia=new LimitowanyTextArea(500);
        lDodajWarunkuZlecenia=new JLabel();

        metody.StworzNapisPanel(lDodajWarunkuZlecenia,"Dodaj warunki zlecenia",20,20,150,20,panel);
       aWarunkiZlecenia.setBounds(20,50,600,200);
        aWarunkiZlecenia.setWrapStyleWord(true);
        aWarunkiZlecenia.setLineWrap(true);
        aWarunkiZlecenia.setCaretPosition(0);



        panel.add(aWarunkiZlecenia);
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
                lGodzinaZaladunku1,lKrajZaladunku,lKrajRozladunku,lGodzinaRozladunku1,lCena,lKwotaFrachtu,lKwotaZlecenia,lUlicaZaladunku,
        lUlicaRozladunku;




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
        lCena=new JLabel();
        lKwotaFrachtu=new JLabel();
        lKwotaZlecenia=new JLabel();
        lUlicaZaladunku=new JLabel();
        lUlicaRozladunku=new JLabel();
        DataZaladunku=new JDateChooser();
        tZleceniodawca=new LimitowanyText(20,false);
        tNumerZlecenia=new LimitowanyText(30,false);
        cZlecenie=new JComboBox();
        cRodzajTowaru=new JComboBox();
        cKrajZaladunku=new JComboBox();
        cKrajRozladunku=new JComboBox();
        cWaluta=new JComboBox();
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
        tKwotaFrachtu=new LimitowanyText(7,true);
        tKwotaZlecenia= new LimitowanyText(7,true);
        tKwotaZlecenia.setEditable(false);
        tUlicaZaladunku=new LimitowanyText(40,false);
        tUlicaRozladunku=new LimitowanyText(50,false);
        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);
        bDodajZleceniodawce=metody.StworzPrzyciskzObrazembezTekstuzWyboremUmiejscowienia(Plus1,290,40,20,20,panel);
        bDodajZleceniodawce.addActionListener(e -> {
            DodajZleceniodawce dodajZleceniodawce=new DodajZleceniodawce(KrajRepozytorium,UzytkownicyRepozytorium,ZleceniodawcaRepozytorium,userID,this);
            dodajZleceniodawce.setVisible(true);
        });


      bDodajPrzewoznika= metody.StworzPrzyciskzObrazembezTekstuzWyboremUmiejscowienia(Plus1,910,10,20,20,panel);
      bDodajPrzewoznika.addActionListener(e -> {
          DodajPrzewoznika dodajPrzewoznika=new DodajPrzewoznika(KrajRepozytorium,UzytkownicyRepozytorium,PrzewoznikRepozytorium,userID,this);
          dodajPrzewoznika.setVisible(true);
      });




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
        metody.StworzNapisPanel(lUlicaZaladunku,"Ulica",10,460,100,20,panel);
        metody.StworzNapisPanel(lKodPocztowyZaladunku,"Kod pocztowy",10,430,100,20,panel);
        metody.StworzNapisPanel(lFirmaZaladunku,"Firma",10,490,100,20,panel);
        metody.StworzNapisPanel(lPrzewoznik,"Przewoźnik",600,10,100,20,panel);
        metody.StworzNapisPanel(lRodzajSamochodu,"Rodzaj samochodu",600,40,140,20,panel);
        metody.StworzNapisPanel(lKierowca,"Kierowca",600,70,100,20,panel);
        metody.StworzNapisPanel(lNumeryRejestracyjne,"Numery rejestracyjne",600,100,150,20,panel);
        metody.StworzNapisPanel(lCena,"Cena",600,180,100,20,panel);
        lCena.setFont(lCena.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisPanel(lKwotaFrachtu,"Kwota frachtu",600,210,150,20,panel);
        metody.StworzNapisPanel(lKwotaZlecenia,"Kwota zlecenia",600,240,150,20,panel);

        metody.StworzNapisPanel(lRozladunek,"Rozladunek",600,310,100,20,panel);
        lRozladunek.setFont(lRozladunek.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisPanel(lKrajRozladunku,"Kraj",600,340,100,20,panel);
        cKrajRozladunku.setBounds(700,340,160,20);
        DodajDaneKrajowdoJComboBoxa(cKrajRozladunku);
        metody.StworzNapisPanel(lDataRozladunku,"Data rozładunku",600,370,100,20,panel);
        DataRozladunku.setBounds(700,370,20,20);
        DataRozladunku.getDateEditor().addPropertyChangeListener(evt -> {
            if("date".equals(evt.getPropertyName()))
            {
                Date wybranaData=DataRozladunku.getDate();
                SimpleDateFormat formatDaty=new SimpleDateFormat("yyyy-MM-dd");
                DataRozladunku1=formatDaty.format(wybranaData);
                tDataRozladunku.setText(DataRozladunku1);
            }
        });
        tDataRozladunku.setBounds(725,370,100,20);
        tDataRozladunku.setEditable(false);
        metody.StworzNapisPanel(lGodzinaRozladunku,"Godzina",840,370,100,20,panel);
        metody.StworzNapisPanel(lMiejscowoscRozladunku,"Miejscowosc",600,400,100,20,panel);

        metody.StworzNapisPanel(lKodPocztowyRozladunku,"Kod pocztowy",600,430,100,20,panel);
        metody.StworzNapisPanel(lUlicaRozladunku,"Ulica",600,460,100,20,panel);
        metody.StworzNapisPanel(lFirmaRozladunku,"Firma",600,490,100,20,panel);
        metody.StworzNapisPanel(lKilogramy,"Kg",255,240,20,20,panel);
        cZlecenie.setBounds(120,10,160,20);
        DodajDaneWykonawcaJComboBoxa(cZlecenie);
        SprawdzWykonawce(cZlecenie);//sprawdzam cos
        metody.StworzLimitowanyText(tZleceniodawca,120,40,160,20,panel);
        metody.StworzLimitowanyText(tNumerZlecenia,120,70,160,20,panel);
        cRodzajTowaru.setBounds(60,210,160,20);
        DodajDaneTowarJComboBoxa(cRodzajTowaru);
        metody.StworzLimitowanyText(tIlosc,60,240,60,20,panel);
        metody.StworzLimitowanyText(tWaga,190,240,60,20,panel);
        cKrajZaladunku.setBounds(100,340,160,20);
        DodajDaneKrajowdoJComboBoxa(cKrajZaladunku);
        metody.StworzLimitowanyText(tGodzinaZaladunku,290,370,30,20,panel);
        metody.StworzLimitowanyText(tGodzinaZaladunku1,332,370,30,20,panel);
        DataZaladunku.setBounds(100,370,20,20);
        DataZaladunku.getDateEditor().addPropertyChangeListener(evt -> {// ustawienie JDateChooser do wyslania do JTextFielda
            if("date".equals(evt.getPropertyName()))
            {
                Date wybranaData=DataZaladunku.getDate();
                SimpleDateFormat formatDaty=new SimpleDateFormat("yyyy-MM-dd");
                DataZaladunku1=formatDaty.format(wybranaData);
                tDataZaladunku.setText(DataZaladunku1);
            }
        });
        tDataZaladunku.setBounds(125,370,100,20);
        tDataZaladunku.setEditable(false);
        metody.StworzLimitowanyText(tMiejscoscZaladunku,100,400,150,20,panel);
        metody.StworzLimitowanyText(tKodPocztowy,100,430,100,20,panel);
        metody.StworzLimitowanyText(tUlicaZaladunku,100,460,250,20,panel);
        aFirmaZaladunku.setBounds(100,490,250,70);
        aFirmaZaladunku.setWrapStyleWord(true);
        aFirmaZaladunku.setLineWrap(true);
        aFirmaZaladunku.setCaretPosition(0);
        metody.StworzLimitowanyText(tPrzewzonik,720,10,180,20,panel);
        cRodzajSamochodu.setBounds(720,40,180,20);
        DodajDaneSamochodJComboBoxa(cRodzajSamochodu);
        metody.StworzLimitowanyText(tKierowca,720,70,180,20,panel);
        metody.StworzLimitowanyText(tNumeryRejestracyjne,720,100,150,20,panel);
        metody.StworzLimitowanyText(tKwotaFrachtu,720,210,100,20,panel);
        cWaluta.setBounds(830,210,70,20);
        DodajDaneWalutaJCoomboBoxa(cWaluta);
        metody.StworzLimitowanyText(tKwotaZlecenia,720,240,100,20,panel);
        metody.StworzLimitowanyText(tGodzinaRozladunku,890,370,30,20,panel);
        metody.StworzNapisPanel(lGodzinaRozladunku1,":",925,370,100,20,panel);
        metody.StworzLimitowanyText(tGodzinaRozladunku1,932,370,30,20,panel);
        metody.StworzLimitowanyText(tMiastoRozladunku,700,400,150,20,panel);
        metody.StworzLimitowanyText(tKodPocztowyRozladunku,700,430,100,20,panel);
        metody.StworzLimitowanyText(tUlicaRozladunku,700,460,250,20,panel);
        aFirmaRozladunku.setBounds(700,490,250,70);
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
        panel.add(cWaluta);

        return panel;
    }



    public void setZleceniodawca(String tekst) {

        tZleceniodawca.setText(tekst);

    }
    public void settPrzewzonik(String tekst)
    {
        tPrzewzonik.setText(tekst);
    }
    public void DodajDaneKrajowdoJComboBoxa(JComboBox comboBox) {

        List<kraj> krajList = KrajRepozytorium.findAll();
        comboBox.removeAllItems();
        for (Encje.kraj kraj : krajList) {
            comboBox.addItem(kraj.getKraj());
        }
    }
    public void DodajDaneWykonawcaJComboBoxa(JComboBox comboBox)
    {
        List<wykonawca> wykonawcaList=WykonawcaRepozytorium.findAll();
        comboBox.removeAllItems();
        for(Encje.wykonawca wykonawca:wykonawcaList)
        {
            comboBox.addItem(wykonawca.getNazwa());
        }
    }
    public void DodajDaneSamochodJComboBoxa(JComboBox comboBox)
    {
        List<samochod> samochodList=SamochodRepozytorium.findAll();
        comboBox.removeAllItems();
        for(Encje.samochod samochod:samochodList)
        {
            comboBox.addItem(samochod.getNazwa());
        }
    }
    public void DodajDaneTowarJComboBoxa(JComboBox comboBox)
    {
        List<towar> towarList=TowarRepozytorium.findAll();
        comboBox.removeAllItems();
        for(Encje.towar towar:towarList)
        {
            comboBox.addItem(towar.getNazwa());
        }
    }

    public void DodajDaneWalutaJCoomboBoxa(JComboBox comboBox)
    {
        List<String> walutaList=WalutaRepozytorium.findSkrot();
        comboBox.removeAllItems();
        for(String skrot:walutaList)
        {
            comboBox.addItem(skrot);
        }
    }
    public void SprawdzWykonawce(JComboBox comboBox)
    {
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object wybranyElement=cZlecenie.getSelectedItem();
                if(wybranyElement!=null&&wybranyElement.toString().equals("Wlasny transport"))
                {

                    tPrzewzonik.setEditable(false);
                    tPrzewzonik.setText(NazwaFirmy);
                    bDodajPrzewoznika.setEnabled(false);
                    sTransport=wybranyElement.toString().trim();
                    tKwotaZlecenia.setEditable(false);
                }
                else if(wybranyElement!=null&&!wybranyElement.toString().equals("Wlasny transport"))
                {

                    tPrzewzonik.setEditable(false);
                    tPrzewzonik.setText("");
                    bDodajPrzewoznika.setEnabled(true);
                    sTransport=wybranyElement.toString().trim();
                    tKwotaZlecenia.setEditable(true);
                }
            }
        });


    }
    public void SprawdzWykonawceNaPoczatku()
    {
        Object wybranyElement=cZlecenie.getSelectedItem();
        if(wybranyElement!=null&&wybranyElement.toString().equals("Wlasny transport"))
        {
            tPrzewzonik.setEditable(false);
            tPrzewzonik.setText(NazwaFirmy);
            bDodajPrzewoznika.setEnabled(false);
            sTransport=wybranyElement.toString().trim();
        }
        else if(wybranyElement!=null&&!wybranyElement.toString().equals("Wlasny transport"))
        {

            tPrzewzonik.setEditable(false);
            tPrzewzonik.setText("");
            bDodajPrzewoznika.setEnabled(true);
            sTransport=wybranyElement.toString().trim();
        }
    }
    public void Pobierz()
    {
        sZleceniodawca=tZleceniodawca.getText().trim();
        sNumerZlecenia=tNumerZlecenia.getText().trim();
        sTowarRodzaj=cRodzajTowaru.getSelectedItem().toString();
        sWaluta=cWaluta.getSelectedItem().toString();
        sIlosc=tIlosc.getText().trim();
        sWarunkuZlecenia=aWarunkiZlecenia.getText().trim();
        if(!sIlosc.isEmpty())
        {
            iIlosc=Integer.parseInt(sIlosc);
            System.out.println("Waga towaru w incie to "+iIlosc);
        }
        sWaga=tWaga.getText().trim();
        if(!sWaga.isEmpty())
        {
            iWaga=Integer.parseInt(sWaga);
        }
        DataZaladunku1=tDataZaladunku.getText().trim();
        DataRozladunku1=tDataRozladunku.getText().trim();
        sKrajZaladunku=cKrajZaladunku.getSelectedItem().toString();
        sGodzinaZaladunku=tGodzinaZaladunku.getText().trim();
        sGodzinaZaladunku1=tGodzinaZaladunku1.getText().trim();
        sMiejscowoscZaladunku=tMiejscoscZaladunku.getText().trim();
        sKodPocztowyZaladunku=tKodPocztowy.getText().trim();
        sFirmaZaladunku=aFirmaZaladunku.getText().trim();
        sPrzewoznik=tPrzewzonik.getText().trim();
        sRodzajSamochodu=cRodzajSamochodu.getSelectedItem().toString().trim();
        sKierowca=tKierowca.getText().trim();
        sNumerRejestracyjny=tNumerZlecenia.getText().trim();
        sKrajRozladunku=cKrajRozladunku.getSelectedItem().toString().trim();
        sGodzinaRozladunku=tGodzinaRozladunku.getText().trim();
        sGodzinaRozladunku1=tGodzinaRozladunku1.getText().trim();
        sMiejscowoscRozladunku=tMiastoRozladunku.getText().trim();
        sKodPocztowyRozladunku=tKodPocztowyRozladunku.getText().trim();
        sFirmaRozladunku=aFirmaRozladunku.getText().trim();
        sUlicaZaladunku=tUlicaZaladunku.getText().trim();
        sUlicaRozladunku=tUlicaRozladunku.getText().trim();
        if(!tKwotaFrachtu.getText().trim().isEmpty()) {
            sKwotaFrachtu = tKwotaFrachtu.getText().trim();
            dKwotaFrachtu=Double.parseDouble(sKwotaFrachtu);
        }
        if(!tKwotaZlecenia.getText().isEmpty()) {
                sKwotaZlecenia = tKwotaZlecenia.getText().trim();
                dKwotaZlecenia = Double.parseDouble(sKwotaZlecenia);
        }



    }
    public void SprawdzCzyWszystkoWypelnione()
    {
        if(sZleceniodawca.isEmpty())
        {
            metody.WyswietlKomunikatoBledzie("Nie wybrałeś zleceniodawcy !");
        }
       else if(sIlosc.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałeś ilości towaru");
       }
       else if(sWaga.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałeś wagi");
       }
       else if(DataZaladunku1.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wybrałeś daty załadunku!");
       }


       else if(sMiejscowoscZaladunku.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałeś miasta załadunku");
       }
       else if(sKodPocztowyZaladunku.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałeś kodu pocztowego załadunku");
       }
       else if(sUlicaZaladunku.isEmpty())
        {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś ulicy załadunku");
        }
       else if(sFirmaZaladunku.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałęś firmy załadunku !");
       }
       else if(sPrzewoznik.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wybrałeś przewoźnika !");
       }

        else if(tKwotaFrachtu.getText().trim().isEmpty())
        {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś kwoty frachtu");
        }
        else if(tKwotaZlecenia.isEditable()&&sKwotaZlecenia==null)
        {

            metody.WyswietlKomunikatoBledzie("Nie wpisałeś kwoty zlecenia");

        }
       else if(DataRozladunku1.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wybrałeś daty rozładunku !");
       }

       else if(sMiejscowoscRozladunku.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("nie wpisałeś miejscowości rozładunku");
       }
       else if(sKodPocztowyRozladunku.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałeś kodu pocztowego rozładunku !");
       }
       else if(sUlicaRozladunku.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałeś ulicy rozładunku");
       }
       else if(sFirmaRozladunku.isEmpty())
       {
           metody.WyswietlKomunikatoBledzie("Nie wpisałeś firmy rozładunku");
       }
       else if(sGodzinaZaladunku.isEmpty()||sGodzinaZaladunku1.isEmpty()||sGodzinaRozladunku.isEmpty()||sGodzinaRozladunku1.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Nie wpisałeś godzin, pola zostaną uzupełnione zerami");
            if(sGodzinaZaladunku.isEmpty())
            {
                tGodzinaZaladunku.setText("00");
                sGodzinaZaladunku=tGodzinaZaladunku.getText().trim();
            }
            if(sGodzinaZaladunku1.isEmpty())
            {
                tGodzinaZaladunku1.setText("00");
                sGodzinaZaladunku1=tGodzinaZaladunku1.getText().trim();
            }
            if(sGodzinaRozladunku.isEmpty())
            {
                tGodzinaRozladunku.setText("00");
                sGodzinaRozladunku=tGodzinaRozladunku.getText().trim();
            }
             if(sGodzinaRozladunku1.isEmpty())
            {
                tGodzinaRozladunku1.setText("00");
                sGodzinaRozladunku1=tGodzinaRozladunku1.getText().trim();
            }

        }

       else
       {
           String numerfakturykosztowej="";
           JOptionPane.showMessageDialog(this, "Dodano nowe zlecenie");
           DodajDdBazyDanych(sTransport,numerfakturykosztowej,sZleceniodawca,sNumerZlecenia,sTowarRodzaj,iIlosc,iWaga,sKrajZaladunku,DataZaladunku1,sMiejscowoscZaladunku,
                   sKodPocztowyZaladunku,sUlicaZaladunku,sFirmaZaladunku,sPrzewoznik,sRodzajSamochodu,sKierowca,sNumerRejestracyjny,sKrajRozladunku,DataRozladunku1,sMiejscowoscRozladunku,sKodPocztowyRozladunku,sUlicaRozladunku,
                   sFirmaRozladunku,dKwotaFrachtu,dKwotaZlecenia,sWaluta,sWarunkuZlecenia,NazwaFirmy,IdFirmy,NazwaUzytkownika);
           dispose();
       }
    }
    public void DodajDdBazyDanych(String wykonawca,String numerfakturykosztowej,String zleceniodawa,String numer_zlecenia,String rodzaj_towaru,int ilosc,int waga,String kraj_zaladunku,String data_zaladunku,
                                  String miejscowosc_zaladunku,String kod_pocztowy_zaladunku,String ulicaZaladunku,String firma_zaladunek,String przewoznik,String samochod,String kierowca,String numery_rejestracyjne,
                                  String kraj_rozladunku,String data_rozladunku,String miejscowosc_rozladunku,String kod_pocztowy_rozladunku,String ulicaRozladunku, String firma_rozladunek, Double kwotaFrachtu,Double kwotaZlecenia,String waluta, String warunku_zlecenia, String nazwa_firmy,
                                  int id_firmy,String nazwa_uzytkownika)
    {
        zlecenie Zlecenie=new zlecenie(wykonawca,numerfakturykosztowej,zleceniodawa,numer_zlecenia,rodzaj_towaru,ilosc,waga,kraj_zaladunku,data_zaladunku,miejscowosc_zaladunku,kod_pocztowy_zaladunku,ulicaZaladunku,firma_zaladunek,
                przewoznik,samochod,kierowca,numery_rejestracyjne,kraj_rozladunku,data_rozladunku,miejscowosc_rozladunku,kod_pocztowy_rozladunku,ulicaRozladunku,firma_rozladunek,kwotaFrachtu,kwotaZlecenia,waluta, warunku_zlecenia, nazwa_firmy,id_firmy,nazwa_uzytkownika);

        ZlecenieRepozytorium.save(Zlecenie);
    }


}
