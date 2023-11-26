import java.util.Scanner;

public class Logic {

    //TODO
    // possibly make it so each time a new line is read it resets all statuses
    private String section = "";
    private String setting = "";
    private String key = "";
    private Scanner scanner;
    private String curentCharacter;
    private int currentIndex;
    boolean commentStatus = false;
    boolean sectionStatus = false;
    boolean settingStatus = false;
    boolean keyStatus = false;
    boolean sameSection = false;

    Logic(Scanner scanner){
        this.scanner = scanner;
    }

    public void read() {

        commentStatus = false;
        sectionStatus = false;
        settingStatus = false;
        keyStatus = false;

        while(scanner.hasNextLine()) {
            String currentLine = scanner.nextLine().trim();

            for (int i = 0; i < currentLine.length(); i++) {
                currentIndex = i;
                curentCharacter = String.valueOf(currentLine.charAt(i));

                if (curentCharacter.equals("#")){
                    commentStatus = true;
                }
                if(commentStatus == true){
                    readComment();
                }
                //
                if (sameSection == true){
                    readSetting();
                }
                if(curentCharacter.equals("[")){
                    sectionStatus = true;
                    sameSection = true;
                }
                if (sectionStatus == true){
                    readSection();
                }
                //
                if (settingStatus == true){
                    readSetting();
                }
                if (keyStatus == true){
                    readKey();
                }


            }

            System.out.println("SECTION: " + section);
            System.out.println("SETTING: " + setting);
            System.out.println("KEY: " + key);

            System.out.println("--------NEXT LINE----------");
            section = "";
            setting = "";
            key = "";

        }
    }

    public void readComment(){
        if (curentCharacter.equals("[")){
            commentStatus = false;
            sameSection =false;
        }
    }

    public void readSection(){
        if (curentCharacter.equals("]")){
            sectionStatus = false;
            settingStatus = true;
            sameSection = true;
        }
        section = section+curentCharacter;
    }

    public void readSetting(){
        if (curentCharacter.equals("=")){
            settingStatus = false;
            keyStatus = true;
        }
        setting = setting + curentCharacter;
    }

    public void readKey(){
        key = key + curentCharacter;

    }


}