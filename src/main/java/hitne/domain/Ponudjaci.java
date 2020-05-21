package hitne.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Ponudjaci.
 */
@Entity
@Table(name = "ponudjaci")
public class Ponudjaci implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "ponudjac", nullable = false)
    private String ponudjac;

    @Column(name = "kontakt")
    private String kontakt;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "grad")
    private String grad;

    @Column(name = "telefon")
    private String telefon;

    @Column(name = "email")
    private String email;

    @Column(name = "postanskibroj")
    private String postanskibroj;

    @Column(name = "pib")
    private String pib;

    @Column(name = "fax")
    private String fax;

    @Column(name = "web")
    private String web;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPonudjac() {
        return ponudjac;
    }

    public Ponudjaci ponudjac(String ponudjac) {
        this.ponudjac = ponudjac;
        return this;
    }

    public void setPonudjac(String ponudjac) {
        this.ponudjac = ponudjac;
    }

    public String getKontakt() {
        return kontakt;
    }

    public Ponudjaci kontakt(String kontakt) {
        this.kontakt = kontakt;
        return this;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getAdresa() {
        return adresa;
    }

    public Ponudjaci adresa(String adresa) {
        this.adresa = adresa;
        return this;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public Ponudjaci grad(String grad) {
        this.grad = grad;
        return this;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getTelefon() {
        return telefon;
    }

    public Ponudjaci telefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public Ponudjaci email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostanskibroj() {
        return postanskibroj;
    }

    public Ponudjaci postanskibroj(String postanskibroj) {
        this.postanskibroj = postanskibroj;
        return this;
    }

    public void setPostanskibroj(String postanskibroj) {
        this.postanskibroj = postanskibroj;
    }

    public String getPib() {
        return pib;
    }

    public Ponudjaci pib(String pib) {
        this.pib = pib;
        return this;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getFax() {
        return fax;
    }

    public Ponudjaci fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWeb() {
        return web;
    }

    public Ponudjaci web(String web) {
        this.web = web;
        return this;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ponudjaci)) {
            return false;
        }
        return id != null && id.equals(((Ponudjaci) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Ponudjaci{" +
            "id=" + getId() +
            ", ponudjac='" + getPonudjac() + "'" +
            ", kontakt='" + getKontakt() + "'" +
            ", adresa='" + getAdresa() + "'" +
            ", grad='" + getGrad() + "'" +
            ", telefon='" + getTelefon() + "'" +
            ", email='" + getEmail() + "'" +
            ", postanskibroj='" + getPostanskibroj() + "'" +
            ", pib='" + getPib() + "'" +
            ", fax='" + getFax() + "'" +
            ", web='" + getWeb() + "'" +
            "}";
    }
}
