package IGJ.imgeokjeong.errors.reponse;

import lombok.Getter;

@Getter
public class SingleResponse<T> extends CommonResponse {
    T data;
}
