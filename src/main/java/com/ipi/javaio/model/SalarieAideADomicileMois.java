package com.ipi.javaio.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Objects;

@Entity
public class SalarieAideADomicileMois {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private SalarieAideADomicile salarieAideADomicile;

    private LocalDate premierDuMois;

    private double joursTravaillesAnneeN = 0;
    private double congesPayesAcquisAnneeN = 0;

    /** en année N sur l'acquis N-1 */
    @Convert(converter = LinkedHashSetStringConverter.class)
    @Column
    private LinkedHashSet<LocalDate> congesPayesPris = new LinkedHashSet<LocalDate>();
    private double joursTravaillesAnneeNMoins1= 0;
    private double congesPayesAcquisAnneeNMoins1= 0;
    private double congesPayesPrisAnneeNMoins1= 0;

    public SalarieAideADomicileMois() {
    }

    public SalarieAideADomicileMois(LocalDate premierDuMois,
                                //LinkedHashSet<LocalDate> congesPayesPris,
                                double joursTravaillesAnneeN, double congesPayesAcquisAnneeN,
                                double joursTravaillesAnneeNMoins1, double congesPayesAcquisAnneeNMoins1, double congesPayesPrisAnneeNMoins1) {
        this.premierDuMois = premierDuMois;
        this.joursTravaillesAnneeNMoins1 = joursTravaillesAnneeNMoins1;
        this.congesPayesAcquisAnneeNMoins1 = congesPayesAcquisAnneeNMoins1;
        this.congesPayesPrisAnneeNMoins1 = congesPayesPrisAnneeNMoins1;
        this.joursTravaillesAnneeN = joursTravaillesAnneeN;
        this.congesPayesAcquisAnneeN = congesPayesAcquisAnneeN;
        //this.congesPayesPris = congesPayesPris;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalarieAideADomicile getSalarieAideADomicile() {
        return salarieAideADomicile;
    }

    public void setSalarieAideADomicile(SalarieAideADomicile salarieAideADomicile) {
        this.salarieAideADomicile = salarieAideADomicile;
    }

    /**
     * @return the v
     */
    public LocalDate getPremierDuMois() {
        return premierDuMois;
    }

    /**
     * @param premierDuMois the premierDuMois to set
     */
    public SalarieAideADomicileMois setPremierDuMois(LocalDate premierDuMois) {
        this.premierDuMois = premierDuMois;
        return this;
    }

    public double getJoursTravaillesAnneeN() {
        return joursTravaillesAnneeN;
    }

    public void setJoursTravaillesAnneeN(double joursTravaillesAnneeN) {
        this.joursTravaillesAnneeN = joursTravaillesAnneeN;
    }

    public double getCongesPayesAcquisAnneeN() {
        return congesPayesAcquisAnneeN;
    }

    public void setCongesPayesAcquisAnneeN(double congesPayesAcquisAnneeN) {
        this.congesPayesAcquisAnneeN = congesPayesAcquisAnneeN;
    }

    public LinkedHashSet<LocalDate> getCongesPayesPris() {
        return congesPayesPris;
    }

    public void setCongesPayesPris(LinkedHashSet<LocalDate> congesPayesPris) {
        this.congesPayesPris = congesPayesPris;
    }

    public double getJoursTravaillesAnneeNMoins1() {
        return joursTravaillesAnneeNMoins1;
    }

    public void setJoursTravaillesAnneeNMoins1(double joursTravaillesAnneeNMoins1) {
        this.joursTravaillesAnneeNMoins1 = joursTravaillesAnneeNMoins1;
    }

    public double getCongesPayesRestantAnneeNMoins1() {
        return this.congesPayesAcquisAnneeNMoins1 - this.getCongesPayesPrisAnneeNMoins1();
    }
    /*
    public double getCongesPayesRestantAnneeNMoins1() {
        return congesPayesRestantAnneeNMoins1;
    }

    public void setCongesPayesRestantAnneeNMoins1(double congesPayesRestantAnneeNMoins1) {
        this.congesPayesRestantAnneeNMoins1 = congesPayesRestantAnneeNMoins1;
    }
    */

    public double getCongesPayesAcquisAnneeNMoins1() {
        return congesPayesAcquisAnneeNMoins1;
    }

    public void setCongesPayesAcquisAnneeNMoins1(double congesPayesAcquisAnneeNMoins1) {
        this.congesPayesAcquisAnneeNMoins1 = congesPayesAcquisAnneeNMoins1;
    }

    public double getCongesPayesPrisAnneeNMoins1() {
        return congesPayesPrisAnneeNMoins1;
    }

    public void setCongesPayesPrisAnneeNMoins1(double congesPayesPrisAnneeNMoins1) {
        this.congesPayesPrisAnneeNMoins1 = congesPayesPrisAnneeNMoins1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalarieAideADomicileMois)) return false;
        SalarieAideADomicileMois s = (SalarieAideADomicileMois) o;
        return Objects.equals(id, s.id) &&
                Objects.equals(premierDuMois, s.premierDuMois);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, premierDuMois);
    }
}
