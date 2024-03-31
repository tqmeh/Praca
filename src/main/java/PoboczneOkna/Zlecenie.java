package PoboczneOkna;

import Encje.zlecenie;
import Metody.Metody;
import Repozytoria.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class Zlecenie extends JFrame {

    JPanel panelZachodni;
    Metody metody=new Metody();
    JButton bDodaj,bUsun;
    krajRepozytorium KrajRepozytorium;
    uzytkownicyRepozytorium UzytkownicyRepozytorium;
    zleceniodawcaRepozytorium ZleceniodawcaRepozytorium;
    przewoznikRepozytorium PrzewoznikRepozytorium;
    wykonawcaRepozytorium WykonawcaRepozytorium;
    samochodRepozytorium SamochodRepozytorium;
    towarRepozytorium TowarRepozytorium;
    zlecenieRepozytorium ZlecenieRepozytorium;
    private int userID;
    int IdFirmy;
    DefaultTableModel model;
    JTable table;
    public Zlecenie(krajRepozytorium KrajRepozytorium, uzytkownicyRepozytorium UytkownicyRepozytorium,zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,
                    int userID,przewoznikRepozytorium PrzewoznikRepozytorium,wykonawcaRepozytorium WykonawcaRepozytorium,samochodRepozytorium SamochodRepozytorium,
                    towarRepozytorium TowarRepozytorium,zlecenieRepozytorium ZlecenieRepozytorium)
    {
        this.KrajRepozytorium=KrajRepozytorium;
        this.UzytkownicyRepozytorium=UytkownicyRepozytorium;
        this.ZleceniodawcaRepozytorium=ZleceniodawcaRepozytorium;
        this.PrzewoznikRepozytorium=PrzewoznikRepozytorium;
        this.WykonawcaRepozytorium=WykonawcaRepozytorium;
        this.SamochodRepozytorium=SamochodRepozytorium;
        this.TowarRepozytorium=TowarRepozytorium;
        this.ZlecenieRepozytorium=ZlecenieRepozytorium;
        IdFirmy=UzytkownicyRepozytorium.findFirmaIdByUserId(userID);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        setLayout(new BorderLayout());
        this.userID=userID;

        panelZachodni =new JPanel();
        panelZachodni.setLayout(new BoxLayout(panelZachodni,BoxLayout.Y_AXIS));
        ImageIcon Plus=metody.StworzObrazIcone("plus.jpg");
        ImageIcon Plus1=metody.PrzeskalujObraz(Plus,20,20);

        bDodaj=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Dodaj",Plus1,100,20);
        bDodaj.addActionListener(e -> {
            NoweZlecenie noweZlecenie=new NoweZlecenie(KrajRepozytorium,UzytkownicyRepozytorium,ZleceniodawcaRepozytorium,userID,PrzewoznikRepozytorium,WykonawcaRepozytorium,
                    SamochodRepozytorium,TowarRepozytorium,ZlecenieRepozytorium);
            noweZlecenie.setVisible(true);
        });
        ImageIcon Usun=metody.StworzObrazIcone("Minus.jpg");
        ImageIcon Usun1=metody.PrzeskalujObraz(Usun,20,20);
        bUsun=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Usun",Usun1,100,20);
        bUsun.setEnabled(false);
        bUsun.addActionListener(e -> {
            UsunzBazyDanych();
        });
        panelZachodni.add(bDodaj);
        panelZachodni.add(bUsun);
        add(panelZachodni);
        setVisible(true);
        WyswietlTabele();
        add(panelZachodni,BorderLayout.WEST);


    }
    public void WyswietlTabele()
    {
        List<Encje.zlecenie> zlecenieList=ZlecenieRepozytorium.findCompanyByFirmaId(IdFirmy);
        Object[][] dane=new Object[zlecenieList.size()][12];
        for(int i=0;i<zlecenieList.size();i++)
        {
            zlecenie zlecenie1=zlecenieList.get(i);
            dane[i][0]=zlecenie1.getId();
            dane[i][1]=zlecenie1.getWykonawca();
            dane[i][2]=zlecenie1.getZlecemiodawca_nazwa_skrocona();
            dane[i][3]=zlecenie1.getRodzaj_towaru();
            dane[i][4]=zlecenie1.getIlosc();
            dane[i][5]=zlecenie1.getWaga();
            dane[i][6]=zlecenie1.getKraj_zaladunku();
            dane[i][7]=zlecenie1.getData_zaladunku();
            dane[i][8]=zlecenie1.getMiejscowosc_zaladunku();
            dane[i][9]=zlecenie1.getKraj_rozladunku();
            dane[i][10]=zlecenie1.getData_rozladunku();
            dane[i][11]=zlecenie1.getMiejscowosc_rozladunku();
        }
        String[] NazwyKolumn={
                "ID","Wykonawca","ZLeceniodawca","Rodzaj towaru","Ilosc","Waga","KrajZaladunku","Data załadunku","Miejscowość załadunku",
                "Kraj rozładunku","Data rozładunku","Miejscowość rozładunku"
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
                        bUsun.setEnabled(true);

                    }
                    else
                    {
                        bUsun.setEnabled(false);

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
            ZlecenieRepozytorium.deleteById(Id);
            JOptionPane.showMessageDialog(null, "Usunieto zlecenie o  numerze ID "+Id);

        }
    }
}
