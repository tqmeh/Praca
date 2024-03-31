package Repozytoria;

import Encje.zlecenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface zlecenieRepozytorium extends JpaRepository<Encje.zlecenie,Integer>
{
    void deleteById(Integer id);
@Query("SELECT u FROM zlecenie u WHERE u.id_firmy = :firmaId")
    List<zlecenie> findCompanyByFirmaId(@Param("firmaId")Integer firmaId);
}
