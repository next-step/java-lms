package nextstep.courses.domain.session.coverimage;

import nextstep.courses.exception.NotExistException;
import java.util.Optional;

import static nextstep.courses.domain.session.coverimage.Extension.*;

public class Name {

    private String fileName;
    private Extension extension;

    public Name(String fileName) throws NotExistException {
        if (isNone(fileName)) {
            throw new NotExistException("파일 이름이 존재하지 않습니다.");
        }

        this.fileName = fileName;
        this.extension = extension(extractExtension(fileName));
    }

    private boolean isNone(String fileName) {
        return fileName == null || fileName.isBlank();
    }

    private String extractExtension(String fileName) throws NotExistException {
        return Optional.ofNullable(fileName)
            .filter(f -> f.contains("."))
            .map(f -> f.substring(fileName.lastIndexOf(".") + 1))
            .orElseThrow(() -> new NotExistException("확장자가 존재하지 않습니다"));
    }
}
