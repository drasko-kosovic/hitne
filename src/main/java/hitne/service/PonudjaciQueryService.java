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

import hitne.domain.Ponudjaci;
import hitne.domain.*; // for static metamodels
import hitne.repository.PonudjaciRepository;
import hitne.service.dto.PonudjaciCriteria;

/**
 * Service for executing complex queries for {@link Ponudjaci} entities in the database.
 * The main input is a {@link PonudjaciCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Ponudjaci} or a {@link Page} of {@link Ponudjaci} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PonudjaciQueryService extends QueryService<Ponudjaci> {

    private final Logger log = LoggerFactory.getLogger(PonudjaciQueryService.class);

    private final PonudjaciRepository ponudjaciRepository;

    public PonudjaciQueryService(PonudjaciRepository ponudjaciRepository) {
        this.ponudjaciRepository = ponudjaciRepository;
    }

    /**
     * Return a {@link List} of {@link Ponudjaci} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Ponudjaci> findByCriteria(PonudjaciCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Ponudjaci> specification = createSpecification(criteria);
        return ponudjaciRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Ponudjaci} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Ponudjaci> findByCriteria(PonudjaciCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Ponudjaci> specification = createSpecification(criteria);
        return ponudjaciRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PonudjaciCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Ponudjaci> specification = createSpecification(criteria);
        return ponudjaciRepository.count(specification);
    }

    /**
     * Function to convert {@link PonudjaciCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Ponudjaci> createSpecification(PonudjaciCriteria criteria) {
        Specification<Ponudjaci> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Ponudjaci_.id));
            }
            if (criteria.getPonudjac() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPonudjac(), Ponudjaci_.ponudjac));
            }
            if (criteria.getKontakt() != null) {
                specification = specification.and(buildStringSpecification(criteria.getKontakt(), Ponudjaci_.kontakt));
            }
            if (criteria.getAdresa() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAdresa(), Ponudjaci_.adresa));
            }
            if (criteria.getGrad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGrad(), Ponudjaci_.grad));
            }
            if (criteria.getTelefon() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefon(), Ponudjaci_.telefon));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Ponudjaci_.email));
            }
            if (criteria.getPostanskibroj() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPostanskibroj(), Ponudjaci_.postanskibroj));
            }
            if (criteria.getPib() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPib(), Ponudjaci_.pib));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), Ponudjaci_.fax));
            }
            if (criteria.getWeb() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWeb(), Ponudjaci_.web));
            }
        }
        return specification;
    }
}
