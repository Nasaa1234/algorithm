package BD1;

public class Subject {
    public String code;
    public String name;
    public float credit;

    public Subject(String code, String name, float credit) {
        this.code = code;
        this.name = name;
        this.credit = credit;
    }
    @Override
    public String toString() {
        return code + "/" + name + "/" +  credit;
    }
}
