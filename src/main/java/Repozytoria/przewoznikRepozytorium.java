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
}
