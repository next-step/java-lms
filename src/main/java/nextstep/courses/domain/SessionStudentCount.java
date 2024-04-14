package nextstep.courses.domain;

public class SessionStudentCount {
    private int studentCount;
    private int maxStudentCount;

    public SessionStudentCount(int studentCount, int maxStudentCount) {
        this.studentCount = studentCount;
        this.maxStudentCount = maxStudentCount;
        validateStudentCount();
    }

    public void addStudent(){
        studentCount++;
        validateStudentCount();
    }

    public void validateStudentCount(){
        if(studentCount > maxStudentCount){
            throw new IllegalArgumentException("강의 최대 수강 인원을 초과할 수 없습니다.");
        }
    }
}