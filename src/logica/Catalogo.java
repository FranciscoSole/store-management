package logica;

import java.util.ArrayList;
import java.util.Scanner;

public class Catalogo {
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Producto> catalogo;
	private int pos = 0; //variable global de ayudin para no tener que hacer otra funcion similar a existe nomas para sacar el index

	
	public Catalogo() {
		catalogo = new ArrayList <Producto>();
	}
	
	public void cargarProducto() {
		String seguir = "S";
		
		while(seguir.equalsIgnoreCase("S")) {
			System.out.print("Ingrese el codigo del producto: "); int codigo = sc.nextInt(); sc.nextLine();
			if(codigo > 0 && !existe("id", String.valueOf(codigo))) {
				System.out.print("Ingrese el nombre: "); String nombre = sc.nextLine();
				if(!nombre.isEmpty() && !existe("nombre", nombre)) {
					System.out.print("Ingrese la cantidad minima de stock: "); int stockMin = sc.nextInt(); sc.nextLine();
					if(stockMin >= 0) {
						System.out.print("Ingrese la cantidad actual de stock: "); int stock = sc.nextInt(); sc.nextLine();
						if(stock >= stockMin) {
							System.out.print("Ingrese el precio unitario: $"); double precioUnitario = sc.nextDouble(); sc.nextLine();
							if(precioUnitario > 0) {
								catalogo.add(new Producto(codigo, nombre, precioUnitario, stock, stockMin));
								Menu.cant++;
								seguir = Menu.validarStr("Desea cargar otro producto? S/N: ");
							} else {
								System.out.print("Error: el precio tiene que ser mayor a 0\n");
							}
						} else {
							System.out.print("Error: el stock actual tiene que ser mayor o igual a la cantidad minima\n");
						}
					} else {
						System.out.print("Error: el stock minimo tiene que ser mayor o igual a 0\n");
					}
				} else {
					System.out.print("Error: el nombre ingresado ya existe o el campo esta vacio\n");
				} 
			} else {
				System.out.print("Error: el codigo ingresado ya existe o es menor o igual a 0\n");	
			}
			System.out.println();	
		}
		
		Menu.inicio();
	}
	
	public void modificarProducto() {
		System.out.print("Ingrese el codigo del producto a modificar: "); int codigo = sc.nextInt(); sc.nextLine();
		validarId(codigo, "modificar"); printNomb();
		boolean cambio = true;
		
		while(cambio) {
			Menu.modOpt(); 
			int opt = Menu.validarInt(1, 6, "Ingrese una opcion: ");
			if(opt == 1) {
				System.out.print("El codigo actual es '"+catalogo.get(pos).getCodigo()+"'\nIngrese el nuevo codigo para '"+catalogo.get(pos).getNombre()+"': ");
				codigo = sc.nextInt(); sc.nextLine();
				
				while(codigo <= 0 || existe("id", String.valueOf(codigo))) {
					System.out.println("Error: el codigo ingresado ya existe o es menor a 0");
					System.out.print("\nIngrese el nuevo codigo: "); codigo = sc.nextInt(); sc.nextLine();
				}
				
				System.out.println("El codigo de '"+catalogo.get(pos).getNombre()+"' cambiara de '"+catalogo.get(pos).getCodigo()+"' a '"+codigo+"'");
				if(confirmar()) {
					catalogo.get(pos).setCodigo(codigo);
				}
			} else if(opt == 2) {
				System.out.print("Ingrese el nuevo nombre para '"+catalogo.get(pos).getNombre()+"': ");
				String nombre = sc.nextLine();
				
				while(nombre.isEmpty() || existe("nombre", nombre)) {
					System.out.println("Error: complete el campo o ingrese un nombre que no exista");
					System.out.print("\nIngrese el nuevo nombre: "); nombre = sc.nextLine();
				}
				
				System.out.println("El nombre de '"+catalogo.get(pos).getNombre()+"' cambiara a '"+nombre+"'");
				if(confirmar()) {
					catalogo.get(pos).setNombre(nombre);
				}
			} else if(opt == 3) {
				System.out.print("El precio actual es $"+catalogo.get(pos).getPrecioUnitario()+"\nIngrese el nuevo precio para '"+catalogo.get(pos).getNombre()+"': $");
				double precio = sc.nextDouble(); sc.nextLine();
				
				while(precio <= 0) {
					System.out.println("Error: ingrese un precio mayor a 0");
					System.out.print("\nIngrese el nuevo precio: "); precio = sc.nextDouble(); sc.nextLine();
				}

				System.out.println("El precio de '"+catalogo.get(pos).getNombre()+"' cambiara de $"+catalogo.get(pos).getPrecioUnitario()+" a $"+precio);
				if(confirmar()) {
					catalogo.get(pos).setPrecioUnitario(precio);
				} 
			} else if(opt == 4) {
				System.out.print("El stock actual es "+catalogo.get(pos).getStock()+"\nIngrese el nuevo stock para '"+catalogo.get(pos).getNombre()+"': ");
				int stock = sc.nextInt(); sc.nextLine();

				while(stock < 0) {
					System.out.println("Error: ingrese una cantidad de stock mayor a 0");
					System.out.print("\nIngrese el nuevo stock: "); stock = sc.nextInt(); sc.nextLine();
				}
				
				System.out.println("El stock de '"+catalogo.get(pos).getNombre()+"' cambiara de "+catalogo.get(pos).getStock()+" a "+stock);
				if(confirmar()) {
					catalogo.get(pos).setStock(stock);
				} 
			} else if(opt == 5){
				System.out.print("El stock minimo actual es "+catalogo.get(pos).getStockMin()+"\nIngrese el nuevo stock minimo para '"+catalogo.get(pos).getNombre()+"': ");
				int stockMinNuevo = sc.nextInt(); sc.nextLine();
				int stockMinActual = catalogo.get(pos).getStock();
				
				while(stockMinNuevo < 0 || stockMinNuevo > stockMinActual) {
					System.out.println("Error: ingrese una cantidad minima de stock que sea mayor o igual 0 y menor al stock actual del producto ("+stockMinActual+")");
					System.out.print("\nIngrese el nuevo stock: "); stockMinNuevo = sc.nextInt(); sc.nextLine();
				}
				System.out.println("El stock minimo de '"+catalogo.get(pos).getNombre()+"' cambiara de '"+catalogo.get(pos).getStockMin()+"' a '"+stockMinNuevo+"'");
				if(confirmar()) {
					catalogo.get(pos).setStockMin(stockMinNuevo);
				}
			} else {
				Menu.inicio();
			}
			cambio = Menu.modOtro();
		}
	}

