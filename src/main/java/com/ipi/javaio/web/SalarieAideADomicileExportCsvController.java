package com.ipi.javaio.web;

import com.ipi.javaio.export.SalarieAideADomicileExportCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("export")
public class SalarieAideADomicileExportCsvController {

    @Autowired
    private final SalarieAideADomicileExportCsvService salarieAideADomicileExportCsvService;

    public SalarieAideADomicileExportCsvController(SalarieAideADomicileExportCsvService salarieAideADomicileExportCsvService) {
        this.salarieAideADomicileExportCsvService = salarieAideADomicileExportCsvService;
    }

    @GetMapping("/salarieAideADomicile/csv")
    public void salarieAideADomicileMoisCsv(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois.csv\"");
        PrintWriter writer = response.getWriter();
        this.salarieAideADomicileExportCsvService.export(writer);
    }

    @GetMapping("/salarieAideADomicile/csv/{salarieId}")
    public void salarieAideADomicileCsv(@PathVariable("salarieId") Long salarieId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois_" + salarieId + ".csv\"");
        PrintWriter writer = response.getWriter();
        this.salarieAideADomicileExportCsvService.export(writer, salarieId);
    }

}
