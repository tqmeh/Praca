package Repozytoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface zleceniodawcaRepozytorium extends JpaRepository<Encje.zleceniodawca,Integer>
{
    void deleteById(Integer id);
@Query("SELECT u FROM zleceniodawca u WHERE u.id_firmy = :firmaId")
    List<Encje.zleceniodawca> findCompanyByFirmaId(@Param("firmaId")Integer firmaId);

    @Query("SELECT u.id FROM zleceniodawca u WHERE u.nazwa_skrocona =:Nazwa")
    int findNazwaPelnaFirmyById(@Param("Nazwa")String Nazwa);

    @Query("SELECT u.nazwa_pelna FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findNazwaPelnaById(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.ulica FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findulicaById(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.numer_domu FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findNumer_DomuById(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.numer_mieszkania FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findNumerMieszkaniaById(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.kod_pocztowy FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findKod_PocztowyById(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.miasto FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findMiastoId(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.nip FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findNIPById(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.regon FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findRegonId(@Param("zleceniodawcaID")Integer zleceniodawcaID);

    @Query("SELECT u.kraj FROM zleceniodawca u WHERE u.id =:zleceniodawcaID")
    String findKRajById(@Param("zleceniodawcaID")Integer zleceniodawcaID);


}
