import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * MenuButtons class helps create buttons in the GameMenu screen 
 * extends StackPane which helps put objects on top of each other
 *
 */

public class MenuButton extends StackPane {

	private Text text;
	
	/**
	 * 
	 * @param btnName of type String is set for the name of the button you want to create 
	 */
	
	public MenuButton(String btnName){ 
		text = new Text(btnName);
		text.getFont();
		text.setFont(Font.font(20));
		text.setFill(Color.GREEN);
		
		Rectangle menuItem = new Rectangle(250, 30); // width and height of the button
		menuItem.setFill(Color.GOLD);
		
		setAlignment(Pos.CENTER_LEFT); // Position of button
		setRotate(0.5);
		getChildren().addAll(menuItem,text);
		
		// sets colour of button when it is to be clicked 
		setOnMouseEntered(e ->{
			menuItem.setTranslateX(10);
			text.setTranslateX(10);
			menuItem.setFill(Color.GREEN);
			text.setFill(Color.WHITE);
		});
		
		
		// Default colour of button when it is not clicked 
		setOnMouseExited(e->{
			menuItem.setTranslateX(0);
			text.setTranslateX(0);
			menuItem.setFill(Color.GOLD);
			text.setFill(Color.GREEN);
		});
		
		DropShadow dropShadow = new DropShadow(50, Color.WHITE);
		dropShadow.setInput(new Glow());
		
		
		// Glow effect when button is clicked 
		setOnMousePressed(e->setEffect(dropShadow));
		setOnMouseReleased(e->setEffect(null));
	}
}
