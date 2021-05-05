package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import model.services.RentalService;
import model.services.USATaxService;

public class Main {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Entrada na data de aluguel");
		System.out.println("Modelo do Carro: ");
		String carModel = sc.nextLine();
		System.out.println("Retirada (dd/MM/aaaa HH:ss): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.println("Retorno (dd/MM/aaaa HH:ss): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Entrada do preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.println("Entrada do preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService  rs = new RentalService(pricePerDay, pricePerHour, new USATaxService());//Escolha do local!!
		
		rs.processInvoice(cr);
		System.out.println("FATURA: ");
		System.out.println("Pagamento basico: "+ String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Taxa de imposoto : "+ String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pamento total: "+ String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
		sc.close();

	}

}
