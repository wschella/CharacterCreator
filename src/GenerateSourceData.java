import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GenerateSourceData {
	
	protected ArrayList<String> raceSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> raceSourceStatic = new ArrayList<>();
	protected ArrayList<String> subraceSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> subraceSourceStatic = new ArrayList<>();
	protected ArrayList<String> adultProfessionSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> adultProfessionSourceStatic = new ArrayList<>();
	protected ArrayList<String> childProfessionSourcePreStatic = new ArrayList<>();
	protected static ArrayList<String> childProfessionSourceStatic = new ArrayList<>();
	protected static ArrayList<String> nameData = new ArrayList<>();
	protected static ArrayList<String> beginningName = new ArrayList<>();
	protected static ArrayList<String> middleName = new ArrayList<>();
	protected static ArrayList<String> endName = new ArrayList<>();
	protected static ArrayList<String> ageRange = new ArrayList<>();
	protected static ArrayList<String> ageRangeStatic = new ArrayList<>();
	protected static ArrayList<String> motivationSourceData = new ArrayList<>();
	protected static ArrayList<String> personalitySourceData = new ArrayList<>();
	protected static ArrayList<String> nicknameSourceData = new ArrayList<>();
	protected static ArrayList<String> detailsSourceData = new ArrayList<>();
	//Data for "Details" Subsection //
	protected static ArrayList<String> theLocalSourceData = new ArrayList<>();
	protected static ArrayList<String> favorToSourceData = new ArrayList<>();
	protected static ArrayList<String> protectedBySourceData = new ArrayList<>();
	protected static ArrayList<String> mapToSourceData = new ArrayList<>();
	protected static ArrayList<String> possessesASourceData = new ArrayList<>();
	protected static ArrayList<String> obsessedBySourceData = new ArrayList<>();
	protected static ArrayList<String> cursedBySourceData = new ArrayList<>();
	protected static ArrayList<RacialStatBlock> raceStatBlock = new ArrayList<>();
	private NodeList raceTag;
	private NodeList subraceTag;
	
	
	////////GETTERS & SETTERS////////

	public static ArrayList<String> getRaceSourceStatic() {
		return raceSourceStatic;
	}

	////////CONSTRUCTOR////////
	GenerateSourceData(){
		generateRacialStatsSourceData();
		raceSourceStatic.add(0, "Any Race");
		generateRaceData();		
		generateSubraceNames();
		generateProfessionData();
		generateNameSourceData();
		generateNameSectionData();
		generateAgeData();
		generateMotivationSourceData();
		generatePersonalitySourceData();
		generateNicknameSourceData();
		generateDetailsSourceData();
	}
	
	////////METHODS////////
	private void generateRaceData() {
		for(RacialStatBlock raceEntry: GenerateSourceData.raceStatBlock){
			if(!raceEntry.isSubrace){
				raceSourceStatic.add(raceEntry.name);
				//System.out.println(raceSourceStatic);
			}
		}
	}
	
	public void generateSubraceNames(){
		subraceSourceStatic.clear();
		for(RacialStatBlock entry: GenerateSourceData.raceStatBlock){
			if(entry.isSubrace){
				subraceSourceStatic.add(entry.name);
			}
		}
	}
	
	private void generateProfessionData(){
		
		File childProfessionSourceFile = new File("ProfessionsChild.txt");
		File adultProfessionSourceFile = new File("Professions.txt");
		
		try {
			
			ReadFromFile file = new ReadFromFile(adultProfessionSourceFile);
			
			adultProfessionSourcePreStatic = file.OpenFile();
			for(String s: adultProfessionSourcePreStatic){
				String[] line = s.split(":");
				adultProfessionSourceStatic.add(line[1]);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			ReadFromFile file = new ReadFromFile(childProfessionSourceFile);
			
			childProfessionSourcePreStatic = file.OpenFile();
			for(String s: childProfessionSourcePreStatic){
				String[] line = s.split(":");
				childProfessionSourceStatic.add(line[1]);
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void generateNameSourceData(){
		//The master list containing all Name options
		File nameListTargetFile = new File ("NameDefault.txt");
		//TODO: Expand this section to have different files for the different races. Should be able to if/else if this, the same as subrace.
		try {
			ReadFromFile file = new ReadFromFile(nameListTargetFile);
			nameData = file.OpenFile();
			
			//DEBUG TOOL: Check to see that the list is being created
		    /*
		    System.out.println("Name List:");
		    for(String out: nameList){
		        System.out.println(out);
		    }
		    */
			
		} catch(Exception e) {
			 System.out.println(e.getMessage());
		}
	}
	
	private void generateNameSectionData(){
		
		for(String entry: nameData){
			if(entry.contains("BEGINNING:")){
				beginningName.add(entry);
			} else if(entry.contains("MIDDLE:")) {
				middleName.add(entry);
			} else if(entry.contains("END:")) {
				endName.add(entry);
			}
		}
	}
	
	private void generateAgeData(){		
		ageRangeStatic.add("Child");
		ageRangeStatic.add("Young Adult");
		ageRangeStatic.add("Adult");
		ageRangeStatic.add("Old");
		ageRangeStatic.add("Very Old");
		
		ageRange.add("Child");
		ageRange.add("Young Adult");
		ageRange.add("Adult");
		ageRange.add("Old");
		ageRange.add("Very Old");
	}
	
	private void generateMotivationSourceData(){
		File motivationListTargetFile = new File("Motivations.txt");
		
		try {
			ReadFromFile file = new ReadFromFile(motivationListTargetFile);
			motivationSourceData = file.OpenFile();
		} catch(Exception e) {
			 System.out.println(e.getMessage());
		}
	}
	
	private void generatePersonalitySourceData() {
		File personalityListTargetFile = new File("Personalities.txt");
		
		try {
			ReadFromFile file = new ReadFromFile(personalityListTargetFile);
			personalitySourceData = file.OpenFile();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void generateNicknameSourceData(){
		File nicknameListTargetFile = new File("Nicknames.txt");
		
		try{
			ReadFromFile file = new ReadFromFile(nicknameListTargetFile);
			nicknameSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private void generateDetailsSourceData(){
		File detailsListTargetFile = new File ("Details.txt");
		try {
			ReadFromFile file = new ReadFromFile(detailsListTargetFile);
			detailsSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File theLocalReplacementTargetFile = new File("TheLocalReplacement.txt");
		try{
			ReadFromFile file = new ReadFromFile(theLocalReplacementTargetFile);
			theLocalSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File favorToReplacementTargetFile = new File("OwesFavorTo.txt");
		try{
			ReadFromFile file = new ReadFromFile(favorToReplacementTargetFile);
			favorToSourceData = file.OpenFile();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File protectedByReplacementFile = new File("ProtectedBy.txt");
		try{
			ReadFromFile file = new ReadFromFile(protectedByReplacementFile);
			protectedBySourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File mapToReplacementFile = new File("MapTo.txt");
		try{
			ReadFromFile file = new ReadFromFile(mapToReplacementFile);
			mapToSourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File possessesAReplacementFile = new File("PossessesA.txt");
		try{
			ReadFromFile file = new ReadFromFile(possessesAReplacementFile);
			possessesASourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File obsessedByReplacementFile = new File("ObsessedBy.txt");
		try {
			ReadFromFile file = new ReadFromFile(obsessedByReplacementFile);
			obsessedBySourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		File cursedReplacementFile = new File("Cursed.txt");
		try{
			ReadFromFile file = new ReadFromFile(cursedReplacementFile);
			cursedBySourceData = file.OpenFile();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
	private void generateRacialStatsSourceData(){
		String filesDirectory = (new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent() + File.separator).replace("%20", " ");
		
		try{
			File xmlSourceFile = new File(filesDirectory + "sourceData" + File.separator + "RaceOptions.xml");
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(xmlSourceFile);
			doc.getDocumentElement().normalize();
			
			raceTag = doc.getElementsByTagName("race");
			parseRacialStatSourceData(raceTag);
			
			subraceTag = doc.getElementsByTagName("subRace");
			parseRacialStatSourceData(subraceTag);
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private static void parseRacialStatSourceData(NodeList nodeList){
		
		NodeList nList = nodeList;
		
		//start looping through races
		for(int i = 0; i < nList.getLength(); i++){
			//set up items
			RacialStatBlock tempRace = new RacialStatBlock();
			Node raceNode = nList.item(i);
			NodeList raceList = raceNode.getChildNodes();
			
			//go through races child nodes
			for(int j = 0; j < raceList.getLength(); j++){
				Node cNode = raceList.item(j);
				//parse children
					switch(cNode.getNodeName().toLowerCase()){
				
					case "name": 
						if(!"name".isEmpty()){
							tempRace.setName(cNode.getTextContent());
						}				
						break;
					case "parentid":
						if(!"parentid".isEmpty()){
							tempRace.setParentID(cNode.getTextContent());
							if(!tempRace.parentID.equals("Not Set")){
								tempRace.setIsSubrace(true);
							}
						}
					case "size": 
						if(!"size".isEmpty()){
							tempRace.setSize(cNode.getTextContent());
						}
						break;
					case "speed": 
						if(!"speed".isEmpty()){
							tempRace.setSpeed(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "speedfly":
						if(!"speedfly".isEmpty()){
							tempRace.setFlySpeed(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "speedswim":
						if(!"speedswim".isEmpty()){
							tempRace.setSwimSpeed(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "language": 
						if(!"language".isEmpty()){
							tempRace.addLanguage(cNode.getTextContent());
						}
						break;
					case "bonusstr": 
						if(!"bonusstr".isEmpty()){
							tempRace.setBonusStr(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "bonusdex": 
						if(!"bonusdex".isEmpty()){
							tempRace.setBonusDex(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "bonuscon": 
						if(!"bonuscon".isEmpty()){
							tempRace.setBonusCon(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "bonusint": 
						if(!"bonusint".isEmpty()){
							tempRace.setBonusInt(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "bonuswis": 
						if(!"bonuswis".isEmpty()){
							tempRace.setBonusWis(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "bonuscha": 
						if(!"bonuscha".isEmpty()){
							tempRace.setBonusCha(Integer.parseInt(cNode.getTextContent()));
						}
						break;
					case "extra": 
						if(!"extra".isEmpty()){
							tempRace.addExtra(cNode.getTextContent());
						}				
						break;
					case "extrachoice": 
						if(!"extrachoice".isEmpty()){
							tempRace.addExtraChoice(cNode.getTextContent());
						}
						break;
					case "source": 
						if(!"source".isEmpty()){
							tempRace.setSource(cNode.getTextContent());
						}
						break;
					}
				}
			raceStatBlock.add(tempRace);
		}
	}

}

