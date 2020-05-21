package hitne.repository;

import hitne.domain.Ponudjaci;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Ponudjaci entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PonudjaciRepository extends JpaRepository<Ponudjaci, Long>, JpaSpecificationExecutor<Ponudjaci> {
}
