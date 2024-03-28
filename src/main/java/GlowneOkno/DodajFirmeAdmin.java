package GlowneOkno;

import Metody.Metody;
import Repozytoria.firmaRepozytorium;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import Encje.firma;
public class DodajFirmeAdmin extends JFrame {

    private final firmaRepozytorium firmaRepozytorium;
     Metody metody=new Metody();
    JButton bDodaj,bMinus;
    JPanel panelZachodni;
    JTextField tNazwa,tUlica,tNumer_Domu,tNumer_Mieszkania,tKod_Pocztowy,tMiasto,tNip,tRegon;
    String Nazwa,Ulica,Numer_Domu,Numer_Mieszkania,Kod_Pocztowy,Miasto,Nip,Regon;

    double NIP,REGON;
    DefaultTableModel model;
    JTable table;
    public DodajFirmeAdmin(firmaRepozytorium firmaRepozytorium)
    {
        this.firmaRepozytorium=firmaRepozytorium;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        panelZachodni =new JPanel();

        panelZachodni.setLayout(new BoxLayout(panelZachodni,BoxLayout.Y_AXIS));

        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);
        ImageIcon Minus=metody.StworzObrazIcone("minus.jpg");
        ImageIcon Minus1=metody.PrzeskalujObraz(Minus,20,20);
        bDodaj=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Dodaj",Plus1,100,20);
        bDodaj.addActionListener(e -> {
            StworzDodawaniedoTabeli();
        });

        bMinus=metody.StworzPrzyciskzObrazemzTekstemObok(bMinus,"Usuń",Minus1,100,20);
        bMinus.setEnabled(false);
        bMinus.addActionListener(e -> {
            UsunzBazyDanych();
        });
        panelZachodni.add(bDodaj);
        panelZachodni.add(bMinus);
        add(panelZachodni);
        setVisible(true);
        add(panelZachodni,BorderLayout.WEST);


