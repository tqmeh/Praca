package Repozytoria;

import Encje.uzytkownicy;
import org.springframework.data.jpa.repository.JpaRepository;
import Encje.firma;

import java.util.List;
import java.util.Optional;

public interface firmaRepozytorium extends JpaRepository<Encje.firma,Integer>
{
    List<firma> findAll();
    void deleteById(Integer id);
}
