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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link hitne.domain.Hitne} entity. This class is used
 * in {@link hitne.web.rest.HitneResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /hitnes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HitneCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter brojpokretanja;

    private LocalDateFilter datumpokretanja;

    private DoubleFilter vrijednost;

    private StringFilter naziv;

    private LongFilter ponudjaciId;

    public HitneCriteria() {
    }

    public HitneCriteria(HitneCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.brojpokretanja = other.brojpokretanja == null ? null : other.brojpokretanja.copy();
        this.datumpokretanja = other.datumpokretanja == null ? null : other.datumpokretanja.copy();
        this.vrijednost = other.vrijednost == null ? null : other.vrijednost.copy();
        this.naziv = other.naziv == null ? null : other.naziv.copy();
        this.ponudjaciId = other.ponudjaciId == null ? null : other.ponudjaciId.copy();
    }

    @Override
    public HitneCriteria copy() {
        return new HitneCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getBrojpokretanja() {
        return brojpokretanja;
    }

    public void setBrojpokretanja(LongFilter brojpokretanja) {
        this.brojpokretanja = brojpokretanja;
    }

    public LocalDateFilter getDatumpokretanja() {
        return datumpokretanja;
    }

    public void setDatumpokretanja(LocalDateFilter datumpokretanja) {
        this.datumpokretanja = datumpokretanja;
    }

    public DoubleFilter getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(DoubleFilter vrijednost) {
        this.vrijednost = vrijednost;
    }

    public StringFilter getNaziv() {
        return naziv;
    }

    public void setNaziv(StringFilter naziv) {
        this.naziv = naziv;
    }

    public LongFilter getPonudjaciId() {
        return ponudjaciId;
    }

    public void setPonudjaciId(LongFilter ponudjaciId) {
        this.ponudjaciId = ponudjaciId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HitneCriteria that = (HitneCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(brojpokretanja, that.brojpokretanja) &&
            Objects.equals(datumpokretanja, that.datumpokretanja) &&
            Objects.equals(vrijednost, that.vrijednost) &&
            Objects.equals(naziv, that.naziv) &&
            Objects.equals(ponudjaciId, that.ponudjaciId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        brojpokretanja,
        datumpokretanja,
        vrijednost,
        naziv,
        ponudjaciId
        );
    }

    @Override
    public String toString() {
        return "HitneCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (brojpokretanja != null ? "brojpokretanja=" + brojpokretanja + ", " : "") +
                (datumpokretanja != null ? "datumpokretanja=" + datumpokretanja + ", " : "") +
                (vrijednost != null ? "vrijednost=" + vrijednost + ", " : "") +
                (naziv != null ? "naziv=" + naziv + ", " : "") +
                (ponudjaciId != null ? "ponudjaciId=" + ponudjaciId + ", " : "") +
            "}";
    }

}
