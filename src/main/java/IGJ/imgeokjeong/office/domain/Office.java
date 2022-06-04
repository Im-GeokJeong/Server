package IGJ.imgeokjeong.office.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Office {

    @GeneratedValue
    @Column(name = "office_id")
    @Id
    private Long id;

    private String name;

    private String phoneNumber;

    private String streetNameAddress;

    private String lotNumberAddress;

    private String latitude;

    private String longitude;

    @JsonIgnore
    @OneToMany(
            mappedBy = "office",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Machine> machines = new ArrayList<>();

    public Office(String name, String phoneNumber, String streetNameAddress, String lotNumberAddress, String latitude, String longitude) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.streetNameAddress = streetNameAddress;
        this.lotNumberAddress = lotNumberAddress;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addMachine(Machine machine) {
        this.machines.add(machine);
        if(machine.getOffice() != this)
            machine.setOffice(this);
    }
}
