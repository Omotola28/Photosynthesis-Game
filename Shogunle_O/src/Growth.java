import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Growth Class implements the GrowthCommands which managers all the commands for the Command Pattern
 * This class provides a concrete implementation of the commands 
 * and are called in the Command classes
 * 
 *
 */
public class Growth extends GameObject {

	public Growth(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img= new Image(Photosynthesis.class.getResource("res/soil.png").toExternalForm());	
		width = 70;
		height = 40;
		update();
	}
	
	@Override
	public void waterSoil() { //First stage
		img = new Image(Photosynthesis.class.getResource("res/snds.png").toExternalForm());
	}

	@Override
	public void seeding() {// Second stage 
		img = new Image("res/stage1.png");
	}

	@Override
	public void sprout() { //Third stage 
		img = new Image("res/stage2.png");
	}
	
	@Override
	public void plant() { //Final stage
		img = new Image("res/plant.png");
	}

}
