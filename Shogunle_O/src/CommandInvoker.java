/**
 * 
 * @author Omotola Shogunle @00442280
 * @version 1 Photosynthesis Application
 * @date 15-12-2017
 * 
 * Command Pattern invoker class calls the execute command in the command interface 
 * which in turn calls the concrete command on the receiver class
 *
 */
public class CommandInvoker {
	
	private CommandIF firstStage;
	private CommandIF secondStage;
	private CommandIF thirdStage;
	private CommandIF lastStage;
	
	public CommandInvoker(CommandIF firstStage, CommandIF secondStage, 
			CommandIF thirdStage, CommandIF lastStage){
		this.firstStage = firstStage;
		this.secondStage = secondStage;
		this.thirdStage = thirdStage;
		this.lastStage = lastStage;
	}
	
	public void stage1(){
		firstStage.execute(); //invokes the first stage of the growth command
	}
	
	public void stage2(){
		secondStage.execute(); //invokes the second stage of the growth command
	}
	
	public void stage3(){
		thirdStage.execute(); //invokes the third stage of the growth command
	}
	
	public void stageFinal(){
		lastStage.execute(); //invokes the last stage of the growth command
	}
	  

}
