import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Logic {

    private final Scanner scanner;
    private String currentLine;
    private String sectionName;
    private String tempSectionName = sectionName;
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

    public void getValue(String sNameSkey){

        int dotIndex = sNameSkey.lastIndexOf(".");
        String sName = sNameSkey.substring(0,dotIndex);
        String sValue = sNameSkey.substring(dotIndex+1,sNameSkey.length());

        String sKey = null;
        // finds whats the key name for the value
        if(sections.containsKey(sName)){
            Map<String,String> outermap = sections.get(sName);

            for(String key : outermap.keySet()){

                if (key.equals(sValue)){
                   sKey = key;
                }
            }


            if(outermap.containsKey(sValue)){

                if(outermap.get(sKey).isEmpty()){
                    System.out.println("Key: "+ sKey +"\nValue: null");
                }
                else{
                    System.out.println("Key: "+ sKey +"\nValue:" + outermap.get(sKey));
                }

            }

        }

        else{
            System.out.println("Key does not exist");
        }

    }

}
