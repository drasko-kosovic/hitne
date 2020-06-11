package hitne.repository;

import hitne.domain.Hitne;
import hitne.domain.Ponudjaci;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Ponudjaci entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PonudjaciRepository extends JpaRepository<Ponudjaci, Long>, JpaSpecificationExecutor<Ponudjaci> {

    @Query(value = "SELECT * FROM hitne ", nativeQuery = true)
    List<Hitne> findAllHitme();
}
