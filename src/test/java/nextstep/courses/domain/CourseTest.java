package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    private Course course;

    @BeforeEach
    void setup(){
        course = new Course();
    }
    @Test
    @DisplayName("과정 객체 생성 case 1 - 기수 필드 여부")
    void course_has_generation_field(){
        assertThat(course)
                .hasFieldOrProperty("generation");
    }





}
