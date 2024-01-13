package com.ipi.javaio.io;

import com.ipi.javaio.exception.SalarieException;
import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.repository.SalarieAideADomicileMoisRepository;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SalarieAideADomicileImportCsvService {

    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileImportCsvService(
            SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository,
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void importFile(String filePath) throws SalarieException {
        // TODO [TP]
    }


}
