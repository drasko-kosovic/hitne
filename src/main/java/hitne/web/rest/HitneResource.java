package hitne.web.rest;

import hitne.domain.Hitne;
import hitne.service.HitneService;
import hitne.web.rest.errors.BadRequestAlertException;
import hitne.service.dto.HitneCriteria;
import hitne.service.HitneQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link hitne.domain.Hitne}.
 */
@RestController
@RequestMapping("/api")
public class HitneResource {

    private final Logger log = LoggerFactory.getLogger(HitneResource.class);

    private static final String ENTITY_NAME = "hitne";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HitneService hitneService;

    private final HitneQueryService hitneQueryService;

    public HitneResource(HitneService hitneService, HitneQueryService hitneQueryService) {
        this.hitneService = hitneService;
        this.hitneQueryService = hitneQueryService;
    }

    /**
     * {@code POST  /hitnes} : Create a new hitne.
     *
     * @param hitne the hitne to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hitne, or with status {@code 400 (Bad Request)} if the hitne has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/hitnes")
    public ResponseEntity<Hitne> createHitne(@Valid @RequestBody Hitne hitne) throws URISyntaxException {
        log.debug("REST request to save Hitne : {}", hitne);
        if (hitne.getId() != null) {
            throw new BadRequestAlertException("A new hitne cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Hitne result = hitneService.save(hitne);
        return ResponseEntity.created(new URI("/api/hitnes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /hitnes} : Updates an existing hitne.
     *
     * @param hitne the hitne to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hitne,
     * or with status {@code 400 (Bad Request)} if the hitne is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hitne couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/hitnes")
    public ResponseEntity<Hitne> updateHitne(@Valid @RequestBody Hitne hitne) throws URISyntaxException {
        log.debug("REST request to update Hitne : {}", hitne);
        if (hitne.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Hitne result = hitneService.save(hitne);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, hitne.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /hitnes} : get all the hitnes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hitnes in body.
     */
    @GetMapping("/hitnes")
    public ResponseEntity<List<Hitne>> getAllHitnes(HitneCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Hitnes by criteria: {}", criteria);
        Page<Hitne> page = hitneQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hitnes/count} : count all the hitnes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/hitnes/count")
    public ResponseEntity<Long> countHitnes(HitneCriteria criteria) {
        log.debug("REST request to count Hitnes by criteria: {}", criteria);
        return ResponseEntity.ok().body(hitneQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /hitnes/:id} : get the "id" hitne.
     *
     * @param id the id of the hitne to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hitne, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/hitnes/{id}")
    public ResponseEntity<Hitne> getHitne(@PathVariable Long id) {
        log.debug("REST request to get Hitne : {}", id);
        Optional<Hitne> hitne = hitneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(hitne);
    }

    /**
     * {@code DELETE  /hitnes/:id} : delete the "id" hitne.
     *
     * @param id the id of the hitne to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/hitnes/{id}")
    public ResponseEntity<Void> deleteHitne(@PathVariable Long id) {
        log.debug("REST request to delete Hitne : {}", id);
        hitneService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
