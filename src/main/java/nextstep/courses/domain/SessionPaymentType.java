package nextstep.courses.domain;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum SessionPaymentType {
    PAID,
    FREE;

    private static final Map<String, SessionPaymentType> paymentTypeCacheMap = Arrays.stream(SessionPaymentType.values()).collect(
            Collectors.toMap(Enum::name, Function.identity()));

    public static SessionPaymentType from(String name){
        if(paymentTypeCacheMap.containsKey(name)){
            return paymentTypeCacheMap.get(name);
        }
        throw new IllegalArgumentException(ExceptionMessage.SESSION_PAYMENT_NOT_FOUND_TYPE.getMessage());
    }
}
