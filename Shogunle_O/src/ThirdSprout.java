/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Third command in the command pattern, third stage for plant growth
 *
 */

public class ThirdSprout implements CommandIF {

	GameObject gameObj; //Receiver 
	public ThirdSprout(GameObject gameObj){
		this.gameObj = gameObj;
	}
	
	@Override
	public void execute() {
		gameObj.sprout();
		
	}
}