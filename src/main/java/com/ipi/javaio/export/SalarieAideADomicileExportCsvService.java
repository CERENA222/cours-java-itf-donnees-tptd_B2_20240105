package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Arrays;

@Service
public class SalarieAideADomicileExportCsvService {
    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileExportCsvService(
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(PrintWriter writer, Long salarieId) {
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        exportBase(writer, Arrays.asList(salarieAideADomicile));
    }
    public void export(PrintWriter writer) {
        Iterable<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries();
        exportBase(writer, salaries);
    }
    public void exportBase(PrintWriter writer, Iterable<SalarieAideADomicile> salaries) {
        // TODO

        writer.println("Nom; MoisEnCours; MoisDebutContrat; JoursTravaillesAnneeN," +
                "CongesPayesAcquisAnneeN; JoursTravaillesAnneeNMoins1; CongesPayesAcquisAnneeNMoins1; CongesPayesPrisAnneeNMoins1");

        for (SalarieAideADomicile salarie : salaries) {
            // Échappement des double quotes
            String nom = escapeDoubleQuotes(salarie.getNom());
            String moisEnCours = escapeDoubleQuotes(salarie.getMoisEnCours().toString());
            String moisDebutContrat = escapeDoubleQuotes(salarie.getMoisDebutContrat().toString());
            String joursTravaillesAnneeN = String.valueOf(salarie.getJoursTravaillesAnneeN());
            String congesPayesAcquisAnneeN = String.valueOf(salarie.getCongesPayesAcquisAnneeN());
            String joursTravaillesAnneeNMoins1 = String.valueOf(salarie.getJoursTravaillesAnneeNMoins1());
            String congesPayesAcquisAnneeNMoins1 = String.valueOf(salarie.getCongesPayesAcquisAnneeNMoins1());
            String congesPayesPrisAnneeNMoins1 = String.valueOf(salarie.getCongesPayesPrisAnneeNMoins1());

            // Écriture des valeurs dans le fichier CSV
            writer.print(nom + ";");
            writer.print(moisEnCours + ";");
            writer.print(moisDebutContrat + ";");
            writer.print(joursTravaillesAnneeN + ";");
            writer.print(congesPayesAcquisAnneeN + ";");
            writer.print(joursTravaillesAnneeNMoins1 + ";");
            writer.print(congesPayesAcquisAnneeNMoins1 + ";");
            writer.println(congesPayesPrisAnneeNMoins1);
        }
    }

    private String escapeDoubleQuotes(String input) {
        // Échapper les double quotes à l'intérieur de la valeur
        return "\"" + input.replace("\"", "\"\"") + "\"";
    }



}
