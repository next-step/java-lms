package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.time.LocalDateTime;

public class SessionTest {
    LocalDateTime startDate = LocalDateTime.of(2024, 3, 1, 0, 0, 0);

    LocalDateTime endDate = LocalDateTime.of(2024, 4, 18, 0, 0, 0);

    Image image = new Image() {
        @Override
        public int getWidth(ImageObserver observer) {
            return 0;
        }

        @Override
        public int getHeight(ImageObserver observer) {
            return 0;
        }

        @Override
        public ImageProducer getSource() {
            return null;
        }

        @Override
        public Graphics getGraphics() {
            return null;
        }

        @Override
        public Object getProperty(String name, ImageObserver observer) {
            return null;
        }
    };

    @Test
    @DisplayName("SessionStatus.PREPARING일 때, 수강신청 불가능")
    public void preparing() {

        Session freeSession = new Session("freeSession", startDate, endDate, image,
                SessionStatus.PREPARING, ChargeStatus.FREE, 0, 1);
        Session chargeSession = new Session("chargeSession", startDate, endDate, image,
                SessionStatus.PREPARING, ChargeStatus.CHARGE, 100000, 1);

        Assertions.assertThatThrownBy(() -> {
            freeSession.registration(NsUser.GUEST_USER, Long.parseLong("0"));
        }).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> {
            chargeSession.registration(NsUser.GUEST_USER, Long.parseLong("100000"));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("SessionStatus.RECRUITING일 때, 수강신청 가능")
    public void recruiting() {

        Session freeSession = new Session("freeSession", startDate, endDate, image,
                SessionStatus.RECRUITING, ChargeStatus.FREE, 0, 1);
        Session chargeSession = new Session("chargeSession", startDate, endDate, image,
                SessionStatus.RECRUITING, ChargeStatus.CHARGE, 100000, 1);

        // 정상
        freeSession.registration(NsUser.GUEST_USER, Long.parseLong("0"));
        Assertions.assertThat(freeSession.getEnrollment().isIncluded(NsUser.GUEST_USER)).isEqualTo(true);
        chargeSession.registration(NsUser.GUEST_USER, Long.parseLong("100000"));
        Assertions.assertThat(chargeSession.getEnrollment().isIncluded(NsUser.GUEST_USER)).isEqualTo(true);


        // 비정상
        Assertions.assertThatThrownBy(() -> {
            freeSession.registration(NsUser.GUEST_USER, Long.parseLong("100000"));
        }).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> {
            chargeSession.registration(NsUser.GUEST_USER, Long.parseLong("0"));
        }).isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("SessionStatus.CLOSE일 때, 수강신청 불가능")
    public void close() {

        Session freeSession = new Session("freeSession", startDate, endDate, image,
                SessionStatus.CLOSE, ChargeStatus.FREE, 0, 1);
        Session chargeSession = new Session("chargeSession", startDate, endDate, image,
                SessionStatus.CLOSE, ChargeStatus.CHARGE, 100000, 1);

        Assertions.assertThatThrownBy(() -> {
            freeSession.registration(NsUser.GUEST_USER, Long.parseLong("0"));
        }).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> {
            chargeSession.registration(NsUser.GUEST_USER, Long.parseLong("100000"));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
