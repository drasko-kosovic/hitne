package hitne.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface viewHitnePonudjaciRepository extends CrudRepository<viewHitnePonudjaci1,Integer> {
    List<viewHitnePonudjaci1> findByBrojpokretanja(@Param("brojpokretanja") Long brojpokretanja);
}
