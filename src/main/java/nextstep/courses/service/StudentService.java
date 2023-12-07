package nextstep.courses.service;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("studentService")
public class StudentService {
    @Resource(name = "studentRepository")
    private StudentRepository studentRepository;

    @Transactional
    public void signUpStudent(String name) {
        Student student = new Student(name);
        studentRepository.save(student);
    }
}
