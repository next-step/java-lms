package nextstep.sessions.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.type.SelectStatusType;

@Service("studentService")
public class StudentService {

	@Resource(name = "studentRepository")
	private StudentRepository studentRepository;

	@Transactional
	public void changeSelectType(Student student, SelectStatusType statusType) {
		studentRepository.save(student.changeSelectStatus(statusType));
	}
}
