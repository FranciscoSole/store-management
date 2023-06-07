package logica;

public class Producto {
	private int codigo, stock, stockMin, stockAnt, cant=1;
	private String nombre;
	private double precioUnitario;
	
	public Producto (int codigo, String nombre, double precioUnitario, int stock, int stockMin) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.stock = stockAnt = stock;
		this.stockMin = stockMin;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public int getStock() {
		return stock;
	}

	public int getStockMin() {
		return stockMin;
	}

	public int getStockAnt() {
		return stockAnt;
	}
	
	public int getCant() {
		return cant;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}
	
	public void setStockAnt(int stockAnt) {
		this.stockAnt = stockAnt;
	}
	
	public void setCant(int cant) {
		this.cant = cant;
	}
	
}
