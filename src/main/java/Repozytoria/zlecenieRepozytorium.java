package Repozytoria;

import Encje.zlecenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface zlecenieRepozytorium extends JpaRepository<Encje.zlecenie,Integer>
{
    void deleteById(Integer id);
@Query("SELECT u FROM zlecenie u WHERE u.id_firmy = :firmaId")
    List<zlecenie> findCompanyByFirmaId(@Param("firmaId")Integer firmaId);
@Query("SELECT u.zlecemiodawca_nazwa_skrocona FROM zlecenie u WHERE u.id =:zlecenieID")
    String findZlecemiodawcaNazwaKrotkaById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.przewoznik_nazwa_skrocona FROM zlecenie u WHERE u.id =:zlecenieID")
    String findZleceniobiorcaNazwaKrotkaById(@Param("zlecenieID")Integer zlecenieID);
    @Query("SELECT u.waga FROM zlecenie u WHERE u.id =:zlecenieID")
    String findWagaById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.rodzaj_towaru FROM zlecenie u WHERE u.id =:zlecenieID")
    String findRodzaj_TowaruById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.ilosc FROM zlecenie u WHERE u.id =:zlecenieID")
    String findIloscById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.kraj_zaladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findKraj_ZaladunkuById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.data_zaladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findData_ZaladunkuById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.miejscowosc_zaladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findMiejscowosc_ZaladunkuById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.kod_pocztowy_zaladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findKod_Pocztowy_ZaladunkuById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.ulica_zaladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findUlica_ZaladunkuById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.firma_zaladunek FROM zlecenie u WHERE u.id =:zlecenieID")
    String findFirma_ZaladunekById(@Param("zlecenieID")Integer zlecenieID);
    @Query("SELECT u.kraj_rozladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findKraj_RozladunkuById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.data_rozladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findData_RozladunkuId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.miejscowosc_rozladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findMiejscowosc_RozladunkuId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.kod_pocztowy_rozladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findKod_Pocztowy_RozladunkukById(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.ulica_rozladunku FROM zlecenie u WHERE u.id =:zlecenieID")
    String findUlica_RozladunkuId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.firma_rozladunek FROM zlecenie u WHERE u.id =:zlecenieID")
    String findFirma_RozladunekId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.kwota_frachtu FROM zlecenie u WHERE u.id =:zlecenieID")
    String findKwota_FrachtuId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.kwota_zlecenia FROM zlecenie u WHERE u.id =:zlecenieID")
    String findKwota_ZleceniaId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.waluta FROM zlecenie u WHERE u.id =:zlecenieID")
    String findWalutaId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.warunku_zlecenia FROM zlecenie u WHERE u.id =:zlecenieID")
    String findWarunkiId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.wykonawca FROM zlecenie u WHERE u.id =:zlecenieID")
    String findWykonawcaId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.nazwa_firmy FROM zlecenie u WHERE u.id =:zlecenieID")
    String findNazwaFirmyProgramuId(@Param("zlecenieID")Integer zlecenieID);

    @Query("SELECT u.id_firmy FROM zlecenie u WHERE u.id =:zlecenieID")
    int findIDFIRMYId(@Param("zlecenieID")Integer zlecenieID);






}
