/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Last command in the command pattern, last stage for plant growth
 *
 */
public class Plant implements CommandIF {

	GameObject gameObj; //Receiver 
	public Plant(GameObject gameObj){
		this.gameObj = gameObj;
	}
	@Override
	public void execute() {
		gameObj.plant();
		
	}
}