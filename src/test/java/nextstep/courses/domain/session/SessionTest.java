package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private Session defaultSessionWithNoPrice;
    private Session defaultSessionWith3000Price;

    @BeforeEach
    void setUp() {
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        defaultSessionWithNoPrice = new Session(title, startDate, endDate);
        defaultSessionWith3000Price = new Session(title, 3000, startDate, endDate);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("제목이 비어있으면, 예외가 발생한다.")
    void testTitleIsNotBlank(String title) {
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //given, when, then
        assertThatThrownBy(() -> new Session(title, startDate, endDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("title cannot be blank");
    }

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 status는 Recruiting 상태가 아니다.")
    void testInitSessionStatusIsReady() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        final Session sessionWithPrice = new Session(title, 3000, startDate, endDate);
        final Session sessionWithNoPrice = new Session(title, 0, startDate, endDate);

        //when
        final boolean SessionWithPriceIsNotRecruiting = sessionWithPrice.isNotRecruiting();
        final boolean SessionWithNoPriceNotRecruiting = sessionWithNoPrice.isNotRecruiting();

        //then
        assertThat(SessionWithPriceIsNotRecruiting).isTrue();
        assertThat(SessionWithNoPriceNotRecruiting).isTrue();
    }

    @Test
    @DisplayName("무료수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (READY 상태)")
    void testEnrollWithFreeSessionStatusIsReady() {
        //given
        final Session sessionWithNoPrice = defaultSessionWithNoPrice;
        sessionWithNoPrice.ready();
        Payment payment = new Payment("tddJava", 0L, 0L, 0L);

        //when, then
        assertThatThrownBy(() -> sessionWithNoPrice.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("무료수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (CLOSED 상태)")
    void testEnrollWithFreeSessionStatusIsClosed() {
        //given
        final Session sessionWithNoPrice = defaultSessionWithNoPrice;
        sessionWithNoPrice.close();
        Payment payment = new Payment("tddJava", 0L, 0L, 0L);

        //when, then
        assertThatThrownBy(() -> sessionWithNoPrice.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("무료강의 상태가 모집중이면, 수강 신청이 가능하다.")
    void testEnrollAtFreeSession() {
        //given
        final Session sessionWithNoPrice = defaultSessionWithNoPrice;
        sessionWithNoPrice.recruit();
        Payment payment = new Payment("tddJava", 0L, 0L, 0L);

        final int currentStudentCountBeforeEnroll = sessionWithNoPrice.getCurrentStudentCount();

        //when
        sessionWithNoPrice.enroll(payment, NsUserTest.JAVAJIGI);
        final int currentStudentCountAfterEnroll = sessionWithNoPrice.getCurrentStudentCount();

        //then
        assertThat(currentStudentCountAfterEnroll).isEqualTo(currentStudentCountBeforeEnroll + 1);
    }

    @Test
    @DisplayName("Session의 수강료를 지정할 수 있다.")
    void testSessionConstructorHasPrice() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        final long price = 3000;
        final Session session = new Session(title, price, startDate, endDate);

        //when
        final long getPrice = session.getPrice();

        //then
        assertThat(getPrice).isEqualTo(price);
    }

    @Test
    @DisplayName("유료수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (READY 상태)")
    void testEnrollWithPaidSessionStatusIsReady() {
        //given
        final Session session = defaultSessionWith3000Price;
        session.ready();
        Payment payment = new Payment("tddJava", 0L, 0L, 3000L);

        //when, then
        assertThatThrownBy(() -> session.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("유료수강 신청시에 강의 상태가 모집중이 아니면, 예외가 발생한다. (CLOSED 상태)")
    void testEnrollWithPaidSessionStatusIsClosed() {
        //given
        final Session session = defaultSessionWith3000Price;
        session.close();
        Payment payment = new Payment("tddJava", 0L, 0L, 3000L);

        //when, then
        assertThatThrownBy(() -> session.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("session is not recruiting");
    }

    @Test
    @DisplayName("유료강의 수강 신청시에 지불한 가격이 강의의 가격과 맞지 않으면, 예외가 발생한다.")
    void testEnrollWithSessionPrice() {
        //given
        final long price = 3000;

        final Session session = buildDefaultSessionWithPrice(price);
        session.recruit();

        Payment payment = new Payment("tddJava", 0L, 0L, price - 1);

        //when, then
        assertThatThrownBy(() -> session.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("paid amount is different with price");
    }

    @Test
    @DisplayName("유료강의 수강 신청시에 최대 수강 인원을 넘으면, 예외가 발생한다.")
    void testEnrollWithMaxStudentLimit() {
        //given
        final long price = 3000;

        final Session session = buildDefaultSessionWithPrice(price);
        session.recruit();

        Payment payment = new Payment("tddJava", 0L, 0L, price);
        enrollForMaxLimit(session, payment);

        //when, then
        assertThatThrownBy(() -> session.enroll(payment, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("max student limit is reached");
    }

    private void enrollForMaxLimit(final Session session, final Payment payment) {
        for (int count = 0; count < 15; count++) {
            session.enroll(payment, buildTempUser(count));
        }
    }

    private NsUser buildTempUser(final int count) {
        final long id = 100 + count;
        final String userId = "tempId" + count;
        final String name = "name" + count;
        final String email = "temp" + count + "@slipp.net";

        return new NsUser(id, userId, "password", name, email);
    }

    @Test
    @DisplayName("유료강의 상태가 모집중이고 강의 가격과 지불한 가격이 같으며 최대 수강 인원을 넘지 않으면, 수강 신청이 가능하다.")
    void testEnroll() {
        //given
        final long price = 3000;

        final Session session = buildDefaultSessionWithPrice(price);
        session.recruit();
        final int currentStudentCountBeforeEnroll = session.getCurrentStudentCount();

        Payment payment = new Payment("tddJava", 0L, 0L, price);

        //when
        session.enroll(payment, NsUserTest.JAVAJIGI);
        final int currentStudentCountAfterEnroll = session.getCurrentStudentCount();

        //then
        assertThat(currentStudentCountAfterEnroll).isEqualTo(currentStudentCountBeforeEnroll + 1);
    }

    private Session buildDefaultSessionWithPrice(final long price) {
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        return new Session(title, price, startDate, endDate);
    }

    @Test
    @DisplayName("session을 생성할 때, 강의 커버 이미지를 넣을 수 있다.")
    void testSessionConstructorWithCoverImage() {
        //given
        final CoverImage coverImage = new CoverImage(1000, ImageType.JPG, 300, 200);
        final Session session = new Session(
                "TDD, 클린 코드 with Java",
                3000L,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 0, 0),
                coverImage
        );

        //when
        final List<CoverImage> getCoverImage = session.getCoverImages();

        //then
        assertThat(getCoverImage).contains(coverImage);
    }

    @Test
    @DisplayName("session을 생성할 때, 강의 커버 이미지를 넣지 않으면 기본 CoverImage가 들어간다.")
    void testSessionConstructorWithNoCoverImage() {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        final Session sessionWithPrice = new Session(title, 3000, startDate, endDate);

        final CoverImage defaultCoverImage = CoverImage.defaultCoverImage();

        //when
        final List<CoverImage> getCoverImage = sessionWithPrice.getCoverImages();

        //then
        assertThat(getCoverImage).contains(defaultCoverImage);
    }
}
