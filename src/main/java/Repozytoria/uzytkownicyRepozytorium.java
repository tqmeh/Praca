package Repozytoria;

import Encje.uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface uzytkownicyRepozytorium extends JpaRepository<Encje.uzytkownicy,Integer>
{
    Optional<uzytkownicy> findByLogin(String login);
    List<uzytkownicy> findAll();
    void deleteById(Integer id);

}