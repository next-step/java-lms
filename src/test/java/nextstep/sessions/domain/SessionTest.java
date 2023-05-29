package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.sessions.exception.AlreadySignUpException;
import nextstep.sessions.exception.CapacityNumberException;
import nextstep.sessions.exception.GuestUserSignUpException;
import nextstep.sessions.exception.NotRecruitingException;
import nextstep.sessions.exception.NumberFullException;
import nextstep.sessions.type.StatusType;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class SessionTest {

	private SessionDate sessionDate;
	private String coveredImageUrl;
	private Session session;

	@BeforeEach
	void setUp() {
		this.sessionDate = new SessionDate("2023-04-03T00:00:00", "2023-06-01T00:00:00");
		this.coveredImageUrl = "http://nextstep/coveredImageUrl.png";
		this.session = new Session(1L, 1L, sessionDate, coveredImageUrl, true, 100, new Students());
	}

	@DisplayName("강의를 오픈한다.")
	@Test
	void test1() {
		this.session.open();

		Session expected = new Session(1L, 1L, sessionDate, coveredImageUrl, true, StatusType.RECRUITING, 100, new Students());
		assertThat(this.session).isEqualTo(expected);
	}

	@DisplayName("강의를 종료한다.")
	@Test
	void test2() {
		this.session.close();

		Session expected = new Session(1L, 1L, sessionDate, coveredImageUrl, true, StatusType.TERMINATION, 100, new Students());
		assertThat(this.session).isEqualTo(expected);
	}

	@DisplayName("수강 신청을 한다.")
	@Test
	void test3() {
		this.session.open();
		this.session.enroll(NsUserTest.JAVAJIGI);

		Session expected = new Session(1L, 1L, sessionDate, coveredImageUrl, true, 100,
			new Students(List.of(new Student(1L, NsUserTest.JAVAJIGI.getId()))));
		expected.open();

		assertThat(this.session).isEqualTo(expected);
	}

	@DisplayName("수강 신청 불가 - 준비중")
	@Test
	void test4() {
		assertThatThrownBy(() -> this.session.enroll(NsUserTest.JAVAJIGI)).isInstanceOf(NotRecruitingException.class);
	}

	@DisplayName("수강 신청 불가 - 종료")
	@Test
	void test5() {
		this.session.close();

		assertThatThrownBy(() -> this.session.enroll(NsUserTest.JAVAJIGI)).isInstanceOf(NotRecruitingException.class);
	}

	@DisplayName("수강 신청 불가 - 모집인원 초과")
	@Test
	void test6() {
		Session numberFullSession = new Session(1L, 1L, sessionDate, coveredImageUrl, true, 1, new Students());
		numberFullSession.open();
		numberFullSession.enroll(NsUserTest.JAVAJIGI);

		assertThatThrownBy(() -> numberFullSession.enroll(NsUserTest.SANJIGI)).isInstanceOf(NumberFullException.class);
	}

	@DisplayName("수강 신청 불가 - 게스트 유저")
	@Test
	void test7() {
		this.session.open();

		assertThatThrownBy(() -> this.session.enroll(NsUser.GUEST_USER)).isInstanceOf(GuestUserSignUpException.class);
	}

	@DisplayName("수강 신청 불가 - 해당 유저로 이미 수강 신청 완료")
	@Test
	void test8() {
		this.session.open();

		this.session.enroll(NsUserTest.JAVAJIGI);
		assertThatThrownBy(() -> this.session.enroll(NsUserTest.JAVAJIGI)).isInstanceOf(AlreadySignUpException.class);
	}

	@DisplayName("수강 가능 인원 예외 케이스 - 음수")
	@Test
	void test9() {
		assertThatThrownBy(() -> new Session(1L, 1L, sessionDate, coveredImageUrl, true, -1,
			new Students())).isInstanceOf(CapacityNumberException.class);
	}
}
