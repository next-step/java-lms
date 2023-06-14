package nextstep.courses.service;

import nextstep.courses.domain.Student;
import nextstep.courses.infrastructure.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("studentService")
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public int registerStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student findByStudentId(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Transactional
    public List<Student> findBySessionId(Long sessionId) {
        return studentRepository.findBySessionId(sessionId);
    }
}
