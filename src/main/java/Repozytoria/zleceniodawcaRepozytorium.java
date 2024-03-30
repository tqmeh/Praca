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
}
