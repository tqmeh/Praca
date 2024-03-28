package Logowanie;

import Encje.uzytkownicy;
import GlowneOkno.GlowneAdmin;
import Metody.Metody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Repozytoria.uzytkownicyRepozytorium;
import Repozytoria.firmaRepozytorium;
import javax.swing.*;
import java.util.Optional;

@Component
public class Logowanie extends JFrame {

    Metody metody=new Metody();


    JLabel lLogin,lHaslo;
    JTextField tLogin,tHaslo;
    uzytkownicyRepozytorium uzytkownicyRepozytorium;
    firmaRepozytorium firmaRepozytorium;

    JButton bZaloguj,bWyjdz;
    @Autowired
    public Logowanie(Repozytoria.uzytkownicyRepozytorium uzytkownicyRepozytorium,firmaRepozytorium firmaRepozytorium) {

        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);
        this.uzytkownicyRepozytorium=uzytkownicyRepozytorium;
        this.firmaRepozytorium=firmaRepozytorium;


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
                uzytkownicy user = optionalUser.get();

                if (user.getHaslo().equals(haslo)) {

                    JOptionPane.showMessageDialog(this, "Zalogowano pomyślnie jako: " + login);
                    dispose();
                    GlowneAdmin glowneAdmin=new GlowneAdmin(uzytkownicyRepozytorium,firmaRepozytorium);
                    glowneAdmin.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(this, "Nieprawidłowe hasło", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Użytkownik o podanym loginie nie istnieje", "Błąd logowania", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

