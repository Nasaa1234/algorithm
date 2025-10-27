package BD1;

import lab.functions.MyArrayLinearList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registration {
    public MyArrayLinearList studentList;
    public MyArrayLinearList subjectList;
    public MyArrayLinearList majorList;

    public Registration() {
        studentList = new MyArrayLinearList();
        subjectList = new MyArrayLinearList();
        majorList = new MyArrayLinearList();
    }

    public void loadSubjects(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] values = line.split("/");
                Subject s = new Subject(values[0], values[1], Float.parseFloat(values[2]));
                subjectList.add(subjectList.size(), s);
            }
        }
    }

    public void loadMajors(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = input.readLine()) != null) {
                String[] values = line.split("/");
                Major m = new Major(values[0], values[1]);
                majorList.add(majorList.size(), m);
            }
        }
    }

    public void loadStudents(String fileName) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(fileName))) {
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
        }
    }

    private Subject findSubjectByCode(String code) {
        for (int i = 0; i < subjectList.size(); i++) {
            Subject s = (Subject) subjectList.get(i);
            if (s.code.equalsIgnoreCase(code)) return s;
        }
        return null;
    }

    public void showAllSubjects() {
        System.out.println("=== Subjects ===");
        for (int i = 0; i < subjectList.size(); i++) {
            System.out.println(subjectList.get(i));
        }
    }

    public void showAllMajors() {
        System.out.println("=== Majors ===");
        for (int i = 0; i < majorList.size(); i++) {
            System.out.println(majorList.get(i));
        }
    }

    public void showAllStudents() {
        System.out.println("=== Students ===");
        for (int i = 0; i < studentList.size(); i++) {
            Student s = (Student) studentList.get(i);
            System.out.println(s);
            for (int j = 0; j < s.lessons.size(); j++) {
                System.out.println("   " + s.lessons.get(j));
            }
        }
    }
    public void showFailedStudents() {
        System.out.println("=== Students with F in 3 or more subjects ===");
        for (int i = 0; i < studentList.size(); i++) {
            Student s = (Student) studentList.get(i);
            int fCount = 0;
            for (int j = 0; j < s.lessons.size(); j++) {
                Lessons l = (Lessons) s.lessons.get(j);
                if (l.score < 60) fCount++;
            }
            if (fCount >= 3) {
                System.out.println(s.code + " - F in " + fCount + " subjects");
            }
        }
    }

    public void showGradesBySubject() {
        System.out.println("=== Grades by Subject ===");
        for (int i = 0; i < subjectList.size(); i++) {
            Subject subj = (Subject) subjectList.get(i);
            System.out.println("Subject: " + subj.code + " - " + subj.name);
            for (int j = 0; j < studentList.size(); j++) {
                Student s = (Student) studentList.get(j);
                for (int k = 0; k < s.lessons.size(); k++) {
                    Lessons l = (Lessons) s.lessons.get(k);
                    if (l.learned.code.equalsIgnoreCase(subj.code)) {
                        System.out.println("   " + s.code + ": " + l.score);
                    }
                }
            }
        }
    }

    public void     showGradesByMajor() {
        System.out.println("=== Grades by Major ===");
        for (int i = 0; i < majorList.size(); i++) {
            Major m = (Major) majorList.get(i);
            System.out.println("Major: " + m.code + " - " + m.name);
            for (int j = 0; j < studentList.size(); j++) {
                Student s = (Student) studentList.get(j);
                if (s.code.startsWith(m.code)) {
                    System.out.println("   " + s.code + " - GPA: " + s.GPA);
                    for (int k = 0; k < s.lessons.size(); k++) {
                        Lessons l = (Lessons) s.lessons.get(k);
                        System.out.println("       " + l.learned.code + ": " + l.score);
                    }
                }
            }
        }
    }
}
