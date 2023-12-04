package nextstep.courses.service;

import nextstep.courses.domain.session.PayType;

import java.util.List;

public class SessionFactory {

    private final List<SessionService> sessionServices;

    public SessionFactory(FreeSessionService freeSessionService, PaySessionService paySessionService) {
        this.sessionServices = List.of(freeSessionService, paySessionService);
    }

    public SessionService findSessionService(PayType payType) {
        return sessionServices.stream()
            .filter(service -> service.supports(payType))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(String.format("일치하는 SessionService가 없습니다. PayType :: %s", payType)));
    }
}
