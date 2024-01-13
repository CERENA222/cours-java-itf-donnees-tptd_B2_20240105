package com.ipi.javaio.service;

import com.ipi.javaio.exception.SalarieException;
import com.ipi.javaio.model.Entreprise;
import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.repository.SalarieAideADomicileMoisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Permet de gérer les salariés aide à domicile, et notamment :
 * en créer, leur ajouter des congés (ajouteConge()), et les mettre à jour à la clôture de mois et d'année.
 */
@Service
public class SalarieAideADomicileMoisService {

    @Autowired
    private SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository;

    public SalarieAideADomicileMoisService() {
    }

}
