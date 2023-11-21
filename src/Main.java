import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {

    // TODO
    // make it so it can handle any way the ini is written, basicly change how it reads them


    public static void main(String[] args) throws IOException {

        Path config = Path.of("./pcparts.ini");

        Scanner scanner = new Scanner(config);

        Logic logic = new Logic(scanner);
        logic.readini();

        System.out.println("--------------------------------");
        String value = logic.getValue("cables.cable2");

        System.out.println("-----\nStored value in the value string is: " + value); // just visual for making sure

    }

}
