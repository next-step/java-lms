package nextstep.courses.domain.session;

public class SessionPrice {

    private static final int ZERO_PRICE = 0;

    private Long price;

    public SessionPrice(Long price) {
        validate(price);
        this.price = price;
    }

    private void validate(Long price) {
        if(price < ZERO_PRICE) {
            throw new IllegalArgumentException("금액은 음수일 수 없습니다");
        }
    }

    public boolean validatePrice(Long userPay) {
        if(this.price != userPay) {
            throw new IllegalArgumentException("정확한 수강료를 납부해주세요");
        }
        return true;
    }
}
