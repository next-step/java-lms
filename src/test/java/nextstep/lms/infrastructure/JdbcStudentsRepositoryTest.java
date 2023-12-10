package nextstep.lms.infrastructure;

import nextstep.lms.domain.Student;
import nextstep.lms.domain.Students;
import nextstep.lms.enums.StudentStatusEnum;
import nextstep.lms.repository.StudentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@JdbcTest
class JdbcStudentsRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private StudentsRepository studentsRepository;

    @BeforeEach
    void setUp() {
        studentsRepository = new JdbcStudentsRepository(jdbcTemplate);
    }

    @Test
    void create_read_update() {
        Student student1 = new Student(1L, 1L);
        Student student2 = new Student(2L, 1L);
        Student student3 = new Student(3L, 1L);

        //create
        int savedCount = studentsRepository.save(student1);
        savedCount += studentsRepository.save(student2);
        savedCount += studentsRepository.save(student3);
        assertThat(savedCount).isEqualTo(3);

        //read
        Students savedStudents = studentsRepository.findBySession(1L);
        assertThat(savedStudents.getStudents()).contains(student1, student2, student3);

        //update
        Student expectedStudent1 = new Student(1L, 1L, StudentStatusEnum.NON_SELECTED.name());
        Student expectedStudent2 = new Student(2L, 1L, StudentStatusEnum.SELECTED.name());
        int updatedCount = studentsRepository.updateStatus(expectedStudent1);
        updatedCount += studentsRepository.updateStatus(expectedStudent2);
        assertThat(updatedCount).isEqualTo(2);

        //read
        Students updatedStudents = studentsRepository.findBySession(1L);
        assertThat(updatedStudents.getStudents()).contains(expectedStudent1, expectedStudent2, student3);
    }

}