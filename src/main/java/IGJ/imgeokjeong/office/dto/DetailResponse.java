package IGJ.imgeokjeong.office.dto;

import IGJ.imgeokjeong.office.domain.Machine;
import IGJ.imgeokjeong.office.domain.Office;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DetailResponse {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private String phoneNumber;

    private String streetNameAddress;

    private String lotNumberAddress;

    @NotNull
    private List<Machine> machines;

    public DetailResponse(Office office) {
        this.id = office.getId();
        this.name = office.getName();
        this.phoneNumber = office.getPhoneNumber();
        this.streetNameAddress = office.getStreetNameAddress();
        this.lotNumberAddress = office.getLotNumberAddress();
        this.machines = office.getMachines();
    }
}
