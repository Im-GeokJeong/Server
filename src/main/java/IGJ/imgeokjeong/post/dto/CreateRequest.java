package IGJ.imgeokjeong.post.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class CreateRequest {

    @NotBlank
    @Length(min = 3, max = 30, message = "제목의 길이는 3이상 30이하 입니다")
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    @Pattern(regexp = "^01(?:0|1|[6-9])-?(\\d{3}|\\d{4})-?(\\d{4})", message = "전화번호가 잘못 입력 되었습니다")
    private String phoneNumber;

    @NotNull
    private boolean qualification;

    @NotBlank
    @Length(min = 3, max = 3, message = "시 • 군은 3자리 단어여야 합니다")
    private String region;

    @NotNull
    private int price;

    @NotBlank
    @Pattern(regexp = "\\d{4}", message = "핀번호는 4자리 숫자여야 합니다")
    private String pin;
}
