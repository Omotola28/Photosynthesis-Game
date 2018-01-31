import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * This class implements the Victory Screen for player when they get elements without exhausting 
 * healthBar.
 *
 */

public class VictoryScreen extends MenuProperty implements Menu {
	private MenuButton btnExit;
	private Label youWon;
	

	@Override
	public Parent menuUpdate() {
		Pane pane = new Pane();
		VBox menu = new VBox(10);
		menu.setTranslateX(300);
		menu.setTranslateY(320);

		canvas = new Canvas(800,600);
		gc = canvas.getGraphicsContext2D();
		background = new Image ("res/bg.png");
		gc.drawImage(background, 0, 0,800, 600);
		
		youWon = new Label();
		youWon.setText("YOU WON!");
		youWon.setTranslateX(250);
		youWon.setTranslateY(200);
		youWon.setFont(Font.font("Impact", 100));
		youWon.setTextFill(Color.GOLDENROD);
		
		btnExit = new MenuButton("EXIT TO DESKTOP");
		btnExit.setOnMouseClicked(e-> {
			System.exit(0);
		});
		
		menu.getChildren().add(btnExit);
		
		Rectangle gameMenuBG = new Rectangle(800, 600);
		gameMenuBG.setFill(Color.WHITE);
		gameMenuBG.setOpacity(0.4);

		pane.getChildren().addAll(gameMenuBG,canvas,youWon, menu);
		
		return pane;
	}

}
