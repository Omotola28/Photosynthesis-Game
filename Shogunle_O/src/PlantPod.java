import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * PlantPod is the player in the photosynthesis game, it uses the Singleton Pattern to make sure that there is 
 * only one instance of the player gameObject. 
 * 
 * PlantPod receives points when impacted by elements in game 
 * and looses its health when impacted with the flame element 
 * Plantpod makes a satisfied sound when it gets element 
 *
 */

public class PlantPod extends GameObject{
	
    private MediaPlayer happyPod;

	public PlantPod(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img= new Image(Photosynthesis.class.getResource("res/seed.png").toExternalForm());	
		update();
	}

	/**
	 * @return position of type Double
	 *  Plant pod on the X-axis of canvas
 	 */
	@Override
	public Double getX(){
		return x;
	}
	
	/**
	 * 
	 * @return Plant pod position on the Y-axis of canvas
	 */
	@Override
	public Double getY(){
		return y;
	}

	/**
	 * 
	 * @param x set X position for PlantPod
	 */
	@Override
	public void setPosX(Double x){
		this.x = x;
	}
	
	/**
	 * 
	 * @param y set Y position for Plantpod
	 */
	@Override
	public void setPosY(Double y){
		this.y = y;
	}
	
	/**
	 * Sound plays when Plant pod successfully collects elements from game
	 * autoPlay set to false and this method is called in Photosynthesis when 
	 * Plant pod makes impact with element
	 */
	@Override
	public void gObjSound(){
		happyPod = new MediaPlayer(new Media(Photosynthesis.class.getResource("res/likes.wav").toExternalForm()));
		happyPod.setAutoPlay(false);
		happyPod.setVolume(0.2);
		happyPod.play();
	}	
}
