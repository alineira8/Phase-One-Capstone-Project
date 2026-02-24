package Model;

public class Graduate extends Student {

    private double researchFee;


    public Graduate(String name, String email, int studentID, String department) {
        super(name, email, studentID, department);
        this.researchFee = 0.0; 
    }


    public double getResearchFee() {
        return researchFee;
    }

    public void setResearchFee(double researchFee) {
        this.researchFee = researchFee;
    }


    public double calculateTuition(int totalCredits) {
        return totalCredits * 300 + researchFee;
    }

    @Override
    public String toString() {
        return super.toString() + " | Type: Graduate";
    }


    @Override
    public double calculateTuition() {
        throw new UnsupportedOperationException("Unimplemented method 'calculateTuition'");
    }
}