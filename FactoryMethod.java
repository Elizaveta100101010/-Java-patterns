import javax.swing.*;

public class FactoryMethod {
	
	public static void main(String[] args) {
		CTransport ct =  getTransportbyName("Monowheel");  //¬ыбор транспорта
		Transport t = ct.chooseTransport();
		t.ChoosingTransport();
	   }
	public static CTransport getTransportbyName(String ct) {
		if (ct.equals("Bicycle"))
			return new CTBicycle();
		if (ct.equals("ElectricBicycle"))
			return new CTElectricBicycle();
		if (ct.equals("Monowheel"))
			return new CTMonowheel();    //ѕроверка на соответствие каталогу транспорта
		if (ct.equals("Scooter"))
			return new CTScooter();
		if (ct.equals("Car"))
			return new CTCar();
		if (ct.equals("Foot"))
			return new CTFoot();
		throw new RuntimeException("There is no such transport: "+ ct);
		
	}
	}

interface Transport{
	void ChoosingTransport(); // интерфейс с методом выбора транспорта
}

class TBicycle  implements Transport{
	public void ChoosingTransport() {
		JOptionPane.showMessageDialog(null, "You have chosen a bicycle. Good Delivery!");
	}
}

class TElectricBicycle implements Transport{
	public void ChoosingTransport() {
		JOptionPane.showMessageDialog(null, "You have chosen an electric bicycle. Good Delivery!"); // виды транспорта
	}
	
}

class TMonowheel implements Transport{
	public void ChoosingTransport() {
		JOptionPane.showMessageDialog(null, "You have chosen a monowheel. Good Delivery!");
	}
	
}

class TScooter implements Transport{
	public void ChoosingTransport() {
		JOptionPane.showMessageDialog(null, "You have chosen a scooter. Good Delivery!");
	}
	
}

class TCar implements Transport{
	public void ChoosingTransport() {
		JOptionPane.showMessageDialog(null, "You have chosen a car. Good Delivery!");
	}
	
}

class TFoot implements Transport{
	public void ChoosingTransport() {
		JOptionPane.showMessageDialog(null, "You have chosen a walking delivery. Good Delivery!");
	}
	
}

interface CTransport{
	Transport chooseTransport();   //интерфейс с фабричным методом
}


class CTBicycle implements CTransport{
public Transport chooseTransport() {
	return new TBicycle();
			}
}

class CTElectricBicycle implements CTransport{
public Transport chooseTransport() {
	return new TElectricBicycle();
			}
}											//выбор транспорта

class CTMonowheel implements CTransport{
public Transport chooseTransport() {
	return new TMonowheel();
			}
}
class CTScooter implements CTransport{
public Transport chooseTransport() {
	return new TScooter();
			}
}
class CTCar implements CTransport{
public Transport chooseTransport() {
	return new TCar();
			}
}
class CTFoot  implements CTransport{
public Transport chooseTransport() {
	return new TFoot();
			}
}




