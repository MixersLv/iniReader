import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Logic {

    private final Scanner scanner;
    private final Map<String, Map<String, String>> sections = new HashMap<>();

    private String currentLine;
    private String sectionName;

    Logic(Scanner scanner) {
        this.scanner = scanner;
    }

    public void readini() {
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine().trim();
            if (currentLine.contains("#")) {
                //IGNORE AND KEEP READING
            } else if (currentLine.contains("[")) {
                ReadSection();
            } else if (!currentLine.contains("#") && !currentLine.contains("[") && !currentLine.isEmpty()) {
                readSetting();
            }

        }

    }

    public void ReadSection() {
        int index = currentLine.lastIndexOf("[");
        sectionName = currentLine.substring(index + 1, currentLine.length() - 1);
        sections.put(sectionName, new HashMap<>());
    }

    public void readSetting() {
        int index = currentLine.lastIndexOf("=");
        String key = currentLine.substring(0, index - 1);
        String value = currentLine.substring(index + 1).trim();

        Map<String, String> currentSection = sections.get(sectionName);
        currentSection.put(key, value);
    }

    //returns a String or null
    public String getValue(String searchString) {
        int dotindex = searchString.lastIndexOf(".");
        String sectionName = searchString.substring(0, dotindex);
        String settingName = searchString.substring(dotindex + 1);

        Map<String, String> innerMap = sections.get(sectionName);

        if (innerMap == null) {
            return null;
        }
        //outer map exists
        return innerMap.get(settingName);

    }


    /**
     * @param searchString The search string "section.setting"
     * @param value        the value to put in the map
     */
    //setValue("blah.blih", "someValue")
    public void setValue(String searchString, String value) {
        int dotindex = searchString.lastIndexOf(".");
        String sectionName = searchString.substring(0, dotindex);
        String settingName = searchString.substring(dotindex + 1);

        Map<String, String> innerMap = sections.get(sectionName);

        if (innerMap == null) {
            innerMap = new HashMap<>();
            sections.put(sectionName, innerMap);
        }
        //innerMap will always exist
        innerMap.put(settingName, value);
    }

    public void save(Path file) throws IOException {
        //open output stream to write to a file
        OutputStream out = Files.newOutputStream(file);
        //convert OutputStream to PrintStream for convenience
        PrintStream p = new PrintStream(out);
        //Iterate through all the entries of the sections Map for the INI file.
        for (Map.Entry<String, Map<String, String>> section : sections.entrySet()) {
            String sectionName = section.getKey();
            Map<String, String> settings = section.getValue();

            //Write the section name in brackets to the file
            p.println("[" + sectionName + "]");

            //iterate through all the settings for this section
            for (Map.Entry<String, String> setting : settings.entrySet()) {
                //write them as key=value pairs to the ini file.
                p.println(setting.getKey() + " = " + setting.getValue());
            }
            //add an empty line before starting with the next section in the iteration
            p.println();
        }
    }
}
