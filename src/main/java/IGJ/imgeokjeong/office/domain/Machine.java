package IGJ.imgeokjeong.office.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Machine {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int count;

    @Lob
    private String picture;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    public Machine(String name, int count, String picture) {
        this.name = name;
        this.count = count;
        this.picture = picture;
    }

    public void setOffice(Office office) {
        this.office = office;
        if(!office.getMachines().contains(this))
            office.getMachines().add(this);
    }
}
