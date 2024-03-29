package GlowneOkno;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.*;

import Repozytoria.uzytkownicyRepozytorium;
import Repozytoria.firmaRepozytorium;
import Encje.uzytkownicy;
import Encje.firma;
import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import Metody.Metody;
import Ograniczenia.LimitowanyText;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class DodajUseraAdmin extends JFrame {

    private final uzytkownicyRepozytorium repozytorium;
    private final firmaRepozytorium firmaRepozytorium;

    int CheckBoxWlasciciel, CheckBoxAdministrator;
    int IdZleceniodawcy;
    Metody metody=new Metody();
    JButton bDodaj,bMinus,bDodaj1,bZapisz;
    JLabel jWybranaFirmaprzezUzytkownika;
    JPanel panelZachodni;
    DefaultTableModel model;
    JTable table;
    String haslo,Login,NazwaFirmy;
    JavaMailSender javaMailSender;
    public DodajUseraAdmin(uzytkownicyRepozytorium repozytorium,firmaRepozytorium firmaRepozytorium,JavaMailSender javaMailSender) {
        this.repozytorium = repozytorium;
        this.firmaRepozytorium=firmaRepozytorium;
        this.javaMailSender=javaMailSender;

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        panelZachodni = new JPanel();

        panelZachodni.setLayout(new BoxLayout(panelZachodni, BoxLayout.Y_AXIS));
        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);
        ImageIcon Minus=metody.StworzObrazIcone("minus.jpg");
        ImageIcon Minus1=metody.PrzeskalujObraz(Minus,20,20);



        bDodaj=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Dodaj",Plus1,100,20);

        bDodaj.addActionListener(e -> {
            StworzDodawanieUzytkownika(this);
        });
        bMinus=metody.StworzPrzyciskzObrazemzTekstemObok(bMinus,"Usuń",Minus1,100,20);
        bMinus.setEnabled(false);
        bMinus.addActionListener(e -> {
            UsunzBazyDanych();
        });
        panelZachodni.add(bDodaj);
        panelZachodni.add(bMinus);


        add(panelZachodni);
        add(panelZachodni);
        setVisible(true);
        DodajTabele();
        add(panelZachodni,BorderLayout.WEST);

    }
    public void StworzDodawanieUzytkownika(DodajUseraAdmin okno)
    {
        JDialog jDialog=new JDialog();
        jDialog.setTitle("Dodaj użytkownika");
        jDialog.setSize(400,300);
        jDialog.setLayout(null);
        jDialog.setVisible(true);
        jDialog.setLocationRelativeTo(null);

        JCheckBox checkBoxAdministrator=new JCheckBox();
        JCheckBox checkBoxWlasciciel=new JCheckBox();

        JLabel jLogin,jMail,jWybranaFirma,jAdministrator,jWlasciciel;

        LimitowanyText tLogin=new LimitowanyText(20,false);
        LimitowanyText tMail=new LimitowanyText(40,false);
        JButton jWybierzFirme;

        jLogin=new JLabel();
        jMail=new JLabel();
        jWybranaFirma=new JLabel();
        jAdministrator=new JLabel();
        jWlasciciel=new JLabel();


        bZapisz=new JButton();
        jWybranaFirmaprzezUzytkownika=new JLabel();
        jWybierzFirme=new JButton();
        jLogin.setText("Nadaj nowy login");
        jLogin.setBounds(10,10,100,20);
        jDialog.add(jLogin);

        jMail.setText("Podaj maila");
        jMail.setBounds(10,40,100,20);
        jDialog.add(jMail);

        jAdministrator.setText("Administator");
        jAdministrator.setBounds(10,70,100,20);
        jDialog.add(jAdministrator);

        checkBoxAdministrator.setBounds(120,70,20,20);
        jDialog.add(checkBoxAdministrator);

        jWlasciciel.setText("Wlaściciel");
        jWlasciciel.setBounds(10,100,100,20);
        jDialog.add(jWlasciciel);

        checkBoxWlasciciel.setBounds(120,100,20,20);
        jDialog.add(checkBoxWlasciciel);

        jWybranaFirma.setText("Wybrana firma");
        jWybranaFirma.setBounds(10,130,100,20);
        jDialog.add(jWybranaFirma);

        jWybranaFirmaprzezUzytkownika.setVisible(false);
        jWybranaFirmaprzezUzytkownika.setBounds(120,130,180,20);
        jDialog.add(jWybranaFirmaprzezUzytkownika);

        tLogin.setBounds(120,10,100,20);
        jDialog.add(tLogin);

        tMail.setBounds(120,40,100,20);
        jDialog.add(tMail);

        jWybierzFirme.setText("Wybierz firmę");
        jWybierzFirme.setBounds(130,170,150,20);
        jDialog.add(jWybierzFirme);

        checkBoxAdministrator.addActionListener(e1 -> {
            if(checkBoxAdministrator.isSelected())
            {
                checkBoxWlasciciel.setSelected(false);
                CheckBoxAdministrator=1;
                CheckBoxWlasciciel=0;
            }
        });
        checkBoxWlasciciel.addActionListener(e1 -> {
            if(checkBoxWlasciciel.isSelected())
            {
                checkBoxAdministrator.setSelected(false);
                CheckBoxWlasciciel=1;
                CheckBoxAdministrator=0;
            }
        });
        bZapisz.setText("Zapisz");
        bZapisz.setBounds(130,200,100,20);
        bZapisz.setEnabled(false);
        bZapisz.addActionListener(e -> {

            String mail=tMail.getText().trim();
            if(SprawdzMaila(mail))
            {

                if(checkBoxWlasciciel.isSelected()||checkBoxAdministrator.isSelected()) {
                    System.out.println("Sprawdzam wlascieiciela " + CheckBoxWlasciciel);
                    System.out.println("Sprawdzam Administratora " + CheckBoxAdministrator);

                    Login = tLogin.getText().trim();
                    haslo = GenerujHasloPoczatkowe(10);

                    DodajUzytkownika(Login, haslo, mail, IdZleceniodawcy, CheckBoxWlasciciel, CheckBoxAdministrator,1);

                    metody.WyslijMailaoZarejestrowaniuNowegoUzytkownikaPrzezAdmina(mail, Login, haslo, javaMailSender);

                    JOptionPane.showMessageDialog(this, "Dodano nowego użytkownika o nazwie " + Login + "\n" +
                            "Wysłano wiadomość do rejestracji dla  : " + Login + "\n" + "Mail " + mail);

                    jDialog.dispose();

                }
                else
                {
                    metody.WyswietlKomunikatoBledzie("Nie wybrałeś funkcji nowego użytkownika");
                }


            }
            else {
                metody.WyswietlKomunikatoBledzie("Niepoprawny mail !");
            }

        });

        jDialog.add(bZapisz);


        jWybierzFirme.addActionListener(e -> {
            Wybierzfirme();
        });


    }

    public void Wybierzfirme() {

        JDialog jDialog = new JDialog();
        jDialog.setTitle("Wybierz firmę");
        jDialog.setSize(1050, 800);
        jDialog.setLayout(null);
        jDialog.setVisible(true);
        jDialog.setLocationRelativeTo(null);

        bDodaj1=new JButton();
        bDodaj1.setEnabled(false);

        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);
        bDodaj1=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj1,"Wybierz",Plus1,100,20);
        bDodaj1.setBounds(10,10,100,20);


        List<Encje.firma> firmaList = firmaRepozytorium.findAll();
        Object[][] dane = new Object[firmaList.size()][9];
        for (int i = 0; i < firmaList.size(); i++) {
            firma firma1 = firmaList.get(i);
            dane[i][0] = firma1.getId();
            dane[i][1] = firma1.getNazwa();
            dane[i][2] = firma1.getUlica();
            dane[i][3] = firma1.getNumer_domu();
            dane[i][4] = firma1.getNumer_mieszkania();
            dane[i][5] = firma1.getKod_pocztowy();
            dane[i][6] = firma1.getMiasto();
            dane[i][7] = firma1.getNip();
            dane[i][8] = firma1.getRegon();
        }

        String[] NazwyKolumn =
                {
                        "ID", "Nazwa", "Ulica", "Numer domu", "Numer mieszkania", "Kod pocztowy", "Miasto", "Nip", "Regon"
                };
        model = new DefaultTableModel(dane, NazwyKolumn);
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // pojedynczy tylko moze byc wybrany wiersz


        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting())
                {
                    if(table.getSelectedRow()!=-1)
                    {
                        bDodaj1.setEnabled(true);// jesli jest klikniete to pozwol aby przycisk byl aktywny
                    }
                    else
                    {
                        bDodaj1.setEnabled(false);
                    }
                }
            }
        });
        table.setModel(model);


        // Dodaj JScrollPane z tabelą do JDialog
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(120, 20, 850, 600); // Ustaw rozmiar i położenie JScrollPane
        jDialog.add(scrollPane); // Dodaj JScrollPane do JDialog



        bDodaj1.addActionListener(e -> {

            int WybranyWiersz=table.getSelectedRow();
            if(WybranyWiersz!=-1) {

                IdZleceniodawcy = (Integer) table.getValueAt(WybranyWiersz, 0);
                NazwaFirmy=(String) table.getValueAt(WybranyWiersz,1);

                Object wartosc=table.getValueAt(WybranyWiersz,1);
                if(wartosc!=null)
                {
                    String wartosc1=wartosc.toString();
                    jWybranaFirmaprzezUzytkownika.setText(wartosc1);
                    jWybranaFirmaprzezUzytkownika.setVisible(true);
                    bZapisz.setEnabled(true);
                }
                System.out.println("ID wybranego zleceniodawcy to "+IdZleceniodawcy);
                System.out.println("Nazwa firmy to "+NazwaFirmy);
                jDialog.dispose();
            }
            else
            {
                metody.WyswietlKomunikatoBledzie("Nie wybrano firmy !");
            }
        });
        jDialog.add(bDodaj1);
    }
    public static boolean SprawdzMaila(String mail)
    {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();

    }

    public static String GenerujHasloPoczatkowe(int dlugosc)
    {
        String znaki= "0123456789!@#$%^&*()_+-=[]{}|;:,.<>?qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        StringBuilder sb=new StringBuilder(dlugosc);
        SecureRandom losowe=new SecureRandom();

        for(int i=0;i<dlugosc;i++)
        {
            int losowyIndex=losowe.nextInt(znaki.length());
            char losowyZnak=znaki.charAt(losowyIndex);
            sb.append(losowyZnak);
        }
        return sb.toString();
    }
