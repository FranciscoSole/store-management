package logica;

public class DetalleDeVenta {
	private int idProd, cantProd;
	private double precioUnitario;
	private String nombreProd;
	
	public DetalleDeVenta(int id, String nombre, int cantProd, double precioUnitario) {
		this.idProd = id;
		this.nombreProd = nombre;
		this.cantProd = cantProd;
		this.precioUnitario = precioUnitario;
	}
	
	public int getIdProd() {
		return idProd;
	}
	
	public String getNombreProd() {
		return nombreProd;
	}
	
	public int getCantProd() {
		return cantProd;
	}
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setIdProd(int idProd) {
		this.idProd = idProd;
	}
	
	public void setNombre(String nombre) {
		this.nombreProd = nombre;
	}
	
	public void setCantProd(int cantProd) {
		this.cantProd = cantProd;
	}
	
	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
}