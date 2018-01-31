import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
//import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * The Flame class represents an enemy to the growth of plants in the Photosynthesis Application 
 * Flame elements move randomly on the screen within the bounds of the canvas 
 * Flame also has a static sound method
 *
 */


public class Flame extends GameObject {
	private static MediaPlayer flameSound; 
	private double dx = 2, dy = 2; //determines the speed of moving flame elements 
	public Flame(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img= new Image(Photosynthesis.class.getResource("res/flame.gif").toExternalForm());	
		update();
	}

	/**
	 * Flame extends GameObject and overrides the method in the GameObject update()
	 * update() handles the movement of Flame Element across the screen
	 */
	@Override
	public void update(){
		x-=dx;
		y-=dy;
		if(x < 1 || x > 750)// constraint for width
			 dx=- dx;
		if(y < 40 || y > 560) //constraint for height 
			dy=-dy;
		
		super.update();
	}
	
	@Override
	public Double getX(){
		return x;
	}
	
	@Override
	public Double getY(){
		return y;
	}
	
	/**
	 * 
	 * @return MediaPlayer sound for flame in game 
	 */
	public static MediaPlayer getFSound(){
		flameSound = new MediaPlayer(new Media(Flame.class.getResource("res/flame.mp3").toExternalForm()));
		flameSound.setAutoPlay(false);
		flameSound.setVolume(0.1);
		flameSound.setCycleCount(MediaPlayer.INDEFINITE);
		return flameSound;
	}
	
	/**
	 * play's flame audio when called within the scope of the application
	 */
	public static void playSound(){
		getFSound().play();
	}
	
	/**
	 * stop's flame audio when called within the scope of application
	 */
	public static void stopSound(){
		if(getFSound() != null)
		    getFSound().stop();
	}
}
