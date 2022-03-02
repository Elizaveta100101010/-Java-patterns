import javax.swing.JOptionPane;

public class Decorator {
		public static void main(String[] args) {
			JOptionPane.showMessageDialog(null, "ORDER COMPOSITION:");
			Order o = new AddingToOrder3( new AddingToOrder2(new AddingToOrder1( new Mainorder()))); //Описание состава заказа
			o.order();
			
		}
}

interface Order{
	void order();
}


abstract class ASDecorator implements Order{
	Order component; // ссылка на компонент
	public ASDecorator(Order component) {this.component=component;} // класс декоратор, от которого будут наследоваться "добавки"
	
	}

class Mainorder implements Order{
	public void order() {
		JOptionPane.showMessageDialog(null, "MAIN ORDER"); //"дефолтный" заказ(главная часть заказа)
	}

}
// разные классы добавок, наследуемые от декоратора
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











