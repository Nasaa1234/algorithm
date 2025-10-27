package BD1;

import lab.functions.MyArrayLinearList;

import java.util.ArrayList;

public class Student {
    public String code;
    public float GPA;
    public MyArrayLinearList lessons;

    public Student(String code) {
        this.code = code;
        this.lessons = new MyArrayLinearList();
        this.GPA = 0;
    }

    public void calculateGPA() {
        float totalCredits = 0;
        float totalPoints = 0;

        for (int i = 0; i < lessons.size(); i++) {
            Lessons l = (Lessons) lessons.get(i);
            totalCredits += l.learned.credit;
            float gradePoint = convertScoreToGPA(l.score);
            totalPoints += gradePoint * l.learned.credit;
        }

        this.GPA = totalCredits > 0 ? totalPoints / totalCredits : 0;
    }
    private float convertScoreToGPA(int score) {
        if (score >= 90) return 4.0f;
        else if (score >= 80) return 3.0f;
        else if (score >= 70) return 2.0f;
        else if (score >= 60) return 1.0f;
        else return 0.0f;
    }

    @Override
    public String toString() {
        return code + " - GPA: " + GPA;
    }
}
