import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Path config = Path.of("C:\\Users\\123\\IdeaProjects\\iniReader\\src\\pcparts.ini");

        Scanner scanner = new Scanner(config);

        Logic logic = new Logic(scanner);
        logic.readini();
        System.out.println("-----------------------------------");
        logic.getValue("cables.cable2"); // can enter whatever section.setting want

    }

}
