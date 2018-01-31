import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import javafx.scene.shape.Rectangle;


/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Sun, one of the elements in the photosynthesis game extends GameObject 
 * 
 */

public class Sun extends GameObject {

	public Sun(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img= new Image(Photosynthesis.class.getResource("res/sun.png").toExternalForm());	
		update();

	}
	
	@Override
	public Double getX(){ //return X position
		return x;
	}
	
	@Override
	public Double getY(){ //return Y position
		return y;
	}

}
