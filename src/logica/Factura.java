package logica;
import java.util.ArrayList;

public class Factura {
	private int nro = Menu.cantFact;
	private double subtotal, total;
	private String medioDePago;
	private ArrayList<DetalleDeVenta> detalle;
	
	public Factura() {
		detalle = new ArrayList<DetalleDeVenta>();
	}
	
	public Factura(ArrayList<DetalleDeVenta> detalles, String medioDePago, double subtotal) {
		this.medioDePago = medioDePago;
		this.subtotal = subtotal;
		this.total = calcularTotal(subtotal, medioDePago);
		detalle = new ArrayList<DetalleDeVenta>();
		detalle.addAll(detalles);
		listarFactura();
	}
	
	public int getNro() {
		return nro;
	}
	
	public String getMedioDePago() {
		return medioDePago;
	}
	
	public ArrayList<DetalleDeVenta> getDetalle() {
		return detalle;
	}

	public double getSubtotal() {
		return subtotal;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setNro(int nro) {
		this.nro = nro;
	}
	
	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public double calcularTotal(double subTotal, String metodoDePago) {
		if (metodoDePago == "efectivo"){
			return subtotal*0.9;
		} else if (metodoDePago == "debito") {
		    return subtotal;
		} else {
		    if (metodoDePago == "2 cuotas") {
		        return subtotal*1.06;
		    } else if (metodoDePago == "3 cuotas") {
		    	return subtotal*1.12;
		    } else {
		        return subtotal*1.2;
		    }
		}
	}

	public void listarFactura() {
		System.out.println("=======================");
		System.out.println("NroFactura: " + getNro());
	    System.out.println("Medio de pago: " + getMedioDePago());
	    for(int i = 0; i < detalle.size(); i++) {
	    	if(i==0) {
			    System.out.println("-----------------------");
	    	}
		    System.out.println("IDProducto: " + detalle.get(i).getIdProd());
		    System.out.println("Producto: " + detalle.get(i).getNombreProd());
		    System.out.println("Precio unitario: " + detalle.get(i).getPrecioUnitario());
		    System.out.println("Cantidad: " + detalle.get(i).getCantProd());
		    System.out.println("-----------------------");
	    }
	    System.out.println("Subtotal: " + getSubtotal());
	    System.out.println("Total: " + getTotal());
		System.out.println("=======================");
		System.out.println();
	}
	
	public void listarFacturas(ArrayList<Factura> facturas) {
		for(int i = 0; i < facturas.size(); i++) {
			System.out.println("=======================");
			System.out.println("NroFactura: " + facturas.get(i).getNro());
		    System.out.println("Medio de pago: " + facturas.get(i).getMedioDePago());
		    for(int j = 0; j < facturas.get(i).detalle.size(); j++) {
		    	if(j==0) {
				    System.out.println("-----------------------");
		    	}
			    System.out.println("IDProducto: " + facturas.get(i).detalle.get(j).getIdProd());
			    System.out.println("Producto: " + facturas.get(i).detalle.get(j).getNombreProd());
			    System.out.println("Precio unitario: " + facturas.get(i).detalle.get(j).getPrecioUnitario());
			    System.out.println("Cantidad: " + facturas.get(i).detalle.get(j).getCantProd());
			    System.out.println("-----------------------");
		    }
		    System.out.println("Subtotal: " + facturas.get(i).getSubtotal());
		    System.out.println("Total: " + facturas.get(i).getTotal());
			System.out.println("=======================");
		}
		System.out.println();
	}    
		
}

		
		
		
	
	


