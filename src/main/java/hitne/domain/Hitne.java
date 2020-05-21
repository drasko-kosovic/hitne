package hitne.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Hitne.
 */
@Entity
@Table(name = "hitne")
public class Hitne implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "brojpokretanja", nullable = false)
    private Long brojpokretanja;

    @NotNull
    @Column(name = "datumpokretanja", nullable = false)
    private LocalDate datumpokretanja;

    @NotNull
    @Column(name = "vrijednost", nullable = false)
    private Double vrijednost;

    @NotNull
    @Column(name = "naziv", nullable = false)
    private String naziv;

    @ManyToOne
    @JsonIgnoreProperties("hitnes")
    private Ponudjaci ponudjaci;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrojpokretanja() {
        return brojpokretanja;
    }

    public Hitne brojpokretanja(Long brojpokretanja) {
        this.brojpokretanja = brojpokretanja;
        return this;
    }

    public void setBrojpokretanja(Long brojpokretanja) {
        this.brojpokretanja = brojpokretanja;
    }

    public LocalDate getDatumpokretanja() {
        return datumpokretanja;
    }

    public Hitne datumpokretanja(LocalDate datumpokretanja) {
        this.datumpokretanja = datumpokretanja;
        return this;
    }

    public void setDatumpokretanja(LocalDate datumpokretanja) {
        this.datumpokretanja = datumpokretanja;
    }

    public Double getVrijednost() {
        return vrijednost;
    }

    public Hitne vrijednost(Double vrijednost) {
        this.vrijednost = vrijednost;
        return this;
    }

    public void setVrijednost(Double vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getNaziv() {
        return naziv;
    }

    public Hitne naziv(String naziv) {
        this.naziv = naziv;
        return this;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Ponudjaci getPonudjaci() {
        return ponudjaci;
    }

    public Hitne ponudjaci(Ponudjaci ponudjaci) {
        this.ponudjaci = ponudjaci;
        return this;
    }

    public void setPonudjaci(Ponudjaci ponudjaci) {
        this.ponudjaci = ponudjaci;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hitne)) {
            return false;
        }
        return id != null && id.equals(((Hitne) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Hitne{" +
            "id=" + getId() +
            ", brojpokretanja=" + getBrojpokretanja() +
            ", datumpokretanja='" + getDatumpokretanja() + "'" +
            ", vrijednost=" + getVrijednost() +
            ", naziv='" + getNaziv() + "'" +
            "}";
    }
}
