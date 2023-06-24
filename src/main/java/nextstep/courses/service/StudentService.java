package nextstep.courses.service;

import nextstep.courses.domain.ApprovalState;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
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
    public int requestSessionApproval(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public Student findById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public List<Student> findBySessionId(Long sessionId) {
        return studentRepository.findBySessionId(sessionId);
    }

    @Transactional
    public int approveStudent(Long sessionId, Long id) {
        return studentRepository.updateApprovalState(sessionId, id, ApprovalState.APPROVAL);
    }

    @Transactional
    public int approveCancelStudent(Long sessionId, Long id) {
        return studentRepository.updateApprovalState(sessionId, id, ApprovalState.UN_APPROVAL);
    }
}
