package Repozytoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface numerRepozytorium extends JpaRepository<Encje.numer,Integer>
{
    @Query("SELECT MAX(n.numer) FROM numer n WHERE n.id_firmy = :idFirmy")
    Integer findMaxNumerByIdFirmy(@Param("idFirmy") Integer idFirmy);
}
