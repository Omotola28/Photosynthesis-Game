import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Encapsulates shared properties between menu screens 
 *
 */

public abstract class MenuProperty {
	protected Image background;
	protected GraphicsContext gc;
	protected Canvas canvas;
	protected Rectangle gameMenuBG;
}
