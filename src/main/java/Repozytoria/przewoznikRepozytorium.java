package Repozytoria;

import Encje.przewoznik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface przewoznikRepozytorium extends JpaRepository<Encje.przewoznik,Integer>
{
    void deleteById(Integer id);
    @Query("SELECT u FROM przewoznik u WHERE u.id_firmy = :firmaId")
    List<przewoznik> findCompanyByFirmaId(@Param("firmaId")Integer firmaId);
    @Query("SELECT u.id FROM przewoznik u WHERE u.nazwa_skrocona =:Nazwa")
    int findNazwaPelnaFirmyById(@Param("Nazwa")String Nazwa);

    @Query("SELECT u.nazwa_pelna FROM przewoznik u WHERE u.id =:zlecenieId")
    String findNazwaPelnaById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.ulica FROM przewoznik u WHERE u.id =:zlecenieId")
    String findUlicaById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.numer_domu FROM przewoznik u WHERE u.id =:zlecenieId")
    String findNumer_DomuById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.numer_mieszkania FROM przewoznik u WHERE u.id =:zlecenieId")
    String findNumer_MieszkaniaById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.kod_pocztowy FROM przewoznik u WHERE u.id =:zlecenieId")
    String findKod_PocztowyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.miasto FROM przewoznik u WHERE u.id =:zlecenieId")
    String findMiastoById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.nip FROM przewoznik u WHERE u.id =:zlecenieId")
    String findNipById(@Param("zlecenieId")Integer zlecenieId);
}
