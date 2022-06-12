package IGJ.imgeokjeong.errors.exception;

public class NotExistOfficeException extends RuntimeException {
    private final static String MESSAGE = "존재하지 않는 사업소 입니다.";

    public NotExistOfficeException() {
        super(MESSAGE);
    }
}
