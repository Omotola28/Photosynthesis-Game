/**
 * 
 * @author Omotola Shogunle 
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * FirstSprout command overrides the execute method for the Command interface 
 * Grows soil in canvas as first progress during photosynthesis 
 *
 */
public class FirstSprout implements CommandIF {

	private GameObject gObj; //Receiver
	public FirstSprout(GameObject gObj){
		this.gObj = gObj;
	}
	

	@Override
	public void execute() {
		gObj.waterSoil();
		
	}}