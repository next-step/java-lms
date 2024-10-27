package nextstep.session;

import nextstep.payments.domain.Payment;
import nextstep.session.image.Image;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Session {

    private static final String PAID_SUBSCRIBE_MESSAGE = "유료강의는 결제내역이 필수입니다.";
    private static final String FREE_SUBSCRIBE_MESSAGE = "무료강의는 결제내역이 필요없습니다.";
    private static final String SUBSCRIBE_STATUS_NOT_WAIT_MESSAGE = "현재 강의가 모집중이 아닙니다.";
    private static final String SUBSCRIBE_COUNT_MAX_MESSAGE = "강의가 이미 만석입니다.";

    private final Long id;
    private final String title;
    private final Image image;
    private final PaymentType paymentType;
    private SubscribeStatus subscribeStatus;
    private int subscribeMax;
    private int price;
    private final Subscribers subscribers = new Subscribers();
    private final DateRange dateRange;

    private Session(Long id, String title, Image image, PaymentType paymentType, int subscribeMax, int price, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.paymentType = paymentType;
        this.subscribeStatus = SubscribeStatus.READY;
        this.subscribeMax = subscribeMax;
        this.price = price;
        this.dateRange = new DateRange(startDate, endDate);
    }

    private Session(Long id, String title, Image image, PaymentType paymentType, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.paymentType = paymentType;
        this.subscribeStatus = SubscribeStatus.READY;
        this.dateRange = new DateRange(startDate, endDate);
    }

    public static Session createFree(Long id, String title, Image image, LocalDateTime startDate, LocalDateTime endDate) {
        return new Session(id, title, image, PaymentType.FREE, startDate, endDate);
    }

    public static Session createPaid(Long id, String title, Image image, int subscribeMax, int price, LocalDateTime startDate, LocalDateTime endDate) {
        return new Session(id, title, image, PaymentType.PAID, subscribeMax, price, startDate, endDate);
    }

    public void subsribe(NsUser user) {
        confirmSubscribeStatus();
        if (paymentType == PaymentType.PAID) {
            throw new IllegalArgumentException(PAID_SUBSCRIBE_MESSAGE);
        }
        subscribeUser(user);
    }

    public void subsribe(NsUser user, Payment payment) {
        confirmSubscribeStatus();
        if (paymentType == PaymentType.FREE) {
            throw new IllegalArgumentException(FREE_SUBSCRIBE_MESSAGE);
        }
        payment.checkMatchAmount(this.price);
        confirmSubscribeMax();
        subscribeUser(user);
    }

    public void waitSession() {
        changeSubscribeStatus(SubscribeStatus.WAIT);
    }

    public void closedSession() {
        changeSubscribeStatus(SubscribeStatus.CLOSED);
    }

    public int getSubscribeCount() {
        return this.subscribers.subscribeUsersSize();
    }

    public SubscribeStatus getSubscribeStatus() {
        return subscribeStatus;
    }

    private void changeSubscribeStatus(SubscribeStatus subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    private void subscribeUser(NsUser user) {
        this.subscribers.addUser(user);
    }

    private void confirmSubscribeStatus() {
        if (this.subscribeStatus != SubscribeStatus.WAIT) {
            throw new IllegalArgumentException(SUBSCRIBE_STATUS_NOT_WAIT_MESSAGE);
        }
    }

    private void confirmSubscribeMax() {
        if (this.subscribeMax < this.subscribers.subscribeUsersSize() + 1) {
            throw new IllegalArgumentException(SUBSCRIBE_COUNT_MAX_MESSAGE);
        }
    }
}
