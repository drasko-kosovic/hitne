package hitne.web.rest;

import hitne.HitneApp;
import hitne.domain.Hitne;
import hitne.domain.Ponudjaci;
import hitne.repository.HitneRepository;
import hitne.service.HitneService;
import hitne.service.dto.HitneCriteria;
import hitne.service.HitneQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link HitneResource} REST controller.
 */
@SpringBootTest(classes = HitneApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class HitneResourceIT {

    private static final Long DEFAULT_BROJPOKRETANJA = 1L;
    private static final Long UPDATED_BROJPOKRETANJA = 2L;
    private static final Long SMALLER_BROJPOKRETANJA = 1L - 1L;

    private static final LocalDate DEFAULT_DATUMPOKRETANJA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUMPOKRETANJA = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATUMPOKRETANJA = LocalDate.ofEpochDay(-1L);

    private static final Double DEFAULT_VRIJEDNOST = 1D;
    private static final Double UPDATED_VRIJEDNOST = 2D;
    private static final Double SMALLER_VRIJEDNOST = 1D - 1D;

    private static final String DEFAULT_NAZIV = "AAAAAAAAAA";
    private static final String UPDATED_NAZIV = "BBBBBBBBBB";

    @Autowired
    private HitneRepository hitneRepository;

    @Autowired
    private HitneService hitneService;

    @Autowired
    private HitneQueryService hitneQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHitneMockMvc;

    private Hitne hitne;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hitne createEntity(EntityManager em) {
        Hitne hitne = new Hitne()
            .brojpokretanja(DEFAULT_BROJPOKRETANJA)
            .datumpokretanja(DEFAULT_DATUMPOKRETANJA)
            .vrijednost(DEFAULT_VRIJEDNOST)
            .naziv(DEFAULT_NAZIV);
        return hitne;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hitne createUpdatedEntity(EntityManager em) {
        Hitne hitne = new Hitne()
            .brojpokretanja(UPDATED_BROJPOKRETANJA)
            .datumpokretanja(UPDATED_DATUMPOKRETANJA)
            .vrijednost(UPDATED_VRIJEDNOST)
            .naziv(UPDATED_NAZIV);
        return hitne;
    }

    @BeforeEach
    public void initTest() {
        hitne = createEntity(em);
    }

    @Test
    @Transactional
    public void createHitne() throws Exception {
        int databaseSizeBeforeCreate = hitneRepository.findAll().size();

        // Create the Hitne
        restHitneMockMvc.perform(post("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hitne)))
            .andExpect(status().isCreated());

        // Validate the Hitne in the database
        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeCreate + 1);
        Hitne testHitne = hitneList.get(hitneList.size() - 1);
        assertThat(testHitne.getBrojpokretanja()).isEqualTo(DEFAULT_BROJPOKRETANJA);
        assertThat(testHitne.getDatumpokretanja()).isEqualTo(DEFAULT_DATUMPOKRETANJA);
        assertThat(testHitne.getVrijednost()).isEqualTo(DEFAULT_VRIJEDNOST);
        assertThat(testHitne.getNaziv()).isEqualTo(DEFAULT_NAZIV);
    }

    @Test
    @Transactional
    public void createHitneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hitneRepository.findAll().size();

        // Create the Hitne with an existing ID
        hitne.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHitneMockMvc.perform(post("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hitne)))
            .andExpect(status().isBadRequest());

        // Validate the Hitne in the database
        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkBrojpokretanjaIsRequired() throws Exception {
        int databaseSizeBeforeTest = hitneRepository.findAll().size();
        // set the field null
        hitne.setBrojpokretanja(null);

        // Create the Hitne, which fails.

        restHitneMockMvc.perform(post("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hitne)))
            .andExpect(status().isBadRequest());

        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDatumpokretanjaIsRequired() throws Exception {
        int databaseSizeBeforeTest = hitneRepository.findAll().size();
        // set the field null
        hitne.setDatumpokretanja(null);

        // Create the Hitne, which fails.

        restHitneMockMvc.perform(post("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hitne)))
            .andExpect(status().isBadRequest());

        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVrijednostIsRequired() throws Exception {
        int databaseSizeBeforeTest = hitneRepository.findAll().size();
        // set the field null
        hitne.setVrijednost(null);

        // Create the Hitne, which fails.

        restHitneMockMvc.perform(post("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hitne)))
            .andExpect(status().isBadRequest());

        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNazivIsRequired() throws Exception {
        int databaseSizeBeforeTest = hitneRepository.findAll().size();
        // set the field null
        hitne.setNaziv(null);

        // Create the Hitne, which fails.

        restHitneMockMvc.perform(post("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hitne)))
            .andExpect(status().isBadRequest());

        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHitnes() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList
        restHitneMockMvc.perform(get("/api/hitnes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hitne.getId().intValue())))
            .andExpect(jsonPath("$.[*].brojpokretanja").value(hasItem(DEFAULT_BROJPOKRETANJA.intValue())))
            .andExpect(jsonPath("$.[*].datumpokretanja").value(hasItem(DEFAULT_DATUMPOKRETANJA.toString())))
            .andExpect(jsonPath("$.[*].vrijednost").value(hasItem(DEFAULT_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV)));
    }
    
    @Test
    @Transactional
    public void getHitne() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get the hitne
        restHitneMockMvc.perform(get("/api/hitnes/{id}", hitne.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hitne.getId().intValue()))
            .andExpect(jsonPath("$.brojpokretanja").value(DEFAULT_BROJPOKRETANJA.intValue()))
            .andExpect(jsonPath("$.datumpokretanja").value(DEFAULT_DATUMPOKRETANJA.toString()))
            .andExpect(jsonPath("$.vrijednost").value(DEFAULT_VRIJEDNOST.doubleValue()))
            .andExpect(jsonPath("$.naziv").value(DEFAULT_NAZIV));
    }


    @Test
    @Transactional
    public void getHitnesByIdFiltering() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        Long id = hitne.getId();

        defaultHitneShouldBeFound("id.equals=" + id);
        defaultHitneShouldNotBeFound("id.notEquals=" + id);

        defaultHitneShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultHitneShouldNotBeFound("id.greaterThan=" + id);

        defaultHitneShouldBeFound("id.lessThanOrEqual=" + id);
        defaultHitneShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja equals to DEFAULT_BROJPOKRETANJA
        defaultHitneShouldBeFound("brojpokretanja.equals=" + DEFAULT_BROJPOKRETANJA);

        // Get all the hitneList where brojpokretanja equals to UPDATED_BROJPOKRETANJA
        defaultHitneShouldNotBeFound("brojpokretanja.equals=" + UPDATED_BROJPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja not equals to DEFAULT_BROJPOKRETANJA
        defaultHitneShouldNotBeFound("brojpokretanja.notEquals=" + DEFAULT_BROJPOKRETANJA);

        // Get all the hitneList where brojpokretanja not equals to UPDATED_BROJPOKRETANJA
        defaultHitneShouldBeFound("brojpokretanja.notEquals=" + UPDATED_BROJPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsInShouldWork() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja in DEFAULT_BROJPOKRETANJA or UPDATED_BROJPOKRETANJA
        defaultHitneShouldBeFound("brojpokretanja.in=" + DEFAULT_BROJPOKRETANJA + "," + UPDATED_BROJPOKRETANJA);

        // Get all the hitneList where brojpokretanja equals to UPDATED_BROJPOKRETANJA
        defaultHitneShouldNotBeFound("brojpokretanja.in=" + UPDATED_BROJPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsNullOrNotNull() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja is not null
        defaultHitneShouldBeFound("brojpokretanja.specified=true");

        // Get all the hitneList where brojpokretanja is null
        defaultHitneShouldNotBeFound("brojpokretanja.specified=false");
    }

    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja is greater than or equal to DEFAULT_BROJPOKRETANJA
        defaultHitneShouldBeFound("brojpokretanja.greaterThanOrEqual=" + DEFAULT_BROJPOKRETANJA);

        // Get all the hitneList where brojpokretanja is greater than or equal to UPDATED_BROJPOKRETANJA
        defaultHitneShouldNotBeFound("brojpokretanja.greaterThanOrEqual=" + UPDATED_BROJPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja is less than or equal to DEFAULT_BROJPOKRETANJA
        defaultHitneShouldBeFound("brojpokretanja.lessThanOrEqual=" + DEFAULT_BROJPOKRETANJA);

        // Get all the hitneList where brojpokretanja is less than or equal to SMALLER_BROJPOKRETANJA
        defaultHitneShouldNotBeFound("brojpokretanja.lessThanOrEqual=" + SMALLER_BROJPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsLessThanSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja is less than DEFAULT_BROJPOKRETANJA
        defaultHitneShouldNotBeFound("brojpokretanja.lessThan=" + DEFAULT_BROJPOKRETANJA);

        // Get all the hitneList where brojpokretanja is less than UPDATED_BROJPOKRETANJA
        defaultHitneShouldBeFound("brojpokretanja.lessThan=" + UPDATED_BROJPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByBrojpokretanjaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where brojpokretanja is greater than DEFAULT_BROJPOKRETANJA
        defaultHitneShouldNotBeFound("brojpokretanja.greaterThan=" + DEFAULT_BROJPOKRETANJA);

        // Get all the hitneList where brojpokretanja is greater than SMALLER_BROJPOKRETANJA
        defaultHitneShouldBeFound("brojpokretanja.greaterThan=" + SMALLER_BROJPOKRETANJA);
    }


    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja equals to DEFAULT_DATUMPOKRETANJA
        defaultHitneShouldBeFound("datumpokretanja.equals=" + DEFAULT_DATUMPOKRETANJA);

        // Get all the hitneList where datumpokretanja equals to UPDATED_DATUMPOKRETANJA
        defaultHitneShouldNotBeFound("datumpokretanja.equals=" + UPDATED_DATUMPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja not equals to DEFAULT_DATUMPOKRETANJA
        defaultHitneShouldNotBeFound("datumpokretanja.notEquals=" + DEFAULT_DATUMPOKRETANJA);

        // Get all the hitneList where datumpokretanja not equals to UPDATED_DATUMPOKRETANJA
        defaultHitneShouldBeFound("datumpokretanja.notEquals=" + UPDATED_DATUMPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsInShouldWork() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja in DEFAULT_DATUMPOKRETANJA or UPDATED_DATUMPOKRETANJA
        defaultHitneShouldBeFound("datumpokretanja.in=" + DEFAULT_DATUMPOKRETANJA + "," + UPDATED_DATUMPOKRETANJA);

        // Get all the hitneList where datumpokretanja equals to UPDATED_DATUMPOKRETANJA
        defaultHitneShouldNotBeFound("datumpokretanja.in=" + UPDATED_DATUMPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsNullOrNotNull() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja is not null
        defaultHitneShouldBeFound("datumpokretanja.specified=true");

        // Get all the hitneList where datumpokretanja is null
        defaultHitneShouldNotBeFound("datumpokretanja.specified=false");
    }

    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja is greater than or equal to DEFAULT_DATUMPOKRETANJA
        defaultHitneShouldBeFound("datumpokretanja.greaterThanOrEqual=" + DEFAULT_DATUMPOKRETANJA);

        // Get all the hitneList where datumpokretanja is greater than or equal to UPDATED_DATUMPOKRETANJA
        defaultHitneShouldNotBeFound("datumpokretanja.greaterThanOrEqual=" + UPDATED_DATUMPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja is less than or equal to DEFAULT_DATUMPOKRETANJA
        defaultHitneShouldBeFound("datumpokretanja.lessThanOrEqual=" + DEFAULT_DATUMPOKRETANJA);

        // Get all the hitneList where datumpokretanja is less than or equal to SMALLER_DATUMPOKRETANJA
        defaultHitneShouldNotBeFound("datumpokretanja.lessThanOrEqual=" + SMALLER_DATUMPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsLessThanSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja is less than DEFAULT_DATUMPOKRETANJA
        defaultHitneShouldNotBeFound("datumpokretanja.lessThan=" + DEFAULT_DATUMPOKRETANJA);

        // Get all the hitneList where datumpokretanja is less than UPDATED_DATUMPOKRETANJA
        defaultHitneShouldBeFound("datumpokretanja.lessThan=" + UPDATED_DATUMPOKRETANJA);
    }

    @Test
    @Transactional
    public void getAllHitnesByDatumpokretanjaIsGreaterThanSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where datumpokretanja is greater than DEFAULT_DATUMPOKRETANJA
        defaultHitneShouldNotBeFound("datumpokretanja.greaterThan=" + DEFAULT_DATUMPOKRETANJA);

        // Get all the hitneList where datumpokretanja is greater than SMALLER_DATUMPOKRETANJA
        defaultHitneShouldBeFound("datumpokretanja.greaterThan=" + SMALLER_DATUMPOKRETANJA);
    }


    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost equals to DEFAULT_VRIJEDNOST
        defaultHitneShouldBeFound("vrijednost.equals=" + DEFAULT_VRIJEDNOST);

        // Get all the hitneList where vrijednost equals to UPDATED_VRIJEDNOST
        defaultHitneShouldNotBeFound("vrijednost.equals=" + UPDATED_VRIJEDNOST);
    }

    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsNotEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost not equals to DEFAULT_VRIJEDNOST
        defaultHitneShouldNotBeFound("vrijednost.notEquals=" + DEFAULT_VRIJEDNOST);

        // Get all the hitneList where vrijednost not equals to UPDATED_VRIJEDNOST
        defaultHitneShouldBeFound("vrijednost.notEquals=" + UPDATED_VRIJEDNOST);
    }

    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsInShouldWork() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost in DEFAULT_VRIJEDNOST or UPDATED_VRIJEDNOST
        defaultHitneShouldBeFound("vrijednost.in=" + DEFAULT_VRIJEDNOST + "," + UPDATED_VRIJEDNOST);

        // Get all the hitneList where vrijednost equals to UPDATED_VRIJEDNOST
        defaultHitneShouldNotBeFound("vrijednost.in=" + UPDATED_VRIJEDNOST);
    }

    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsNullOrNotNull() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost is not null
        defaultHitneShouldBeFound("vrijednost.specified=true");

        // Get all the hitneList where vrijednost is null
        defaultHitneShouldNotBeFound("vrijednost.specified=false");
    }

    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost is greater than or equal to DEFAULT_VRIJEDNOST
        defaultHitneShouldBeFound("vrijednost.greaterThanOrEqual=" + DEFAULT_VRIJEDNOST);

        // Get all the hitneList where vrijednost is greater than or equal to UPDATED_VRIJEDNOST
        defaultHitneShouldNotBeFound("vrijednost.greaterThanOrEqual=" + UPDATED_VRIJEDNOST);
    }

    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost is less than or equal to DEFAULT_VRIJEDNOST
        defaultHitneShouldBeFound("vrijednost.lessThanOrEqual=" + DEFAULT_VRIJEDNOST);

        // Get all the hitneList where vrijednost is less than or equal to SMALLER_VRIJEDNOST
        defaultHitneShouldNotBeFound("vrijednost.lessThanOrEqual=" + SMALLER_VRIJEDNOST);
    }

    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsLessThanSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost is less than DEFAULT_VRIJEDNOST
        defaultHitneShouldNotBeFound("vrijednost.lessThan=" + DEFAULT_VRIJEDNOST);

        // Get all the hitneList where vrijednost is less than UPDATED_VRIJEDNOST
        defaultHitneShouldBeFound("vrijednost.lessThan=" + UPDATED_VRIJEDNOST);
    }

    @Test
    @Transactional
    public void getAllHitnesByVrijednostIsGreaterThanSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where vrijednost is greater than DEFAULT_VRIJEDNOST
        defaultHitneShouldNotBeFound("vrijednost.greaterThan=" + DEFAULT_VRIJEDNOST);

        // Get all the hitneList where vrijednost is greater than SMALLER_VRIJEDNOST
        defaultHitneShouldBeFound("vrijednost.greaterThan=" + SMALLER_VRIJEDNOST);
    }


    @Test
    @Transactional
    public void getAllHitnesByNazivIsEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where naziv equals to DEFAULT_NAZIV
        defaultHitneShouldBeFound("naziv.equals=" + DEFAULT_NAZIV);

        // Get all the hitneList where naziv equals to UPDATED_NAZIV
        defaultHitneShouldNotBeFound("naziv.equals=" + UPDATED_NAZIV);
    }

    @Test
    @Transactional
    public void getAllHitnesByNazivIsNotEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where naziv not equals to DEFAULT_NAZIV
        defaultHitneShouldNotBeFound("naziv.notEquals=" + DEFAULT_NAZIV);

        // Get all the hitneList where naziv not equals to UPDATED_NAZIV
        defaultHitneShouldBeFound("naziv.notEquals=" + UPDATED_NAZIV);
    }

    @Test
    @Transactional
    public void getAllHitnesByNazivIsInShouldWork() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where naziv in DEFAULT_NAZIV or UPDATED_NAZIV
        defaultHitneShouldBeFound("naziv.in=" + DEFAULT_NAZIV + "," + UPDATED_NAZIV);

        // Get all the hitneList where naziv equals to UPDATED_NAZIV
        defaultHitneShouldNotBeFound("naziv.in=" + UPDATED_NAZIV);
    }

    @Test
    @Transactional
    public void getAllHitnesByNazivIsNullOrNotNull() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where naziv is not null
        defaultHitneShouldBeFound("naziv.specified=true");

        // Get all the hitneList where naziv is null
        defaultHitneShouldNotBeFound("naziv.specified=false");
    }
                @Test
    @Transactional
    public void getAllHitnesByNazivContainsSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where naziv contains DEFAULT_NAZIV
        defaultHitneShouldBeFound("naziv.contains=" + DEFAULT_NAZIV);

        // Get all the hitneList where naziv contains UPDATED_NAZIV
        defaultHitneShouldNotBeFound("naziv.contains=" + UPDATED_NAZIV);
    }

    @Test
    @Transactional
    public void getAllHitnesByNazivNotContainsSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);

        // Get all the hitneList where naziv does not contain DEFAULT_NAZIV
        defaultHitneShouldNotBeFound("naziv.doesNotContain=" + DEFAULT_NAZIV);

        // Get all the hitneList where naziv does not contain UPDATED_NAZIV
        defaultHitneShouldBeFound("naziv.doesNotContain=" + UPDATED_NAZIV);
    }


    @Test
    @Transactional
    public void getAllHitnesByPonudjaciIsEqualToSomething() throws Exception {
        // Initialize the database
        hitneRepository.saveAndFlush(hitne);
        Ponudjaci ponudjaci = PonudjaciResourceIT.createEntity(em);
        em.persist(ponudjaci);
        em.flush();
        hitne.setPonudjaci(ponudjaci);
        hitneRepository.saveAndFlush(hitne);
        Long ponudjaciId = ponudjaci.getId();

        // Get all the hitneList where ponudjaci equals to ponudjaciId
        defaultHitneShouldBeFound("ponudjaciId.equals=" + ponudjaciId);

        // Get all the hitneList where ponudjaci equals to ponudjaciId + 1
        defaultHitneShouldNotBeFound("ponudjaciId.equals=" + (ponudjaciId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultHitneShouldBeFound(String filter) throws Exception {
        restHitneMockMvc.perform(get("/api/hitnes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hitne.getId().intValue())))
            .andExpect(jsonPath("$.[*].brojpokretanja").value(hasItem(DEFAULT_BROJPOKRETANJA.intValue())))
            .andExpect(jsonPath("$.[*].datumpokretanja").value(hasItem(DEFAULT_DATUMPOKRETANJA.toString())))
            .andExpect(jsonPath("$.[*].vrijednost").value(hasItem(DEFAULT_VRIJEDNOST.doubleValue())))
            .andExpect(jsonPath("$.[*].naziv").value(hasItem(DEFAULT_NAZIV)));

        // Check, that the count call also returns 1
        restHitneMockMvc.perform(get("/api/hitnes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultHitneShouldNotBeFound(String filter) throws Exception {
        restHitneMockMvc.perform(get("/api/hitnes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restHitneMockMvc.perform(get("/api/hitnes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingHitne() throws Exception {
        // Get the hitne
        restHitneMockMvc.perform(get("/api/hitnes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHitne() throws Exception {
        // Initialize the database
        hitneService.save(hitne);

        int databaseSizeBeforeUpdate = hitneRepository.findAll().size();

        // Update the hitne
        Hitne updatedHitne = hitneRepository.findById(hitne.getId()).get();
        // Disconnect from session so that the updates on updatedHitne are not directly saved in db
        em.detach(updatedHitne);
        updatedHitne
            .brojpokretanja(UPDATED_BROJPOKRETANJA)
            .datumpokretanja(UPDATED_DATUMPOKRETANJA)
            .vrijednost(UPDATED_VRIJEDNOST)
            .naziv(UPDATED_NAZIV);

        restHitneMockMvc.perform(put("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedHitne)))
            .andExpect(status().isOk());

        // Validate the Hitne in the database
        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeUpdate);
        Hitne testHitne = hitneList.get(hitneList.size() - 1);
        assertThat(testHitne.getBrojpokretanja()).isEqualTo(UPDATED_BROJPOKRETANJA);
        assertThat(testHitne.getDatumpokretanja()).isEqualTo(UPDATED_DATUMPOKRETANJA);
        assertThat(testHitne.getVrijednost()).isEqualTo(UPDATED_VRIJEDNOST);
        assertThat(testHitne.getNaziv()).isEqualTo(UPDATED_NAZIV);
    }

    @Test
    @Transactional
    public void updateNonExistingHitne() throws Exception {
        int databaseSizeBeforeUpdate = hitneRepository.findAll().size();

        // Create the Hitne

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHitneMockMvc.perform(put("/api/hitnes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(hitne)))
            .andExpect(status().isBadRequest());

        // Validate the Hitne in the database
        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHitne() throws Exception {
        // Initialize the database
        hitneService.save(hitne);

        int databaseSizeBeforeDelete = hitneRepository.findAll().size();

        // Delete the hitne
        restHitneMockMvc.perform(delete("/api/hitnes/{id}", hitne.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Hitne> hitneList = hitneRepository.findAll();
        assertThat(hitneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