public void DodajUzytkownika(String login,String haslo,String mail,int idFirmy,int wlasciciel,int administrator,int zmiana_hasla)
{
    uzytkownicy Uzytkownicy=new uzytkownicy(login,haslo,mail,idFirmy,NazwaFirmy,wlasciciel,administrator,zmiana_hasla);
    repozytorium.save(Uzytkownicy);
}

public void DodajTabele()
{
    List<Encje.uzytkownicy> uzytkownicyList=repozytorium.findAll();
    Object[][] dane=new Object[uzytkownicyList.size()][8];
    for(int i=0;i<uzytkownicyList.size();i++)
    {
        uzytkownicy uzytkownicy1=uzytkownicyList.get(i);
        dane[i][0]=uzytkownicy1.getId();
        dane[i][1]=uzytkownicy1.getLogin();
        dane[i][2]=uzytkownicy1.getMail();
        dane[i][3]=uzytkownicy1.getId_firmy();
        dane[i][4]=uzytkownicy1.getNazwa_firmy();
        dane[i][5]=uzytkownicy1.getWlasciciel();
        dane[i][6]=uzytkownicy1.getAdministrator();
        dane[i][7]=uzytkownicy1.getZmiana_hasla();


    }
    String[] NazwyKolumn=
            {
                    "ID","Login","Mail","ID_firmy","Nazwa firmy","Właściciel","Administrator","Wymagana zmiana hasła"
            };

    model=new DefaultTableModel(dane,NazwyKolumn);
    table=new JTable();
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if(!e.getValueIsAdjusting())
            {
                if(table.getSelectedRow()!=-1)
                {
                    bMinus.setEnabled(true);
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
public void UsunzBazyDanych()
{
    int WybranyWiersz=table.getSelectedRow();
    if(WybranyWiersz!=-1)
    {
        int Id=(Integer) table.getValueAt(WybranyWiersz,0);
        repozytorium.deleteById(Id);
        JOptionPane.showMessageDialog(null, "Usunieto uzytkownika o ID "+Id);

    }
}


}

