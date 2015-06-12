package gestion.academica.servicio;

import gestion.academica.dao.ArchivoProcesoDao;
import gestion.academica.dao.ProcesoDao;
import gestion.academica.dao.ProductoProcesoDao;
import gestion.academica.modelo.ArchivoProceso;
import gestion.academica.modelo.Proceso;
import gestion.academica.modelo.ProductoProceso;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ProcesoServicio {
	
	@EJB
	private ProcesoDao procesoDao;
	@EJB
	private ArchivoProcesoDao archivoProcesoDao;
	@EJB
	private ProductoProcesoDao productoProcesoDao;
	
	/**
     * Obtener por codigo de proceso
     * @param codigoProceso
     * @return
     */
    public Proceso obtenerPorCodigoProceso(String codigoProceso) {
    	return procesoDao.obtenerPorCodigoProceso(codigoProceso);
    }
    
    public void guardarProceso(Proceso proceso, List<ArchivoProceso> listaArchivosProceso, 
    		List<ProductoProceso> listaProductoProceso){
    	try {
			procesoDao.create(proceso);
			if(listaArchivosProceso.size() > 0){
				for(ArchivoProceso ap : listaArchivosProceso){
					ap.setIdProceso(proceso);
					archivoProcesoDao.create(ap);
				}
			}
			if(listaProductoProceso.size() > 0){
				for(ProductoProceso pr : listaProductoProceso){
					pr.setIdProceso(proceso);
					productoProcesoDao.create(pr);
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
