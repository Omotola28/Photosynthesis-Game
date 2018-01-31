import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * This class displays the Welcome Menu screen to the player
 *
 */

public class WelcomeScreen extends MenuProperty implements Menu{
	private final int offset = 300;
	private MediaPlayer themeSong;
	private MenuButton btnNewGame, btnSound, btnExit, btnON, btnOFF, btnBack, controls;

	@Override
	public Parent menuUpdate() {
		Pane pane = new Pane();
		VBox mainMenu = new VBox(10);//Main gameMenu on the Welcome page 
		VBox soundOption = new VBox(10);//OptionMenu for toggling game sound

		mainMenu.setTranslateX(300);
		mainMenu.setTranslateY(300);

		soundOption.setTranslateX(300);
		soundOption.setTranslateY(300);
		soundOption.setTranslateX(offset); // set in a position off the screen until its transitioned in 

		canvas = new Canvas(600,400);
		canvas.setTranslateX(80);
		canvas.setTranslateY(50);
		gc = canvas.getGraphicsContext2D();
		background = new Image ("res/photo.png");
		gc.drawImage(background, 0, 0,600, 400);

		themeSong = new MediaPlayer(new Media(Photosynthesis.class.getResource("res/gamesong.mp3").toExternalForm()));
		themeSong.setAutoPlay(true);
		themeSong.setVolume(0.1);

		// Button for starting game 
		btnNewGame = new MenuButton("START GAME");
		btnNewGame.setOnMouseClicked(e->{ //GameMenu fades into game on clicking start button
			FadeTransition transition = new FadeTransition(Duration.seconds(0.5), pane);
			transition.setFromValue(1);
			transition.setToValue(0);
			transition.setOnFinished(evt->pane.setVisible(false));
			Timeline timeline = new Timeline( //fades out the background music into the game
					new KeyFrame(Duration.seconds(1),
							new KeyValue(themeSong.volumeProperty(), 0)));
			timeline.play();
			Flame.playSound(); //play sound for flame 
			transition.play();
		});
		
		controls = new MenuButton("CONTROLS"); //show player, how to play game 
		controls.setOnMouseClicked(e->{
			
			Pane p = new Pane();
			Stage stage = new Stage();
            stage.setScene(new Scene(p, 500, 400)); 
            stage.getIcons().add(new Image("res/seed.png"));
    		stage.setTitle("Photosynthesis");
            stage.show();
            
            background = new Image ("res/controls.png");
            canvas = new Canvas(500,400);
    		gc = canvas.getGraphicsContext2D();
    		gc.drawImage(background, 0, 0,500, 400);
            
            p.getChildren().add(canvas);
			
		});


		//Toggles on and off of sound for game 
		btnSound = new MenuButton("SOUND ON/OFF"); 
		btnSound.setOnMouseClicked(e->{
			pane.getChildren().add(soundOption);

			TranslateTransition main = new TranslateTransition(Duration.seconds(0.25),mainMenu);
			main.setToX(mainMenu.getTranslateX() - offset); //Bring menu to screen

			TranslateTransition soundMenu = new TranslateTransition(Duration.seconds(0.5),soundOption);
			soundMenu.setToX(mainMenu.getTranslateX());

			main.play();
			soundMenu.play();

			main.setOnFinished(evt->{
				pane.getChildren().remove(mainMenu); //remove mainMenu from screen when soundOptions show 
			});

		});

		btnExit = new MenuButton("EXIT TO DESKTOP"); //exit java application
		btnExit.setOnMouseClicked(e-> {
			System.exit(0);
		});

		btnON = new MenuButton("ON"); // toggle theme song to play
		btnON.setOnMouseClicked(e-> {
			themeSong.play();
		});

		btnOFF = new MenuButton("OFF"); // toggle theme song to stop
		btnOFF.setOnMouseClicked(e-> {
			themeSong.pause();
		});

		btnBack = new MenuButton("BACK"); // Go back to mainMenu
		btnBack.setOnMouseClicked(e-> {
			pane.getChildren().add(mainMenu);

			TranslateTransition soundMenu = new TranslateTransition(Duration.seconds(0.25),soundOption);
			soundMenu.setToX(soundOption.getTranslateX());

			TranslateTransition main = new TranslateTransition(Duration.seconds(0.5),mainMenu);
			main.setToX(mainMenu.getTranslateX() + offset);

			main.play();
			soundMenu.play();

			soundMenu.setOnFinished(evt->{
				pane.getChildren().remove(soundOption); //remove sound option menu from screen 
			});
		});

		mainMenu.getChildren().addAll(btnNewGame, controls, btnSound,btnExit); //add buttons to Vbox mainMenu
		soundOption.getChildren().addAll(btnON, btnOFF, btnBack); // add buttons to Vbox soundOption

		gameMenuBG = new Rectangle(800, 600); //Transparent background for the gameMenu
		gameMenuBG.setFill(Color.WHITE);
		gameMenuBG.setOpacity(0.4);
				
		pane.getChildren().addAll(gameMenuBG,canvas, mainMenu); //Add nodes to the GameMenu
		
		
		return pane;
	}
}