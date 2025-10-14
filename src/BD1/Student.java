package BD1;

import dataStructures.Chain;

public class Student {
    public String code;
    public float GPA;
    public Chain lessons;

    public Student(String code) {
        this.code = code;
        this.GPA = 0;
        this.lessons = new Chain();
    }

    @Override
    public String toString(){
        return code;
    }

    public void calculateGPA(){}

}
