package nextstep.courses.service;

import nextstep.courses.domain.session.Teacher;
import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.repository.TeacherRepository;
import nextstep.courses.domain.session.student.Student;
import nextstep.courses.dto.SelectInfo;

public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public TeacherService(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    public void select(SelectInfo selectInfo) {
        Teacher teacher = teacherRepository.findById(selectInfo.getTeacherId())
            .orElseThrow(() -> new IllegalArgumentException("존재하는 강사가 없습니다."));
        Student student = studentRepository.findById(selectInfo.getStudentId())
            .orElseThrow(() -> new IllegalArgumentException("존재하는 수강생이 없습니다."));

        Student statusChanged = teacher.select(student, selectInfo.getEnrollStatus());

        studentRepository.update(statusChanged);
    }
}
