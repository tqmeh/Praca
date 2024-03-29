package Metody;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Metody {


    public void StworzNapis(Container container, JLabel label, String napis, int a, int b, int c, int d)
    {
        label=new JLabel();
        label.setText(napis);
        label.setBounds(a,b,c,d);
        container.add(label);
    }

    public JTextField StworzJTextField(Container container,int a,int b, int c,int d)
    {
        JTextField textField=new JTextField();
        textField=new JTextField();
        textField.setBounds(a,b,c,d);
        container.add(textField);
        return textField;
    }

    public JButton StworzJButton(Container container,String napis, int a,int b, int c, int d)
    {
        JButton jButton=new JButton();
        jButton=new JButton();
        jButton.setText(napis);
        jButton.setBounds(a,b,c,d);
        container.add(jButton);

        return jButton;
    }

    public ImageIcon StworzObrazIcone(String sciezka)
    {
        try {

            ClassLoader classLoader = getClass().getClassLoader();//Uzyskanie dostepu do classloadera bya zaciagnac dane
            URL imgUrl = classLoader.getResource(sciezka);//szukaj obrazu
            if (imgUrl != null) {// Sprawdzenie, czy plik został znaleziony
                BufferedImage img = ImageIO.read(imgUrl);
                return new ImageIcon(img);
            } else {
                System.out.println("Nie można znaleźć pliku z obrazem w zasobach: " + sciezka);
                return null;
            }
        } catch (IOException e) {
            System.out.println("Błąd podczas wczytywania obrazu: " + e.getMessage());
            return null;
        }
    }

public ImageIcon PrzeskalujObraz(ImageIcon icon,int szerokosc,int wysokosc) {
    Image przeskaluj = icon.getImage().getScaledInstance(szerokosc, wysokosc, Image.SCALE_SMOOTH);
    return new ImageIcon(przeskaluj);
}

public JButton StworzPrzyciskzObrazem(JButton jButton,String tekst,ImageIcon icon,int a,int b)
{
    jButton=new JButton(tekst,icon);
    jButton.setPreferredSize(new Dimension(a,b));
    jButton.setHorizontalTextPosition(SwingConstants.CENTER);
    jButton.setVerticalTextPosition(SwingConstants.BOTTOM);

    return jButton;
}
    public JButton StworzPrzyciskzObrazemzTekstemObok(JButton jButton,String tekst,ImageIcon icon,int a,int b)
    {
        jButton=new JButton(tekst,icon);
        jButton.setPreferredSize(new Dimension(a,b));


        return jButton;
    }
    public void WyswietlKomunikatoBledzie(String Blad)
    {
        JOptionPane.showMessageDialog(
                null,
                Blad,
                "Bład",
                JOptionPane.ERROR_MESSAGE
        );
    }
    public void WyslijMailaoZarejestrowaniuNowegoUzytkownikaPrzezAdmina(String mail,String login, String haslo, JavaMailSender javaMailSender)
    {
        SimpleMailMessage wiadomosc=new SimpleMailMessage();
        wiadomosc.setFrom("druzynajavy@gmail.com");
        wiadomosc.setTo(mail);
        wiadomosc.setSubject("Nowe konto");
        wiadomosc.setText("Utworzyliśmy dla Państwa nowe konto"+"\n"+"Login " +login +"\n"+"Hasło "+haslo);
        javaMailSender.send(wiadomosc);

    }

}
