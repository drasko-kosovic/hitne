package hitne.service;

import hitne.domain.Ponudjaci;
import hitne.repository.PonudjaciRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Ponudjaci}.
 */
@Service
@Transactional
public class PonudjaciService {

    private final Logger log = LoggerFactory.getLogger(PonudjaciService.class);

    private final PonudjaciRepository ponudjaciRepository;

    public PonudjaciService(PonudjaciRepository ponudjaciRepository) {
        this.ponudjaciRepository = ponudjaciRepository;
    }

    /**
     * Save a ponudjaci.
     *
     * @param ponudjaci the entity to save.
     * @return the persisted entity.
     */
    public Ponudjaci save(Ponudjaci ponudjaci) {
        log.debug("Request to save Ponudjaci : {}", ponudjaci);
        return ponudjaciRepository.save(ponudjaci);
    }

    /**
     * Get all the ponudjacis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Ponudjaci> findAll(Pageable pageable) {
        log.debug("Request to get all Ponudjacis");
        return ponudjaciRepository.findAll(pageable);
    }

    /**
     * Get one ponudjaci by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Ponudjaci> findOne(Long id) {
        log.debug("Request to get Ponudjaci : {}", id);
        return ponudjaciRepository.findById(id);
    }

    /**
     * Delete the ponudjaci by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ponudjaci : {}", id);
        ponudjaciRepository.deleteById(id);
    }
}
