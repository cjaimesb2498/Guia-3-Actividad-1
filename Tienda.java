import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Tienda {
	static ArrayList<Perros> baseDatos; 
	public static void main(String[] args) {
		// Encontre que estas clases permiten el manejo de muchos elementos y simulan una base de datos (ArrayList)
		baseDatos = new ArrayList<Perros>();
		//1. Se cargaran 10 registros por defecto
		CargarBaseDdatosPerros();
		//System.out.println("El sistema cargo 10 perros por defecto.");
		//2. Metodo que carga las opciones de menu
		MostrarMenu();
	}
	public static void MostrarMenu(){
		int opcion = 0;
		while(opcion!= 4){
			System.out.println("\n\n********************************************************");
			System.out.println("********************************************************");
			System.out.println("** Colegio Canino Cachorros Mimados** ");
			System.out.println("** Opciones de menu");
			System.out.println("** 1 Registre un perro");
			System.out.println("** 2 Imprima perros existentes");
			System.out.println("** 3 Imprima perros agrupados por localidad");
			System.out.println("** 4 Salir");
			System.out.println("********************************************************");
			System.out.println("********************************************************");
			try{
				BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
				String line=buffer.readLine();
				System.out.println("\n\n\n");
				opcion = Integer.parseInt(line);
				switch(opcion){
					case 1:
						RegistrarPerro();
						break;
					case 2:
						ListarPerros();
						break;
					case 3:
						ListarAgrupado();
						break;
					case 4:
						opcion = ConfirmacionSalir();
						break;
				}
			}
			catch(Exception e){
				opcion = 5;
				System.out.println("Digite una opción correcta. Números del 1 al 4.");
			}
		}
	}
	public static void ListarPerros(){
		//Recorre la lista de valores de la base de datos y los imprime en pantalla
		for(int i=0;i<baseDatos.size();i++){
			//Perro actual
			Perros perro = baseDatos.get(i);
			int indice = i+1;
			System.out.print(indice + ". ");
			//Metodo que imprime la informacion del perro
			ImprimirPerro(perro);
		}
		
	}
	public static int ConfirmacionSalir(){
		try{
			System.out.println("¿Esta seguro que quiere salir del menu? [Y para si]");
			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
			String line=buffer.readLine();
			if(line!=null && line.toLowerCase().equals("y")){
				//El valor 4 termina el programa
				return 4;
			}
			System.out.println("["+line+"]");
			//Retornando 5 el programa continuara
			return 5;
	
		}
		catch(Exception e){
			//Retornando 5 el programa continuara
			return 5;
		}
	}
	public static void ListarAgrupado(){
		//1. Se duplica la lista en otro arreglo para ordenarlo y no alterar el original
		ArrayList<Perros> datos = Clonar(baseDatos);
		//2. Se ordenan los datos segun su localidad
		Collections.sort(datos, new Comparator<Perros>() {
	        public int compare(Perros  perro1, Perros  perro2)
	        {
	            return  perro1.Localidad.compareTo(perro2.Localidad);
	        }
	    });
		//3. Se muestran en pantalla los valores ordenados
		Perros.Localidades locaAct = Perros.Localidades.NA;
		int indice = 1;
		for(int i=0;i<datos.size();i++){
			//Perro actual
			Perros perro = datos.get(i);
			if(locaAct != perro.Localidad){
				System.out.println("::: LOCALIDAD --> " + perro.MostrarLocalidad() + ":::");
				indice = 1;
				locaAct = perro.Localidad;
			}
			System.out.print(indice + ". ");
			//Metodo que imprime la informacion del perro
			ImprimirPerro(perro);
			indice+=1;
		}
	}
	public static void RegistrarPerro(){
		Perros perro = new Perros();
		baseDatos.add(perro);
		
		int temp =0;
		System.out.println("Registre información del perro:");
		
		System.out.println("Nombre del Perro:");
		perro.Nombre = LeerTexto();
		
		System.out.println("Nombre del dueño:");
		perro.NombreDuenio = LeerTexto();

		System.out.println("Raza del perro:");
		perro.Raza = LeerTexto();
		
		System.out.println("Telefono:");
		perro.Telefono = LeerTexto();
		
		System.out.println("Cedula:");
		perro.Cedula = LeerEntero();
		
		
		System.out.println("Dia [1=Lunes, 2=Martes, 3=Miercoles, 4=Jueves, 5=Viernes, 6=Sabado, 7=Domingo]:");
		temp = 0;
		while (temp == 0){
			temp = LeerEntero();
			if(temp < 1 || temp > 7){
				System.out.println(" DEBE SER UN VALOR ENTRE 1 a 7 ");
				temp=0;
			}else{
				perro.Dia = (byte)temp; 
			}
		}
		
		System.out.println("Localidad [1=Chapinero, 2=Teusaquillo, 3=Usaquen, 4=Suba]:");
		temp = 0;
		while (temp == 0){
			temp = LeerEntero();
			if(temp < 1 || temp > 4){
				System.out.println(" Digite un valor de 1 a 4  [1=Chapinero, 2=Teusaquillo, 3=Usaquen, 4=Suba]");
				temp=0;
			}else{
				switch(temp){
					case 1:
						perro.Localidad = Perros.Localidades.Chapinero;
						break;
					case 2:
						perro.Localidad = Perros.Localidades.Teusaquillo;
						break;
					case 3:
						perro.Localidad = Perros.Localidades.Usaquen;
						break;
					case 4:
						perro.Localidad = Perros.Localidades.Suba;
						break;
				}
			}
		}
		
		System.out.println("Registre información del perro: ");
	}
	public static ArrayList<Perros> Clonar(ArrayList<Perros> list) {
		ArrayList<Perros> clonar = new ArrayList<Perros>(baseDatos.size());
		for(int i=0;i<baseDatos.size();i++){
			clonar.add(baseDatos.get(i));
		}
	    return clonar;
	}
	public static void ImprimirPerro(Perros perro){
		System.out.println(" \tNombre:" + perro.Nombre + ". \tRaza:" + perro.Raza + ". \tDia de asistencia:" + perro.MostrarDia());
		System.out.println("  \tDueño:" + perro.NombreDuenio + ". \tTelefono:" + perro.Telefono + ". \tCedula:" + perro.Cedula + ". \tLocalidad:" + perro.MostrarLocalidad());
		System.out.println("   ");
	}
	public static String LeerTexto(){
		String line = "";
		try{
			BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
			line=buffer.readLine();
		}
		catch(Exception ed){
			line = "";
		}
		return line;
	}
	public static int LeerEntero(){
		String line = "";
		boolean exitoso = false; 
		int opcion = 0;
		while(!exitoso){
			try{
				BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
				line=buffer.readLine();
				opcion = Integer.parseInt(line);
				exitoso=true;
			}
			catch(Exception ed){
				System.out.println("Seleccion invalida, intente nuevamente");
				exitoso=false;
			}
		}
		return opcion;
	}
	public static void CargarBaseDdatosPerros(){
		//Base de datos de 10 perros
		//--1
		Perros perro = new Perros();
		perro.Cedula = 80123654;
		perro.Dia=1;
		perro.Localidad=Perros.Localidades.Chapinero;
		perro.Nombre="Jacob";
		perro.NombreDuenio="Luis Gomez";
		perro.Raza="Chow Chow";	
		perro.Telefono="2286536";
		baseDatos.add(perro);
		//--2
		perro = new Perros();
		perro.Cedula = 123456789;
		perro.Dia=4;
		perro.Localidad=Perros.Localidades.Suba;
		perro.Nombre="Millu";
		perro.NombreDuenio="Tin tin";
		perro.Raza="Fox terrier";	
		perro.Telefono="1234567";
		baseDatos.add(perro);
		//--3
		perro = new Perros();
		perro.Cedula = 987534;
		perro.Dia=4;
		perro.Localidad=Perros.Localidades.Usaquen;
		perro.Nombre="Godzilla";
		perro.NombreDuenio="Yessica Lopez";
		perro.Raza="Chiguagua";	
		perro.Telefono="8123073";
		baseDatos.add(perro);
		//--4
		perro = new Perros();
		perro.Cedula = 548912;
		perro.Dia=2;
		perro.Localidad=Perros.Localidades.Suba;
		perro.Nombre="Gato";
		perro.NombreDuenio="Carlos Niño";
		perro.Raza="Huskye";	
		perro.Telefono="7642112";
		baseDatos.add(perro);
		//--5
		perro = new Perros();
		perro.Cedula = 1234567;
		perro.Dia=3;
		perro.Localidad=Perros.Localidades.Chapinero;
		perro.Nombre="Ares";
		perro.NombreDuenio="Maria Mercedes Velandia";
		perro.Raza="Cooker spaniel";	
		perro.Telefono="6783543";
		baseDatos.add(perro);
		//--6
		perro = new Perros();
		perro.Cedula = 55661122;
		perro.Dia=6;
		perro.Localidad=Perros.Localidades.Usaquen;
		perro.Nombre="Hans";
		perro.NombreDuenio="David Gomez";
		perro.Raza="Pastor Aleman";	
		perro.Telefono="3429089";
		baseDatos.add(perro);
		//--7
		perro = new Perros();
		perro.Cedula = 6549090;
		perro.Dia=1;
		perro.Localidad=Perros.Localidades.Chapinero;
		perro.Nombre="Lucky";
		perro.NombreDuenio="Dana Garcia";
		perro.Raza="French puddle";	
		perro.Telefono="4357790";
		baseDatos.add(perro);
		//--8
		perro = new Perros();
		perro.Cedula = 4612323;
		perro.Dia=2;
		perro.Localidad=Perros.Localidades.Suba;
		perro.Nombre="Yankee";
		perro.NombreDuenio="Mery Naranjo";
		perro.Raza="Pitbull";	
		perro.Telefono="3002345590";
		baseDatos.add(perro);
		//--9
		perro = new Perros();
		perro.Cedula = 53054195;
		perro.Dia=3;
		perro.Localidad=Perros.Localidades.Teusaquillo;
		perro.Nombre="Wallie";
		perro.NombreDuenio="Angelica Cruz";
		perro.Raza="Criollo";	
		perro.Telefono="3018213344";
		baseDatos.add(perro);
		//--10
		perro = new Perros();
		perro.Cedula = 52450901;
		perro.Dia=5;
		perro.Localidad=Perros.Localidades.Teusaquillo;
		perro.Nombre="Huesos";
		perro.NombreDuenio="Carlos Ortiz";
		perro.Raza="Dalmata";	
		perro.Telefono="3184563241";
		baseDatos.add(perro);
	}
}
