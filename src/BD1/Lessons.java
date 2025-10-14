package BD1;

public class Lessons {
    public Subject learned;
    public int score;

    public Lessons(Subject learned, int score) {
        this.learned = learned;
        this.score = score;
    }

    @Override
    public String toString() {
        return learned.code + ": " + score;
    }


}
