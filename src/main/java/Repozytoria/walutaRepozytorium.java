package Repozytoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface walutaRepozytorium extends JpaRepository<Encje.waluta,Integer>
{
@Query("SELECT w.skrot FROM waluta w")
    List<String>findSkrot();
}
