package IGJ.imgeokjeong.errors.exception;

public class NotExistPostException extends RuntimeException {
    private final static String MESSAGE = "존재하지 않는 게시글 입니다.";

    public NotExistPostException() {
        super(MESSAGE);
    }
}
