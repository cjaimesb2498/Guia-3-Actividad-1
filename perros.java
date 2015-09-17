
public class Perros {
	//Enummeracion para la localidad
	public enum Localidades{
		Chapinero,
		Suba,
		Teusaquillo,
		Usaquen,
		NA //No aplica
	}
	
	//Propiedades
	public Localidades Localidad;
	public String Nombre;
	public String Raza;
	public int Cedula;
	public String NombreDuenio;
	public String Telefono;
	public byte Dia;
	
	public String MostrarDia(){
		switch(Dia){
			case 1:
				return "Lunes";
			case 2:
				return "Martes";
			case 3:
				return "Miercoles";
			case 4:
				return "Jueves";
			case 5:
				return "Viernes";
			case 6:
				return "Sabado";
			case 7:
				return "Domingo";
			default:
				return "Dia invalido";
		}
	}
	public String MostrarLocalidad(){
		switch(Localidad){
			case Teusaquillo:
				return "Teusaquillo";
			case Usaquen:
				return "Usaquen";
			case Suba:
				return "Suba";
			case Chapinero:
				return "Chapinero";
			default:
				return "Localidad no existe";
		}
	}
}
