import java.util.List;
import java.util.ArrayList;
public class Mediator {
public static void main(String[] args) {
	System.out.println("[��� ����������� ���������� ������]");
	CourierChat chat = new CourierChat();
	
	User wworkeradmin= new Warehouseworker(chat,"������� ������ ");
	User courier1 = new Courier(chat,"���� �������");
	User courier2= new Courier(chat,"��������� �����");
	User courier3= new Courier(chat,"�������� �������");  // �������� ���������� ����
	User courier4= new Courier(chat,"������ �������");
	User courier5= new Courier(chat,"������� ������");
	courier2.NotAtWork(false);
	courier3.NotAtWork(false);//���������� �� �� ������ 
	chat.setAdmin(wworkeradmin);//���������� ������
	chat.addCourier(courier1);
	chat.addCourier(courier2);//���������� �����������
	chat.addCourier(courier3);
	chat.addCourier(courier4);
	chat.addCourier(courier5);
	
	wworkeradmin.sendM("��� �� ������! ��� �� ����� ?");
	courier1.sendM("������ �� ������!");// ������ ���������
	courier4.sendM("�� �����!");
	courier5.sendM("��� �������!");
	
	
}
}

abstract class User{           //����� "��������"
	
Chat chat;
String namecourier;

boolean NotAtWork = true;
public boolean NotAtWork(){return NotAtWork;}
public void NotAtWork(boolean NotAtWork) {this.NotAtWork=NotAtWork;} // ������� ��� ����������� ������������ �����������

public User(Chat chat, String namecourier) {this.chat=chat;this.namecourier=namecourier;} // ����������� user

public String getName() {return namecourier;}


public void sendM(String message) {
	chat.sendM(message,this);    // ������� �������� ���������
}

abstract void getM(String message); // ������� ������ ���������

public String toString() {
	
	return"User [namecorier="+ namecourier +"]";
}


}


class Warehouseworker extends User{
	
	public Warehouseworker(Chat chat, String namecourier ) {super (chat,namecourier);}   // �������� ������ ����� User
	public void getM(String message) {
		System.out.println("�������� ������(�����)"+getName()+" �������(�) ��������� �� ������� '"+message +"'");
		
	}
		
}

class Courier extends User{
												
	public Courier(Chat chat, String namecourier ) {super (chat,namecourier);}      // �������� ������ ������  User
	public void getM(String message) { 
		System.out.println("������ "+getName()+" �������(�) ��������� '"+ message +"'");
		
	}
		
}
interface Chat{
	void sendM(String message, User user);
}

class CourierChat implements Chat{
	User wworkeradmin;
	List<User>users = new ArrayList<>(); // ������ �������������
	
	public void setAdmin (User wworkeradmin) {
		if( wworkeradmin instanceof Warehouseworker ) {   // ���������� ������������ ���������������
			this.wworkeradmin = wworkeradmin;
			System.out.println("["+wworkeradmin.getName()+" �������� � ��� � ��������� ��������������� ���� ]");
		}
		else {
			throw new RuntimeException("�� �� ������ ����� ���������������. ��� ������'������'");
		}
	}
	public void addCourier( User courier ) {
	

			if (courier instanceof Courier)   //���������� ������� � ��� 
			{
				users.add(courier);
				System.out.println("������ "+courier.getName()+" �������� � ��� ");
			}
		}
	
	public void sendM(String message, User user) {
		
	if (user instanceof Warehouseworker) {
		for (User courier: users) {
			courier.getM(user.getName()+":"+message);  // �������� ��������� �� ������
		}
	}
	
		
	if (user instanceof Courier) {
		for (User courier:users) {
			if(courier!=user && courier.NotAtWork())
				{courier.getM(user.getName()+":" +message);} //�������� ��������� �� �������� ������������
		}
		if (wworkeradmin.NotAtWork())
			{wworkeradmin.getM(user.getName()+":"+message);} // ��������� �� ������� ������������� �� ��������� ���, ��� �� �� ������
	}
		
	
	}	

}








