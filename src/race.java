import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Race {
	
	Random randomGenerator = new Random();
	public String chosenRace;
	
	ArrayList<String> raceList = new ArrayList<String>();
	ArrayList<String> raceStaticPreList = new ArrayList<String>();
	static ArrayList<String> raceStaticList = new ArrayList<String>();
		
     public Race() {
    	 chosenRace= "";
    	 loadRaceList(); 
     } //end Race()
     
     public void pickNewRace(){
    	chosenRace= "";
	   	int index = randomGenerator.nextInt(raceList.size());
	   	chosenRace = raceList.get(index);
		chosenRace = chosenRace.replace("RACE:", "");
		//DEBUG TOOL: Check to see how big raceList ArrayList is
	   	//System.out.println("Debug Info: raceList is " + raceList.size() + " items"); 
		//DEBUG TOOL: check to see what value is being assigned to Index
	    //System.out.println("Debug Info: index has been randomly set to " + index); 
     }
     
     public void pickNewRace(String race){
    	 chosenRace = race;
     }
     
     private void loadRaceList(){
    	
    	 //The master list, containing all Races
    	 File targetFile = new File("Race.txt"); //path to the file on local environment.
    	 
    	 try {
    		    ReadFromFile file = new ReadFromFile(targetFile);
    		    raceList = file.OpenFile();
    		    raceList.sort(null); // Fun fact about vectors, they can do sorts for you, uncommenting this will put stuff in alphabetical order.
    		    
    		    raceStaticPreList = file.OpenFile();
    		    //System.out.println(raceStaticPreList);
    		    for(String s: raceStaticPreList){
    		    	raceStaticList.add(s.replace("RACE:", ""));
    		    }
    		    //DEBUG TOOL: Check to see that the list is being created
    		    /*
    		    System.out.println("Race List:");
    		    for(String out: raceList){
    		        System.out.println(out);
    		    }
    		    */
    		   
    		} catch (Exception e) {
    		    //Default error message
    		    System.out.println(e.getMessage());
    		} //End try / catch
     }//End loadRaceList()
     
     private ArrayList<String> getRaceStaticList(){
		return raceStaticList;
     }
}// End class
