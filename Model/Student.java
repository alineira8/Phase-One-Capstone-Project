package Model;
import java.util.HashMap;
import java.util.Map;

public class Student extends Person {

    private String studentID;
    private String department;
    private double gpa;

    private Map<Course, Double> courses = new HashMap<>();

    public Student(String name, String email, String studentID, String department) {
        super(name, email);
        this.studentID = studentID;
        this.department = department;
        this.gpa = 0.0;
    }

    public double calculateTuition() {
        return 0.0;
    }

    public void updateGpa(double newGpa) {
        if (newGpa >= 0 && newGpa <= 4.0) {
            gpa = newGpa;
        }
    }

    public void enrollInCourse(Course course) {
        courses.put(course, 0.0);
        course.enrollStudent(this);
    }

    public void assignGrade(Course course, double grade) {
        if (courses.containsKey(course)) {
            courses.put(course, grade);
        }
    }

    public Map<Course, Double> getCourses() {
        return courses;
    }

    @Override
    
    public String getRole() {
        return "Student";
    }


    public void displayStudentInfo() {
        System.out.println("Name: " + getName() + ", ID: " + studentID + ", Department: " + department);
    }

    public void displayCourses() {
        System.out.println("Courses and Grades:");
        for (Map.Entry<Course, Double> entry : courses.entrySet()) {
            System.out.println(entry.getKey().getCourseName() + " : " + entry.getValue());
        }
    }

    public String getId() {
    return studentID; 
}
}