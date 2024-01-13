package com.ipi.javaio.web;

import com.ipi.javaio.export.SalarieAideADomicileExportCsvService;
import com.ipi.javaio.export.SalarieAideADomicileExportXlsxService;
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

public class SalarieAideDomicileExportXlsxController {
    @Autowired
    private final SalarieAideADomicileExportXlsxService salarieAideADomicileExportXlsxService;

    public SalarieAideDomicileExportXlsxController(SalarieAideADomicileExportXlsxService salarieAideADomicileExportXlsxService) {
        this.salarieAideADomicileExportXlsxService = salarieAideADomicileExportXlsxService;
    }

    @GetMapping("/salarieAideADomicile/xlsx")
    public void salarieAideADomicileXlsx(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.xlsx\"");
        this.salarieAideADomicileExportXlsxService.export(response.getOutputStream());
    }

    @GetMapping("/salarieAideADomicile/xlsx/{salarieId}")
    public void salarieAideADomicileXlsx(@PathVariable("salarieId") Long salarieId,
                                        HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xlsx");
        response.setHeader("Content-Disposition", "attachment; filename=\"export" + salarieId + ".xlsx\"");
       // this.salarieAideADomicileExportXlsxService.export( salarieId);
    }

}
