package PoboczneOkna;

import Encje.faktura;
import Encje.fakturykosztowe;
import Encje.zlecenie;
import Metody.Metody;
import Ograniczenia.LimitowanyText;
import Repozytoria.*;
import com.toedter.calendar.JDateChooser;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Zlecenie extends JFrame {

    JPanel panelZachodni;
    Metody metody=new Metody();
    JButton bDodaj,bUsun,bGenerujZlecenie,bDodajFaktureKosztowa,bDodajFakture,bGenerujFakture;
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
    numerRepozytorium NumerRepozytorium;

    fakutraRepozytorium FakutraRepozytorium;
    stawkavatRepozytorium StawkavatRepozytorium;
    private int userID;
    int IdFirmy;
    int IdZlecenia,idFirmyProgramu;
    double kwota,KwotaBrutto,KwotaNetto;
    DefaultTableModel model;
    JTable table;
    String sNazwafirmy,sUlica,sDom,sMieszkanie,sKodPocztowy,sMiastp,sNip,sRegon,sKwota,sKontoPLN,sKontoEuro,sIban,sSwift,sNumerFaktury,WalutaZLecenia,
            nazwaFirmyProgramu,sDataSprzedazy,sDataWystawienia;

    String Wybranastawka;
    public Zlecenie(krajRepozytorium KrajRepozytorium, uzytkownicyRepozytorium UytkownicyRepozytorium,zleceniodawcaRepozytorium ZleceniodawcaRepozytorium,
                    int userID,przewoznikRepozytorium PrzewoznikRepozytorium,wykonawcaRepozytorium WykonawcaRepozytorium,samochodRepozytorium SamochodRepozytorium,
                    towarRepozytorium TowarRepozytorium,zlecenieRepozytorium ZlecenieRepozytorium,walutaRepozytorium WalutaRepozytorium,firmaRepozytorium FirmaRepozytorium,
                    fakturykosztoweRepozytoirum FakturyKosztoweRepozytorium, numerRepozytorium NumerRepozytorium,stawkavatRepozytorium StawkavatRepozytorium,
                    fakutraRepozytorium FakutraRepozytorium)
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
        this.NumerRepozytorium=NumerRepozytorium;
        this.StawkavatRepozytorium=StawkavatRepozytorium;
        this.FakutraRepozytorium=FakutraRepozytorium;
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
        bGenerujZlecenie=metody.StworzPrzyciskzObrazemzTekstemObok(bGenerujZlecenie,"Zapisz zlecenie pdf",Usun1,100,20);
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
        bGenerujFakture=metody.StworzPrzyciskzObrazemzTekstemObok(bGenerujFakture,"Zapisz fakturę pdf",Usun1,100,20);
        bGenerujFakture.addActionListener(e -> {
            try {
                WygenerujFakture();
            }
            catch (IOException e12)
            {
                e12.printStackTrace();
            }
        });
        bGenerujFakture.setEnabled(false);

        bDodajFakture=metody.StworzPrzyciskzObrazemzTekstemObok(bDodajFakture,"Dodaj fakturę",Usun1,200,20);
        bDodajFakture.setEnabled(false);
        bDodajFakture.addActionListener(e -> {
            PrzyciskDodajFakture();
        });

        bDodajFaktureKosztowa=metody.StworzPrzyciskzObrazemzTekstemObok(bDodajFaktureKosztowa,"Dodaj fakturę kosztową",Usun1,200,20);
        bDodajFaktureKosztowa.setEnabled(false);
        bDodajFaktureKosztowa.addActionListener(e -> {
            PrzyciskDodajFaktureKosztowa();
        });
        panelZachodni.add(bDodaj);
        panelZachodni.add(bUsun);
        panelZachodni.add(bGenerujZlecenie);
        panelZachodni.add(bGenerujFakture);
        panelZachodni.add(bDodajFakture);
        panelZachodni.add(bDodajFaktureKosztowa);
        add(panelZachodni);
        setVisible(true);
        WyswietlTabele();
        add(panelZachodni,BorderLayout.WEST);


    }
    public void WyswietlTabele()
    {
        List<Encje.zlecenie> zlecenieList=ZlecenieRepozytorium.findCompanyByFirmaId(IdFirmy);
        Object[][] dane=new Object[zlecenieList.size()][18];
        for(int i=0;i<zlecenieList.size();i++)
        {
            zlecenie zlecenie1=zlecenieList.get(i);
            dane[i][0]=zlecenie1.getId();
            dane[i][1]=zlecenie1.getWykonawca();
            dane[i][2]=zlecenie1.getPrzewoznik_nazwa_skrocona();
            dane[i][3]=zlecenie1.getZlecemiodawca_nazwa_skrocona();
            dane[i][4]=zlecenie1.getNumerfakturykosztowej();
            dane[i][5]=zlecenie1.getNumerfaktury();
            dane[i][6]=zlecenie1.getRodzaj_towaru();
            dane[i][7]=zlecenie1.getIlosc();
            dane[i][8]=zlecenie1.getWaga();
            dane[i][9]=zlecenie1.getKraj_zaladunku();
            dane[i][10]=zlecenie1.getData_zaladunku();
            dane[i][11]=zlecenie1.getMiejscowosc_zaladunku();
            dane[i][12]=zlecenie1.getKraj_rozladunku();
            dane[i][13]=zlecenie1.getData_rozladunku();
            dane[i][14]=zlecenie1.getMiejscowosc_rozladunku();
            dane[i][15]=zlecenie1.getKwota_frachtu();
            dane[i][16]=zlecenie1.getKwota_zlecenia();
            dane[i][17]=zlecenie1.getWaluta();
        }
        String[] NazwyKolumn={
                "ID","Wykonawca","Zleceniobiorca","Zleceniodawca","Numer faktury kosztowej","Numer faktury","Rodzaj towaru","Ilosc","Waga","KrajZaladunku","Data załadunku","Miejscowość załadunku",
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
                        String numerFaktury=ZlecenieRepozytorium.findNumerFakturyId(Id);
                        String numerFakturyKosztowej=ZlecenieRepozytorium.findNumerFakturKosztowejyId(Id);
                        if(wykonawca.equals("Wlasny transport"))
                        {
                            bDodajFaktureKosztowa.setEnabled(false);
                            if(numerFaktury!=null) {
                                bDodajFakture.setEnabled(false);
                                bGenerujFakture.setEnabled(true);
                            }
                            else
                            {
                                bDodajFakture.setEnabled(true);
                                bGenerujFakture.setEnabled(false);
                            }
                        }
                        else
                        {

                            if(numerFaktury!=null) {
                                bDodajFakture.setEnabled(false);
                                bGenerujFakture.setEnabled(true);
                            }
                            else
                            {
                                bDodajFakture.setEnabled(true);
                                bGenerujFakture.setEnabled(false);
                            }
                            if(numerFakturyKosztowej!=null)
                            {
                                bDodajFaktureKosztowa.setEnabled(false);
                            }
                            else
                            {
                                bDodajFaktureKosztowa.setEnabled(true);
                            }
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
    public void WygenerujFakture() throws IOException
    {
        int WybranyWiersz=table.getSelectedRow();
        if(WybranyWiersz!=-1){
            int Id=(Integer) table.getValueAt(WybranyWiersz,0);
            String ZleceniodawcaNazwaKrotka=ZlecenieRepozytorium.findZlecemiodawcaNazwaKrotkaById(Id);
            int IdZleceniodawcy= ZleceniodawcaRepozytorium.findNazwaPelnaFirmyById(ZleceniodawcaNazwaKrotka);
            String numerFaktury=ZlecenieRepozytorium.findNumerFakturyId(Id);
            String nazwaPelnaZleceniodawcy=ZleceniodawcaRepozytorium.findNazwaPelnaById(IdZleceniodawcy);
            String UlicaNabywca=ZleceniodawcaRepozytorium.findulicaById(IdZleceniodawcy);
            String NumerDomuNabywcy=ZleceniodawcaRepozytorium.findNumer_DomuById(IdZleceniodawcy);
            String NumerMieszkaniaNabywcy=ZleceniodawcaRepozytorium.findNumerMieszkaniaById(IdZleceniodawcy);
            String KodPocztowyNabywcy=ZleceniodawcaRepozytorium.findKod_PocztowyById(IdZleceniodawcy);
            String MiastoNabywcy=ZleceniodawcaRepozytorium.findMiastoId(IdZleceniodawcy);
            String NIPNabywcy=ZleceniodawcaRepozytorium.findNIPById(IdZleceniodawcy);
            String NazwaPelnaSprzedawcy=FirmaRepozytorium.findnazwaFirmyById(IdFirmy);
            String RegonNabywcy=ZleceniodawcaRepozytorium.findRegonId(IdZleceniodawcy);
            String UlicaSprzedawcy=FirmaRepozytorium.findulicaFirmyById(IdFirmy);
            String NumerDomuSprzedawcy=FirmaRepozytorium.findnumerdomuFirmyById(IdFirmy);
            String NumerMieszkaniaSprzedawcy=FirmaRepozytorium.findnumermieszkaniaFirmyById(IdFirmy);
            String KodPocztowySprzedawcy=FirmaRepozytorium.findkodpocztowyFirmyById(IdFirmy);
            String MiastoSprzedawcy=FirmaRepozytorium.findmiastoFirmyById(IdFirmy);
            String NIPSprzedawcy=FirmaRepozytorium.findnipFirmyById(IdFirmy);
            String RegonSprzedawcy=FirmaRepozytorium.findregonFirmyById(IdFirmy);
            int NumerFakturyId=FakutraRepozytorium.findcostamById(Id);
            String DataWystawieniaFaktury=FakutraRepozytorium.findDataWystawieniaById(NumerFakturyId);
            String DataSprzedazyFaktury=FakutraRepozytorium.findDataSprzedazyById(NumerFakturyId);
            String KwotaNettoFaktury=FakutraRepozytorium.findKwotaNettoById(NumerFakturyId);
            String KwotaBruttoFaktury=FakutraRepozytorium.findKwotaBruttoById(NumerFakturyId);
            String Waluta=FakutraRepozytorium.findWalutaById(NumerFakturyId);
            String KontoPLN=FakutraRepozytorium.findKontoPLNById(NumerFakturyId);
            String KontroEUR=FakutraRepozytorium.findKontoEURById(NumerFakturyId);
            String Iban=FakutraRepozytorium.findIBANById(NumerFakturyId);
            String Swift=FakutraRepozytorium.findSwiftById(NumerFakturyId);

            if(RegonNabywcy.equals("0.0"))
            {
                RegonNabywcy="";
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
            contentStream.newLineAtOffset(240,y);
            contentStream.showText("Faktura nr "+numerFaktury);
            contentStream.newLineAtOffset(250,przesuniecieY);
            contentStream.showText("Oryginal/Kopia");
            contentStream.newLineAtOffset(-450,przesuniecieY*3);
            contentStream.showText("Nabywca: ");
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(nazwaPelnaZleceniodawcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(UlicaNabywca+" "+NumerDomuNabywcy+" "+NumerMieszkaniaNabywcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(KodPocztowyNabywcy+" "+MiastoNabywcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("NIP: "+NIPNabywcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Regon "+RegonNabywcy);
            contentStream.newLineAtOffset(350,100);
            contentStream.showText("Sprzedawca: ");
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(NazwaPelnaSprzedawcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(UlicaSprzedawcy+" "+NumerDomuSprzedawcy+" "+NumerMieszkaniaSprzedawcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText(KodPocztowySprzedawcy+" "+MiastoSprzedawcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("NIP: "+NIPSprzedawcy);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Regon: "+RegonSprzedawcy);
            contentStream.newLineAtOffset(-350,przesuniecieY*4);
           contentStream.showText("Data wystawienia "+DataWystawieniaFaktury);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Data sprzedazy "+DataSprzedazyFaktury);
            contentStream.newLineAtOffset(0,przesuniecieY*5);
            contentStream.showText("Kwota netto :");
            contentStream.newLineAtOffset(180,0);
            contentStream.showText("Kwota brutto :");
            contentStream.newLineAtOffset(180,0);
            contentStream.showText("Waluta :");
            contentStream.newLineAtOffset(-360,przesuniecieY);
            contentStream.showText(KwotaNettoFaktury);
            contentStream.newLineAtOffset(180,0);
            contentStream.showText(KwotaBruttoFaktury);
            contentStream.newLineAtOffset(180,0);
            contentStream.showText(Waluta);
            contentStream.newLineAtOffset(-360,przesuniecieY*3);
            contentStream.showText("Konto PLN: "+KontoPLN);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Konto EUR: "+KontroEUR);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Iban: "+Iban);
            contentStream.newLineAtOffset(0,przesuniecieY);
            contentStream.showText("Swift: "+Swift);


            contentStream.newLineAtOffset(0,przesuniecieY*5);
            contentStream.showText("Razem do zaplaty: "+KwotaBruttoFaktury);












            contentStream.endText();
            contentStream.close();

            document.save("Numer faktury "+Id+".pdf");
            document.close();
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
    public void PrzyciskDodajFakture()
    {
        JDialog jNoweOknoDialogowe=new JDialog();
        jNoweOknoDialogowe.setTitle("Dodaj fakturę");
        jNoweOknoDialogowe.setSize(800,600);
        jNoweOknoDialogowe.setLayout(null);
        jNoweOknoDialogowe.setVisible(true);
        jNoweOknoDialogowe.setLocationRelativeTo(null);

        int WybranyWiersz=table.getSelectedRow();
        IdZlecenia = (Integer) table.getValueAt(WybranyWiersz, 0);
        System.out.println("ID firmy to "+IdFirmy);
        int maxNumerWBazieNumer=ZnajdzNajwiekszyNumer();//pobieram z bazy najwiekszy numer dla danego id firmy
        LocalDate aktualnyczas=LocalDate.now();// biore czas
        int aktualnyrok=aktualnyczas.getYear();
        String sAktualnyrok=String.valueOf(aktualnyrok);
        String numer=String.valueOf(maxNumerWBazieNumer);
        sNumerFaktury=numer+"/"+sAktualnyrok;//numer faktury skonwertowany na to co potrzebujemy
        String ZleceniodawcaNazwaSkrocona=ZlecenieRepozytorium.findZleceniodawcaById(IdZlecenia);
        System.out.println("Nazwa krotka zleceniodawcy to "+ZleceniodawcaNazwaSkrocona);
        int IDZleceniodawcy=ZleceniodawcaRepozytorium.findNazwaPelnaFirmyById(ZleceniodawcaNazwaSkrocona);
        sNazwafirmy=ZleceniodawcaRepozytorium.findNazwaPelnaById(IDZleceniodawcy);
        sUlica=ZleceniodawcaRepozytorium.findulicaById(IDZleceniodawcy);
        sDom=ZleceniodawcaRepozytorium.findNumer_DomuById(IDZleceniodawcy);
        sMieszkanie=ZleceniodawcaRepozytorium.findNumerMieszkaniaById(IDZleceniodawcy);
        sKodPocztowy=ZleceniodawcaRepozytorium.findKod_PocztowyById(IDZleceniodawcy);
        sMiastp=ZleceniodawcaRepozytorium.findMiastoId(IDZleceniodawcy);
        sNip=ZleceniodawcaRepozytorium.findNIPById(IDZleceniodawcy);
        sRegon=ZleceniodawcaRepozytorium.findRegonId(IDZleceniodawcy);
        sKwota=ZlecenieRepozytorium.findKwota_FrachtuId(IdZlecenia);
        WalutaZLecenia=ZlecenieRepozytorium.findWalutaId(IdZlecenia);
        nazwaFirmyProgramu=ZlecenieRepozytorium.findNazwaFirmyProgramuId(IdZlecenia);
        String kraj=ZleceniodawcaRepozytorium.findKRajById(IDZleceniodawcy);
        idFirmyProgramu=ZlecenieRepozytorium.findIDFIRMYId(IdZlecenia);
        if(sRegon.equals("0.0"))
        {
            sRegon="";
        }


        JLabel lFirma,lNazwa,lUlica,lKodPocztowy,lMiasto,lDane,lNip,lRegon,lDaneDoFaktury,lKontoPLN,lKontoEUR,lIban,lSwift,lSlash,
                lKwota,lWaluta,lNumerFaktury,lKwotaBrutto,lKwotaZlecenia,lStawka,lStawkaVat,lWaluta1,lWaluta2,lDataWystawienia,lData,
        lDataSprzedazy,lKraj;

        JTextField tFirma,tUlica,tNumerDomu,tNumerMieszkania,tKodPocztowy,tMiasto,tNip,tRegon,tKwotaNetto,tNumerFaktury,tKwotaBrutto,tKwotaZlecenia,
        tKraj;

        JComboBox cStawkaVat;

        JDateChooser dDataWystawienia,dDataSprzedazy;

        JButton bZapisz;

        LimitowanyText tKontoPLN,tKontoEUR,tIban,tSwift;

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
        lKwotaBrutto=new JLabel();
        lKwotaZlecenia=new JLabel();
        lStawka=new JLabel();
        lStawkaVat=new JLabel();
        lWaluta1=new JLabel();
        lWaluta2=new JLabel();
        lDataWystawienia=new JLabel();
        lData=new JLabel();
        lDataSprzedazy=new JLabel();
        lKraj=new JLabel();


        tFirma=new JTextField();
        tUlica=new JTextField();
        tNumerDomu=new JTextField();
        tNumerMieszkania=new JTextField();
        tKodPocztowy=new JTextField();
        tMiasto=new JTextField();
        tNip=new JTextField();
        tRegon=new JTextField();
        tKwotaNetto=new JTextField();
        tNumerFaktury=new JTextField();
        tKwotaBrutto=new JTextField();
        tKwotaZlecenia=new JTextField();
        tKraj=new JTextField();

        dDataWystawienia=new JDateChooser();
        dDataSprzedazy=new JDateChooser();
        cStawkaVat=new JComboBox();

        bZapisz=new JButton();

        tKontoPLN=new LimitowanyText(29,true);
        tKontoEUR=new LimitowanyText(29,true);
        tIban=new LimitowanyText(20,false);
        tSwift=new LimitowanyText(20,false);

        metody.StworzNapisJDialog(jNoweOknoDialogowe,lFirma,"Firma",10,10,100,10);
        lFirma.setFont(lFirma.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lNazwa,"Nazwa",10,40,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lUlica,"Ulica",10,70,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKodPocztowy,"Kod pocztowy",10,100,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lMiasto,"Miasto",10,130,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKraj,"Kraj",10,160,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lStawka,"Stawka",10,190,100,20);
        lStawka.setFont(lStawka.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKwotaZlecenia,"Kwota Zlecenia",10,220,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lWaluta,"",230,220,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lStawkaVat,"Stawka Vat",10,250,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lData,"Wybierz date faktury",10,300,150,20);
        lData.setFont(lData.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lDataWystawienia,"Data wystawienia",10,330,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lDataSprzedazy,"Data sprzedaży",10,360,150,20);

        metody.StworzNapisJDialog(jNoweOknoDialogowe,lDane,"Dane",400,10,100,20);
        lDane.setFont(lFirma.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lNip,"NIP",400,40,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lRegon,"Regon",400,70,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lDaneDoFaktury,"Dane  faktury",400,100,150,20);
        lDaneDoFaktury.setFont(lFirma.getFont().deriveFont(Font.BOLD));
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKwota,"Kwota netto",400,130,130,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lWaluta1,"",620,130,130,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKwotaBrutto,"Kwota brutto",400,160,130,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lWaluta2,"",620,160,130,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKontoPLN,"Konto PLN",400,190,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lKontoEUR,"Konto EUR",400,220,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lIban,"IBAN",400,250,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lSwift,"Swift",400,280,100,20);
        metody.StworzNapisJDialog(jNoweOknoDialogowe,lSlash,"/",325,70,20,20);

        metody.StworzNapisJDialog(jNoweOknoDialogowe,lNumerFaktury,"Numer faktury",400,310,100,20);


        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tFirma,120,40,250,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tUlica,120,70,160,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tNumerDomu,290,70,30,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tNumerMieszkania,335,70,30,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tKodPocztowy,120,100,100,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tMiasto,120,130,160,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tKraj,120,160,160,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tKwotaZlecenia,120,220,100,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tNip,510,40,150,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tRegon,510,70,150,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tKwotaNetto,510,130,100,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tKwotaBrutto,510,160,100,20);
        metody.StworzJTextFieldJDialog(jNoweOknoDialogowe,tNumerFaktury,510,310,150,20);

        metody.StworzLimitowanyTextjDialog(tKontoPLN,510,190,160,20,jNoweOknoDialogowe);
        metody.StworzLimitowanyTextjDialog(tKontoEUR,510,220,160,20,jNoweOknoDialogowe);
        metody.StworzLimitowanyTextjDialog(tIban,510,250,160,20,jNoweOknoDialogowe);
        metody.StworzLimitowanyTextjDialog(tSwift,510,280,160,20,jNoweOknoDialogowe);


        metody.StworzJButtonJDialog(jNoweOknoDialogowe,bZapisz,"Zapisz",220,400,150,20);

        dDataWystawienia.setBounds(130,330,100,20);
        dDataSprzedazy.setBounds(130,360,100,20);
        cStawkaVat.setBounds(120,250,100,20);
        DodajDaneStawkaVatJComboBoxa(cStawkaVat);

        tFirma.setEditable(false);
        tUlica.setEditable(false);
        tNumerDomu.setEditable(false);
        tNumerMieszkania.setEditable(false);
        tKodPocztowy.setEditable(false);
        tMiasto.setEditable(false);
        tNip.setEditable(false);
        tRegon.setEditable(false);
        tKwotaNetto.setEditable(false);
        tNumerFaktury.setEditable(false);
        tKwotaBrutto.setEditable(false);
        tKwotaZlecenia.setEditable(false);
        tKraj.setEditable(false);


        tFirma.setText(sNazwafirmy);
        tUlica.setText(sUlica);
        tNumerDomu.setText(sDom);
        tNumerMieszkania.setText(sMieszkanie);
        tKodPocztowy.setText(sKodPocztowy);
        tMiasto.setText(sMiastp);
        tNip.setText(sNip);
        tKraj.setText(kraj);
        tRegon.setText(sRegon);
        tNumerFaktury.setText(sNumerFaktury);
        tKwotaZlecenia.setText(sKwota);
        lWaluta.setText(WalutaZLecenia);
        lWaluta1.setText(WalutaZLecenia);
        lWaluta2.setText(WalutaZLecenia);

        cStawkaVat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sKwotaBrutto,sKwotaNetto;
                Wybranastawka=(String) cStawkaVat.getSelectedItem();
                kwota=Double.parseDouble(sKwota);
                if(!Wybranastawka.equals("NP"))
                {
                    double stawka1=Double.parseDouble(Wybranastawka);
                    if(stawka1==0.0)
                    {
                        KwotaBrutto=kwota;
                        KwotaNetto=kwota;
                         sKwotaBrutto=String.valueOf(KwotaBrutto);
                         sKwotaNetto=String.valueOf(KwotaNetto);
                        tKwotaBrutto.setText(sKwotaBrutto);
                        tKwotaNetto.setText(sKwotaNetto);
                        System.out.println("Kwota w double brutto wynosi "+KwotaBrutto);

                    }
                    else
                    {
                        stawka1=1.23;
                        KwotaBrutto=kwota*stawka1;
                        KwotaNetto=kwota;
                        sKwotaBrutto=String.valueOf(KwotaBrutto);
                        tKwotaBrutto.setText(sKwotaBrutto);
                        sKwotaNetto=String.valueOf(KwotaNetto);
                        tKwotaNetto.setText(sKwotaNetto);

                    }
                }
                else
                {
                    KwotaBrutto=kwota;
                    KwotaNetto=kwota;
                    sKwotaBrutto=String.valueOf(KwotaBrutto);
                    tKwotaBrutto.setText(sKwotaBrutto);
                    sKwotaNetto=String.valueOf(KwotaNetto);
                    tKwotaNetto.setText(sKwotaNetto);
                }



            }// automatycznie wybieraie kwoty netto i brutto z comboboxa
        });

        bZapisz.addActionListener(e -> {

            PobierzzFaktury(kraj,sKontoPLN,tKontoPLN,sKontoEuro,tKontoEUR,sIban,tIban,sSwift,tSwift,Wybranastawka,sDataWystawienia,
                    sDataSprzedazy,dDataWystawienia,dDataSprzedazy);

        });
        jNoweOknoDialogowe.add(cStawkaVat);
        jNoweOknoDialogowe.add(dDataWystawienia);
        jNoweOknoDialogowe.add(dDataSprzedazy);
    }
    public void PobierzzFaktury(String Kraj,String KontoPLN,JTextField tKontoPLN1,String KontoEUR, JTextField tKontroEUR1,
                                String IBAN, JTextField tIBAN1,String Swift,JTextField tSwift1,String wybrany,String datawystawienia,
                                String datasprzedazy,JDateChooser datawystawienia1,JDateChooser datasprzedazy1)
    {
        KontoPLN=tKontoPLN1.getText().trim();
        KontoEUR=tKontroEUR1.getText().trim();
        IBAN=tIBAN1.getText().trim();
        Swift=tSwift1.getText().trim();



        /* if(!Kraj.isEmpty())
        {
            if(Kraj.equals("Polska"))
            {
                if(KontoPLN.isEmpty())
                {
                    metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru konta PLN");
                }
            }
            else
            {
                if(KontoEUR.isEmpty())
                {
                    metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru konta EUR");
                }
                else if(IBAN.isEmpty())
                {
                    metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru IBAN");
                }
                else if(Swift.isEmpty())
                {
                    metody.WyswietlKomunikatoBledzie("Nie wpisałeś numeru Swift");
                }
            }
            if(wybrany==null)
            {
                metody.WyswietlKomunikatoBledzie("Nie wybrałeś stawki VAT !");
            }
            Date data=datawystawienia1.getDate();
            if(data==null)
            {
                metody.WyswietlKomunikatoBledzie("Nie wybrałeś daty wystawienia faktury !");
            }
            Date data1=datasprzedazy1.getDate();
            if(data1==null)
            {
                metody.WyswietlKomunikatoBledzie("Nie wybrałeś daty sprzedazy faktury !");
            }

        }*/


             Date wybrana_data=datawystawienia1.getDate();
             Date wybrana_data1=datasprzedazy1.getDate();
             SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
             datawystawienia=dateFormat.format(wybrana_data);
             datasprzedazy=dateFormat.format(wybrana_data1);

             System.out.println("rozpoczynam zapis");
            Encje.faktura faktura=new faktura(IdZlecenia,sNazwafirmy,sUlica,sDom,sMieszkanie,sKodPocztowy,sMiastp,Kraj,kwota,Wybranastawka,datawystawienia,datasprzedazy,sNip,
                    sRegon,KwotaNetto,KwotaBrutto,KontoPLN,KontoEUR,WalutaZLecenia,IBAN,Swift,sNumerFaktury,nazwaFirmyProgramu,idFirmyProgramu);

            FakutraRepozytorium.save(faktura);

            Optional<zlecenie>optionalZlecenie=ZlecenieRepozytorium.findById(IdZlecenia);
            if(optionalZlecenie.isPresent())
            {
                zlecenie zleceniedo=optionalZlecenie.get();
                zleceniedo.setNumerfaktury(sNumerFaktury);
                ZlecenieRepozytorium.save(zleceniedo);

            }



    }
    public void PrzyciskDodajFaktureKosztowa()
    {
        JDialog jNoweOknoDialogowe=new JDialog();
        jNoweOknoDialogowe.setTitle("Dodaj fakturę kosztową");
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
    public int ZnajdzNajwiekszyNumer()
    {
        Integer MaxNumer=NumerRepozytorium.findMaxNumerByIdFirmy(IdFirmy);
        if(MaxNumer!=null)
        {
            return MaxNumer+1;
        }
        else
        {
            return 1;
        }

    }
public void DodajDaneStawkaVatJComboBoxa(JComboBox comboBox)
{
    List<Encje.stawkavat> stawkavats=StawkavatRepozytorium.findAll();
    comboBox.removeAllItems();
    for(Encje.stawkavat stawkavat:stawkavats)
    {
        comboBox.addItem(stawkavat.getStawka());
    }
}

}
