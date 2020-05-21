package hitne.repository;


import hitne.domain.viewHitnePonudjaci;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface viewHitnePonudjaciRepository extends CrudRepository<viewHitnePonudjaci,Integer> {
    List<viewHitnePonudjaci> findByBrojpokretanja(@Param("brojpokretanja") Long brojpokretanja);
}
