package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.exception.StudentsException;
import nextstep.session.domain.Student;
import nextstep.session.domain.StudentService;
import nextstep.session.domain.Students;
import nextstep.session.dto.StudentVO;
import nextstep.session.domain.StudentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    public static final int NO_UPDATE_COUNT = 0;
    @Resource(name = "studentRepository")
    private StudentRepository studentRepository;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    public Students findBySessionId(long sessionId) {
        List<StudentVO> studentsDto = studentRepository.findBySessionId(sessionId);
        List<Student> students = studentsDto.stream()
                .map(studentVO ->
                        new Student(
                                studentVO.getId(),
                                studentVO.getSessionId(),
                                studentVO.getUserId(),
                                new BaseEntity(studentVO.isDeleted(), studentVO.getCreatedAt(), studentVO.getLastModifiedAt())
                        )
                ).collect(Collectors.toList());

        return new Students(students);
    }

    @Override
    public Long save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public DeleteHistory delete(NsUser requestStudent, Student student) {
        student.delete();
        StudentVO studentVO = student.toVO();

        int updateResult = studentRepository.updateDeleteStatus(studentVO.getSessionId(), studentVO.getUserId(), true);
        validateUpdateResult(updateResult);

        return DeleteHistory.createStudent(studentVO.getId(), requestStudent, LocalDateTime.now());
    }

    private void validateUpdateResult(int updateResult) {
        if (updateResult <= NO_UPDATE_COUNT) {
            throw new StudentsException("이미 삭제 되었거나, 삭제할 대상이 없습니다.");
        }
    }

    @Override
    public DeleteHistoryTargets deleteAll(Students targetStudents, NsUser requestUser) {
        return targetStudents.deleteAll(requestUser);
    }
}
