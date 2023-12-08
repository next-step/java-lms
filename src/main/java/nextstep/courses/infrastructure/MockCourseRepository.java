package nextstep.courses.infrastructure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;

public class MockCourseRepository implements CourseRepository {

    private Map<Integer, Course> courseStorage = new LinkedHashMap<>();

    public MockCourseRepository() {
        this.courseStorage.put(1, new Course(1L, "코스", 1L));
    }

    @Override
    public int save(Course course) {
        long courseId = Optional.ofNullable(course.getId())
                .orElseGet(() -> (long) (courseStorage.size() + 1));
        int intCourseId = Math.toIntExact(courseId);
        courseStorage.put(intCourseId, course);
        return intCourseId;
    }

    @Override
    public Course findById(Long id) {
        return Optional.ofNullable(courseStorage.get(Integer.parseInt(String.valueOf(id))))
                .orElseThrow(() -> new IllegalStateException("과정을 찾을 수 없습니다."));
    }
}
