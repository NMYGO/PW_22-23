package factory_reserva;

public abstract class Reserva {
		// Factory methods for each product
		
		public abstract RInfantil creaRInf();
		
		public abstract RFamiliar creaRFam();
		
		public abstract RAdulto creaRAdu();
}
