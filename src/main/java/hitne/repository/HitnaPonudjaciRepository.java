package hitne.repository;

import hitne.domain.Hitne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HitnaPonudjaciRepository extends JpaRepository<Hitne, Long>, JpaSpecificationExecutor<Hitne> {

    @Query(value="SELECT \n" +
        "  hitne.id, \n" +
        "  hitne.brojpokretanja, \n" +
        "  hitne.ponudjaci_id, \n" +

        "  hitne.datumpokretanja, \n" +
        "  add_business_day(hitne.datumpokretanja, 2) AS datumodluke, \n" +
        "  hitne.vrijednost, \n" +
        "  hitne.naziv, \n" +
        "  ponudjaci.kontakt, \n" +
        "  ponudjaci.adresa, \n" +
        "  ponudjaci.email, \n" +
        "  ponudjaci.fax, \n" +
        "  ponudjaci.grad, \n" +
        "  ponudjaci.pib, \n" +
        "  ponudjaci.ponudjac, \n" +
        "  ponudjaci.postanskibroj, \n" +
        "  ponudjaci.telefon, \n" +
        "  ponudjaci.web \n" +
        "FROM (hitne \n" +
        "  JOIN ponudjaci ON \n" +
        "    (\n" +
        "      (hitne.ponudjaci_id = ponudjaci.id)\n" +
        "    ));", nativeQuery = true)
    List<Hitne> findAll();


}
