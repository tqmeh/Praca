package Repozytoria;

import Encje.uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface uzytkownicyRepozytorium extends JpaRepository<Encje.uzytkownicy,Integer>
{
    Optional<uzytkownicy> findByLogin(String login);
    List<uzytkownicy> findAll();
    void deleteById(Integer id);
    @Query("SELECT u.wlasciciel FROM uzytkownicy u WHERE u.id = :userId")//Wyszukiwanie po id z bazy danych
    Integer findWlascicielById(@Param("userId") Integer userId);

    @Query("SELECT u.id_firmy FROM uzytkownicy u WHERE u.id = :userId")
    Integer findFirmaIdByUserId(@Param("userId")Integer userId);

    @Query("SELECT u.nazwa_firmy FROM uzytkownicy u WHERE u.id= :userId")
    String findNazwaFirmyByUserId(@Param("userId")Integer userId);

    @Query("SELECT u FROM uzytkownicy u WHERE u.id_firmy = :firmaId")
    List<Encje.uzytkownicy> findUsersByFirmaIdLista(@Param("firmaId") Integer firmaId);


}