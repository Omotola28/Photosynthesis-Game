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
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * This class implements the screen that shows player that they have exhausted their healthBar
 * GameOver screen appears when health bar < 0
 *
 */

public class GameOver extends MenuProperty implements Menu{
	private MenuButton btnExit;
	private Label gameOver;

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

		gameOver = new Label();
		gameOver.setText("GAME OVER!");
		gameOver.setTranslateX(250);
		gameOver.setTranslateY(200);
		gameOver.setFont(Font.font("Impact", 100));
		gameOver.setTextFill(Color.RED);
		
		btnExit = new MenuButton("EXIT TO DESKTOP");
		btnExit.setOnMouseClicked(e-> {
			System.exit(0);
		});
		
		menu.getChildren().add(btnExit);
		
		Rectangle gameMenuBG = new Rectangle(800, 600);
		gameMenuBG.setFill(Color.WHITE);
		gameMenuBG.setOpacity(0.4);

		pane.getChildren().addAll(gameMenuBG,canvas,gameOver, menu);
		return pane;
	}

}
