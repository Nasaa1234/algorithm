package BD1;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("Current working dir: " + System.getProperty("user.dir"));
        System.out.println("Hello World");
        Registration reg = new Registration();
        reg.loadSubjects("src/BD1/database/Subjects.txt");
        reg.loadSubjects("src/BD1/database/Subjects.txt");
        reg.loadSubjects("src/BD1/database/Subjects.txt");
    }
}
