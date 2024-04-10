package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.exception.StudentsException;
import nextstep.session.domain.Student;
import nextstep.session.domain.Students;
import nextstep.session.dto.StudentDto;
import nextstep.session.infrastructure.StudentRepository;
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
        List<StudentDto> studentsDto = studentRepository.findBySessionId(sessionId);
        List<Student> students = studentsDto.stream()
                .map(studentDto ->
                        new Student(
                                studentDto.getId(),
                                studentDto.getSessionId(),
                                userService.findByUserId(studentDto.getUserId()),
                                new BaseEntity(studentDto.isDeleted(), studentDto.getCreatedAt(), studentDto.getLastModifiedAt())
                        )
                ).collect(Collectors.toList());

        return new Students(students);
    }

    @Override
    public Long save(Student student) {
        return studentRepository.save(student.toDto());
    }

    @Override
    public DeleteHistory delete(NsUser requestStudent, Student student) {
        student.delete();
        StudentDto studentDto = student.toDto();

        int updateResult = studentRepository.updateDeleteStatus(studentDto.getSessionId(), studentDto.getUserId(), true);
        validateUpdateResult(updateResult);

        return DeleteHistory.createStudent(studentDto.getId(), requestStudent, LocalDateTime.now());
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
