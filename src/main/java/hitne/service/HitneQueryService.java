package hitne.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import hitne.domain.Hitne;
import hitne.domain.*; // for static metamodels
import hitne.repository.HitneRepository;
import hitne.service.dto.HitneCriteria;

/**
 * Service for executing complex queries for {@link Hitne} entities in the database.
 * The main input is a {@link HitneCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Hitne} or a {@link Page} of {@link Hitne} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HitneQueryService extends QueryService<Hitne> {

    private final Logger log = LoggerFactory.getLogger(HitneQueryService.class);

    private final HitneRepository hitneRepository;

    public HitneQueryService(HitneRepository hitneRepository) {
        this.hitneRepository = hitneRepository;
    }

    /**
     * Return a {@link List} of {@link Hitne} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Hitne> findByCriteria(HitneCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Hitne> specification = createSpecification(criteria);
        return hitneRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Hitne} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Hitne> findByCriteria(HitneCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Hitne> specification = createSpecification(criteria);
        return hitneRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HitneCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Hitne> specification = createSpecification(criteria);
        return hitneRepository.count(specification);
    }

    /**
     * Function to convert {@link HitneCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Hitne> createSpecification(HitneCriteria criteria) {
        Specification<Hitne> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Hitne_.id));
            }
            if (criteria.getBrojpokretanja() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBrojpokretanja(), Hitne_.brojpokretanja));
            }
            if (criteria.getDatumpokretanja() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDatumpokretanja(), Hitne_.datumpokretanja));
            }
            if (criteria.getVrijednost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVrijednost(), Hitne_.vrijednost));
            }
            if (criteria.getNaziv() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNaziv(), Hitne_.naziv));
            }
            if (criteria.getPonudjaciId() != null) {
                specification = specification.and(buildSpecification(criteria.getPonudjaciId(),
                    root -> root.join(Hitne_.ponudjaci, JoinType.LEFT).get(Ponudjaci_.id)));
            }
        }
        return specification;
    }
}
