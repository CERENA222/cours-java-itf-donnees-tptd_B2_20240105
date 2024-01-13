package com.ipi.javaio.export;

import com.ipi.javaio.model.SalarieAideADomicile;
import com.ipi.javaio.service.SalarieAideADomicileService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class SalarieAideADomicileExportXlsxService {

    public static final int COL_ID_SALARIE = 0;
    public static final int COL_NOM = 1;
    public static final int COL_MOIS_DEBUT_CONTRAT = 2;
    public static final int COL_ANCIENNETE = 3;
    public static final int COL_JOUR_TRAVAILLES_ANNEE_N = 4;
    public static final int COL_CONGES_PAYES_ACQUIS_ANNEE_N = 5;
    public static final int COL_JOUR_TRAVAILLES_ANNEE_N_MOINS_1 = 6;
    public static final int COL_CONGES_PAYES_ACQUIS_ANNEE_N_MOINS_1 = 7;

    @Autowired
    private final SalarieAideADomicileService salarieAideADomicileService;

    public SalarieAideADomicileExportXlsxService(
            SalarieAideADomicileService salarieAideADomicileService) {
        this.salarieAideADomicileService = salarieAideADomicileService;
    }

    public void export(ServletOutputStream outputStream) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Salariés");
        Row headerRow = sheet.createRow(0);

        CellStyle styleHeader = styleColor(workbook);

        Cell cellHeaderIdSalarie= headerRow.createCell(COL_ID_SALARIE); // 0
        cellHeaderIdSalarie.setCellValue("ID salarie");
        cellHeaderIdSalarie.setCellStyle(styleHeader);

        Cell cellHeaderNom= headerRow.createCell(COL_NOM); // 0
        cellHeaderNom.setCellValue("Nom");
        cellHeaderNom.setCellStyle(styleHeader);

        Cell cellHeaderMoisBebutContrat= headerRow.createCell(COL_MOIS_DEBUT_CONTRAT); // 0
        cellHeaderMoisBebutContrat.setCellValue("Mois debut contrat");
        cellHeaderMoisBebutContrat.setCellStyle(styleHeader);

        Cell cellHeaderAnciennete= headerRow.createCell(COL_ANCIENNETE); // 0
        cellHeaderAnciennete.setCellValue("Anciennete");
        cellHeaderAnciennete.setCellStyle(styleHeader);

        Cell cellHeaderJourTravailleAnneeN= headerRow.createCell(COL_JOUR_TRAVAILLES_ANNEE_N); // 0
        cellHeaderJourTravailleAnneeN.setCellValue("Jour de travaille année");
        cellHeaderJourTravailleAnneeN.setCellStyle(styleHeader);

        Cell cellHeaderCongesPayesAcquisAnneeN= headerRow.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N); // 0
        cellHeaderCongesPayesAcquisAnneeN.setCellValue("Congés payés anneés ");
        cellHeaderCongesPayesAcquisAnneeN.setCellStyle(styleHeader);

        List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries();
        exportBase(sheet, salaries);

        workbook.write(outputStream);
        workbook.close();
    }

    private void exportBase(Sheet sheet, List<SalarieAideADomicile> salaries) {
        int rowIndex = 1;

        for (SalarieAideADomicile salarie : salaries) {
            Row row = sheet.createRow(rowIndex++);

            Cell cellIdSalarie = row.createCell(COL_ID_SALARIE);
            cellIdSalarie.setCellValue(salarie.getId());
            setCellStyle(cellIdSalarie);

            Cell cellNom = row.createCell(COL_NOM);
            cellNom.setCellValue(salarie.getNom());
            setCellStyle(cellNom);

            Cell cellMoisDebutContrat = row.createCell(COL_MOIS_DEBUT_CONTRAT);
            cellMoisDebutContrat.setCellValue(salarie.getMoisDebutContrat().toString());
            setCellStyle(cellMoisDebutContrat);

            int anciennete = salarie.getMoisDebutContrat().until(LocalDate.now()).getYears();
            Cell cellAnciennete = row.createCell(COL_ANCIENNETE);
            cellAnciennete.setCellValue(anciennete + " ans");
            setCellStyle(cellAnciennete);

            Cell cellJoursTravaillesAnneeN = row.createCell(COL_JOUR_TRAVAILLES_ANNEE_N);
            cellJoursTravaillesAnneeN.setCellValue(salarie.getJoursTravaillesAnneeN());
            setCellStyle(cellJoursTravaillesAnneeN);

            Cell cellCongesPayesAcquisAnneeN = row.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N);
            cellCongesPayesAcquisAnneeN.setCellValue(salarie.getCongesPayesAcquisAnneeN());
            setCellStyle(cellCongesPayesAcquisAnneeN);

            Cell cellJoursTravaillesAnneeNMoins1 = row.createCell(COL_JOUR_TRAVAILLES_ANNEE_N_MOINS_1);
            cellJoursTravaillesAnneeNMoins1.setCellValue(salarie.getJoursTravaillesAnneeNMoins1());
            setCellStyle(cellJoursTravaillesAnneeNMoins1);

            Cell cellCongesPayesAcquisAnneeNMoins1 = row.createCell(COL_CONGES_PAYES_ACQUIS_ANNEE_N_MOINS_1);
            cellCongesPayesAcquisAnneeNMoins1.setCellValue(salarie.getCongesPayesAcquisAnneeNMoins1());
            setCellStyle(cellCongesPayesAcquisAnneeNMoins1);
        }
    }

    private CellStyle styleColor(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);

        Font font = workbook.createFont();
        font.setColor(IndexedColors.GREEN.getIndex());
        style.setFont(font);
        return style;
    }

    private CellStyle newStyleBorder(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        setBorderColor(style);
        return style;
    }

    private void setBorderColor(CellStyle style) {
        style.setBorderBottom(BorderStyle.MEDIUM);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderTop(BorderStyle.MEDIUM);
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderLeft(BorderStyle.MEDIUM);
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());

        style.setBorderRight(BorderStyle.MEDIUM);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
    }

    private void createHeaderCell(Row headerRow, int columnIndex, String value, CellStyle style) {
        Cell cell = headerRow.createCell(columnIndex);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }

    private void setCellStyle(Cell cell) {
        CellStyle style = newStyleBorder(cell.getSheet().getWorkbook());
        cell.setCellStyle(style);
    }
}
