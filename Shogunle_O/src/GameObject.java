import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import javafx.scene.shape.Rectangle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * SuperClass for GameObjects in the Photosynthesis application 
 * All elements implement the gameObject class apart from the play seed
 *
 */
public class GameObject {
	protected Image img;
	protected double x, y;
	protected double width = 40;
	protected double height = 40;
	protected GraphicsContext gc;
	protected MediaPlayer sound;
	
		
	public GameObject(GraphicsContext gc, double x, double y) //constructor for gameObject
	{
	this.gc=gc;
	this.x=x;
	this.y=y;
	}
		
	public void update()
	{
		
	if(img!=null)
		
		gc.drawImage(img, x, y, width, height);
	}
	
	/**Plays sound of gameObject upon doing a certain task */
	public void gObjSound() {
		sound = new MediaPlayer(new Media(Photosynthesis.class.getResource(null).toExternalForm()));
		sound.setAutoPlay(false);
		sound.setVolume(0.2);
		sound.play();
		
	}	
	
	/** methods overridden in the command class  */
	public void waterSoil(){
		//does nothing
	}
	
	public void seeding() {
		//does nothing
	}

	public void sprout() {
		//does nothing
	}
	
	public void plant() { 
		//does nothing
		}
  /** End of overridden methods */
	
	/**
	 * 
	 * @return x position
	 */
	public Double getX(){
		return x;
	}
	
	/**
	 * @return y position
	 */ 
	public Double getY(){
		return y;
	}
	
	/**
	 * 
	 * @param x set X position of gameObject 
	 */
	public void setPosX(Double x){
		this.x = x;
	}
	
	/**
	 * 
	 * @param y set Y position for gameObject
	 */
	public void setPosY(Double y){
		this.y = y;
	}
	
	/**
	 * @return width of gameObject
	 */
	public Double getWidth(){
		return width;
	}
	
	/**
	 * @return height of gameObject
	 */
	public Double getHeight(){
		return height;
	}
}
