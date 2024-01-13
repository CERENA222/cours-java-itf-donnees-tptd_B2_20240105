package com.ipi.javaio.repository;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.model.SalarieAideADomicileMois;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalarieAideADomicileMoisRepository extends PagingAndSortingRepository<SalarieAideADomicileMois, Long> {

    @Query("from SalarieAideADomicileMois salarieMois "
            + "where salarieMois.salarieAideADomicile = :salarie and year(salarieMois.premierDuMois) = :annee ")
    List<SalarieAideADomicileMois> findBySalarieAideADomicileAndAnnee(@Param("salarie") SalarieAideADomicile salarieAideADomicile,
                                           @Param("annee") int annee);
    @Query("from SalarieAideADomicileMois salarieMois "
            + "where salarieMois.salarieAideADomicile = :salarie and salarieMois = :premierDuMois ")
    SalarieAideADomicileMois findBySalarieAideADomicileAndMois(@Param("salarie") SalarieAideADomicile salarieAideADomicile,
                                                                      @Param("premierDuMois") LocalDate premierDuMois);
    List<SalarieAideADomicileMois> findBySalarieAideADomicile(SalarieAideADomicile salarieAideADomicile);
    //Page<SalarieAideADomicileMois> findBySalarieAideADomicile(SalarieAideADomicile salarieAideADomicile,
    //                                                          Pageable pageable);

}
