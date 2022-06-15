package IGJ.imgeokjeong.post.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class AuthorizeRequest {

    @NotNull
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\d{4}", message = "핀번호는 4자리 숫자여야 합니다")
    private String pin;
}
