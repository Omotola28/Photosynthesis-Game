import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 *       This application educates primary school kids of the importance of
 *       photosynthesis and how plants grow It has implemented a couple of
 *       design patterns namely Factory pattern, singleton pattern and the
 *       Command Pattern The Factory Pattern was used to construct elements
 *       such as WATER, CO2 and SUN unto the application The Singleton Pattern
 *       was used to make the single player which is the SEED that gets all the
 *       elements to grow Finally the command pattern was used to show the
 *       different growth stages of the plant The application also takes user
 *       experience into consideration such as adding sound effects and button
 *       controls
 * 
 */

public class Photosynthesis extends Application {

	private Pane root;
	private Scene scene;
	private Stage window;
	private Canvas canvas;
	private GraphicsContext gc;
	private ElementFactory factory;
	private GameMenus menu;
	private HBox hbox;
	private BorderPane border;
	private StackPane stack;
	private Label healthLabel, scoreLabel, score, growth;
	private int percentScore, health = 300;
	private Rectangle healthBar, container;
	private Image background;
	private CommandInvoker invoker;
	private GameObject pod, grow;
	private ArrayList<GameObject> list = new ArrayList<GameObject>();
	private Random rnd = new Random(System.currentTimeMillis());
	private Parent screen;

	/**
	 * The handle method is where the game logic resides A Rectangle object is
	 * used to wrap objects on the canvas to detect collision PercentScore
	 * increase by 4 when an element is taken by seed. The list iterator removes
	 * elements from the arraylist upon collision HealthBar decrease by 1 when
	 * there is collision between the seed and the flame
	 */
	AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long arg0) {
			gc.drawImage(background, 0, 0, 800, 600);
			Iterator<GameObject> it = list.iterator(); //creating a list iterator for deleting elements 
			if (screen.isVisible() == false) {
				hbox.setVisible(true);
				for (GameObject element : list) {
					element.update(); //elements meaning sun,water and co2
				}
				pod.update(); //Draw Player pod on canvas 
				grow.update();//Draw plant growth on canvas 
				while (it.hasNext()) {
					//take each element and wrap it in a rectangle 
					GameObject gObj = it.next(); 
					Rectangle wrapElement = new Rectangle(gObj.getX(), gObj.getY(), gObj.getWidth(), gObj.getHeight());
					if (!(gObj instanceof Flame)) { 
						if (wrapElement.intersects(pod.getX(), pod.getY(), pod.getWidth(),
								pod.getHeight())) {//collision detection
							it.remove();
							percentScore += 4;
							score.setText("" + percentScore + "%"); //update the percentage score on screen
							pod.gObjSound();// sound seed makes when it collects an element
						}
					} else if (gObj instanceof Flame) { 
						if (wrapElement.intersects(pod.getX(), pod.getY(), pod.getWidth(),
								pod.getHeight())) {
							gc.setFill(Color.RED);
							gc.setFont(Font.font("Impact", 20));
							gc.fillText("OUCH", pod.getX(), pod.getY());
							healthBar.setWidth(health -= 1);// reduce health = 300 by -1
						}
					}
				}
				/**
				 * Implementing the command pattern for drawing different plant
				 * stages when percentScore reaches a certain point 20 - 40 :
				 * soil 41 - 60 : firstSprout 61 - 80 : secondSprout 81 - 100:
				 * thirdSprout
				 */
				if (percentScore >= 20 && percentScore <= 40) {
					invoker.stage1(); //invoke execute in FirstSprout
				} else if (percentScore > 40 && percentScore <= 60) {
					invoker.stage2(); //invoke execute in SecondSprout
				} else if (percentScore > 60 && percentScore <= 80) {
					invoker.stage3();//invoke execute in ThirdSprout
				} else if (percentScore > 80 && percentScore <= 100) {
					invoker.stageFinal(); //invoke execute in Plant
				}

				/**
				 * checks to see that all the elements have been removed from
				 * the screen the size of the list was checked against 10 as
				 * that is the number of flame objects on the screen animation
				 * time will stop one all elements are taken off the screen and
				 * a you win screen would appear
				 */
				if (list.size() == 10 && health > 0) {
					timer.stop();
					winScreen();
				} else if (health < 0) {
					timer.stop();
					gameOver();
				}
			}
		}
	}; // end of game logic

	/**
	 * Handles movement of PlantPod UP Arrow key is for Up movement DOWN Arrow
	 * key is for Down movement RIGHT Arrow key is for right movement LEFT Arrow
	 * key is for left movement
	 */
	EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			if (event.getCode() == KeyCode.RIGHT) {
				pod.setPosX(pod.getX()+ 5);
			}
			if (event.getCode() == KeyCode.LEFT) {
				pod.setPosX(pod.getX() - 5);
			}
			if (event.getCode() == KeyCode.UP) {
				pod.setPosY(pod.getY() - 5);
			}
			if (event.getCode() == KeyCode.DOWN) {
				pod.setPosY(pod.getY() + 5);
			}

		}
	};

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage; // assign primaryStage to global window variable 
		root = new Pane();
		scene = new Scene(root, 800, 600);
		
		window.setScene(scene);
		window.setResizable(false);
		window.getIcons().add(new Image("res/seed.png"));
		window.setTitle("Photosynthesis");
		window.show();

		menu = new GameMenus(new WelcomeScreen());
		screen = menu.menuUpdate();
		screen.setVisible(false);
		
		canvas = new Canvas(800, 600);
		background =new Image(Photosynthesis.class.getResource("res/background.jpg").toExternalForm());
		gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		border = new BorderPane();
		hbox = addScoreBoard(); //returns HBox
		border.setTop(hbox); // BorderPane used to lay HBox at the top of screen
		addHealthBar(hbox); // Add stack to HBox in top region


		root.getChildren().addAll(canvas, border,screen);

		scene.setOnKeyPressed(keyHandler); // handles movement of seed plant

		animationMenu(); // Welcome page to players loads on starting the game

		/**
		 * Factory pattern used to construct elements on the canvas the for loop
		 * returns elements at random positions within a width of 700 and height
		 * 551
		 * 
		 * water produces 10 elements sun produces 8 elements co2 produces 7
		 * elements fire produces 10 elements
		 */
		factory = new ElementFactory(gc);

		int i, j, k;
		for (i = 0; i < 500; i += 50) {
			list.add(factory.createElement("water", rnd.nextInt(700), rnd.nextInt((550 - 50) + 1) + 50));
		}
		for (j = 0; j < 400; j += 50) {
			list.add(factory.createElement("sun", rnd.nextInt(700), rnd.nextInt((550 - 50) + 1) + 50));
		}
		for (j = 0; j < 350; j += 50) {
			list.add(factory.createElement("CO2", rnd.nextInt(700), rnd.nextInt((550 - 50) + 1) + 50));
		}
		for (k = 0; k < 500; k += 50) {
			list.add(factory.createElement("fire", rnd.nextInt(700), rnd.nextInt((550 - 50) + 1) + 50));
		}

		pod = factory.createElement("plant", 400, 300); //Player Object 
		grow = factory.createElement("growSeed", 295, 20); //Receiver Object for Command
		//The invoker takes four parameters to represent the commands available to execute
		invoker = new CommandInvoker(new FirstSprout(grow), new SecondSprout(grow),
				new ThirdSprout(grow), new Plant(grow)); 
		timer.start();
	}

	/**
	 * The animationMenu checks to see if the GameMenu is visible, if not this
	 * method helps to dynamically load gameMenu screen on start of the
	 * Application
	 */
	public void animationMenu() {
		if (!screen.isVisible()) {
			FadeTransition transition = new FadeTransition(Duration.seconds(3), screen);
			transition.setFromValue(0); // from not visible
			transition.setToValue(1); // to visible
			screen.setVisible(true);
			transition.play();
		}
	}

	/**
	 * WinScreen appears on screen when player has won a game i.e successfully
	 * collected all elements without health bar decreasing
	 */
	public void winScreen(){	
		menu.changeMenu(new VictoryScreen()); //change delegate object 
		screen = menu.menuUpdate();
		screen.setVisible(false);
		if(!screen.isVisible()){
			FadeTransition transition = new FadeTransition(Duration.seconds(2),
					screen);
			transition.setFromValue(0);
			transition.setToValue(1);
			screen.setVisible(true);
			transition.play();
		}
		scene = new Scene(screen,800,600);
		window.setScene(scene);
	}

	/**
	 * GameOver screen menu appears when health bar is exhausted
	 */
	public void gameOver(){
		menu.changeMenu(new GameOver()); //change delegate object 
		screen = menu.menuUpdate();
		screen.setVisible(false);
		if(!screen.isVisible()){
			FadeTransition transition = new FadeTransition(Duration.seconds(2),
					screen);
			transition.setFromValue(0); // from not visible
			transition.setToValue(1); // to visible
			screen.setVisible(true);
			transition.play();
		}
		scene = new Scene(screen, 800, 600);
		window.setScene(scene);
	}

	/**
	 * This will handle the scoreBoard for the game, player would be able to see
	 * 
	 * Score in % Growth of Plant Seed HealthBar
	 * 
	 * @return HBox pane
	 */
	public HBox addScoreBoard() {
		hbox = new HBox();
		hbox.setPadding(new Insets(20, 20, 20, 20));
		hbox.setSpacing(20);
		hbox.setStyle("-fx-background-color:rgba(192,192,192,0.3)"); // transparent white colour
		hbox.setPrefWidth(800);
		hbox.setPrefHeight(50);
		hbox.setVisible(false);

		healthLabel = new Label();
		healthLabel.setText("HEALTH:");
		healthLabel.setTranslateY(-2);
		healthLabel.setTranslateX(100);
		healthLabel.setFont(Font.font("Impact", 20));
		healthLabel.setTextFill(Color.WHITE);

		scoreLabel = new Label();
		scoreLabel.setText("ENERGY LEVEL:");
		scoreLabel.setTranslateY(-2);
		scoreLabel.setFont(Font.font("Impact", 20));
		scoreLabel.setTextFill(Color.WHITE);

		score = new Label();
		score.setText("0%");
		score.setTranslateY(-2);
		score.setFont(Font.font("Impact", 20));
		score.setTextFill(Color.GOLDENROD);

		growth = new Label();
		growth.setText("GROWTH:");
		growth.setTranslateY(-2);
		growth.setFont(Font.font("Impact", 20));
		growth.setTextFill(Color.LIMEGREEN);

		hbox.getChildren().addAll(scoreLabel, score, growth, healthLabel);
		return hbox;
	}

	/**
	 * 
	 * @param hb of type HBox, health bar is added to other 
	 * Children scoreLabel, score, growth and healthLabel
	 * healthBar is the rectangle that reduces while container shows background for the 
	 * health bar 
	 */
	public void addHealthBar(HBox hb) { // Add HBox to the stack at the top of screen
		stack = new StackPane();

		healthBar = new Rectangle(300,30); // health indicator
		healthBar.setFill(Color.DARKSEAGREEN);
		healthBar.setTranslateX(100);
		healthBar.setTranslateY(-5);
		healthBar.setStroke(Color.WHITE);

		container = new Rectangle(300, 30); // helps indicate when seed life is reducing, creates contrast
		container.setStroke(Color.WHITE);
		container.setTranslateX(100);
		container.setTranslateY(-5);
		container.setFill(Color.GRAY);

		stack.getChildren().addAll(container, healthBar);
		stack.setMaxSize(300, 30);
		stack.setAlignment(Pos.TOP_LEFT); // Left-justify nodes in stack

		hb.getChildren().add(stack);
		HBox.setHgrow(stack, Priority.ALWAYS); // Give stack any extra space
	}

	@Override
	public void stop() {
		System.exit(0); // Shuts down java application properly.
	}
}
