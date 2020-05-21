package hitne.web.rest;

import hitne.HitneApp;
import hitne.domain.Ponudjaci;
import hitne.repository.PonudjaciRepository;
import hitne.service.PonudjaciService;
import hitne.service.dto.PonudjaciCriteria;
import hitne.service.PonudjaciQueryService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PonudjaciResource} REST controller.
 */
@SpringBootTest(classes = HitneApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class PonudjaciResourceIT {

    private static final String DEFAULT_PONUDJAC = "AAAAAAAAAA";
    private static final String UPDATED_PONUDJAC = "BBBBBBBBBB";

    private static final String DEFAULT_KONTAKT = "AAAAAAAAAA";
    private static final String UPDATED_KONTAKT = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESA = "AAAAAAAAAA";
    private static final String UPDATED_ADRESA = "BBBBBBBBBB";

    private static final String DEFAULT_GRAD = "AAAAAAAAAA";
    private static final String UPDATED_GRAD = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFON = "AAAAAAAAAA";
    private static final String UPDATED_TELEFON = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_POSTANSKIBROJ = "AAAAAAAAAA";
    private static final String UPDATED_POSTANSKIBROJ = "BBBBBBBBBB";

    private static final String DEFAULT_PIB = "AAAAAAAAAA";
    private static final String UPDATED_PIB = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_WEB = "AAAAAAAAAA";
    private static final String UPDATED_WEB = "BBBBBBBBBB";

    @Autowired
    private PonudjaciRepository ponudjaciRepository;

    @Autowired
    private PonudjaciService ponudjaciService;

    @Autowired
    private PonudjaciQueryService ponudjaciQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPonudjaciMockMvc;

    private Ponudjaci ponudjaci;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ponudjaci createEntity(EntityManager em) {
        Ponudjaci ponudjaci = new Ponudjaci()
            .ponudjac(DEFAULT_PONUDJAC)
            .kontakt(DEFAULT_KONTAKT)
            .adresa(DEFAULT_ADRESA)
            .grad(DEFAULT_GRAD)
            .telefon(DEFAULT_TELEFON)
            .email(DEFAULT_EMAIL)
            .postanskibroj(DEFAULT_POSTANSKIBROJ)
            .pib(DEFAULT_PIB)
            .fax(DEFAULT_FAX)
            .web(DEFAULT_WEB);
        return ponudjaci;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ponudjaci createUpdatedEntity(EntityManager em) {
        Ponudjaci ponudjaci = new Ponudjaci()
            .ponudjac(UPDATED_PONUDJAC)
            .kontakt(UPDATED_KONTAKT)
            .adresa(UPDATED_ADRESA)
            .grad(UPDATED_GRAD)
            .telefon(UPDATED_TELEFON)
            .email(UPDATED_EMAIL)
            .postanskibroj(UPDATED_POSTANSKIBROJ)
            .pib(UPDATED_PIB)
            .fax(UPDATED_FAX)
            .web(UPDATED_WEB);
        return ponudjaci;
    }

    @BeforeEach
    public void initTest() {
        ponudjaci = createEntity(em);
    }

    @Test
    @Transactional
    public void createPonudjaci() throws Exception {
        int databaseSizeBeforeCreate = ponudjaciRepository.findAll().size();

        // Create the Ponudjaci
        restPonudjaciMockMvc.perform(post("/api/ponudjacis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ponudjaci)))
            .andExpect(status().isCreated());

        // Validate the Ponudjaci in the database
        List<Ponudjaci> ponudjaciList = ponudjaciRepository.findAll();
        assertThat(ponudjaciList).hasSize(databaseSizeBeforeCreate + 1);
        Ponudjaci testPonudjaci = ponudjaciList.get(ponudjaciList.size() - 1);
        assertThat(testPonudjaci.getPonudjac()).isEqualTo(DEFAULT_PONUDJAC);
        assertThat(testPonudjaci.getKontakt()).isEqualTo(DEFAULT_KONTAKT);
        assertThat(testPonudjaci.getAdresa()).isEqualTo(DEFAULT_ADRESA);
        assertThat(testPonudjaci.getGrad()).isEqualTo(DEFAULT_GRAD);
        assertThat(testPonudjaci.getTelefon()).isEqualTo(DEFAULT_TELEFON);
        assertThat(testPonudjaci.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPonudjaci.getPostanskibroj()).isEqualTo(DEFAULT_POSTANSKIBROJ);
        assertThat(testPonudjaci.getPib()).isEqualTo(DEFAULT_PIB);
        assertThat(testPonudjaci.getFax()).isEqualTo(DEFAULT_FAX);
        assertThat(testPonudjaci.getWeb()).isEqualTo(DEFAULT_WEB);
    }

    @Test
    @Transactional
    public void createPonudjaciWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ponudjaciRepository.findAll().size();

        // Create the Ponudjaci with an existing ID
        ponudjaci.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPonudjaciMockMvc.perform(post("/api/ponudjacis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ponudjaci)))
            .andExpect(status().isBadRequest());

        // Validate the Ponudjaci in the database
        List<Ponudjaci> ponudjaciList = ponudjaciRepository.findAll();
        assertThat(ponudjaciList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPonudjacIsRequired() throws Exception {
        int databaseSizeBeforeTest = ponudjaciRepository.findAll().size();
        // set the field null
        ponudjaci.setPonudjac(null);

        // Create the Ponudjaci, which fails.

        restPonudjaciMockMvc.perform(post("/api/ponudjacis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ponudjaci)))
            .andExpect(status().isBadRequest());

        List<Ponudjaci> ponudjaciList = ponudjaciRepository.findAll();
        assertThat(ponudjaciList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPonudjacis() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList
        restPonudjaciMockMvc.perform(get("/api/ponudjacis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ponudjaci.getId().intValue())))
            .andExpect(jsonPath("$.[*].ponudjac").value(hasItem(DEFAULT_PONUDJAC)))
            .andExpect(jsonPath("$.[*].kontakt").value(hasItem(DEFAULT_KONTAKT)))
            .andExpect(jsonPath("$.[*].adresa").value(hasItem(DEFAULT_ADRESA)))
            .andExpect(jsonPath("$.[*].grad").value(hasItem(DEFAULT_GRAD)))
            .andExpect(jsonPath("$.[*].telefon").value(hasItem(DEFAULT_TELEFON)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].postanskibroj").value(hasItem(DEFAULT_POSTANSKIBROJ)))
            .andExpect(jsonPath("$.[*].pib").value(hasItem(DEFAULT_PIB)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].web").value(hasItem(DEFAULT_WEB)));
    }
    
    @Test
    @Transactional
    public void getPonudjaci() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get the ponudjaci
        restPonudjaciMockMvc.perform(get("/api/ponudjacis/{id}", ponudjaci.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ponudjaci.getId().intValue()))
            .andExpect(jsonPath("$.ponudjac").value(DEFAULT_PONUDJAC))
            .andExpect(jsonPath("$.kontakt").value(DEFAULT_KONTAKT))
            .andExpect(jsonPath("$.adresa").value(DEFAULT_ADRESA))
            .andExpect(jsonPath("$.grad").value(DEFAULT_GRAD))
            .andExpect(jsonPath("$.telefon").value(DEFAULT_TELEFON))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.postanskibroj").value(DEFAULT_POSTANSKIBROJ))
            .andExpect(jsonPath("$.pib").value(DEFAULT_PIB))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.web").value(DEFAULT_WEB));
    }


    @Test
    @Transactional
    public void getPonudjacisByIdFiltering() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        Long id = ponudjaci.getId();

        defaultPonudjaciShouldBeFound("id.equals=" + id);
        defaultPonudjaciShouldNotBeFound("id.notEquals=" + id);

        defaultPonudjaciShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultPonudjaciShouldNotBeFound("id.greaterThan=" + id);

        defaultPonudjaciShouldBeFound("id.lessThanOrEqual=" + id);
        defaultPonudjaciShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByPonudjacIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where ponudjac equals to DEFAULT_PONUDJAC
        defaultPonudjaciShouldBeFound("ponudjac.equals=" + DEFAULT_PONUDJAC);

        // Get all the ponudjaciList where ponudjac equals to UPDATED_PONUDJAC
        defaultPonudjaciShouldNotBeFound("ponudjac.equals=" + UPDATED_PONUDJAC);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPonudjacIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where ponudjac not equals to DEFAULT_PONUDJAC
        defaultPonudjaciShouldNotBeFound("ponudjac.notEquals=" + DEFAULT_PONUDJAC);

        // Get all the ponudjaciList where ponudjac not equals to UPDATED_PONUDJAC
        defaultPonudjaciShouldBeFound("ponudjac.notEquals=" + UPDATED_PONUDJAC);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPonudjacIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where ponudjac in DEFAULT_PONUDJAC or UPDATED_PONUDJAC
        defaultPonudjaciShouldBeFound("ponudjac.in=" + DEFAULT_PONUDJAC + "," + UPDATED_PONUDJAC);

        // Get all the ponudjaciList where ponudjac equals to UPDATED_PONUDJAC
        defaultPonudjaciShouldNotBeFound("ponudjac.in=" + UPDATED_PONUDJAC);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPonudjacIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where ponudjac is not null
        defaultPonudjaciShouldBeFound("ponudjac.specified=true");

        // Get all the ponudjaciList where ponudjac is null
        defaultPonudjaciShouldNotBeFound("ponudjac.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByPonudjacContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where ponudjac contains DEFAULT_PONUDJAC
        defaultPonudjaciShouldBeFound("ponudjac.contains=" + DEFAULT_PONUDJAC);

        // Get all the ponudjaciList where ponudjac contains UPDATED_PONUDJAC
        defaultPonudjaciShouldNotBeFound("ponudjac.contains=" + UPDATED_PONUDJAC);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPonudjacNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where ponudjac does not contain DEFAULT_PONUDJAC
        defaultPonudjaciShouldNotBeFound("ponudjac.doesNotContain=" + DEFAULT_PONUDJAC);

        // Get all the ponudjaciList where ponudjac does not contain UPDATED_PONUDJAC
        defaultPonudjaciShouldBeFound("ponudjac.doesNotContain=" + UPDATED_PONUDJAC);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByKontaktIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where kontakt equals to DEFAULT_KONTAKT
        defaultPonudjaciShouldBeFound("kontakt.equals=" + DEFAULT_KONTAKT);

        // Get all the ponudjaciList where kontakt equals to UPDATED_KONTAKT
        defaultPonudjaciShouldNotBeFound("kontakt.equals=" + UPDATED_KONTAKT);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByKontaktIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where kontakt not equals to DEFAULT_KONTAKT
        defaultPonudjaciShouldNotBeFound("kontakt.notEquals=" + DEFAULT_KONTAKT);

        // Get all the ponudjaciList where kontakt not equals to UPDATED_KONTAKT
        defaultPonudjaciShouldBeFound("kontakt.notEquals=" + UPDATED_KONTAKT);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByKontaktIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where kontakt in DEFAULT_KONTAKT or UPDATED_KONTAKT
        defaultPonudjaciShouldBeFound("kontakt.in=" + DEFAULT_KONTAKT + "," + UPDATED_KONTAKT);

        // Get all the ponudjaciList where kontakt equals to UPDATED_KONTAKT
        defaultPonudjaciShouldNotBeFound("kontakt.in=" + UPDATED_KONTAKT);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByKontaktIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where kontakt is not null
        defaultPonudjaciShouldBeFound("kontakt.specified=true");

        // Get all the ponudjaciList where kontakt is null
        defaultPonudjaciShouldNotBeFound("kontakt.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByKontaktContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where kontakt contains DEFAULT_KONTAKT
        defaultPonudjaciShouldBeFound("kontakt.contains=" + DEFAULT_KONTAKT);

        // Get all the ponudjaciList where kontakt contains UPDATED_KONTAKT
        defaultPonudjaciShouldNotBeFound("kontakt.contains=" + UPDATED_KONTAKT);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByKontaktNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where kontakt does not contain DEFAULT_KONTAKT
        defaultPonudjaciShouldNotBeFound("kontakt.doesNotContain=" + DEFAULT_KONTAKT);

        // Get all the ponudjaciList where kontakt does not contain UPDATED_KONTAKT
        defaultPonudjaciShouldBeFound("kontakt.doesNotContain=" + UPDATED_KONTAKT);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByAdresaIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where adresa equals to DEFAULT_ADRESA
        defaultPonudjaciShouldBeFound("adresa.equals=" + DEFAULT_ADRESA);

        // Get all the ponudjaciList where adresa equals to UPDATED_ADRESA
        defaultPonudjaciShouldNotBeFound("adresa.equals=" + UPDATED_ADRESA);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByAdresaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where adresa not equals to DEFAULT_ADRESA
        defaultPonudjaciShouldNotBeFound("adresa.notEquals=" + DEFAULT_ADRESA);

        // Get all the ponudjaciList where adresa not equals to UPDATED_ADRESA
        defaultPonudjaciShouldBeFound("adresa.notEquals=" + UPDATED_ADRESA);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByAdresaIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where adresa in DEFAULT_ADRESA or UPDATED_ADRESA
        defaultPonudjaciShouldBeFound("adresa.in=" + DEFAULT_ADRESA + "," + UPDATED_ADRESA);

        // Get all the ponudjaciList where adresa equals to UPDATED_ADRESA
        defaultPonudjaciShouldNotBeFound("adresa.in=" + UPDATED_ADRESA);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByAdresaIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where adresa is not null
        defaultPonudjaciShouldBeFound("adresa.specified=true");

        // Get all the ponudjaciList where adresa is null
        defaultPonudjaciShouldNotBeFound("adresa.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByAdresaContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where adresa contains DEFAULT_ADRESA
        defaultPonudjaciShouldBeFound("adresa.contains=" + DEFAULT_ADRESA);

        // Get all the ponudjaciList where adresa contains UPDATED_ADRESA
        defaultPonudjaciShouldNotBeFound("adresa.contains=" + UPDATED_ADRESA);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByAdresaNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where adresa does not contain DEFAULT_ADRESA
        defaultPonudjaciShouldNotBeFound("adresa.doesNotContain=" + DEFAULT_ADRESA);

        // Get all the ponudjaciList where adresa does not contain UPDATED_ADRESA
        defaultPonudjaciShouldBeFound("adresa.doesNotContain=" + UPDATED_ADRESA);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByGradIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where grad equals to DEFAULT_GRAD
        defaultPonudjaciShouldBeFound("grad.equals=" + DEFAULT_GRAD);

        // Get all the ponudjaciList where grad equals to UPDATED_GRAD
        defaultPonudjaciShouldNotBeFound("grad.equals=" + UPDATED_GRAD);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByGradIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where grad not equals to DEFAULT_GRAD
        defaultPonudjaciShouldNotBeFound("grad.notEquals=" + DEFAULT_GRAD);

        // Get all the ponudjaciList where grad not equals to UPDATED_GRAD
        defaultPonudjaciShouldBeFound("grad.notEquals=" + UPDATED_GRAD);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByGradIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where grad in DEFAULT_GRAD or UPDATED_GRAD
        defaultPonudjaciShouldBeFound("grad.in=" + DEFAULT_GRAD + "," + UPDATED_GRAD);

        // Get all the ponudjaciList where grad equals to UPDATED_GRAD
        defaultPonudjaciShouldNotBeFound("grad.in=" + UPDATED_GRAD);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByGradIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where grad is not null
        defaultPonudjaciShouldBeFound("grad.specified=true");

        // Get all the ponudjaciList where grad is null
        defaultPonudjaciShouldNotBeFound("grad.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByGradContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where grad contains DEFAULT_GRAD
        defaultPonudjaciShouldBeFound("grad.contains=" + DEFAULT_GRAD);

        // Get all the ponudjaciList where grad contains UPDATED_GRAD
        defaultPonudjaciShouldNotBeFound("grad.contains=" + UPDATED_GRAD);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByGradNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where grad does not contain DEFAULT_GRAD
        defaultPonudjaciShouldNotBeFound("grad.doesNotContain=" + DEFAULT_GRAD);

        // Get all the ponudjaciList where grad does not contain UPDATED_GRAD
        defaultPonudjaciShouldBeFound("grad.doesNotContain=" + UPDATED_GRAD);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByTelefonIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where telefon equals to DEFAULT_TELEFON
        defaultPonudjaciShouldBeFound("telefon.equals=" + DEFAULT_TELEFON);

        // Get all the ponudjaciList where telefon equals to UPDATED_TELEFON
        defaultPonudjaciShouldNotBeFound("telefon.equals=" + UPDATED_TELEFON);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByTelefonIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where telefon not equals to DEFAULT_TELEFON
        defaultPonudjaciShouldNotBeFound("telefon.notEquals=" + DEFAULT_TELEFON);

        // Get all the ponudjaciList where telefon not equals to UPDATED_TELEFON
        defaultPonudjaciShouldBeFound("telefon.notEquals=" + UPDATED_TELEFON);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByTelefonIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where telefon in DEFAULT_TELEFON or UPDATED_TELEFON
        defaultPonudjaciShouldBeFound("telefon.in=" + DEFAULT_TELEFON + "," + UPDATED_TELEFON);

        // Get all the ponudjaciList where telefon equals to UPDATED_TELEFON
        defaultPonudjaciShouldNotBeFound("telefon.in=" + UPDATED_TELEFON);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByTelefonIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where telefon is not null
        defaultPonudjaciShouldBeFound("telefon.specified=true");

        // Get all the ponudjaciList where telefon is null
        defaultPonudjaciShouldNotBeFound("telefon.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByTelefonContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where telefon contains DEFAULT_TELEFON
        defaultPonudjaciShouldBeFound("telefon.contains=" + DEFAULT_TELEFON);

        // Get all the ponudjaciList where telefon contains UPDATED_TELEFON
        defaultPonudjaciShouldNotBeFound("telefon.contains=" + UPDATED_TELEFON);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByTelefonNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where telefon does not contain DEFAULT_TELEFON
        defaultPonudjaciShouldNotBeFound("telefon.doesNotContain=" + DEFAULT_TELEFON);

        // Get all the ponudjaciList where telefon does not contain UPDATED_TELEFON
        defaultPonudjaciShouldBeFound("telefon.doesNotContain=" + UPDATED_TELEFON);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where email equals to DEFAULT_EMAIL
        defaultPonudjaciShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the ponudjaciList where email equals to UPDATED_EMAIL
        defaultPonudjaciShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where email not equals to DEFAULT_EMAIL
        defaultPonudjaciShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the ponudjaciList where email not equals to UPDATED_EMAIL
        defaultPonudjaciShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultPonudjaciShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the ponudjaciList where email equals to UPDATED_EMAIL
        defaultPonudjaciShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where email is not null
        defaultPonudjaciShouldBeFound("email.specified=true");

        // Get all the ponudjaciList where email is null
        defaultPonudjaciShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByEmailContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where email contains DEFAULT_EMAIL
        defaultPonudjaciShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the ponudjaciList where email contains UPDATED_EMAIL
        defaultPonudjaciShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where email does not contain DEFAULT_EMAIL
        defaultPonudjaciShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the ponudjaciList where email does not contain UPDATED_EMAIL
        defaultPonudjaciShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByPostanskibrojIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where postanskibroj equals to DEFAULT_POSTANSKIBROJ
        defaultPonudjaciShouldBeFound("postanskibroj.equals=" + DEFAULT_POSTANSKIBROJ);

        // Get all the ponudjaciList where postanskibroj equals to UPDATED_POSTANSKIBROJ
        defaultPonudjaciShouldNotBeFound("postanskibroj.equals=" + UPDATED_POSTANSKIBROJ);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPostanskibrojIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where postanskibroj not equals to DEFAULT_POSTANSKIBROJ
        defaultPonudjaciShouldNotBeFound("postanskibroj.notEquals=" + DEFAULT_POSTANSKIBROJ);

        // Get all the ponudjaciList where postanskibroj not equals to UPDATED_POSTANSKIBROJ
        defaultPonudjaciShouldBeFound("postanskibroj.notEquals=" + UPDATED_POSTANSKIBROJ);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPostanskibrojIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where postanskibroj in DEFAULT_POSTANSKIBROJ or UPDATED_POSTANSKIBROJ
        defaultPonudjaciShouldBeFound("postanskibroj.in=" + DEFAULT_POSTANSKIBROJ + "," + UPDATED_POSTANSKIBROJ);

        // Get all the ponudjaciList where postanskibroj equals to UPDATED_POSTANSKIBROJ
        defaultPonudjaciShouldNotBeFound("postanskibroj.in=" + UPDATED_POSTANSKIBROJ);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPostanskibrojIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where postanskibroj is not null
        defaultPonudjaciShouldBeFound("postanskibroj.specified=true");

        // Get all the ponudjaciList where postanskibroj is null
        defaultPonudjaciShouldNotBeFound("postanskibroj.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByPostanskibrojContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where postanskibroj contains DEFAULT_POSTANSKIBROJ
        defaultPonudjaciShouldBeFound("postanskibroj.contains=" + DEFAULT_POSTANSKIBROJ);

        // Get all the ponudjaciList where postanskibroj contains UPDATED_POSTANSKIBROJ
        defaultPonudjaciShouldNotBeFound("postanskibroj.contains=" + UPDATED_POSTANSKIBROJ);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPostanskibrojNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where postanskibroj does not contain DEFAULT_POSTANSKIBROJ
        defaultPonudjaciShouldNotBeFound("postanskibroj.doesNotContain=" + DEFAULT_POSTANSKIBROJ);

        // Get all the ponudjaciList where postanskibroj does not contain UPDATED_POSTANSKIBROJ
        defaultPonudjaciShouldBeFound("postanskibroj.doesNotContain=" + UPDATED_POSTANSKIBROJ);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByPibIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where pib equals to DEFAULT_PIB
        defaultPonudjaciShouldBeFound("pib.equals=" + DEFAULT_PIB);

        // Get all the ponudjaciList where pib equals to UPDATED_PIB
        defaultPonudjaciShouldNotBeFound("pib.equals=" + UPDATED_PIB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPibIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where pib not equals to DEFAULT_PIB
        defaultPonudjaciShouldNotBeFound("pib.notEquals=" + DEFAULT_PIB);

        // Get all the ponudjaciList where pib not equals to UPDATED_PIB
        defaultPonudjaciShouldBeFound("pib.notEquals=" + UPDATED_PIB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPibIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where pib in DEFAULT_PIB or UPDATED_PIB
        defaultPonudjaciShouldBeFound("pib.in=" + DEFAULT_PIB + "," + UPDATED_PIB);

        // Get all the ponudjaciList where pib equals to UPDATED_PIB
        defaultPonudjaciShouldNotBeFound("pib.in=" + UPDATED_PIB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPibIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where pib is not null
        defaultPonudjaciShouldBeFound("pib.specified=true");

        // Get all the ponudjaciList where pib is null
        defaultPonudjaciShouldNotBeFound("pib.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByPibContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where pib contains DEFAULT_PIB
        defaultPonudjaciShouldBeFound("pib.contains=" + DEFAULT_PIB);

        // Get all the ponudjaciList where pib contains UPDATED_PIB
        defaultPonudjaciShouldNotBeFound("pib.contains=" + UPDATED_PIB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByPibNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where pib does not contain DEFAULT_PIB
        defaultPonudjaciShouldNotBeFound("pib.doesNotContain=" + DEFAULT_PIB);

        // Get all the ponudjaciList where pib does not contain UPDATED_PIB
        defaultPonudjaciShouldBeFound("pib.doesNotContain=" + UPDATED_PIB);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByFaxIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where fax equals to DEFAULT_FAX
        defaultPonudjaciShouldBeFound("fax.equals=" + DEFAULT_FAX);

        // Get all the ponudjaciList where fax equals to UPDATED_FAX
        defaultPonudjaciShouldNotBeFound("fax.equals=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByFaxIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where fax not equals to DEFAULT_FAX
        defaultPonudjaciShouldNotBeFound("fax.notEquals=" + DEFAULT_FAX);

        // Get all the ponudjaciList where fax not equals to UPDATED_FAX
        defaultPonudjaciShouldBeFound("fax.notEquals=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByFaxIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where fax in DEFAULT_FAX or UPDATED_FAX
        defaultPonudjaciShouldBeFound("fax.in=" + DEFAULT_FAX + "," + UPDATED_FAX);

        // Get all the ponudjaciList where fax equals to UPDATED_FAX
        defaultPonudjaciShouldNotBeFound("fax.in=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByFaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where fax is not null
        defaultPonudjaciShouldBeFound("fax.specified=true");

        // Get all the ponudjaciList where fax is null
        defaultPonudjaciShouldNotBeFound("fax.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByFaxContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where fax contains DEFAULT_FAX
        defaultPonudjaciShouldBeFound("fax.contains=" + DEFAULT_FAX);

        // Get all the ponudjaciList where fax contains UPDATED_FAX
        defaultPonudjaciShouldNotBeFound("fax.contains=" + UPDATED_FAX);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByFaxNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where fax does not contain DEFAULT_FAX
        defaultPonudjaciShouldNotBeFound("fax.doesNotContain=" + DEFAULT_FAX);

        // Get all the ponudjaciList where fax does not contain UPDATED_FAX
        defaultPonudjaciShouldBeFound("fax.doesNotContain=" + UPDATED_FAX);
    }


    @Test
    @Transactional
    public void getAllPonudjacisByWebIsEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where web equals to DEFAULT_WEB
        defaultPonudjaciShouldBeFound("web.equals=" + DEFAULT_WEB);

        // Get all the ponudjaciList where web equals to UPDATED_WEB
        defaultPonudjaciShouldNotBeFound("web.equals=" + UPDATED_WEB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByWebIsNotEqualToSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where web not equals to DEFAULT_WEB
        defaultPonudjaciShouldNotBeFound("web.notEquals=" + DEFAULT_WEB);

        // Get all the ponudjaciList where web not equals to UPDATED_WEB
        defaultPonudjaciShouldBeFound("web.notEquals=" + UPDATED_WEB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByWebIsInShouldWork() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where web in DEFAULT_WEB or UPDATED_WEB
        defaultPonudjaciShouldBeFound("web.in=" + DEFAULT_WEB + "," + UPDATED_WEB);

        // Get all the ponudjaciList where web equals to UPDATED_WEB
        defaultPonudjaciShouldNotBeFound("web.in=" + UPDATED_WEB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByWebIsNullOrNotNull() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where web is not null
        defaultPonudjaciShouldBeFound("web.specified=true");

        // Get all the ponudjaciList where web is null
        defaultPonudjaciShouldNotBeFound("web.specified=false");
    }
                @Test
    @Transactional
    public void getAllPonudjacisByWebContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where web contains DEFAULT_WEB
        defaultPonudjaciShouldBeFound("web.contains=" + DEFAULT_WEB);

        // Get all the ponudjaciList where web contains UPDATED_WEB
        defaultPonudjaciShouldNotBeFound("web.contains=" + UPDATED_WEB);
    }

    @Test
    @Transactional
    public void getAllPonudjacisByWebNotContainsSomething() throws Exception {
        // Initialize the database
        ponudjaciRepository.saveAndFlush(ponudjaci);

        // Get all the ponudjaciList where web does not contain DEFAULT_WEB
        defaultPonudjaciShouldNotBeFound("web.doesNotContain=" + DEFAULT_WEB);

        // Get all the ponudjaciList where web does not contain UPDATED_WEB
        defaultPonudjaciShouldBeFound("web.doesNotContain=" + UPDATED_WEB);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPonudjaciShouldBeFound(String filter) throws Exception {
        restPonudjaciMockMvc.perform(get("/api/ponudjacis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ponudjaci.getId().intValue())))
            .andExpect(jsonPath("$.[*].ponudjac").value(hasItem(DEFAULT_PONUDJAC)))
            .andExpect(jsonPath("$.[*].kontakt").value(hasItem(DEFAULT_KONTAKT)))
            .andExpect(jsonPath("$.[*].adresa").value(hasItem(DEFAULT_ADRESA)))
            .andExpect(jsonPath("$.[*].grad").value(hasItem(DEFAULT_GRAD)))
            .andExpect(jsonPath("$.[*].telefon").value(hasItem(DEFAULT_TELEFON)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].postanskibroj").value(hasItem(DEFAULT_POSTANSKIBROJ)))
            .andExpect(jsonPath("$.[*].pib").value(hasItem(DEFAULT_PIB)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].web").value(hasItem(DEFAULT_WEB)));

        // Check, that the count call also returns 1
        restPonudjaciMockMvc.perform(get("/api/ponudjacis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPonudjaciShouldNotBeFound(String filter) throws Exception {
        restPonudjaciMockMvc.perform(get("/api/ponudjacis?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPonudjaciMockMvc.perform(get("/api/ponudjacis/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPonudjaci() throws Exception {
        // Get the ponudjaci
        restPonudjaciMockMvc.perform(get("/api/ponudjacis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePonudjaci() throws Exception {
        // Initialize the database
        ponudjaciService.save(ponudjaci);

        int databaseSizeBeforeUpdate = ponudjaciRepository.findAll().size();

        // Update the ponudjaci
        Ponudjaci updatedPonudjaci = ponudjaciRepository.findById(ponudjaci.getId()).get();
        // Disconnect from session so that the updates on updatedPonudjaci are not directly saved in db
        em.detach(updatedPonudjaci);
        updatedPonudjaci
            .ponudjac(UPDATED_PONUDJAC)
            .kontakt(UPDATED_KONTAKT)
            .adresa(UPDATED_ADRESA)
            .grad(UPDATED_GRAD)
            .telefon(UPDATED_TELEFON)
            .email(UPDATED_EMAIL)
            .postanskibroj(UPDATED_POSTANSKIBROJ)
            .pib(UPDATED_PIB)
            .fax(UPDATED_FAX)
            .web(UPDATED_WEB);

        restPonudjaciMockMvc.perform(put("/api/ponudjacis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPonudjaci)))
            .andExpect(status().isOk());

        // Validate the Ponudjaci in the database
        List<Ponudjaci> ponudjaciList = ponudjaciRepository.findAll();
        assertThat(ponudjaciList).hasSize(databaseSizeBeforeUpdate);
        Ponudjaci testPonudjaci = ponudjaciList.get(ponudjaciList.size() - 1);
        assertThat(testPonudjaci.getPonudjac()).isEqualTo(UPDATED_PONUDJAC);
        assertThat(testPonudjaci.getKontakt()).isEqualTo(UPDATED_KONTAKT);
        assertThat(testPonudjaci.getAdresa()).isEqualTo(UPDATED_ADRESA);
        assertThat(testPonudjaci.getGrad()).isEqualTo(UPDATED_GRAD);
        assertThat(testPonudjaci.getTelefon()).isEqualTo(UPDATED_TELEFON);
        assertThat(testPonudjaci.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPonudjaci.getPostanskibroj()).isEqualTo(UPDATED_POSTANSKIBROJ);
        assertThat(testPonudjaci.getPib()).isEqualTo(UPDATED_PIB);
        assertThat(testPonudjaci.getFax()).isEqualTo(UPDATED_FAX);
        assertThat(testPonudjaci.getWeb()).isEqualTo(UPDATED_WEB);
    }

    @Test
    @Transactional
    public void updateNonExistingPonudjaci() throws Exception {
        int databaseSizeBeforeUpdate = ponudjaciRepository.findAll().size();

        // Create the Ponudjaci

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPonudjaciMockMvc.perform(put("/api/ponudjacis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ponudjaci)))
            .andExpect(status().isBadRequest());

        // Validate the Ponudjaci in the database
        List<Ponudjaci> ponudjaciList = ponudjaciRepository.findAll();
        assertThat(ponudjaciList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePonudjaci() throws Exception {
        // Initialize the database
        ponudjaciService.save(ponudjaci);

        int databaseSizeBeforeDelete = ponudjaciRepository.findAll().size();

        // Delete the ponudjaci
        restPonudjaciMockMvc.perform(delete("/api/ponudjacis/{id}", ponudjaci.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ponudjaci> ponudjaciList = ponudjaciRepository.findAll();
        assertThat(ponudjaciList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
