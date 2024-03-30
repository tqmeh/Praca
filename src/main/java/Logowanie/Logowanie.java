package Logowanie;

import Encje.uzytkownicy;
import GlowneOkno.GlowneAdmin;
import GlowneOkno.OknoGlowneProgramu;
import Metody.Metody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import Repozytoria.uzytkownicyRepozytorium;
import Repozytoria.firmaRepozytorium;
import Repozytoria.krajRepozytorium;
import Repozytoria.zleceniodawcaRepozytorium;
import Repozytoria.przewoznikRepozytorium;
import javax.swing.*;
import java.util.Optional;

@Component
public class Logowanie extends JFrame {

    Metody metody=new Metody();


    JLabel lLogin,lHaslo;
    JTextField tLogin,tHaslo;
    uzytkownicyRepozytorium uzytkownicyRepozytorium;
    firmaRepozytorium firmaRepozytorium;
    krajRepozytorium KrajRepozytorium;
    JavaMailSender javaMailSender;
    JButton bZaloguj,bWyjdz;
    String haslo,mail,login1,haslo1;
    uzytkownicy user;
    zleceniodawcaRepozytorium ZleceniodawcaRepozytorium;
    przewoznikRepozytorium PrzewoznikRepozytorium;

    @Autowired

    public Logowanie(Repozytoria.uzytkownicyRepozytorium uzytkownicyRepozytorium,firmaRepozytorium firmaRepozytorium,JavaMailSender javaMailSender,krajRepozytorium KrajRepozytorium,
                     zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,przewoznikRepozytorium PrzewoznikRepozytorium) {

        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        this.KrajRepozytorium=KrajRepozytorium;
        this.uzytkownicyRepozytorium=uzytkownicyRepozytorium;
        this.firmaRepozytorium=firmaRepozytorium;
        this.javaMailSender=javaMailSender;
        this.ZleceniodawcaRepozytorium=ZleceniodawcaRepozytorium;
        this.PrzewoznikRepozytorium=PrzewoznikRepozytorium;


        metody.StworzNapis(this, lLogin, "Login", 10, 10, 100, 20);
        metody.StworzNapis(this, lHaslo, "Hasło", 10, 40, 100, 20);

        tLogin = metody.StworzJTextField(this, 120, 10, 100, 20);
        tHaslo = metody.StworzJTextField(this, 120, 40, 100, 20);

        bZaloguj = metody.StworzJButton(this, "Zaloguj", 60, 80, 100, 20);
        bWyjdz = metody.StworzJButton(this, "Wyjdź", 170, 80, 100, 20);

        bWyjdz.addActionListener(e -> {
            dispose();
        });


        bZaloguj.addActionListener(e -> {
            String login=tLogin.getText().trim();
            String haslo=tHaslo.getText().trim();

            Optional<uzytkownicy> optionalUser = uzytkownicyRepozytorium.findByLogin(login);

            if (optionalUser.isPresent()) {
                 user = optionalUser.get();

                if (user.getHaslo().equals(haslo)) {

                    JOptionPane.showMessageDialog(this, "Zalogowano pomyślnie jako: " + login);

                    if(user.getAdministrator()==1) {
                        GlowneAdmin glowneAdmin = new GlowneAdmin(uzytkownicyRepozytorium, firmaRepozytorium, javaMailSender);
                        dispose();
                        glowneAdmin.setVisible(true);

                    }
                    else {
                        if(user.getZmiana_hasla()==1)
                        {
                            JOptionPane.showMessageDialog(this, "Jako, że jest to twoje pierwsze logowanie, wymagamy zmiane hasła");
                            mail=user.getMail();
                            login1=user.getLogin();
                            ZmienHaslo();
                        }
                        else {
                            OknoGlowneProgramu oknoGlowneProgramu=new OknoGlowneProgramu(user.getId(),uzytkownicyRepozytorium,javaMailSender,KrajRepozytorium,ZleceniodawcaRepozytorium,PrzewoznikRepozytorium);
                            oknoGlowneProgramu.setVisible(true);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Nieprawidłowe hasło", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Użytkownik o podanym loginie nie istnieje", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
public void ZmienHaslo()
{
    JDialog jDialog=new JDialog();
    jDialog.setTitle("Zmień hasło");
    jDialog.setSize(300,200);
    jDialog.setLayout(null);
    jDialog.setVisible(true);
    jDialog.setLocationRelativeTo(null);

    JLabel lNoweHaslo,lPowtorzHaslo,lHasloMail;
    JTextField tNoweHaslo,tPowtorzHaslo,tHasloMail;
    JButton bZapisz1,bZapisz2;

    lNoweHaslo=new JLabel();
    lPowtorzHaslo=new JLabel();
    lHasloMail=new JLabel();
    lHasloMail.setVisible(false);

    tNoweHaslo=new JTextField();
    tPowtorzHaslo=new JTextField();
    tHasloMail=new JTextField();
    tHasloMail.setVisible(false);
    bZapisz1=new JButton();
    bZapisz2=new JButton();
    bZapisz2.setVisible(false);

    metody.StworzNapisJDialog(jDialog,lNoweHaslo,"Nowe Hasło",10,10,100,20);
    metody.StworzNapisJDialog(jDialog,lPowtorzHaslo,"Powtórz hasło",10,40,100,20);
    metody.StworzNapisJDialog(jDialog,lHasloMail,"Hasło z maila",10,10,100,20);
    metody.StworzJTextFieldJDialog(jDialog,tNoweHaslo,120,10,100,20);
    metody.StworzJTextFieldJDialog(jDialog,tPowtorzHaslo,120,40,100,20);
    metody.StworzJTextFieldJDialog(jDialog,tHasloMail,120,10,100,20);

    metody.StworzJButtonJDialog(jDialog,bZapisz1,"Zapisz",120,70,100,20);
    metody.StworzJButtonJDialog(jDialog,bZapisz2,"Zapisz",120,70,100,20);

    bZapisz1.addActionListener(e -> {
         haslo1=tNoweHaslo.getText().trim();
        String haslo2=tPowtorzHaslo.getText().trim();

        if(!haslo1.isEmpty()&&!haslo2.isEmpty())
        {
            haslo=metody.GenerujKoddlaPotwierdzenia(10);
            metody.WyslijMailazKodemJednorazowym(mail,"Kod jednorwazowy do zmiany hasła",
                    javaMailSender,haslo,"Witamy "+login1+" wysyłamy do cebie jednorazowy kod potwierdzajacy."+"\n Wprowadź go do programu aby potwierdzić zmianę hasła");
            JOptionPane.showMessageDialog(this, "Na maila został wysłany kod potwierdający wprowadź go!");
            lNoweHaslo.setVisible(false);
            lPowtorzHaslo.setVisible(false);
            tNoweHaslo.setVisible(false);
            tPowtorzHaslo.setVisible(false);
            lHasloMail.setVisible(true);
            tHasloMail.setVisible(true);
            bZapisz1.setVisible(false);
            bZapisz2.setVisible(true);




            bZapisz2.addActionListener(e1 -> {
                String haslo3=tHasloMail.getText().trim();
                if(haslo3.equals(haslo))
                {
                    user.setHaslo(haslo1);
                    user.setZmiana_hasla(0);
                    uzytkownicyRepozytorium.save(user);
                    JOptionPane.showMessageDialog(this, "Hasło zmienione");
                }
                else
                {
                    metody.WyswietlKomunikatoBledzie("Hasła nie są zgodne !");
                }


            });


        }
        else {
            metody.WyswietlKomunikatoBledzie("Któreś pole nie jest wpisane !");
        }
    });








}


}

