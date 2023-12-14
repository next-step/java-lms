package nextstep.courses.infrastructure.exception;

public class DbInsertFailException extends RuntimeException {
    public DbInsertFailException(String entityName) {
        super(entityName + "의 DB 삽입이 실패했습니다.");
    }
}
