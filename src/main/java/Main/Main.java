package Main;


import Logowanie.Logowanie;

import Metody.MailConfig;
import com.formdev.flatlaf.FlatLightLaf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.swing.*;
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"Repozytoria"})
@EntityScan(basePackages = {"Encje"})
@ComponentScan(basePackages = {"Logowanie", "Repozytoria", "Encje"})
@Import(MailConfig.class)
public class Main {
    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            throw new RuntimeException(ex);
        }

        System.setProperty("java.awt.headless", "false");
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Logowanie logowanie=context.getBean(Logowanie.class);
        logowanie.setVisible(true);

    }

}
