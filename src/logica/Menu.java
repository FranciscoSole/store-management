package logica;

import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
	private static Scanner sc = new Scanner(System.in);
	private static Catalogo catalogo = new Catalogo();
	private static Factura factura = new Factura();
	private static ArrayList<Factura> facturasTotales = new ArrayList<Factura>();
	private static boolean hayProductos;
	public static int cant = 0, cantFact = 0;

	public static void main(String[] args) {
		inicio();
	}
	
	public static void inicio() {
		int opt;
		System.out.println("--------- Bienvenido al sistema ---------");
		System.out.println("1) Agregar productos");
		
		if(cant > 0) {
			hayProductos = true;
			System.out.println("2) Listar productos");
			System.out.println("3) Vender productos");
			if(cantFact > 0) {
				System.out.println("4) Listar facturas");
				System.out.println("5) Salir\n"); opt = validarInt(1, 5, "Ingrese una opcion: ");
			} else {
				System.out.println("4) Salir\n"); opt = validarInt(1, 4, "Ingrese una opcion: ");
			}
		} else {
			hayProductos = false;
			if(cantFact > 0) {
				System.out.println("2) Listar facturas");
				System.out.println("3) Salir\n"); opt = validarInt(1, 3, "Ingrese una opcion: ");
			} else {
				System.out.println("2) Salir\n"); opt = validarInt(1, 2, "Ingrese una opcion: ");
			}
		}
		
		if(opt == 1) {
			agregar();
		} else {			
			if((hayProductos && opt == 4 && cantFact == 0) || cantFact > 0 && opt == 5 || !hayProductos) {
				salir();
			}
			
			if((hayProductos && opt == 4 && cantFact > 0) || !hayProductos && opt == 2) {
				factura.listarFacturas(facturasTotales);
				inicio();
			}
			
			if(hayProductos && opt == 2) {
				listar();
			}
			
			if(hayProductos && opt == 3) {
				vender();
			}
		}
		
		System.out.println();
		inicio();
	}
	
	public static void agregar() {
		System.out.println("--------- Bienvenido al sistema de carga de productos ---------");
		System.out.println("\n1) Iniciar carga");
		System.out.println("2) Volver al menu principal\n");
		int opt = validarInt(1, 2, "Ingrese una opcion: ");
		
		if(opt == 1) {
			catalogo.cargarProducto();
		} else {
			System.out.println();
			inicio();
		}
	}
	
	public static void vender(){
		System.out.print("--------- Bienvenido al panel de venta ---------");
		System.out.println("\n1) Iniciar venta");
		System.out.println("2) Volver al menu principal\n");
		int opt = validarInt(1, 2, "Ingrese una opcion: ");
		if(opt == 1) {
			Ventas venta = catalogo.venderProducto();
			System.out.print("\n--------- Productos seleccionados para esta venta ---------\n");
			catalogo.confProdVenta(venta);
			cantFact++;
			metodoVenta();
			Factura factura = new Factura(venta.finalizar(), venta.metodoDePago(), venta.getSubTotal());
			facturasTotales.add(factura);
		}
		inicio();
	}
		
	private static void metodoVenta() {
		System.out.println("\n--------- Seleccione el metodo de pago ---------\n");
		System.out.println("1) Efectivo (10% descuento)");
		System.out.println("2) Debito");
		System.out.println("3) Credito (2, 3 y 6 cuotas CON interes)\n");
	}
	
	public static void listar() {
		if(hayProductos) {
			catalogo.listarCatalogo();
			System.out.println("\n1) Modificar un producto");
			System.out.println("2) Eliminar un producto");
			System.out.println("3) Volver al menu principal\n");
			int opt = validarInt(1, 3, "Ingrese una opcion: ");
			
			if(opt == 1) {
				modificar();
			} else if(opt == 2) {
				borrar();
			} else {
				inicio();
			}
		} else {
			inicio();
		}
	} 
		
	public static void modificar() {
		System.out.println("--------- Bienvenido al panel de modificacion ---------\n");
		catalogo.modificarProducto();
	}
	
	public static void modOpt() {
		System.out.println("\n1) Modificar codigo");
		System.out.println("2) Modificar nombre");
		System.out.println("3) Modificar precio unitario");
		System.out.println("4) Modificar stock actual");
		System.out.println("5) Modificar stock minimo");
		System.out.println("6) Volver al menu principal\n");
	}
	
	public static boolean modOtro() {
		return(validarStr("Desea modificar otro detalle del producto? S/N: ").equalsIgnoreCase("S"));
	}
	
	public static void borrar() {
		System.out.println("--------- Bienvenido al panel de eliminacion ---------\n");
		catalogo.eliminarProducto();
	}
	
	public static void salir() {
		System.out.println("--------- Gracias por usar el sistema ---------");
		sc.close();
		catalogo.salir();
	}
		
	public static int validarInt(int min, int max, String msg) {
		System.out.print(msg); int opt = sc.nextInt(); sc.nextLine();
		
		while(opt < min || opt > max) {
			System.out.println("Error: ingrese un valor entre "+min+" y "+max);
			System.out.print(msg); opt = sc.nextInt(); sc.nextLine(); 
		}
		
		System.out.println();
		return opt;
	}
	
	public static String validarStr(String msg) {
		String seguir;
		
		System.out.print(msg); seguir = sc.nextLine();
		while(!seguir.equalsIgnoreCase("N") && !seguir.equalsIgnoreCase("S")) {
			System.out.println("Error: ingrese 'S' o 'N'\n");
			System.out.print(msg); seguir = sc.nextLine();
		}
		
		return seguir;
	}
}
