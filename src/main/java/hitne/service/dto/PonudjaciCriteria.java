package hitne.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link hitne.domain.Ponudjaci} entity. This class is used
 * in {@link hitne.web.rest.PonudjaciResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ponudjacis?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PonudjaciCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter ponudjac;

    private StringFilter kontakt;

    private StringFilter adresa;

    private StringFilter grad;

    private StringFilter telefon;

    private StringFilter email;

    private StringFilter postanskibroj;

    private StringFilter pib;

    private StringFilter fax;

    private StringFilter web;

    public PonudjaciCriteria() {
    }

    public PonudjaciCriteria(PonudjaciCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.ponudjac = other.ponudjac == null ? null : other.ponudjac.copy();
        this.kontakt = other.kontakt == null ? null : other.kontakt.copy();
        this.adresa = other.adresa == null ? null : other.adresa.copy();
        this.grad = other.grad == null ? null : other.grad.copy();
        this.telefon = other.telefon == null ? null : other.telefon.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.postanskibroj = other.postanskibroj == null ? null : other.postanskibroj.copy();
        this.pib = other.pib == null ? null : other.pib.copy();
        this.fax = other.fax == null ? null : other.fax.copy();
        this.web = other.web == null ? null : other.web.copy();
    }

    @Override
    public PonudjaciCriteria copy() {
        return new PonudjaciCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getPonudjac() {
        return ponudjac;
    }

    public void setPonudjac(StringFilter ponudjac) {
        this.ponudjac = ponudjac;
    }

    public StringFilter getKontakt() {
        return kontakt;
    }

    public void setKontakt(StringFilter kontakt) {
        this.kontakt = kontakt;
    }

    public StringFilter getAdresa() {
        return adresa;
    }

    public void setAdresa(StringFilter adresa) {
        this.adresa = adresa;
    }

    public StringFilter getGrad() {
        return grad;
    }

    public void setGrad(StringFilter grad) {
        this.grad = grad;
    }

    public StringFilter getTelefon() {
        return telefon;
    }

    public void setTelefon(StringFilter telefon) {
        this.telefon = telefon;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getPostanskibroj() {
        return postanskibroj;
    }

    public void setPostanskibroj(StringFilter postanskibroj) {
        this.postanskibroj = postanskibroj;
    }

    public StringFilter getPib() {
        return pib;
    }

    public void setPib(StringFilter pib) {
        this.pib = pib;
    }

    public StringFilter getFax() {
        return fax;
    }

    public void setFax(StringFilter fax) {
        this.fax = fax;
    }

    public StringFilter getWeb() {
        return web;
    }

    public void setWeb(StringFilter web) {
        this.web = web;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PonudjaciCriteria that = (PonudjaciCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(ponudjac, that.ponudjac) &&
            Objects.equals(kontakt, that.kontakt) &&
            Objects.equals(adresa, that.adresa) &&
            Objects.equals(grad, that.grad) &&
            Objects.equals(telefon, that.telefon) &&
            Objects.equals(email, that.email) &&
            Objects.equals(postanskibroj, that.postanskibroj) &&
            Objects.equals(pib, that.pib) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(web, that.web);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        ponudjac,
        kontakt,
        adresa,
        grad,
        telefon,
        email,
        postanskibroj,
        pib,
        fax,
        web
        );
    }

    @Override
    public String toString() {
        return "PonudjaciCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (ponudjac != null ? "ponudjac=" + ponudjac + ", " : "") +
                (kontakt != null ? "kontakt=" + kontakt + ", " : "") +
                (adresa != null ? "adresa=" + adresa + ", " : "") +
                (grad != null ? "grad=" + grad + ", " : "") +
                (telefon != null ? "telefon=" + telefon + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (postanskibroj != null ? "postanskibroj=" + postanskibroj + ", " : "") +
                (pib != null ? "pib=" + pib + ", " : "") +
                (fax != null ? "fax=" + fax + ", " : "") +
                (web != null ? "web=" + web + ", " : "") +
            "}";
    }

}
