import java.io.*;
import java.util.*;

public class FileManager {

    private static final String STUDENT_FILE = "students.txt";
    private static final String COURSE_FILE = "courses.txt";
    private static final String ENROLLMENT_FILE = "enrollments.txt";

    // ======================================================
    // SAVE ALL DATA
    // ======================================================
    public static void saveAll(List<Student> students, List<Course> courses) {
        saveStudents(students);
        saveCourses(courses);
        saveEnrollments(students);
    }

    // ======================================================
    // LOAD ALL DATA
    // ======================================================
    public static void loadAll(List<Student> students, List<Course> courses) {
        loadStudents(students);
        loadCourses(courses);
        loadEnrollments(students, courses);
    }

    // ======================================================
    // SAVE STUDENTS
    // Format:
    // type,studentID,name,email,department,gpa
    // ======================================================
    private static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {

            for (Student s : students) {
                writer.write(
                        s.getClass().getSimpleName() + "," +
                        s.getStudentID() + "," +
                        s.getName() + "," +
                        s.getEmail() + "," +
                        s.getDepartment() + "," +
                        s.getGpa()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    // ======================================================
    // LOAD STUDENTS
    // ======================================================
    private static void loadStudents(List<Student> students) {

        File file = new File(STUDENT_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String type = data[0];
                String studentID = data[1];
                String name = data[2];
                String email = data[3];
                String department = data[4];
                double gpa = Double.parseDouble(data[5]);

                Student s;

                if (type.equals("Graduate")) {
                    s = new Graduate(name, email, studentID, department);
                } else {
                    s = new Undergraduate(name, email, studentID, department);
                }

                s.setGpa(gpa);
                students.add(s);
            }

        } catch (IOException e) {
            System.out.println("Error loading students.");
        }
    }

    // ======================================================
    // SAVE COURSES
    // Format:
    // courseID,courseName,credits
    // ======================================================
    private static void saveCourses(List<Course> courses) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSE_FILE))) {

            for (Course c : courses) {
                writer.write(
                        c.getCourseID() + "," +
                        c.getCourseName() + "," +
                        c.getCredits()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }

    // ======================================================
    // LOAD COURSES
    // ======================================================
    private static void loadCourses(List<Course> courses) {

        File file = new File(COURSE_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(COURSE_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String courseID = data[0];
                String courseName = data[1];
                int credits = Integer.parseInt(data[2]);

                courses.add(new Course(courseID, courseName, credits));
            }

        } catch (IOException e) {
            System.out.println("Error loading courses.");
        }
    }

    // ======================================================
    // SAVE ENROLLMENTS
    // Format:
    // studentID,courseID,grade
    // ======================================================
    private static void saveEnrollments(List<Student> students) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ENROLLMENT_FILE))) {

            for (Student s : students) {

                for (Map.Entry<Course, Double> entry :
                        s.getEnrolledCourses().entrySet()) {

                    writer.write(
                            s.getStudentID() + "," +
                            entry.getKey().getCourseID() + "," +
                            entry.getValue()
                    );
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error saving enrollments.");
        }
    }

    // ======================================================
    // LOAD ENROLLMENTS (Reconnect relationships)
    // ======================================================
    private static void loadEnrollments(List<Student> students, List<Course> courses) {

        File file = new File(ENROLLMENT_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(ENROLLMENT_FILE))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String studentID = data[0];
                String courseID = data[1];
                double grade = Double.parseDouble(data[2]);

                Student student = findStudentById(students, studentID);
                Course course = findCourseById(courses, courseID);

                if (student != null && course != null) {
                    student.enrollCourse(course, grade);
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading enrollments.");
        }
    }

    // ======================================================
    // Helper Methods
    // ======================================================
    private static Student findStudentById(List<Student> students, String id) {
        for (Student s : students) {
            if (s.getStudentID().equals(id)) return s;
        }
        return null;
    }

    private static Course findCourseById(List<Course> courses, String id) {
        for (Course c : courses) {
            if (c.getCourseID().equals(id)) return c;
        }
        return null;
    }
}