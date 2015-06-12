package gestion.academica.dto;

import gestion.academica.modelo.ProductoProveedor;
import gestion.academica.modelo.Proveedor;
import gestion.academica.modelo.Telefono;

import java.util.List;

public class ProveedorDTO {
	
	private Proveedor proveedor;
	private List<Telefono> telefonos;
	private List<ProductoProveedor> productos;
	
	public ProveedorDTO(){}
	
	public ProveedorDTO(Proveedor proveedor, List<Telefono> telefonos,
			List<ProductoProveedor> productos) {
		this.proveedor = proveedor;
		this.telefonos = telefonos;
		this.productos = productos;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public List<Telefono> getTelefonos() {
		return telefonos;
	}
	
	public void setTelefonos(List<Telefono> telefonos) {
		this.telefonos = telefonos;
	}
	
	public List<ProductoProveedor> getProductos() {
		return productos;
	}
	
	public void setProductos(List<ProductoProveedor> productos) {
		this.productos = productos;
	}
	
	

}
