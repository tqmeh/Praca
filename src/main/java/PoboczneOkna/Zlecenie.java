package PoboczneOkna;

import Encje.fakturykosztowe;
import Encje.zlecenie;
import Metody.Metody;
import Ograniczenia.LimitowanyText;
import Repozytoria.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.aspectj.bridge.Message;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    fakturykosztoweRepozytoirum  FakturyKosztoweRepozytorium;
    private int userID;
    int IdFirmy;
    int IdZlecenia,idFirmyProgramu;
    double kwota;
    DefaultTableModel model;
    JTable table;
    String sNazwafirmy,sUlica,sDom,sMieszkanie,sKodPocztowy,sMiastp,sNip,sRegon,sKwota,sKontoPLN,sKontoEuro,sIban,sSwift,sNumerFaktury,WalutaZLecenia,
            nazwaFirmyProgramu;
    public Zlecenie(krajRepozytorium KrajRepozytorium, uzytkownicyRepozytorium UytkownicyRepozytorium,zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,
                    int userID,przewoznikRepozytorium PrzewoznikRepozytorium,wykonawcaRepozytorium WykonawcaRepozytorium,samochodRepozytorium SamochodRepozytorium,
                    towarRepozytorium TowarRepozytorium,zlecenieRepozytorium ZlecenieRepozytorium,walutaRepozytorium WalutaRepozytorium,firmaRepozytorium FirmaRepozytorium,
                    fakturykosztoweRepozytoirum FakturyKosztoweRepozytorium)
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
        this.FakturyKosztoweRepozytorium=FakturyKosztoweRepozytorium;
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
        jNoweOknoDialogowe.setSize(800,400);
        jNoweOknoDialogowe.setLayout(null);
        jNoweOknoDialogowe.setVisible(true);
        jNoweOknoDialogowe.setLocationRelativeTo(null);

        int WybranyWiersz=table.getSelectedRow();
        IdZlecenia = (Integer) table.getValueAt(WybranyWiersz, 0);
        String NazwaKrotkaPrzewoznika=ZlecenieRepozytorium.findZleceniobiorcaNazwaKrotkaById(IdZlecenia);
        int IdPrzewoznika=PrzewoznikRepozytorium.findNazwaPelnaFirmyById(NazwaKrotkaPrzewoznika);
        String NazwaPelnaPrzewoznika=PrzewoznikRepozytorium.findNazwaPelnaById(IdPrzewoznika);
        String UlicaPrzewoznika=PrzewoznikRepozytorium.findUlicaById(IdPrzewoznika);
        String NumerDomuPrzewoznika=PrzewoznikRepozytorium.findNumer_DomuById(IdPrzewoznika);
        String NumerMieszkaniaPrzewoznika=PrzewoznikRepozytorium.findNumer_MieszkaniaById(IdPrzewoznika);
        String KodPocztowyPrzewoznika=PrzewoznikRepozytorium.findKod_PocztowyById(IdPrzewoznika);
        String MiastoPrzewoznika=PrzewoznikRepozytorium.findMiastoById(IdPrzewoznika);
        String NipPrzewoznika=PrzewoznikRepozytorium.findNipById(IdPrzewoznika);
        String RegonPrzewoznika=PrzewoznikRepozytorium.findRegonById(IdPrzewoznika);
        String KwotaZlecenia=ZlecenieRepozytorium.findKwota_ZleceniaId(IdZlecenia);
         WalutaZLecenia=ZlecenieRepozytorium.findWalutaId(IdZlecenia);
        String KrajPrzewoznika=PrzewoznikRepozytorium.findKrajId(IdPrzewoznika);
         nazwaFirmyProgramu=ZlecenieRepozytorium.findNazwaFirmyProgramuId(IdZlecenia);
         idFirmyProgramu=ZlecenieRepozytorium.findIDFIRMYId(IdZlecenia);
        if(RegonPrzewoznika.equals("0.0"))
        {
            RegonPrzewoznika="";
        }




        JLabel lFirma,lNazwa,lUlica,lKodPocztowy,lMiasto,lDane,lNip,lRegon,lDaneDoFaktury,lKontoPLN,lKontoEUR,lIban,lSwift,lSlash,
        lKwota,lWaluta,lNumerFaktury;

        JTextField tFirma,tUlica,tNumerDomu,tNumerMieszkania,tKodPocztowy,tMiasto,tNip,tRegon,tKwota;
        LimitowanyText tIban,tSwitf,tKontoPLN,tKontoEur,tNumerFaktury;
        lFirma=new JLabel();
        lNazwa=new JLabel();
        lUlica=new JLabel();
        lKodPocztowy=new JLabel();
        lMiasto=new JLabel();
        lDane=new JLabel();
        lNip=new JLabel();
        lRegon=new JLabel();
        lDaneDoFaktury=new JLabel();
        lKontoPLN=new JLabel();
        lKontoEUR=new JLabel();
        lIban=new JLabel();
        lSwift=new JLabel();
        lKwota=new JLabel();
        lSlash=new JLabel();
        lWaluta=new JLabel();
        lNumerFaktury=new JLabel();
        JButton bZapisz;

        tFirma=new JTextField();
        tUlica=new JTextField();
        tNumerDomu=new JTextField();
        tNumerMieszkania=new JTextField();
        tKodPocztowy=new JTextField();
        tMiasto=new JTextField();
        tNip=new JTextField();
        tRegon=new JTextField();
        tKwota=new JTextField();
        tKontoPLN=new LimitowanyText(26,true);
        tKontoEur=new LimitowanyText(26,true);
        tIban=new LimitowanyText(20,false);
        tSwitf=new LimitowanyText(20,false);
        tNumerFaktury=new LimitowanyText(30,false);

        bZapisz=new JButton();
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lFirma,"Firma",10,10,100,10);
        lFirma.setFont(lFirma.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lNazwa,"Nazwa",10,40,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lUlica,"Ulica",10,70,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKodPocztowy,"Kod pocztowy",10,100,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lMiasto,"Miasto",10,130,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lDane,"Dane",400,10,100,20);
        lDane.setFont(lFirma.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lNip,"NIP",400,40,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lRegon,"Regon",400,70,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lDaneDoFaktury,"Dane  faktury",400,100,150,20);
        lDaneDoFaktury.setFont(lFirma.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKwota,"Kwota",400,130,130,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKontoPLN,"Konto PLN",400,160,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKontoEUR,"Konto EUR",400,190,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lIban,"IBAN",400,220,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lSwift,"Swift",400,250,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lSlash,"/",315,70,20,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lWaluta,"",620,130,30,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lNumerFaktury,"Numer faktury",400,280,100,20);

        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tFirma,120,40,160,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tUlica,120,70,160,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tNumerDomu,290,70,20,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tNumerMieszkania,325,70,20,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tKodPocztowy,120,100,100,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tMiasto,120,130,160,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tNip,510,40,150,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tRegon,510,70,150,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tKwota,510,130,100,20);
        metody.StworzLimitowanyTextjDialog(tKontoPLN,510,160,160,20,jNoweOknoDialogowe);
        metody.StworzLimitowanyTextjDialog(tKontoEur,510,190,160,20,jNoweOknoDialogowe);
        metody.StworzLimitowanyTextjDialog(tIban,510,220,160,20,jNoweOknoDialogowe);
        metody.StworzLimitowanyTextjDialog(tSwitf,510,250,160,20,jNoweOknoDialogowe);
        metody.StworzLimitowanyTextjDialog(tNumerFaktury,510,280,160,20,jNoweOknoDialogowe);

        tFirma.setText(NazwaPelnaPrzewoznika);
        tUlica.setText(UlicaPrzewoznika);
        tNumerDomu.setText(NumerDomuPrzewoznika);
        tNumerMieszkania.setText(NumerMieszkaniaPrzewoznika);
        tKodPocztowy.setText(KodPocztowyPrzewoznika);
        tMiasto.setText(MiastoPrzewoznika);
        tNip.setText(NipPrzewoznika);
        tRegon.setText(RegonPrzewoznika);
        tKwota.setText(KwotaZlecenia);
        lWaluta.setText(WalutaZLecenia);


        metody.StworzJButtonJDialog(jNoweOknoDialogowe,bZapisz,"Zapisz",350,330,100,20);
        bZapisz.addActionListener(e -> {

           sNazwafirmy=pobierz(tFirma);
            sUlica=pobierz(tUlica);
            sDom=pobierz(tNumerDomu);
            sMieszkanie=pobierz(tNumerMieszkania);
            sKodPocztowy=pobierz(tKodPocztowy);
            sMiastp=pobierz(tMiasto);
            sNip=pobierz(tNip);
            sRegon=pobierz(tRegon);
            sKwota=pobierz(tKwota);
            sKontoPLN=pobierz(tKontoPLN);
            sKontoEuro=pobierz(tKontoEur);
            sIban=pobierz(tIban);
            sSwift=pobierz(tSwitf);
            sNumerFaktury=pobierz(tNumerFaktury);


           Sprawdz(WalutaZLecenia,KrajPrzewoznika,sKontoPLN,sKontoEuro,sIban,sSwift,sNumerFaktury,jNoweOknoDialogowe);
            System.out.println(KrajPrzewoznika);

        });
        tFirma.setEditable(false);
        tUlica.setEditable(false);
        tNumerDomu.setEditable(false);
        tNumerMieszkania.setEditable(false);
        tKodPocztowy.setEditable(false);
        tMiasto.setEditable(false);
        tNip.setEditable(false);
        tRegon.setEditable(false);
        tKwota.setEditable(false);



    }