	public void eliminarProducto() {
		System.out.print("Ingrese el codigo del producto a eliminar: "); int codigo = sc.nextInt(); sc.nextLine();
		validarId(codigo, "eliminar");
		
		System.out.println("Esta eliminando el producto '"+catalogo.get(pos).getNombre()+"'");
		if(confirmar()) {
			catalogo.remove(pos);
			Menu.cant--;
			if(Menu.cant > 0) {
				Menu.listar();
			} else {
				System.out.println();
				Menu.inicio();
			}
		} else {
			System.out.println("\nEliminacion cancelada");
			Menu.inicio();
		}
	}
		
	public Ventas venderProducto() {
		Ventas venta = new Ventas();
		String seguir = "S";
		
		while(seguir.equalsIgnoreCase("S")) {
			listarCatalogo();
			
			System.out.print("\nIngrese el codigo del producto a vender: "); int codigo = sc.nextInt(); sc.nextLine();
			validarId(codigo, "vender");

			System.out.print("Ingrese la cantidad a vender: "); int cant = sc.nextInt(); sc.nextLine();
			int stock = catalogo.get(pos).getStock();
			int stockMin = catalogo.get(pos).getStockMin(); 
			if(cant <= stock && stock - cant >= stockMin) {
				stock -= cant;
				if(stock == stockMin) {
					System.out.println("Info: con esta venta el producto llegara a su stock minimo permitido ("+stockMin+")"); 
				}
				
				if(venta.existe(codigo)) {
					int posVenta = venta.getPosGral();
					venta.setCant(posVenta, venta.getCant()+cant);
				} else {
					venta.agregarProdVenta(catalogo.get(pos), cant);
				}
				
				catalogo.get(pos).setStock(stock);
				
				} else {
					System.out.println("Error: el stock actual no es suficiente o con esta venta superaria el stock minimo permitido ("+stockMin+")\n"); 
				}
			seguir = Menu.validarStr("Desea agregar otro producto a la venta? S/N: ");
		}
		
		return venta;
	}
	
