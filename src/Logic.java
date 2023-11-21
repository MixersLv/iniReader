import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Logic {

    private final Scanner scanner;
    private String currentLine;
    private String sectionName;
    private String key;
    private String value;
    private Map<String, Map<String, String>> sections = new HashMap<>();

    Logic(Scanner scanner) {
    this.scanner = scanner;
    }
    public void readini(){

        while(scanner.hasNextLine()){
            currentLine = scanner.nextLine().trim();
            System.out.println(currentLine);
            if (currentLine.contains("#")){
                //IGNORE AND KEEP READING
            }
            else if(currentLine.contains("[")){
                ReadSection();
            }
            else if(!currentLine.contains("#") && !currentLine.contains("[") && !currentLine.isEmpty()){
                readSetting();
            }

        }

    }

    public void ReadSection(){
        int index = currentLine.lastIndexOf("[");
        sectionName = currentLine.substring(index +1, currentLine.length()-1);
        sections.put(sectionName, new HashMap<>());
    }

    public void readSetting(){
        int index = currentLine.lastIndexOf("=");
        key = currentLine.substring(0,index-1);
        value = currentLine.substring(index+1, currentLine.length());

        Map<String, String> currentSection = sections.get(sectionName);
        currentSection.put(key,value);
    }

    public String getValue(String searchString){
        int dotindex = searchString.lastIndexOf(".");
            String sectionName = searchString.substring(0, dotindex);
            String settingName = searchString.substring(dotindex+1, searchString.length());

        if(sections.containsKey(sectionName)){
            Map<String,String> outermap = sections.get(sectionName);

            if(outermap.containsKey(settingName)){

                if(outermap.get(settingName).isEmpty()){
                    System.out.println("Key: "+ settingName +"\nValue: null"); // just visual
                    return value = null;
                }
                else{
                    System.out.println("Key: "+ settingName +"\nValue:" + outermap.get(settingName)); // just visual
                    value = outermap.get(settingName);
                }

            }

        }

        else{
            System.out.println("Key does not exist");
        }
        return value;

    }


}
