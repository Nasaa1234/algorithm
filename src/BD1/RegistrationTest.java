package BD1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest {

    private Registration reg;

    @BeforeEach
    void setup() throws IOException {
        reg = new Registration();

        createTestFiles();
        reg.loadSubjects("Subjects_test.txt");
        reg.loadMajors("Majors_test.txt");
        reg.loadStudents("Exams_test.txt");
    }

    private void createTestFiles() throws IOException {
        try (FileWriter fw = new FileWriter("Subjects_test.txt")) {
            fw.write("CS101/Programming/3.0\n");
            fw.write("CS102/Algorithms/3.0\n");
        }

        try (FileWriter fw = new FileWriter("Majors_test.txt")) {
            fw.write("SW/Software Engineering\n");
        }

        try (FileWriter fw = new FileWriter("Exams_test.txt")) {
            fw.write("SW001/CS101/90\n");
            fw.write("SW001/CS102/50\n"); // 1 F
            fw.write("SW002/CS101/40\n");
            fw.write("SW002/CS102/45\n"); // 2 F
        }
    }

    @Test
    void testLoadSubjects() {
        assertEquals(2, reg.subjectList.size());
        Subject s = (Subject) reg.subjectList.get(0);
        assertEquals("CS101", s.code);
        assertEquals(3.0f, s.credit);
    }

    @Test
    void testLoadMajors() {
        assertEquals(1, reg.majorList.size());
        Major m = (Major) reg.majorList.get(0);
        assertEquals("SW", m.code);
    }

    @Test
    void testLoadStudentsAndGPA() {
        assertEquals(2, reg.studentList.size());
        Student st = (Student) reg.studentList.get(0);
        assertNotEquals(0, st.GPA);
        assertEquals(2, st.lessons.size());
    }

    @Test
    void testFindFailedStudents() {
        boolean hasFailed3 = false;
        for (int i = 0; i < reg.studentList.size(); i++) {
            Student s = (Student) reg.studentList.get(i);
            int fCount = 0;
            for (int j = 0; j < s.lessons.size(); j++) {
                Lessons l = (Lessons) s.lessons.get(j);
                if (l.score < 60)
                    fCount++;
            }
            if (fCount >= 3)
                hasFailed3 = true;
        }
        assertFalse(hasFailed3);
    }

    @Test
    void testSubjectGradesMapping() {
        Student st = (Student) reg.studentList.get(0);
        Lessons ls = (Lessons) st.lessons.get(0);
        assertNotNull(ls.learned);
        assertTrue(ls.score >= 0);
    }
}
