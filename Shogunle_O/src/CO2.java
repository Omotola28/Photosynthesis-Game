import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * One of the elements in the photosynthesis game extends GameObject 
 * 
 */

public class CO2 extends GameObject {


	public CO2(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img= new Image(Photosynthesis.class.getResource("res/gas.png").toExternalForm());	
		update();
	}

	/**
	 * @return X position of the CO2 element 
	 */
	@Override
	public Double getX(){ 
		return x;
	}

	/**
	 * @return Y position of the CO2 element 
	 */
	@Override
	public Double getY(){
		return y;
	}

}
