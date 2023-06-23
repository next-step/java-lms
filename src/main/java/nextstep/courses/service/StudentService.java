package nextstep.courses.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("studentService")
public class StudentService {

//    @Autowired
//    private StudentRepository studentRepository;
//
//    public StudentService(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//    public int registerStudent(Student student) {
//        return studentRepository.save(student);
//    }
//
//    public Student findByStudentId(Long studentId) {
//        return studentRepository.findById(studentId);
//    }
//
//    public List<Student> findBySessionId(Long sessionId) {
//        return studentRepository.findAllBySessionId(sessionId);
//    }
}
