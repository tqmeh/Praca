package PoboczneOkna;

import Encje.kraj;
import Encje.zleceniodawca;
import Metody.Metody;
import Ograniczenia.LimitowanyText;
import Ograniczenia.LimitowanyTextArea;
import Repozytoria.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Encje.*;

public class DodajPrzewoznika extends JFrame {

    Metody metody = new Metody();
    JPanel panelZachodni;
    LimitowanyText tNazwaSkrocona, tUlica, tNumerDomu, tNumerMieszkania, tKodPocztowy, tMiasto, tNip, tRegon, tMail, tTelefonStacjonarny,
            tTelefonKomorkowy, tSkype;
    JComboBox<String> cKraj;
    LimitowanyTextArea aNazwaPelna;
    JButton bDodaj,busun,bEdutuj,bWybierz;
    krajRepozytorium KrajRepozytorium;
    uzytkownicyRepozytorium UzytkownicyRepozytorium;

    przewoznikRepozytorium PrzewoznikRepozytorium;
    private int userID;
    int IdFirmy;
    double Regon1;
    DefaultTableModel model;
    JTable table;

    String NazwaSkrocona, NazwaPelna, NumerDomu, NumerMieszkania, KodPocztowy, Miasto, Nip, Regon, Mail, TelefonStacjonarny, TelefonKomorkowy, Skype, Ulica, Kraj,
            Nazwauzytkownika, NazwaFirmy;
    NoweZlecenie noweZlecenie;
    String WybranazTabeliNazwa;
    public DodajPrzewoznika(krajRepozytorium KrajRepozytorium, uzytkownicyRepozytorium UzytkownicyRepozytorium,przewoznikRepozytorium PrzewoznikRepozytorium,int userID,NoweZlecenie noweZlecenie) {
        this.KrajRepozytorium = KrajRepozytorium;
        this.UzytkownicyRepozytorium=UzytkownicyRepozytorium;
        this.PrzewoznikRepozytorium=PrzewoznikRepozytorium;
        this.userID=userID;
        this.noweZlecenie=noweZlecenie;
        Nazwauzytkownika=UzytkownicyRepozytorium.findNazwaUzytkownikaById(userID);
        NazwaFirmy=UzytkownicyRepozytorium.findNazwaFirmyByUserId(userID);
        IdFirmy=UzytkownicyRepozytorium.findFirmaIdByUserId(userID);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        panelZachodni = new JPanel();
        panelZachodni.setLayout(new BoxLayout(panelZachodni, BoxLayout.Y_AXIS));
        ImageIcon Plus = metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1 = metody.PrzeskalujObraz(Plus, 20, 20);
        bDodaj = metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj, "Dodaj", Plus1, 100, 20);
        bDodaj.addActionListener(e -> {
            StworzDodawaniePrzewoznika();
        });
        ImageIcon Usun=metody.StworzObrazIcone("Minus.jpg");
        ImageIcon Usun1=metody.PrzeskalujObraz(Usun,20,20);
        busun=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Usun",Usun1,100,20);
        busun.setEnabled(false);
        busun.addActionListener(e -> {
            UsunzBazyDanych();
        });
        bEdutuj=metody.StworzPrzyciskzObrazemzTekstemObok(bEdutuj,"Edytuj",Plus1,100,20);
        ImageIcon Wybierz=metody.StworzObrazIcone("Akceptuj.png");
        ImageIcon Wybierz1=metody.PrzeskalujObraz(Wybierz,20,20);
        bWybierz=metody.StworzPrzyciskzObrazemzTekstemObok(bWybierz,"Wybierz",Wybierz1,100,20);
        bWybierz.setEnabled(false);
        bWybierz.addActionListener(e -> {
            int WybranyWiersz=table.getSelectedRow();
            if(WybranyWiersz!=-1)
            {
                Object wartosc=table.getValueAt(WybranyWiersz,1);
                if(wartosc!=null)
                {
                    WybranazTabeliNazwa=wartosc.toString();
                    System.out.println("Wybrana nazwa to "+WybranazTabeliNazwa);
                    noweZlecenie.settPrzewzonik(WybranazTabeliNazwa);

                    dispose();
                }
            }
        });
        add(panelZachodni);
        panelZachodni.add(bDodaj);
        panelZachodni.add(busun);
        panelZachodni.add(bEdutuj);
        panelZachodni.add(bWybierz);
        setVisible(true);
        WyswietlTabele();
        add(panelZachodni, BorderLayout.WEST);
    }

    public void StworzDodawaniePrzewoznika() {
        JDialog jDialog = new JDialog();
        Metody metody = new Metody();
        jDialog.setTitle("Dodaj przewoźnika");
        jDialog.setSize(1100, 500);
        jDialog.setLayout(null);
        jDialog.setVisible(true);
        jDialog.setLocationRelativeTo(null);

        JLabel lFirma, lNazwaSkrocona, lNazwaPelna, lAdres, lKraj, lUlica, lKodPocztowy, lMiasto, lNumerDomu, lDane, lNip, lRegon,
                lMail, lTelefonStacjonarny, lTelefonKomorkowy, lSkype;


        JButton bZapisz, bWyjdz;

        lFirma = new JLabel();
        lNazwaSkrocona = new JLabel();
        lNazwaPelna = new JLabel();
        lAdres = new JLabel();
        lKraj = new JLabel();
        lUlica = new JLabel();
        lKodPocztowy = new JLabel();
        lMiasto = new JLabel();
        lNumerDomu = new JLabel();
        lDane = new JLabel();
        lNip = new JLabel();
        lRegon = new JLabel();
        lMail = new JLabel();
        lTelefonStacjonarny = new JLabel();
        lTelefonKomorkowy = new JLabel();
        lSkype = new JLabel();
        tNazwaSkrocona = new LimitowanyText(20, false);
        aNazwaPelna = new LimitowanyTextArea(80);
        cKraj = new JComboBox();
        tUlica = new LimitowanyText(30, false);
        tNumerDomu = new LimitowanyText(5, false);
        tNumerMieszkania = new LimitowanyText(5, false);
        tKodPocztowy = new LimitowanyText(10, false);
        tMiasto = new LimitowanyText(40, false);
        tNip = new LimitowanyText(20, true);
        tRegon = new LimitowanyText(9, true);
        tMail = new LimitowanyText(40, false);
        tTelefonStacjonarny = new LimitowanyText(30, true);
        tTelefonKomorkowy = new LimitowanyText(30, true);
        tSkype = new LimitowanyText(40, false);
        bZapisz = new JButton();
        bWyjdz = new JButton();


        metody.StworzNapisJDialog(jDialog, lFirma, "Firma", 10, 10, 100, 20);
        lFirma.setFont(lFirma.getFont().deriveFont(Font.BOLD));

        metody.StworzNapisJDialog(jDialog, lNazwaSkrocona, "Nazwa skrócona", 10, 40, 100, 20);
        metody.StworzNapisJDialog(jDialog, lNazwaPelna, "Nazwa pełna", 10, 70, 100, 20);
        metody.StworzNapisJDialog(jDialog, lAdres, "Adres", 10, 170, 100, 20);
        lAdres.setFont(lAdres.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jDialog, lKraj, "Kraj", 10, 200, 100, 20);
        metody.StworzNapisJDialog(jDialog, lUlica, "Ulica", 10, 230, 100, 20);
        metody.StworzNapisJDialog(jDialog, lKodPocztowy, "Kod pocztowy", 10, 260, 100, 20);
        metody.StworzNapisJDialog(jDialog, lMiasto, "Miasto", 10, 290, 100, 20);

        metody.StworzLimitowanyTextjDialog(tNazwaSkrocona, 120, 40, 160, 20, jDialog);
        metody.StworzLimitowanyTextAreajDialog(aNazwaPelna, 120, 70, 160, 70, jDialog);
        cKraj.setBounds(120, 200, 160, 20);
        DodajDaneKrajowdoJComboBoxa();
        metody.StworzLimitowanyTextjDialog(tUlica, 120, 230, 200, 20, jDialog);
        metody.StworzLimitowanyTextjDialog(tNumerDomu, 330, 230, 60, 20, jDialog);
        metody.StworzNapisJDialog(jDialog, lNumerDomu, "/", 395, 230, 10, 20);
        metody.StworzLimitowanyTextjDialog(tNumerMieszkania, 410, 230, 60, 20, jDialog);
        metody.StworzLimitowanyTextjDialog(tKodPocztowy, 120, 260, 100, 20, jDialog);
        metody.StworzLimitowanyTextjDialog(tMiasto, 120, 290, 270, 20, jDialog);
        metody.StworzNapisJDialog(jDialog, lDane, "Dane", 600, 10, 100, 20);
        lDane.setFont(lDane.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jDialog, lNip, "NIP", 600, 40, 100, 20);
        metody.StworzNapisJDialog(jDialog, lRegon, "Regon", 600, 70, 100, 20);
        metody.StworzLimitowanyTextjDialog(tNip, 710, 40, 150, 20, jDialog);
        metody.StworzLimitowanyTextjDialog(tRegon, 710, 70, 100, 20, jDialog);
        metody.StworzNapisJDialog(jDialog, lMail, "Mail", 600, 160, 100, 20);
        metody.StworzNapisJDialog(jDialog, lTelefonStacjonarny, "Telefon stacjonarny", 600, 100, 150, 20);
        metody.StworzNapisJDialog(jDialog, lTelefonKomorkowy, "Telefon komórkowy", 600, 130, 150, 20);
        metody.StworzNapisJDialog(jDialog, lSkype, "Skype", 600, 190, 100, 20);
        metody.StworzLimitowanyTextjDialog(tTelefonStacjonarny, 710, 100, 220, 20, jDialog);
        metody.StworzLimitowanyTextjDialog(tTelefonKomorkowy, 710, 130, 220, 20, jDialog);
        metody.StworzLimitowanyTextjDialog(tMail, 710, 160, 290, 20, jDialog);
        metody.StworzLimitowanyTextjDialog(tSkype, 710, 190, 290, 20, jDialog);
        metody.StworzJButtonJDialog(jDialog, bZapisz, "Zapisz", 400, 400, 100, 20);
        metody.StworzJButtonJDialog(jDialog, bWyjdz, "Wyjdź", 510, 400, 100, 20);


        jDialog.add(cKraj);
        bWyjdz.addActionListener(e -> {
            jDialog.dispose();
        });
        bZapisz.addActionListener(e -> {
            PobierzDane();
            SprawdzCzyDaneWypelnione(jDialog);
        });


    }

    public void DodajDaneKrajowdoJComboBoxa() {

        List<kraj> krajList = KrajRepozytorium.findAll();
        cKraj.removeAllItems();
        for (Encje.kraj kraj : krajList) {
            cKraj.addItem(kraj.getKraj());
        }
    }

    public void PobierzDane() {
        NazwaSkrocona = tNazwaSkrocona.getText().trim();
        NazwaPelna = aNazwaPelna.getText().trim();
        Ulica = tUlica.getText().trim();
        NumerDomu = tNumerDomu.getText().trim();
        NumerMieszkania = tNumerMieszkania.getText().trim();
        KodPocztowy = tKodPocztowy.getText().trim();
        Miasto = tMiasto.getText().trim();
        Nip = tNip.getText().trim();
        Regon = tRegon.getText().trim();
        TelefonStacjonarny = tTelefonStacjonarny.getText().trim();
        TelefonKomorkowy = tTelefonKomorkowy.getText().trim();
        Mail = tMail.getText().trim();
        Skype = tSkype.getText().trim();
        Kraj = (String) cKraj.getSelectedItem();



    }

    public void SprawdzCzyDaneWypelnione(JDialog jDialog) {
        if (NazwaSkrocona.isEmpty()) {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś nazwy skróconej zleceniodawcy");
        } else if (NazwaPelna.isEmpty()) {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś nazwy pełnej zleceniodawcy");
        } else if (Ulica.isEmpty()) {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś ulicy");
        } else if (NumerDomu.isEmpty()) {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru domu");
        } else if (KodPocztowy.isEmpty()) {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś kodu pocztowego");
        } else if (Miasto.isEmpty()) {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś miasta");
        } else if (Nip.isEmpty()) {
            metody.WyswietlKomunikatoBledzie("Nie wpisałeś nipu");
        }
        else
        {
            if(!Regon.isEmpty())
            {
                Regon1=Double.parseDouble(Regon);
                System.out.println("Wszystko wypelnione");

                DodajUzytkownika(NazwaSkrocona,NazwaPelna,Kraj,Ulica,NumerDomu,NumerMieszkania,KodPocztowy,Miasto,Nip,Regon1,TelefonStacjonarny,TelefonKomorkowy,Mail,Skype,IdFirmy,
                        NazwaFirmy,userID,Nazwauzytkownika);
                JOptionPane.showMessageDialog(this, "Dodano nowego przewoznika o nazwie "+NazwaSkrocona);
                jDialog.dispose();
            }
            else
            {
                System.out.println("Wszystko wypelnione");

                DodajUzytkownika(NazwaSkrocona,NazwaPelna,Kraj,Ulica,NumerDomu,NumerMieszkania,KodPocztowy,Miasto,Nip,Regon1,TelefonStacjonarny,TelefonKomorkowy,Mail,Skype,IdFirmy,
                        NazwaFirmy,userID,Nazwauzytkownika);
                JOptionPane.showMessageDialog(this, "Dodano nowego przewoznika o nazwie "+NazwaSkrocona);
                jDialog.dispose();
            }


        }
    }
    public void DodajUzytkownika(String nazwaSkrocona,String nazwaPelna,String kraj,String ulica,String numerDomu,String numerMieszkania,String kodPocztowy,String miasto,String nip,double Regon,
                                 String telefonStacjonarny,String telefonKomorkowy,String mail,String skype,int idFirmy,String nazwaFirmy,int userID,String nazwauzytkownika)
    {
        przewoznik Przewoznik=new przewoznik(nazwaSkrocona,nazwaPelna,kraj,ulica,numerDomu,numerMieszkania,kodPocztowy,miasto,nip,Regon,telefonStacjonarny,telefonKomorkowy,mail,
                skype,idFirmy,nazwaFirmy,userID,nazwauzytkownika);
        PrzewoznikRepozytorium.save(Przewoznik);
    }
    public void WyswietlTabele()
    {
        List<Encje.przewoznik> zleceniodawcaList=PrzewoznikRepozytorium.findCompanyByFirmaId(IdFirmy);
        Object[][] dane=new Object[zleceniodawcaList.size()][11];
        for(int i=0;i<zleceniodawcaList.size();i++)
        {
            przewoznik zleceniodawca1=zleceniodawcaList.get(i);
            dane[i][0]=zleceniodawca1.getId();
            dane[i][1]=zleceniodawca1.getNazwa_skrocona();
            dane[i][2]=zleceniodawca1.getNazwa_pelna();
            dane[i][3]=zleceniodawca1.getKraj();
            dane[i][4]=zleceniodawca1.getUlica();
            dane[i][5]=zleceniodawca1.getNumer_domu();
            dane[i][6]=zleceniodawca1.getNumer_mieszkania();
            dane[i][7]=zleceniodawca1.getKod_pocztowy();
            dane[i][8]=zleceniodawca1.getMiasto();
            dane[i][9]=zleceniodawca1.getNip();
            dane[i][10]=zleceniodawca1.getRegon();
        }
        String[] NazwyKolumn={
                "ID","Nazwa skrócona","Nazwa pełna","Kraj","Ulica","Numer domu","Numer mieszkania","Kod pocztowy","Miasto","NIP","Regon"
        };
        model = new DefaultTableModel(dane, NazwyKolumn);
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())
                {
                    if(table.getSelectedRow()!=-1)
                    {
                        busun.setEnabled(true);
                        bWybierz.setEnabled(true);
                    }
                    else
                    {
                        busun.setEnabled(false);
                        bWybierz.setEnabled(false);
                    }
                }
            }
        });
        table.setModel(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

    }
    public void UsunzBazyDanych()
    {
        int WybranyWiersz=table.getSelectedRow();
        if(WybranyWiersz!=-1)
        {
            int Id=(Integer) table.getValueAt(WybranyWiersz,0);
            PrzewoznikRepozytorium.deleteById(Id);
            JOptionPane.showMessageDialog(null, "Usunieto uzytkownika o ID "+Id);

        }
    }
}