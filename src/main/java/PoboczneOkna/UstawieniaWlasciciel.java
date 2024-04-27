package PoboczneOkna;


import GlowneOkno.DodajUseraAdmin;
import Metody.Metody;
import Repozytoria.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Encje.*;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
public class UstawieniaWlasciciel extends JFrame {
    JPanel panelZachodni;
     Metody metody=new Metody();
     JButton bDodaj,bMinus;
     private int userID;
     uzytkownicyRepozytorium UzytkownicyRepozytoria;
     String HasloPoczatkowe;
     Integer FirmaId;
     String NazwaFirmy;
     JavaMailSender javaMailSender;
    DefaultTableModel model;
    JTable table;
    public UstawieniaWlasciciel(int userID, uzytkownicyRepozytorium UzytkownicyRepozytoria, JavaMailSender javaMailSender)
    {
        this.userID=userID;
        this.UzytkownicyRepozytoria=UzytkownicyRepozytoria;
        this.javaMailSender=javaMailSender;
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        panelZachodni = new JPanel();
        panelZachodni.setLayout(new BoxLayout(panelZachodni, BoxLayout.Y_AXIS));
        FirmaId=UzytkownicyRepozytoria.findFirmaIdByUserId(userID);
        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);
        ImageIcon Minus=metody.StworzObrazIcone("minus.jpg");
        ImageIcon Minus1=metody.PrzeskalujObraz(Minus,20,20);
        bDodaj=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Dodaj",Plus1,100,20);
        bDodaj.addActionListener(e -> {


            NazwaFirmy=UzytkownicyRepozytoria.findNazwaFirmyByUserId(userID);


            StworzDodawanieUzytkownika();
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
        WyswietlTablice();
        add(panelZachodni,BorderLayout.WEST);
    }
    public void StworzDodawanieUzytkownika() {
        JDialog jDialog = new JDialog();
        jDialog.setTitle("Dodaj użytkownika");
        jDialog.setSize(400, 200);
        jDialog.setLayout(null);
        jDialog.setVisible(true);
        jDialog.setLocationRelativeTo(null);

        JLabel lLogin,lMail;
        JTextField tLogin,tMail;
        JButton bZapisz;

        lLogin=new JLabel();
        lMail=new JLabel();

        tLogin=new JTextField();
        tMail=new JTextField();

        bZapisz=new JButton();

        metody.StworzNapisJDialog(jDialog,lLogin,"Login",10,10,100,20);
        jDialog.add(lLogin);

        metody.StworzNapisJDialog(jDialog,lMail,"Mail",10,40,100,20);
        jDialog.add(lMail);

        metody.StworzJTextFieldJDialog(jDialog,tLogin,120,10,100,20);
        jDialog.add(tLogin);

        metody.StworzJTextFieldJDialog(jDialog,tMail,120,40,100,20);
        jDialog.add(tMail);

        metody.StworzJButtonJDialog(jDialog,bZapisz,"Zapisz",120,90,100,20);
        bZapisz.addActionListener(e -> {
            String mail=tMail.getText().trim();
            if(SprawdzMaila(mail))
            {
                String login=tLogin.getText().trim();

                HasloPoczatkowe=metody.GenerujHasloPoczatkowe(10);

                if(!login.isEmpty()&&!mail.isEmpty())
                {
                    DodajUzytkownika(login,HasloPoczatkowe,mail,FirmaId,0,0,1);
                    metody.WyslijMailaoZarejestrowaniuNowegoUzytkownikaPrzezAdmina(mail,login,HasloPoczatkowe,javaMailSender);
                    JOptionPane.showMessageDialog(this, "Dodano nowego użytkownika o nazwie " + login + "\n" +
                            "Wysłano wiadomość do rejestracji dla  : " + login + "\n" + "Mail " + mail);
                    jDialog.dispose();
                }
            }
            else
            {
                metody.WyswietlKomunikatoBledzie("Mail niepoprawny !");
            }
        });
        jDialog.add(bZapisz);
    }
    public static boolean SprawdzMaila(String mail)
    {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();

    }
    public void DodajUzytkownika(String login,String haslo,String mail,int idFirmy,int wlasciciel,int administrator,int zmiana_hasla)
    {
        uzytkownicy Uzytkownicy=new uzytkownicy(login,haslo,mail,idFirmy,NazwaFirmy,wlasciciel,administrator,zmiana_hasla);
        UzytkownicyRepozytoria.save(Uzytkownicy);
    }

    public void WyswietlTablice()
    {
        List<Encje.uzytkownicy> uzytkownicy = UzytkownicyRepozytoria.findUsersByFirmaIdLista(FirmaId);
        Object[][] dane = new Object[uzytkownicy.size()][4];
        for(int i=0;i<uzytkownicy.size();i++)
        {
            uzytkownicy uzytkownicy1= uzytkownicy.get(i);
            dane[i][0]=uzytkownicy1.getId();
            dane[i][1]=uzytkownicy1.getLogin();
            dane[i][2]=uzytkownicy1.getMail();
            dane[i][3]=uzytkownicy1.getZmiana_hasla();
        }
        String[] NazwyKolumn={
          "ID","Login","Mail","Wymagana zmiana hasła"
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
            UzytkownicyRepozytoria.deleteById(Id);
            JOptionPane.showMessageDialog(null, "Usunieto uzytkownika o ID "+Id);

        }
    }
}
