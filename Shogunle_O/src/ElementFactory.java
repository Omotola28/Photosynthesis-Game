import javafx.scene.canvas.GraphicsContext;
/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Factory Pattern implementation, this enable elements to be shown on the screen based on if keyWord matches 
 * when initialising the objects  
 *
 */

public class ElementFactory implements FactoryIF {

	private GraphicsContext gc;
	public ElementFactory(GraphicsContext gc) {
		this.gc = gc;
	}
	
	/**
	 * @return GameObject based on what keyWord is matched else return NullElement
	 * GameObject are returned at random positions.
	 *
	 */
	@Override
	public GameObject createElement(String keyWord, double x, double y) {
		if(keyWord.equalsIgnoreCase("sun")) 
			return new Sun(gc, x, y);
		else if(keyWord.equalsIgnoreCase("water"))
			return new Water(gc, x, y);
		else if (keyWord.equalsIgnoreCase("fire"))
			return new Flame(gc, x, y);
		else if(keyWord.equalsIgnoreCase("CO2"))
			return new CO2(gc, x, y);
		else if(keyWord.equalsIgnoreCase("plant"))
			return new PlantPod(gc,x,y);
		else if(keyWord.equalsIgnoreCase("growSeed"))
			return new Growth(gc,x,y);
		return NullElement.getInstance(gc, x, y);
		
	}

}
