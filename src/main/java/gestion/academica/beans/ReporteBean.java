package gestion.academica.beans;

import gestion.academica.utilitario.Utilitario;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@ViewScoped
public class ReporteBean extends Utilitario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("static-access")
	public void imprimirPDF(List<Object> listaReporte, String nombreArchivo, Map<String, Object> pars) {
        JRBeanCollectionDataSource dataSource;
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext
                .getResponse();
        try {
            dataSource = new JRBeanCollectionDataSource(listaReporte);
            String url = getRequest().getSession().getServletContext()
                    .getRealPath("/reportes/" + nombreArchivo + ".jrxml");
            jasperReport = JasperCompileManager.compileReport(url);
            jasperPrint = JasperFillManager.fillReport(jasperReport, pars,
                    dataSource);
            byte[] bites = JasperExportManager.exportReportToPdf(jasperPrint);
            JasperExportManager.exportReportToPdfStream(jasperPrint,
                    response.getOutputStream());

            response.setHeader("Content-disposition",
                    "attachment; filename=reporte.pdf");
            response.setContentLength(bites.length);
            facesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
