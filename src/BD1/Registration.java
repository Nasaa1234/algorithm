package BD1;

import dataStructures.ArrayLinearList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Registration {
    public ArrayLinearList studentList;
    public ArrayLinearList subjectList;
    public ArrayLinearList majorList;

    public Registration() {
        studentList = new ArrayLinearList();
        subjectList = new ArrayLinearList();
        majorList = new ArrayLinearList();
    }

    public void loadSubjects(String fileName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;


        int index = 0;
        while ((line = input.readLine()) != null) {
            String[] values = line.split("/");
            Subject s = new Subject(values[0], values[1], Float.parseFloat(values[2]));
            subjectList.add(subjectList.size(), s);
        }
        input.close();
    }

    public void loadMajors(String fileName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = input.readLine()) != null) {
            String[] values = line.split("/");
            Major m = new Major(values[0], values[1]);
            majorList.add(majorList.size(), m);
        }
        input.close();
    }

    public void loadStudents(String fileName) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(fileName));
        String line;
        Map<String, Student> studentMap = new HashMap<>();

        while ((line = input.readLine()) != null) {
            String[] values = line.split("/");
            String studentCode = values[0];
            String lessonCode = values[1];
            int score = Integer.parseInt(values[2]);

            Student st = studentMap.getOrDefault(studentCode, new Student(studentCode));
            Subject subj = findSubjectByCode(lessonCode);
            if (subj != null) {
                st.lessons.add(st.lessons.size(), new Lessons(subj, score));
            }
            studentMap.put(studentCode, st);
        }

        for (Student s : studentMap.values()) {
            s.calculateGPA();
            studentList.add(studentList.size(), s);
        }

        input.close();
    }

    private Subject findSubjectByCode(String code) {
        for (int i = 0; i < subjectList.size(); i++) {
            Subject s = (Subject) subjectList.get(i);
            if (s.code.equals(code)) return s;
        }
        return null;
    }
}
