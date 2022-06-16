package IGJ.imgeokjeong.errors;

import IGJ.imgeokjeong.errors.exception.NotExistOfficeException;
import IGJ.imgeokjeong.errors.exception.NotExistPostException;
import IGJ.imgeokjeong.errors.response.CommonResponse;
import IGJ.imgeokjeong.errors.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final ResponseService responseService;

    @ExceptionHandler({NotExistOfficeException.class, NotExistPostException.class})
    CommonResponse handleBadRequestException(Exception ex) {
        return handleBadRequest(ex);
    }

    @ExceptionHandler(Exception.class)
    CommonResponse handleException(Exception ex) {
        return handleInternalServerError(ex);
    }

    private CommonResponse handleBadRequest(Exception ex) {
        log.info(ex.getMessage());
        return responseService.getErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    private CommonResponse handleInternalServerError(Exception ex) {
        log.info(ex.getMessage());
        return responseService.getErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
