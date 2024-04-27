package Repozytoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface fakutraRepozytorium extends JpaRepository<Encje.faktura,Integer> {

    @Query("SELECT u.id FROM faktura u WHERE u.id_zlecenia =:zlecenieId")
    int findcostamById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.data_wystawienia FROM faktura u WHERE u.id =:zlecenieId")
    String findDataWystawieniaById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.data_sprzedazy FROM faktura u WHERE u.id =:zlecenieId")
    String findDataSprzedazyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.kwota_netto FROM faktura u WHERE u.id =:zlecenieId")
    String findKwotaNettoById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.kwota_brutto FROM faktura u WHERE u.id =:zlecenieId")
    String findKwotaBruttoById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.waluta FROM faktura u WHERE u.id =:zlecenieId")
    String findWalutaById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.konto_pln FROM faktura u WHERE u.id =:zlecenieId")
    String findKontoPLNById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.konto_eur FROM faktura u WHERE u.id =:zlecenieId")
    String findKontoEURById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.iban FROM faktura u WHERE u.id =:zlecenieId")
    String findIBANById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.swift FROM faktura u WHERE u.id =:zlecenieId")
    String findSwiftById(@Param("zlecenieId")Integer zlecenieId);
}
