package nextstep.payments;

public enum ExceptionMessage {
    INVALID_PAYMENT("잘못된 결제 입니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
