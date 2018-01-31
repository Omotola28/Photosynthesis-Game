/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Second command in the command pattern, second stage for plant growth
 *
 */
public class SecondSprout implements CommandIF {

	GameObject gameObj; //Receiver
	public SecondSprout(GameObject gameObj){
		this.gameObj = gameObj;
	}
	@Override
	public void execute() {
		gameObj.seeding();
		
	}

}