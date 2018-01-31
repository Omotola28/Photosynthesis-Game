/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Factory Interface for creating element in Photosynthesis game 
 * ElementFactory implements the Factory Interface and provides 
 * concrete body for method 
 *
 */
public interface FactoryIF {
	public GameObject createElement(String keyWord, double x, double y);
}


