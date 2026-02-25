//package Model;

public class Undergraduate extends Student {

    private static final double FLAT_RATE = 5000.0;

    public Undergraduate(String name, String email, String studentID, String department) {
        super(name, email, studentID, department);
    }

    @Override
    public double calculateTuition() {
        return FLAT_RATE; // flat rate regardless of enrolled credits
    }

    @Override
    public String getRole() {
        return "Undergraduate Student";
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Undergraduate";
    }
}