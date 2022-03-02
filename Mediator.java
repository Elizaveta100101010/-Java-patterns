import java.util.List;
import java.util.ArrayList;
public class Mediator {
public static void main(String[] args) {
	System.out.println("[ЧАТ СОТРУДНИКОВ КУРЬЕРСКОЙ СЛУЖБЫ]");
	CourierChat chat = new CourierChat();
	
	User wworkeradmin= new Warehouseworker(chat,"Краснов Михаил ");
	User courier1 = new Courier(chat,"Иван Остапов");
	User courier2= new Courier(chat,"Гаврилова Алиса");
	User courier3= new Courier(chat,"Чернышев Евгений");  // создание участников чата
	User courier4= new Courier(chat,"Крюков Василий");
	User courier5= new Courier(chat,"Жданова Ксения");
	courier2.NotAtWork(false);
	courier3.NotAtWork(false);//сотрудники не на работе 
	chat.setAdmin(wworkeradmin);//добавление админа
	chat.addCourier(courier1);
	chat.addCourier(courier2);//добавление сотрудников
	chat.addCourier(courier3);
	chat.addCourier(courier4);
	chat.addCourier(courier5);
	
	wworkeradmin.sendM("Все за работу! Кто на месте ?");
	courier1.sendM("Прибыл на работу!");// пример сообщений
	courier4.sendM("На месте!");
	courier5.sendM("Уже работаю!");
	
	
}
}

abstract class User{           //класс "медиатор"
	
Chat chat;
String namecourier;

boolean NotAtWork = true;
public boolean NotAtWork(){return NotAtWork;}
public void NotAtWork(boolean NotAtWork) {this.NotAtWork=NotAtWork;} // функции для обозначения неработающих сотрудников

public User(Chat chat, String namecourier) {this.chat=chat;this.namecourier=namecourier;} // конструктор user

public String getName() {return namecourier;}


public void sendM(String message) {
	chat.sendM(message,this);    // функция отправки сообщений
}

abstract void getM(String message); // функция приема сообщений

public String toString() {
	
	return"User [namecorier="+ namecourier +"]";
}


}


class Warehouseworker extends User{
	
	public Warehouseworker(Chat chat, String namecourier ) {super (chat,namecourier);}   // Описание класса Админ User
	public void getM(String message) {
		System.out.println("Работник склада(АДМИН)"+getName()+" получил(а) сообщение от курьера '"+message +"'");
		
	}
		
}

class Courier extends User{
												
	public Courier(Chat chat, String namecourier ) {super (chat,namecourier);}      // Описание класса Курьер  User
	public void getM(String message) { 
		System.out.println("Курьер "+getName()+" получил(а) сообщение '"+ message +"'");
		
	}
		
}
interface Chat{
	void sendM(String message, User user);
}

class CourierChat implements Chat{
	User wworkeradmin;
	List<User>users = new ArrayList<>(); // Массив пользователей
	
	public void setAdmin (User wworkeradmin) {
		if( wworkeradmin instanceof Warehouseworker ) {   // Назначение пользователя администратором
			this.wworkeradmin = wworkeradmin;
			System.out.println("["+wworkeradmin.getName()+" добавлен в чат и назначени АДМИНИСТРАТОРОМ чата ]");
		}
		else {
			throw new RuntimeException("Вы не можете стать администратором. Ваш статус'курьер'");
		}
	}
	public void addCourier( User courier ) {
	

			if (courier instanceof Courier)   //Добавление курьера в чат 
			{
				users.add(courier);
				System.out.println("Курьер "+courier.getName()+" добавлен в чат ");
			}
		}
	
	public void sendM(String message, User user) {
		
	if (user instanceof Warehouseworker) {
		for (User courier: users) {
			courier.getM(user.getName()+":"+message);  // отправка сообщений от админа
		}
	}
	
		
	if (user instanceof Courier) {
		for (User courier:users) {
			if(courier!=user && courier.NotAtWork())
				{courier.getM(user.getName()+":" +message);} //отправка сообщений от обычного пользователя
		}
		if (wworkeradmin.NotAtWork())
			{wworkeradmin.getM(user.getName()+":"+message);} // сообщения от обычных пользователей не поступают тем, кто не на работе
	}
		
	
	}	

}








