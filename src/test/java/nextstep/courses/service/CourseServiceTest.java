package nextstep.courses.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.Duration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionPaymentType;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.Sessions;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseServiceTest {


    private CourseRepository courseRepository;

    private Session session;
    private List<NsUser> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        session = new Session(0L,
                CourseTest.C1,
                1000L,
                SessionPaymentType.PAID,
                new NsUsers(userList),
                1,
                new Duration(LocalDateTime.now(), LocalDateTime.now().plusMonths(1L)),
                SessionStatus.ENROLLING,
                new CoverImage("pobi.png",500L, 300D, 200D)
        );
        courseRepository = new CourseRepository() {

            private final List<Course> courses = new ArrayList<>(
                    List.of(
                            new Course(0L, "JAVA", NsUserTest.JAVAJIGI.getId(), new Sessions(List.of(session)))));

            @Override
            public int save(Course course) {
                Optional<Course> result = this.courses.stream().filter(e -> e.equals(course)).findFirst()
                        .or(Optional::empty);
                if(result.isEmpty()){
                    return -1;
                }
                courses.add(course);
                return 1;
            }

            @Override
            public Course findById(Long id) {
                Optional<Course> result = this.courses.stream().filter(e -> e.equals(new Course("",id))).findFirst()
                        .or(Optional::empty);
                return result.get();
            }
        };
    }

    @Test
    @DisplayName("CourseService 강의 유저 등록")
    void enroll() {
        CourseService courseService = new CourseService(courseRepository);
        courseService.enroll(NsUserTest.SANJIGI,0L,0L);
        assertThat(userList).contains(NsUserTest.SANJIGI);
    }
}
