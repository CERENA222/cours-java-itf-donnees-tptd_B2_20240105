package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.model.SalarieAideADomicileMois;
import com.ipi.javaio.repository.SalarieAideADomicileMoisRepository;
import com.ipi.javaio.service.SalarieAideADomicileService;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class SalarieAideADomicileExportPdfService {

    DateTimeFormatter frenchDateTimeFormatter = DateTimeFormatter.ofPattern("'MMM' yyyy");

    @Autowired
    private final SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository;
    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileExportPdfService(
            SalarieAideADomicileMoisRepository salarieAideADomicileMoisRepository,
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileMoisRepository = salarieAideADomicileMoisRepository;
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(OutputStream outputStream) throws IOException {
        Iterable<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository.findAll();
        exportBase(outputStream, allMois);
    }

    public void export(OutputStream outputStream, Long salarieId/*, LocalDate premierDuMois*/) throws IOException {
        SalarieAideADomicile salarieAideADomicile = salarieAideADomicileService.getSalarie(salarieId);
        //SalarieAideADomicileMois mois = salarieAideADomicileMoisRepository
        //        .findBySalarieAideADomicileAndMois(salarieAideADomicile, premierDuMois).get();
        List<SalarieAideADomicileMois> allMois = salarieAideADomicileMoisRepository
                .findBySalarieAideADomicile(salarieAideADomicile);
        exportBase(outputStream, allMois);
    }

    public void exportBase(OutputStream outputStream, Iterable<SalarieAideADomicileMois> allMois) throws IOException {

        // Création d'un document
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(outputStream));
        Document document = new Document(pdfDoc);

        // En-tête : Nom du salarié et logo de Spring Boot
        SalarieAideADomicile salarie = allMois.iterator().next().getSalarieAideADomicile();
        Paragraph header = new Paragraph(salarie.getNom())
                .setFontSize(18)
                .setBold();
        document.add(header);

        String logoPath = "spring-initializr.svg";
        ImageData imageData = ImageDataFactory.create(logoPath);
        Image image = new Image(imageData);
        document.add(new Paragraph("\n\n").add(image));


        // Création d'un tableau pour le détail du salarié
        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table.addCell("Champ");
        table.addCell("Valeur");

        // Ajout de chaque champ avec sa valeur
        table.addCell("ID salarié");
        table.addCell(salarie.getId().toString());

        table.addCell("Nom");
        table.addCell(salarie.getNom());


        // Bonus : Calcul de l'ancienneté
        LocalDate moisDebutContrat = salarie.getMoisDebutContrat();
        int anciennete = moisDebutContrat.until(LocalDate.now()).getYears();
        table.addCell("Ancienneté");
        table.addCell(anciennete + " ans");


        document.add(table);
        document.close();
    }


}
