package com.ipi.javaio.web;

import com.ipi.javaio.export.SalarieAideADomicileMoisExportXlsxService;
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
public class SalarieAideADomicileMoisExportXlsxController {

    @Autowired
    private final SalarieAideADomicileMoisExportXlsxService salarieAideADomicileMoisExportXlsxService;

    public SalarieAideADomicileMoisExportXlsxController(SalarieAideADomicileMoisExportXlsxService salarieAideADomicileMoisExportXlsxService) {
        this.salarieAideADomicileMoisExportXlsxService = salarieAideADomicileMoisExportXlsxService;
    }

    @GetMapping("/salarieAideADomicileMois/xlsx")
    public void salarieAideADomicileMoisXlsx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois.xlsx\"");
        this.salarieAideADomicileMoisExportXlsxService.export(response.getOutputStream());
    }

    /**
     * PAS ENCORE IMPLEMENTEE
     * @param salarieId
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/salarieAideADomicileMois/xlsx/{salarieId}")
    public void salarieAideADomicileMoisXlsx(@PathVariable("salarieId") Long salarieId,
            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois_" + salarieId + ".xlsx\"");
        /// TODO this.salarieAideADomicileMoisExportXlsxService.export(response.getOutputStream(), salarieId);
    }

    /**
     * PAS ENCORE IMPLEMENTEE
     * @param salarieId
     * @param annee
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("/salarieAideADomicileMois/xlsx/{salarieId}/{annee}")
    public void salarieAideADomicileMoisXlsx(@PathVariable("salarieId") Long salarieId, @PathVariable("annee") int annee,
                                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=\"export_mois" + salarieId + "_" + annee + ".xlsx\"");
        /// TODO this.salarieAideADomicileMoisExportXlsxService.export(response.getOutputStream(), salarieId, annee);
    }

}
