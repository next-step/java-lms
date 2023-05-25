package nextstep.courses.domain;

import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @BeforeEach
    public void setUp() {
        TestFixture.fixtureInit();
    }

    @DisplayName("강의는 시작일과 종료일을 가진다")
    @Test
    public void startAndEnd() {
        //given

        //when
        //then
        fail();
    }
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다")
    @Test
    public void sessionImage() {
        //given
        //when
        //then
        fail();
    }
    @DisplayName("강의는 무료 강의와 유료 강의로 나뉜다")
    @Test
    public void payedOrFree() {
        //given
        //when
        //then
        fail();
    }
    @DisplayName("강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다")
    @Test
    public void sessionStatus() {
        //given
        //when
        //then
        fail();
    }
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다")
    @Test
    public void enrollCanOnlyOnRecruit() {
        //given
        //when
        //then
        fail();
    }

    @DisplayName("강의는 강의 최대 수강 인원을 초과할 수 없다")
    @Test
    public void notExceedMaxStudents() {
        //given
        //when
        //then
        fail();
    }
}