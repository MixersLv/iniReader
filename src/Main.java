import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static String out = "blah";
    // TODO
    // make it so it can handle any way the ini is written, basicly change how it reads them


    public static void main(String[] args) throws IOException {
        Path config = Path.of("./pcparts.ini");

        Scanner scanner = new Scanner(config);

        Logic logic = new Logic(scanner);
        logic.readini();

        logic.save(config);
        System.out.println("--------------------------------");
        String value = logic.getValue("cables.cable2");

        System.out.println("-----\nStored value in the value string is: " + value); // just visual for making sure

    }
}