        DodajTabele();

    }

    public void StworzDodawaniedoTabeli()
    {
        JDialog jNoweOknoDialogowe=new JDialog();
        jNoweOknoDialogowe.setTitle("Dodaj firmę");
        jNoweOknoDialogowe.setSize(650,300);
        jNoweOknoDialogowe.setLayout(null);
        jNoweOknoDialogowe.setVisible(true);
        jNoweOknoDialogowe.setLocationRelativeTo(null);

        JButton bWyjdz,bZapisz;
        JLabel lNazwa,lUlica,lNumer_Domu,lNumer_Mieszkania,lKod_Pocztowy,lMiasto,lNip,lRegon;

        lNazwa=new JLabel();
        lUlica=new JLabel();
        lNumer_Domu=new JLabel();
        lNumer_Mieszkania=new JLabel();

        lKod_Pocztowy=new JLabel();
        lMiasto=new JLabel();
        lNip=new JLabel();
        lRegon=new JLabel();
        bWyjdz=new JButton();
        bZapisz=new JButton();
        tNazwa=new JTextField();
        tUlica=new JTextField();
        tKod_Pocztowy=new JTextField();
        tNumer_Domu=new JTextField();
        tNumer_Mieszkania=new JTextField();

        tMiasto=new JTextField();
        tNip=new JTextField();
        tRegon=new JTextField();
        lNazwa.setText("Nazwa Firmy");
        lNazwa.setBounds(10,10,100,20);
        lUlica.setText("Ulica");
        lUlica.setBounds(10,40,100,20);
        lNumer_Domu.setText("Numer domu");
        lNumer_Domu.setBounds(10,70,100,20);
        lNumer_Mieszkania.setText("Numer mieszkania");
        lNumer_Mieszkania.setBounds(10,100,100,20);
        lKod_Pocztowy.setText("Kod pocztowy");
        lKod_Pocztowy.setBounds(340,10,100,20);
        lMiasto.setText("Miasto");
        lMiasto.setBounds(340,40,100,20);
        lNip.setText("Nip");
        lNip.setBounds(340,70,100,20);
        lRegon.setText("Regon");
        lRegon.setBounds(340,100,100,20);

        tNazwa.setBounds(120,10,200,20);
        tUlica.setBounds(120,40,200,20);
        tNumer_Domu.setBounds(120,70,50,20);
        tNumer_Mieszkania.setBounds(120,100,50,20);

        tKod_Pocztowy.setBounds(440,10,100,20);
        tMiasto.setBounds(440,40,150,20);
        tNip.setBounds(440,70,150,20);
        tRegon.setBounds(440,100,150,20);

        bZapisz.setText("Zapisz");
        bZapisz.setBounds(200,200,100,20);
        bZapisz.addActionListener(e -> {
            Pobierz();
            SprawdzCzyWszystkoWypelnioneiWyslij();
            jNoweOknoDialogowe.dispose();
        });

        bWyjdz.setText("Wyjdź");
        bWyjdz.setBounds(310,200,100,20);
        bWyjdz.addActionListener(e -> {
            jNoweOknoDialogowe.dispose();
        });

        jNoweOknoDialogowe.add(lNazwa);
        jNoweOknoDialogowe.add(lUlica);
        jNoweOknoDialogowe.add(lNumer_Domu);
        jNoweOknoDialogowe.add(lNumer_Mieszkania);
        jNoweOknoDialogowe.add(lKod_Pocztowy);
        jNoweOknoDialogowe.add(lMiasto);
        jNoweOknoDialogowe.add(lNip);
        jNoweOknoDialogowe.add(lRegon);
        jNoweOknoDialogowe.add(bZapisz);
        jNoweOknoDialogowe.add(bWyjdz);
        jNoweOknoDialogowe.add(tNazwa);
        jNoweOknoDialogowe.add(tUlica);
        jNoweOknoDialogowe.add(tNumer_Domu);
        jNoweOknoDialogowe.add(tNumer_Mieszkania);

        jNoweOknoDialogowe.add(tKod_Pocztowy);
        jNoweOknoDialogowe.add(tMiasto);
        jNoweOknoDialogowe.add(tNip);
        jNoweOknoDialogowe.add(tRegon);
    }

    public void Pobierz()
    {
        Nazwa=tNazwa.getText().trim();
        Ulica=tUlica.getText().trim();
        Numer_Domu=tNumer_Domu.getText().trim();
        Numer_Mieszkania=tNumer_Mieszkania.getText().trim();
        Kod_Pocztowy=tKod_Pocztowy.getText().trim();
        Miasto=tMiasto.getText().trim();
        Nip=tNip.getText().trim();
        Regon=tRegon.getText().trim();
    }
    public void SprawdzCzyWszystkoWypelnioneiWyslij()
    {
        if(Nazwa.isEmpty())
        {
            WyswietlKomunikatoBledzie("Nie wpisałeś nazwy firmy");
        }
        else if(Ulica.isEmpty())
        {
            WyswietlKomunikatoBledzie("Nie wpisałeś ulicy");
        }
        else if(Numer_Domu.isEmpty())
        {
            WyswietlKomunikatoBledzie("Nie wpisałeś numeru domu");
        }
        else if(Kod_Pocztowy.isEmpty())
        {
            WyswietlKomunikatoBledzie("Nie wpisałeś kodu pocztowego");
        }
        else if(Miasto.isEmpty())
        {
            WyswietlKomunikatoBledzie("Nie wpisałeś miasta");
        }
        else if(Nip.isEmpty())
        {
            WyswietlKomunikatoBledzie("Nie wpisałeś nipu");
        }
        else if(Regon.isEmpty())
        {
            WyswietlKomunikatoBledzie("Nie wpisałeś regonu");
        }
        else {
            System.out.println("Konwertuje stringa na liczbe");
            NIP=Double.parseDouble(Nip);
            REGON=Double.parseDouble(Regon);
            System.out.println("Nip w incie to "+NIP);
            System.out.println("Regon w incie to "+REGON);

            firma Firma=new firma(Nazwa,Ulica,Numer_Domu,Numer_Mieszkania,Kod_Pocztowy,Miasto,NIP,REGON);
            firmaRepozytorium.save(Firma);

        }
    }

    public static void WyswietlKomunikatoBledzie(String Blad)
    {
        JOptionPane.showMessageDialog(
                null,
                Blad,
                "Bład",
                JOptionPane.ERROR_MESSAGE
        );
    }
    public void DodajTabele()
    {

        List<Encje.firma> firmaList= firmaRepozytorium.findAll();
        Object[][]dane=new Object[firmaList.size()][9];
        for(int i=0;i<firmaList.size();i++)
        {

            firma firma1=firmaList.get(i);

            dane[i][0]=firma1.getId();
            dane[i][1]=firma1.getNazwa();
            dane[i][2]=firma1.getUlica();
            dane[i][3]=firma1.getNumer_domu();
            dane[i][4]=firma1.getNumer_mieszkania();
            dane[i][5]=firma1.getKod_pocztowy();
            dane[i][6]=firma1.getMiasto();
            dane[i][7]=firma1.getNip();
            dane[i][8]=firma1.getRegon();
        }
        String[] NazwyKolumn=
                {
                  "ID","Nazwa","Ulica","Numer domu","Numer mieszkania","Kod pocztowy","Miasto","Nip","Regon"
                };
        model=new DefaultTableModel(dane,NazwyKolumn);
        table=new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//pojedynczy tylko moze byc wybrany wiersz
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {//nasluchiwacz do zmiany
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())
                {
                    if(table.getSelectedRow()!=-1)
                    {
                        bMinus.setEnabled(true);// jesli jest klikniete to pozwol aby przycisk byl aktywny
                    }
                    else
                    {
                        bMinus.setEnabled(false);
                    }
                }
            }
        });
        table.setModel(model);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    public void UsunzBazyDanych()//metoda do usuwania z bazy danych przy pomocy springa
    {
        int WybranyWiersz=table.getSelectedRow();
        if(WybranyWiersz!=-1)
        {
            int Id=(Integer) table.getValueAt(WybranyWiersz,0);
            firmaRepozytorium.deleteById(Id);
            JOptionPane.showMessageDialog(null, "Dane usunięte z bazy danych.");


        }
        else
        {
            JOptionPane.showMessageDialog(null, "Nie usunięto wpisu z bazy danych");
        }
    }


}
