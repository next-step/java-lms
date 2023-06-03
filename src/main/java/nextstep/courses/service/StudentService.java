package nextstep.courses.service;

import nextstep.courses.domain.StudentRepository;
import nextstep.courses.domain.enrollment.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public int registerStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findByStudentId(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Student> findBySessionId(Long sessionId) {
        return studentRepository.findBySessionId(sessionId);
    }
}
