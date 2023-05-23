package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private  Session session ;

    @BeforeEach
    void setup() {
        session = new Session();
    }

    @Test
    @DisplayName("강의 객체 생성 case 1 - 시작일 종료일 필드 여부")
    void session_has_field_startDate_endDate_test(){
        assertThat(session)
                .hasFieldOrProperty("startDate")
                .hasFieldOrProperty("endDate");
    }

    @Test
    @DisplayName("강의 객체 생성 case 2 - 커버 이미지 정보 필드 여부")
    void session_has_field_thumbnail_info_test(){
        assertThat(session)
                .hasFieldOrProperty("thumbnailInfo");
    }

    @Test
    @DisplayName("강의 객체 생성 case 3 - 강의 종류(무료/유료) 필드 여부")
    void session_has_field_paid_test(){
        assertThat(session)
                .hasFieldOrProperty("isPaid");
    }

    @Test
    @DisplayName("강의 객체 생성 case 4 - 강의 상태 필드 여부")
    void session_has_field_status_test(){
        assertThat(session)
                .hasFieldOrProperty("status");
    }


    @Test
    @DisplayName("강의 객체 생성 case 5 - 강의 최대 수강 인원 필드 여부")
    void session_has_field_maxium_test(){
        assertThat(session)
                .hasFieldOrProperty("maximumEnrollments");
    }


    @Test
    @DisplayName("강의 최대 수강 인원을 넘을 수 없다.")
    void impossible_over_maximum_enrollments(){
        assertThatThrownBy(() -> {
            for (int i = 0; i < 31; i++) {
                session.plusEnrollment();
            }
        }).isInstanceOf(Exception.class)
                .hasMessage("해당 강의는 수강인원을 초과 하였습니다.");
    }

    @DisplayName("강의 수강신청 가능여부 판단")
    @ParameterizedTest
    @CsvSource(value = {"READY:false","RECRUIT:true","CLOSED:false"}, delimiter = ':')
    void test_course_registration(SessionStatusEnum status, boolean result){
        session.statusChange(status);
        assertThat(session.isPossible()).isEqualTo(result);
    }

    @Test
    @DisplayName("강의 상태는 준비중, 모집중, 종료")
    void session_status_test(){
        assertThat(SessionStatusEnum.values())
                .contains(SessionStatusEnum.READY)
                .contains(SessionStatusEnum.RECRUIT)
                .contains(SessionStatusEnum.CLOSED)
                ;
    }
}