public String pobierz(JTextField textField)
{
    return textField.getText().trim();
}
    public void Sprawdz(String Waluta,String Kraj,String PLN,String Euro,String IBAN,String Swift,String cos,JDialog jDialog) {
       if(Waluta.equals("PLN"))
       {
           if(PLN.isEmpty())
           {
               metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru konta PLN");
           }
           else if(cos.isEmpty())
           {
               metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru faktury");
           }
           else
           {
               kwota=Double.parseDouble(sKwota);
               Encje.fakturykosztowe Fakturykosztowe=new fakturykosztowe(IdZlecenia,sNazwafirmy,sUlica,sDom,sMieszkanie,sKodPocztowy,sMiastp,sNip,sRegon,kwota,WalutaZLecenia,sKontoPLN,
                       sKontoEuro,sIban,sSwift,sNumerFaktury,nazwaFirmyProgramu,idFirmyProgramu);
               FakturyKosztoweRepozytorium.save(Fakturykosztowe);
               JOptionPane.showMessageDialog(null, "Dodano fakturę kosztową do zlecenia o numerze  "+IdZlecenia);

               Optional<zlecenie>optionalZlecenie=ZlecenieRepozytorium.findById(IdZlecenia);
               if (optionalZlecenie.isPresent())
               {
                   zlecenie zleceniedo=optionalZlecenie.get();
                   zleceniedo.setNumerfakturykosztowej(sNumerFaktury);
                   ZlecenieRepozytorium.save(zleceniedo);
               }
               jDialog.dispose();

           }

       }
       else if(!Waluta.equals("PLN"))
       {
           if(Kraj.equals("Polska"))
           {
               metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru konta PLN");
           }
           else if(Euro.isEmpty())
           {
               metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru konta w EURO");
           }

           else if(IBAN.isEmpty())
           {
               metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru IBAN");
           }
           else if(Swift.isEmpty())
           {
               metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru Swift");
           }
           else if(cos.isEmpty())
           {
               metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru faktury");
           }
           else
           {
               kwota=Double.parseDouble(sKwota);
               Encje.fakturykosztowe Fakturykosztowe=new fakturykosztowe(IdZlecenia,sNazwafirmy,sUlica,sDom,sMieszkanie,sKodPocztowy,sMiastp,sNip,sRegon,kwota,WalutaZLecenia,sKontoPLN,
                       sKontoEuro,sIban,sSwift,sNumerFaktury,nazwaFirmyProgramu,idFirmyProgramu);
               FakturyKosztoweRepozytorium.save(Fakturykosztowe);
               JOptionPane.showMessageDialog(null, "Dodano fakturę kosztową do zlecenia o numerze  "+IdZlecenia);
               jDialog.dispose();
           }
       }



    }


}
