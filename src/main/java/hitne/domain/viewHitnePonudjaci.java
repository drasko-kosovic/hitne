package hitne.domain;


import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "view_hitne")
public class viewHitnePonudjaci {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;



    private Long brojpokretanja;
    private Long brojzahtjeva;
    private Long brojodluke;


    private LocalDate datumpokretanja;

    private LocalDate datumodluke;


    private Double vrijednost;


    private String naziv;


    private String ponudjac;


    private String kontakt;


    private String adresa;


    private String grad;


    private String telefon;


    private String email;


    private String postanskibroj;


    private String pib;


    private String fax;


    private String web;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBrojpokretanja() {
        return brojpokretanja;
    }

    public void setBrojpokretanja(Long brojpokretanja) {
        this.brojpokretanja = brojpokretanja;
    }

    public Long getBrojzahtjeva() {
        return brojzahtjeva;
    }

    public void setBrojzahtjeva(Long brojzahtjeva) {
        this.brojzahtjeva = brojzahtjeva;
    }

    public Long getBrojodluke() {
        return brojodluke;
    }

    public void setBrojodluke(Long brojodluke) {
        this.brojodluke = brojodluke;
    }

    public LocalDate getDatumpokretanja() {
        return datumpokretanja;
    }

    public void setDatumpokretanja(LocalDate datumpokretanja) {
        this.datumpokretanja = datumpokretanja;
    }

    public LocalDate getDatumodluke() {
        return datumodluke;
    }

    public void setDatumodluke(LocalDate datumodluke) {
        this.datumodluke = datumodluke;
    }

    public Double getVrijednost() {
        return vrijednost;
    }

    public void setVrijednost(Double vrijednost) {
        this.vrijednost = vrijednost;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPonudjac() {
        return ponudjac;
    }

    public void setPonudjac(String ponudjac) {
        this.ponudjac = ponudjac;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostanskibroj() {
        return postanskibroj;
    }

    public void setPostanskibroj(String postanskibroj) {
        this.postanskibroj = postanskibroj;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
}
