public class StudentCourseIds {
    private int studentId;
    private int courseId;

    public StudentCourseIds(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public StudentCourseIds() {

    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }
}
