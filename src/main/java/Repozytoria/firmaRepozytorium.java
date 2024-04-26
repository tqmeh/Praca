package Repozytoria;

import Encje.uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import Encje.firma;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface firmaRepozytorium extends JpaRepository<Encje.firma,Integer>
{
    List<firma> findAll();
    void deleteById(Integer id);
    @Query("SELECT u.nazwa FROM firma u WHERE u.id =:zlecenieId")
    String findnazwaFirmyById(@Param("zlecenieId")Integer zlecenieId);
    @Query("SELECT u.ulica FROM firma u WHERE u.id =:zlecenieId")
    String findulicaFirmyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.numer_domu FROM firma u WHERE u.id =:zlecenieId")
    String findnumerdomuFirmyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.numer_mieszkania FROM firma u WHERE u.id =:zlecenieId")
    String findnumermieszkaniaFirmyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.kod_pocztowy FROM firma u WHERE u.id =:zlecenieId")
    String findkodpocztowyFirmyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.miasto FROM firma u WHERE u.id =:zlecenieId")
    String findmiastoFirmyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.nip FROM firma u WHERE u.id =:zlecenieId")
    String findnipFirmyById(@Param("zlecenieId")Integer zlecenieId);

    @Query("SELECT u.regon FROM firma u WHERE u.id =:zlecenieId")
    String findregonFirmyById(@Param("zlecenieId")Integer zlecenieId);
}
