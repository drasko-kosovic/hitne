package hitne.service;

import hitne.domain.Hitne;
import hitne.repository.HitneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Hitne}.
 */
@Service
@Transactional
public class HitneService {

    private final Logger log = LoggerFactory.getLogger(HitneService.class);

    private final HitneRepository hitneRepository;

    public HitneService(HitneRepository hitneRepository) {
        this.hitneRepository = hitneRepository;
    }

    /**
     * Save a hitne.
     *
     * @param hitne the entity to save.
     * @return the persisted entity.
     */
    public Hitne save(Hitne hitne) {
        log.debug("Request to save Hitne : {}", hitne);
        return hitneRepository.save(hitne);
    }

    /**
     * Get all the hitnes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Hitne> findAll(Pageable pageable) {
        log.debug("Request to get all Hitnes");
        return hitneRepository.findAll(pageable);
    }

    /**
     * Get one hitne by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Hitne> findOne(Long id) {
        log.debug("Request to get Hitne : {}", id);
        return hitneRepository.findById(id);
    }

    /**
     * Delete the hitne by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Hitne : {}", id);
        hitneRepository.deleteById(id);
    }
}
