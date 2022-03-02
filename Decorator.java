import javax.swing.JOptionPane;

public class Decorator {
		public static void main(String[] args) {
			JOptionPane.showMessageDialog(null, "ORDER COMPOSITION:");
			Order o = new AddingToOrder3( new AddingToOrder2(new AddingToOrder1( new Mainorder()))); //�������� ������� ������
			o.order();
			
		}
}

interface Order{
	void order();
}


abstract class ASDecorator implements Order{
	Order component; // ������ �� ���������
	public ASDecorator(Order component) {this.component=component;} // ����� ���������, �� �������� ����� ������������� "�������"
	
	}

class Mainorder implements Order{
	public void order() {
		JOptionPane.showMessageDialog(null, "MAIN ORDER"); //"���������" �����(������� ����� ������)
	}

}
// ������ ������ �������, ����������� �� ����������
class AddingToOrder1 extends ASDecorator{
	
	public AddingToOrder1(Order component) {super(component);}
	public void order() {
		component.order();
		JOptionPane.showMessageDialog(null, "ADDING TO ORDER: COFFEE");	
	}
	
	
}
 
class AddingToOrder2 extends ASDecorator{
	
	public AddingToOrder2(Order component) {super(component);}
	public void order() {
		component.order();
		JOptionPane.showMessageDialog(null, "ADDING TO ORDER: PACKAGE");	
	}
}
	
class AddingToOrder3 extends ASDecorator{
		
		public AddingToOrder3(Order component) {super(component);}
		public void order() {
			component.order();
			JOptionPane.showMessageDialog(null, "ADDING TO ORDER: FLOWERS");	
		}
}











