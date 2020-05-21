package hitne.repository;

import hitne.domain.Hitne;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Hitne entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HitneRepository extends JpaRepository<Hitne, Long>, JpaSpecificationExecutor<Hitne> {
}