	public void confProdVenta(Ventas venta) {
		int n = venta.listarProdVenta();
		int opt = 0, optMenu = -1;
		boolean reImprimir = true;
		
		while(optMenu == -1 && Menu.validarStr("\nLos productos cargados son correctos? S/N: ").equalsIgnoreCase("N")) {
			System.out.println("\n1) Modificar venta");
			System.out.println("2) Cancelar venta y empezar otra");
			System.out.println("3) Volver al menu principal\n");
			opt = Menu.validarInt(1, 3, "Ingrese una opcion: ");
			
			if(opt == 1) {
				if(n > 0) {
					System.out.println("1) Modificar cantidad de algun articulo");
					System.out.println("2) Eliminar algun articulo");
				} else {
					System.out.println("1) Modificar cantidad del articulo");
					System.out.println("2) Eliminar articulo");
				}
				
				opt = Menu.validarInt(1, 2, "Ingrese una opcion: ");
				if(opt == 1) {
					System.out.println();
					n = venta.listarProdVenta();
					int stockAnt = venta.getStockAnt(n);
					int stockMin = venta.getStockMin(n);

					if(n > 0) {
						n = Menu.validarInt(0, n, "\nIngrese el codigo del articulo a modificar: ");
					}
					
					System.out.print("Ingrese la nueva cantidad: "); int cant = sc.nextInt(); sc.nextLine();
					while(stockAnt - cant < stockMin) {
						System.out.print("Error: no es posible vender esa cantidad porque el stock quedaria por debajo del stock minimo requerido ("+stockMin+")");
						System.out.print("\nIngrese una cantidad valida: "); cant = sc.nextInt(); sc.nextLine();
					}
					
					if(stockAnt - cant == stockMin) {
						System.out.println("Info: con esta venta el producto llegara a su stock minimo permitido ("+stockMin+")"); 
					}
					
					venta.setCant(n, cant);
					String target = String.valueOf(venta.getId(n));
					updateStock(cant, target);
					updateStockAnt(venta);
				} else {
					reImprimir = false;
					optMenu = 2;
					
					if(n>0) {
						System.out.print("Ingrese el codigo del producto a eliminar de la venta: "); n = sc.nextInt(); sc.nextLine();
						venta.eliminarProdVenta(n);
						reImprimir = true;
					} else {
						cancelarVenta(venta);
						break;
					}
				}
			} else {
				optMenu = 2;
				reImprimir = false;
				cancelarVenta(venta);
			}
			
			if(reImprimir) {
				System.out.println("\n--------- Productos seleccionados para esta venta ---------");
				n = venta.listarProdVenta();
			}
		} 
		
		if(optMenu != -1) {
			if(optMenu == 2) {
				Menu.vender();
			} else {
				System.out.println();
				Menu.inicio();
			}
		}
	}
	
	public void listarCatalogo() {
		System.out.println("--------- Lista de productos disponibles en el catalogo ---------");
		for (Producto i: catalogo) {
			if(i.getStock() == i.getStockMin()) {
				System.out.print("\u001B[33m");
			} else {
				System.out.print("\u001B[0m");
			}
			System.out.println("Codigo: "+i.getCodigo()+" - Nombre: "+i.getNombre()+" "
								 + "- Stock: "+i.getStock()+" - Stock minimo: "+ i.getStockMin()+" "
								 + "- Precio unitario: $"+i.getPrecioUnitario());
		}
		System.out.print("\u001B[0m");
	}
	
	public void validarId(int id, String msg) {
		while(id <= 0 || !existe("id", String.valueOf(id))) {
			System.out.println("Error: ingrese un codigo valido");
			System.out.print("\nIngrese el codigo del producto a "+msg+": ");
			id = sc.nextInt(); sc.nextLine();
		}
	}

	public void salir() {
		sc.close();
		System.exit(0);
	}

	public void printNomb() {
		System.out.println("Selecciono el producto llamado: "+catalogo.get(pos).getNombre());
	}
		
	public boolean confirmar() {
		if(Menu.validarStr("\nConfirmar accion? S/N: ").equalsIgnoreCase("S")) {
			return true;
		} else {
			System.out.println("\nCambio cancelado");
			return false;
		}
		
	}
	
	public boolean existe(String change, String check) {
		boolean id = change.equalsIgnoreCase("id");
		boolean existe = false;
		int i = 0;
		
		while(i < catalogo.size() && !existe) {
			if((id && catalogo.get(i).getCodigo() == Integer.parseInt(check)) || (!id && catalogo.get(i).getNombre().equalsIgnoreCase(check))) {
				existe = true;
				if(id) {
					pos = i;
				}
			} else {
				i += 1;
			}
		}
		return existe;
	}

	public void cancelarVenta(Ventas venta) {
		ArrayList<Producto> lista = venta.getVenta();
		for (Producto i: lista) {
			existe("id", String.valueOf(i.getCodigo()));
			catalogo.get(pos).setStock(catalogo.get(pos).getStockAnt());
		}
	}
	
	public void updateStock(int newStock, String id) {
		existe("id", id);
		catalogo.get(pos).setStock(catalogo.get(pos).getStockAnt() - newStock);
	}

	public void updateStockAnt(Ventas venta) {
		ArrayList<Producto> lista = venta.getVenta();
		for (Producto i: lista) {
			existe("id", String.valueOf(i.getCodigo()));
			catalogo.get(pos).setStockAnt(catalogo.get(pos).getStock());
		}
	}
}
