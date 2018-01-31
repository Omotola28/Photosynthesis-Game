import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Water, one of the elements in the phtosynthesis game extends GameObject 
 *
 */

public class Water extends GameObject {

	public Water(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img= new Image(Photosynthesis.class.getResource("res/water.png").toExternalForm());	
		update();
	}
	
	@Override
	public Double getX(){
		return x;
	}
	
	@Override
	public Double getY(){
		return y;
	}
}
