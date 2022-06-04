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

    // 사진은 추후에 추가 예정 - 이름으로 S3에 저장된 사진을 가져와서 넣어주는 방식으로 구현
    private String picture;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;

    public Machine(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public void setOffice(Office office) {
        this.office = office;
        if(!office.getMachines().contains(this))
            office.getMachines().add(this);
    }
}
