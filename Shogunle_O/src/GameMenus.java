import javafx.scene.Parent;
/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * GameMenu is the delegator class for the Menu to be displayed to
 * player. It takes the different class Menu's as a parameter and 
 * calls their menuUpdate method when required 
 *
 */

public class GameMenus implements Menu{
 
	private Menu delegate = null;
	
	/**
	 * 
	 * @param delegate of type Menu changes the view of users 
	 * base on the type of menu that is entered.
	 */
	public GameMenus (Menu delegate){
		this.delegate = delegate;
	}

	@Override
	public Parent menuUpdate(){
		 return delegate.menuUpdate();
	}
	
	/**Change menu to be displayed to user*/
	public void changeMenu(Menu delegate)
	{
		this.delegate = delegate;
	}
	
}
