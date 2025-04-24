package nextstep.courses.infrastructure.entity;

import nextstep.courses.domain.model.BaseEntity;
import nextstep.courses.domain.model.SessionImage;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class JdbcSessionImage extends BaseEntity {
    private String imagePath;
    private byte[] imageFile;

    public JdbcSessionImage() {

    }

    public JdbcSessionImage(Long id, String imagePath, Blob imageFile, LocalDateTime createdAt, LocalDateTime updatedAt) throws SQLException, IOException {
        super(id, createdAt, updatedAt);
        this.imagePath = imagePath;
        this.imageFile = toByteArray(imageFile);
    }

    private static byte[] toByteArray(Blob blob) throws IOException, SQLException {
        byte[] file = null;
        if (blob != null) {
            file = blob.getBinaryStream().readAllBytes();
        }
        return file;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }

    public SessionImage toDomain() {
        return new SessionImage(imagePath, imageFile);
    }
}
