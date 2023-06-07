package logica;
import java.util.ArrayList;

public class Ventas {
	private int posGral = 0; //lo mismo que en catalogo
	private ArrayList<Producto> venta;
	private ArrayList<DetalleDeVenta> detalles;
	private double subTotal = 0;

	public Ventas(){
		venta = new ArrayList<Producto>();
		detalles = new ArrayList<DetalleDeVenta>();
	}
	
	public void agregarProdVenta(Producto producto, int cant) {
		venta.add(producto);
		int pos = venta.size() - 1;
		venta.get(pos).setCant(cant);
	}
	
	public void eliminarProdVenta(int pos) {
		venta.remove(pos);
	}
	
	public void setCant(int pos, int cant) {
		venta.get(pos).setCant(cant);
	}
	
	public void setStock(int pos, int cant) {
		venta.get(pos).setStock(cant);
	}	
	
	public int getCant() {
		return venta.get(posGral).getCant();
	}
	
	public ArrayList<Producto> getVenta(){
		return venta;
	}
	public int getId(int pos) {
		return venta.get(pos).getCodigo();
	}
	
	public int getStock(int pos) {
		return venta.get(pos).getStock();
	}
	
	public int getStockMin(int pos) {
		return venta.get(pos).getStockMin();
	}
	
	public int getStockAnt(int pos) {
		return venta.get(pos).getStockAnt();
	}
	
	public int getPosGral() {
		return posGral;
	}
	
	public double getSubTotal() {
		return subTotal;
	}
	
	public ArrayList<DetalleDeVenta> finalizar() {
		for (Producto i: venta) {
			detalles.add(new DetalleDeVenta(i.getCodigo(), i.getNombre(), i.getCant(), i.getPrecioUnitario()));
			subTotal += i.getPrecioUnitario()*i.getCant();
		}
		
		return detalles;
	}
	
	public int listarProdVenta() {
		int n = 0;
		for (Producto i: venta) {
			System.out.println(n+") Codigo: "+i.getCodigo()+" - Nombre: "+i.getNombre()+" "
								 + "- Cantidad: "+i.getCant()+" " + "- Precio unitario: $"+i.getPrecioUnitario());
			n++;
		}
		
		return n - 1;
	}
	
	public String metodoDePago() {
		String metodo = "";
		
		int opt = Menu.validarInt(1, 3, "Ingrese una opcion: ");
		if(opt == 1) {
			metodo = "efectivo";
		} else if(opt == 2) {
			metodo = "debito";
		} else {
			System.out.println("1) 2 cuotas (6% de interes)");
			System.out.println("2) 3 cuotas (12% de interes)");
			System.out.println("3) 6 cuotas (20% de interes)");
			opt = Menu.validarInt(1, 3, "Ingrese una opcion: ");
			if(opt == 1) {
				metodo = "2 cuotas";
			} else if(opt == 2) {
				metodo = "3 cuotas";
			} else {
				metodo = "6 cuotas";
			}
		}
		
		return metodo;
	}
	
	public boolean existe(int id) {
		boolean existe = false;
		int i = 0;
		
		while(i < venta.size() && !existe) {
			if(id == venta.get(i).getCodigo()) {
				existe = true;
				posGral = i;
			} else {
				i += 1;
			}
		}
		
		return existe;
	}
}