package nextstep.lms.domain;

public class SessionPrice {
    private final Integer price;

    public SessionPrice(Integer price) {
        this.price = validatePrice(price);
    }

    private Integer validatePrice(Integer price) {
        if (price == null) {
            return 0;
        }
        if (price < 0) {
            throw new IllegalArgumentException("수강료는 음수일 수 없습니다.");
        }
        return price;
    }

    public boolean isFree() {
        return price == 0;
    }

    public boolean isPaid() {
        return price > 0;
    }

    public Integer getPrice() {
        return price;
    }
}
