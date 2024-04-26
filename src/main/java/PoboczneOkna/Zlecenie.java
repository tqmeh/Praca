package PoboczneOkna;

import Encje.zlecenie;
import Metody.Metody;
import Repozytoria.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Zlecenie extends JFrame {

    JPanel panelZachodni;
    Metody metody=new Metody();
    JButton bDodaj,bUsun,bGenerujZlecenie,bDodajFaktureKosztowa;
    krajRepozytorium KrajRepozytorium;
    uzytkownicyRepozytorium UzytkownicyRepozytorium;
    zleceniodawcaRepozytorium ZleceniodawcaRepozytorium;
    przewoznikRepozytorium PrzewoznikRepozytorium;
    wykonawcaRepozytorium WykonawcaRepozytorium;
    samochodRepozytorium SamochodRepozytorium;
    towarRepozytorium TowarRepozytorium;
    firmaRepozytorium FirmaRepozytorium;
    zlecenieRepozytorium ZlecenieRepozytorium;
    walutaRepozytorium WalutaRepozytorium;
    private int userID;
    int IdFirmy;
    DefaultTableModel model;
    JTable table;
    public Zlecenie(krajRepozytorium KrajRepozytorium, uzytkownicyRepozytorium UytkownicyRepozytorium,zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,
                    int userID,przewoznikRepozytorium PrzewoznikRepozytorium,wykonawcaRepozytorium WykonawcaRepozytorium,samochodRepozytorium SamochodRepozytorium,
                    towarRepozytorium TowarRepozytorium,zlecenieRepozytorium ZlecenieRepozytorium,walutaRepozytorium WalutaRepozytorium,firmaRepozytorium FirmaRepozytorium)
    {
        this.KrajRepozytorium=KrajRepozytorium;
        this.UzytkownicyRepozytorium=UytkownicyRepozytorium;
        this.ZleceniodawcaRepozytorium=ZleceniodawcaRepozytorium;
        this.PrzewoznikRepozytorium=PrzewoznikRepozytorium;
        this.WykonawcaRepozytorium=WykonawcaRepozytorium;
        this.SamochodRepozytorium=SamochodRepozytorium;
        this.TowarRepozytorium=TowarRepozytorium;
        this.ZlecenieRepozytorium=ZlecenieRepozytorium;
        this.WalutaRepozytorium=WalutaRepozytorium;
        this.FirmaRepozytorium=FirmaRepozytorium;
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
                    SamochodRepozytorium,TowarRepozytorium,ZlecenieRepozytorium,WalutaRepozytorium);
            noweZlecenie.setVisible(true);
        });
        ImageIcon Usun=metody.StworzObrazIcone("Minus.jpg");
        ImageIcon Usun1=metody.PrzeskalujObraz(Usun,20,20);
        bUsun=metody.StworzPrzyciskzObrazemzTekstemObok(bDodaj,"Usun",Usun1,100,20);
        bUsun.setEnabled(false);
        bUsun.addActionListener(e -> {
            UsunzBazyDanych();
        });
        bGenerujZlecenie=metody.StworzPrzyciskzObrazemzTekstemObok(bGenerujZlecenie,"Generuj zlecenie",Usun1,100,20);
        bGenerujZlecenie.setEnabled(false);
        bGenerujZlecenie.addActionListener(e -> {
            try {
                WezzBazy();

            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }


        });

        bDodajFaktureKosztowa=metody.StworzPrzyciskzObrazemzTekstemObok(bDodajFaktureKosztowa,"Dodaj fakturę kosztową",Usun1,200,20);
        bDodajFaktureKosztowa.setEnabled(false);
        bDodajFaktureKosztowa.addActionListener(e -> {
            PrzyciskDodajFaktureKosztowa();
        });
        panelZachodni.add(bDodaj);
        panelZachodni.add(bUsun);
        panelZachodni.add(bGenerujZlecenie);
        panelZachodni.add(bDodajFaktureKosztowa);
        add(panelZachodni);
        setVisible(true);
        WyswietlTabele();
        add(panelZachodni,BorderLayout.WEST);


    }
    public void WyswietlTabele()
    {
        List<Encje.zlecenie> zlecenieList=ZlecenieRepozytorium.findCompanyByFirmaId(IdFirmy);
        Object[][] dane=new Object[zlecenieList.size()][16];
        for(int i=0;i<zlecenieList.size();i++)
        {
            zlecenie zlecenie1=zlecenieList.get(i);
            dane[i][0]=zlecenie1.getId();
            dane[i][1]=zlecenie1.getWykonawca();
            dane[i][2]=zlecenie1.getPrzewoznik_nazwa_skrocona();
            dane[i][3]=zlecenie1.getZlecemiodawca_nazwa_skrocona();
            dane[i][4]=zlecenie1.getRodzaj_towaru();
            dane[i][5]=zlecenie1.getIlosc();
            dane[i][6]=zlecenie1.getWaga();
            dane[i][7]=zlecenie1.getKraj_zaladunku();
            dane[i][8]=zlecenie1.getData_zaladunku();
            dane[i][9]=zlecenie1.getMiejscowosc_zaladunku();
            dane[i][10]=zlecenie1.getKraj_rozladunku();
            dane[i][11]=zlecenie1.getData_rozladunku();
            dane[i][12]=zlecenie1.getMiejscowosc_rozladunku();
            dane[i][13]=zlecenie1.getKwota_frachtu();
            dane[i][14]=zlecenie1.getKwota_zlecenia();
            dane[i][15]=zlecenie1.getWaluta();
        }
        String[] NazwyKolumn={
                "ID","Wykonawca","Zleceniobiorca","Zleceniodawca","Rodzaj towaru","Ilosc","Waga","KrajZaladunku","Data załadunku","Miejscowość załadunku",
                "Kraj rozładunku","Data rozładunku","Miejscowość rozładunku","Kwota frachtu","Kwota zlecenia","Waluta"
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
                        bGenerujZlecenie.setEnabled(true);
                        int WybranyWiersz=table.getSelectedRow();
                        int Id = (Integer) table.getValueAt(WybranyWiersz, 0);
                        String wykonawca=ZlecenieRepozytorium.findWykonawcaId(Id);
                        if(wykonawca.equals("Wlasny transport"))
                        {
                            bDodajFaktureKosztowa.setEnabled(false);
                        }
                        else
                        {
                            bDodajFaktureKosztowa.setEnabled(true);
                        }

                    }
                    else
                    {
                        bUsun.setEnabled(false);
                        bGenerujZlecenie.setEnabled(false);
                        bDodajFaktureKosztowa.setEnabled(false);

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
    public void WezzBazy() throws IOException
    {
        int WybranyWiersz=table.getSelectedRow();
        if(WybranyWiersz!=-1)
        {
            int Id=(Integer) table.getValueAt(WybranyWiersz,0);

            String zleceniobiorcaNazwaKrotka=ZlecenieRepozytorium.findZleceniobiorcaNazwaKrotkaById(Id);
            int idPrzewoznika=PrzewoznikRepozytorium.findNazwaPelnaFirmyById(zleceniobiorcaNazwaKrotka);

            String nazwaFirmy=FirmaRepozytorium.findnazwaFirmyById(IdFirmy);
            String ulica=FirmaRepozytorium.findulicaFirmyById(IdFirmy);
            String numer_domu=FirmaRepozytorium.findnumerdomuFirmyById(IdFirmy);
            String numer_mieszkania=FirmaRepozytorium.findnumermieszkaniaFirmyById(IdFirmy);
            String kod_pocztowy=FirmaRepozytorium.findkodpocztowyFirmyById(IdFirmy);
            String miasto=FirmaRepozytorium.findmiastoFirmyById(IdFirmy);
            String nip=FirmaRepozytorium.findnipFirmyById(IdFirmy);
            String regon=FirmaRepozytorium.findregonFirmyById(IdFirmy);

            String NazwaPelnaPrzewoznika=PrzewoznikRepozytorium.findNazwaPelnaById(idPrzewoznika);
            String UlicaPrzewoznika=PrzewoznikRepozytorium.findUlicaById(idPrzewoznika);
            String NumerDomuPrzewoznika=PrzewoznikRepozytorium.findNumer_DomuById(idPrzewoznika);
            String NumerMieszkaniaPrzewoznika=PrzewoznikRepozytorium.findNumer_MieszkaniaById(idPrzewoznika);
            String KodPocztowyPrzewoznika=PrzewoznikRepozytorium.findKod_PocztowyById(idPrzewoznika);
            String MiastoPrzewoznika=PrzewoznikRepozytorium.findMiastoById(idPrzewoznika);
            String NipPrzewoznika=PrzewoznikRepozytorium.findNipById(idPrzewoznika);

            String RodzajTowaru=ZlecenieRepozytorium.findRodzaj_TowaruById(Id);
            String Ilosc=ZlecenieRepozytorium.findIloscById(Id);
            String waga=ZlecenieRepozytorium.findWagaById(Id);

            String KwotaFrachtu=ZlecenieRepozytorium.findKwota_FrachtuId(Id);
            String KwotaZlecenie=ZlecenieRepozytorium.findKwota_ZleceniaId(Id);
            String Waluta=ZlecenieRepozytorium.findWalutaId(Id);

            String DataZaladunku=ZlecenieRepozytorium.findData_ZaladunkuById(Id);
            String KodPocztowyZaladunku=ZlecenieRepozytorium.findKod_Pocztowy_ZaladunkuById(Id);
            String MiastoZaladunku=ZlecenieRepozytorium.findMiejscowosc_ZaladunkuById(Id);
            String UlicaZaladunku=ZlecenieRepozytorium.findUlica_ZaladunkuById(Id);
            String FirmaZaladunek=ZlecenieRepozytorium.findFirma_ZaladunekById(Id);
            String KrajZaladunku=ZlecenieRepozytorium.findKraj_ZaladunkuById(Id);
            String DataRozladunku=ZlecenieRepozytorium.findData_RozladunkuId(Id);
            String KodPocztowyRozladunku=ZlecenieRepozytorium.findKod_Pocztowy_RozladunkukById(Id);
            String MiastoRozladunku=ZlecenieRepozytorium.findMiejscowosc_RozladunkuId(Id);
            String UlicaRozladunku=ZlecenieRepozytorium.findUlica_RozladunkuId(Id);
            String FirmaRozladunek=ZlecenieRepozytorium.findFirma_RozladunekId(Id);
            String KrajRozladunku=ZlecenieRepozytorium.findKraj_RozladunkuById(Id);
            String WarunkiZlecenia=ZlecenieRepozytorium.findWarunkiId(Id);
            System.out.println(NumerMieszkaniaPrzewoznika);
            if(NumerMieszkaniaPrzewoznika==null)
            {
                NumerMieszkaniaPrzewoznika="";
            }
            if(numer_mieszkania==null)
            {
                numer_mieszkania="";
            }
            PDDocument document=new PDDocument();
            PDPage page=new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();

            contentStream.setFont(PDType1Font.COURIER, 12);
            float x = 50; // Współrzędna x
            float y = 700; // Współrzędna y
            float przesuniecieY = -20; // Przesunięcie w dół
            float przenusiecieX=10;
            contentStream.newLineAtOffset(x, y);
            contentStream.showText("Zleceniodawca");
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(nazwaFirmy);
            contentStream.newLineAtOffset(0, przesuniecieY);
            contentStream.showText(ulica+" "+numer_domu+" "+numer_mieszkania);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(kod_pocztowy+" "+miasto);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("NIP "+nip);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Regon "+regon);
            contentStream.newLineAtOffset(350,100);
            contentStream.showText("Zleceniobiorca");
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(NazwaPelnaPrzewoznika);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(UlicaPrzewoznika+" "+NumerDomuPrzewoznika+" "+NumerMieszkaniaPrzewoznika);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(KodPocztowyPrzewoznika+" "+MiastoPrzewoznika);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("NIP "+NipPrzewoznika);
            contentStream.newLineAtOffset(-150,przesuniecieY*4);
            contentStream.showText("Zlecenie numer "+Id);
            contentStream.newLineAtOffset(-200,przesuniecieY*2);
            contentStream.showText("Towar "+RodzajTowaru);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Ilosc "+Ilosc);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Waga "+waga+" kg");

            contentStream.newLineAtOffset(300,40);
            contentStream.showText("Kwota zlecenie "+KwotaFrachtu+" "+Waluta);


            contentStream.newLineAtOffset(-300,przesuniecieY*4);
            contentStream.showText("Zaladunek");

            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Data zaladunku "+ DataZaladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Kod poczotwy "+KodPocztowyZaladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Miasto "+MiastoZaladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Ulica "+UlicaZaladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Firma zaladunku");
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(FirmaZaladunek);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Kraj "+KrajZaladunku);
            contentStream.newLineAtOffset(300,140);
            contentStream.showText("Rozladunek");
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Data rozladunku "+DataRozladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Kod pocztowy "+KodPocztowyRozladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Miasto "+MiastoRozladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Ulica "+UlicaRozladunku);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Firma rozladunku");
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(FirmaRozladunek);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Kraj "+KrajRozladunku);


            contentStream.endText();
            contentStream.close();

            document.save("Numer zlecenia "+Id+".pdf");
            document.close();
            JOptionPane.showMessageDialog(null, "wygenerowałem nowe zlecenie o numerze "+Id);

        }
    }
    public void PrzyciskDodajFaktureKosztowa()
    {
        JDialog jNoweOknoDialogowe=new JDialog();
        jNoweOknoDialogowe.setTitle("Dodaj firmę");
        jNoweOknoDialogowe.setSize(650,300);
        jNoweOknoDialogowe.setLayout(null);
        jNoweOknoDialogowe.setVisible(true);
        jNoweOknoDialogowe.setLocationRelativeTo(null);

        int WybranyWiersz=table.getSelectedRow();
        int Id = (Integer) table.getValueAt(WybranyWiersz, 0);
        JLabel lNazwa;
        lNazwa=new JLabel();
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lNazwa,"Firma",0,0,100,20);


    }




}
