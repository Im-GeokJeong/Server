package IGJ.imgeokjeong.office.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NearestRequest {

    @NotBlank
    private String machine;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;
}
