package hitne.web.rest;


import hitne.domain.Hitne;
import hitne.domain.viewHitnePonudjaci;
import hitne.repository.viewHitnePonudjaciRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.type.OrientationEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterJob;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = ("*"))
@RestController
@RequestMapping("/report")
public class Pdf {

    @Autowired
    ApplicationContext context;



    @Autowired
    hitne.repository.viewHitnePonudjaciRepository viewHitnePonudjaciRepository;

    @Autowired
    hitne.repository.HitneRepository hitneRepository;


    @GetMapping(path = "/zahtjev/{brojpokretanja}")
    @ResponseBody

    public void getPdfZahtjev(HttpServletResponse response, @PathVariable Long brojpokretanja) throws Exception {

        Resource resource = context.getResource("classpath:reports/repZahtjev.jrxml");

        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();


         List<viewHitnePonudjaci> hitna= (List<viewHitnePonudjaci>) viewHitnePonudjaciRepository.findByBrojpokretanja(brojpokretanja);

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(hitna);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @GetMapping(path = "/pokretanje/{brojpokretanja}")
    @ResponseBody
    public void getPdfPokretanje(HttpServletResponse response, @PathVariable Long brojpokretanja) throws Exception {

        Resource resource = context.getResource("classpath:reports/repPokretanje.jrxml");

        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();


        List<viewHitnePonudjaci> viwhitna = (List<viewHitnePonudjaci>) viewHitnePonudjaciRepository.findByBrojpokretanja(brojpokretanja);

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(viwhitna);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

    @GetMapping(path = "/odluka/{brojpokretanja}")
    @ResponseBody
    public void getPdfOdluka(HttpServletResponse response, @PathVariable Long brojpokretanja) throws Exception {

        Resource resource = context.getResource("classpath:reports/repOdluka.jrxml");

        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();


        List<viewHitnePonudjaci> viwhitna= (List<viewHitnePonudjaci>) viewHitnePonudjaciRepository.findByBrojpokretanja(brojpokretanja);

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(viwhitna);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }



    @GetMapping(path = "/print")

    public void getPrint(HttpServletResponse response) throws Exception {
              Resource resource = context.getResource("classpath:reports/hitne.jrxml");

        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        Map<String, Object> params = new HashMap<>();


        List<Hitne> hitna = (List<Hitne>) hitneRepository.findAll();

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(hitna);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);

              //Media Type

//        response.setContentType(MediaType.APPLICATION_PDF_VALUE);

        //Export PDF Stream
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//
//        PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//        printRequestAttributeSet.add(new Copies(2));
        jasperPrint.setPageHeight(100);
        jasperPrint.setPageWidth(80);
        jasperPrint.setOrientation(OrientationEnum.LANDSCAPE);
        JasperPrintManager.printReport(jasperPrint, false);
    }


}
