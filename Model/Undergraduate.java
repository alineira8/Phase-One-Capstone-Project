package Model;

public class Undergraduate extends Student {


    public Undergraduate(String name, String email, int studentID, String department) {
        super(name, email, studentID, department);
    }


    public double calculateTuition() {
        return 5000;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Undergraduate";
    }
}

