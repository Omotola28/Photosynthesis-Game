import javafx.scene.canvas.GraphicsContext;
/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * NullElement that does nothing upon calling in the factory
 * The NullElement class using the singleton pattern
 *
 */
public class NullElement extends GameObject {
	private static NullElement instance = null;

	private NullElement(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		
	}
	
	public static NullElement getInstance(GraphicsContext gc, double x, double y){
		if(instance == null)
			instance = new NullElement(gc, x, y);
		else 
			System.out.println("No such element");
		return instance;
		
	}

}
