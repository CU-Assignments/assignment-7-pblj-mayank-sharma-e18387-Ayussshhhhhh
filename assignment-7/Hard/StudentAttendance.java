public class StudentAttendance {
    private int studentId;
    private String name;
    private String date;
    private String status;

    public StudentAttendance(int studentId, String name, String date, String status) {
        this.studentId = studentId;
        this.name = name;
        this.date = date;
        this.status = status;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
}
