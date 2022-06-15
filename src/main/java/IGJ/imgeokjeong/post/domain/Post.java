package IGJ.imgeokjeong.post.domain;

import IGJ.imgeokjeong.post.dto.CreateRequest;
import IGJ.imgeokjeong.post.dto.UpdateRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@NoArgsConstructor
@Getter
@Entity
public class Post {

    @GeneratedValue
    @Id
    private Long id;

    private String title;

    @Lob
    private String content;

    private String phoneNumber;

    private boolean qualification;

    private String region;

    private int price;

    @JsonIgnore
    private String pin;

    public Post (CreateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.phoneNumber = request.getPhoneNumber();
        this.qualification = request.isQualification();
        this.region = request.getRegion();
        this.price = request.getPrice();
        this.pin = request.getPin();
    }

    public void update(UpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.phoneNumber = request.getPhoneNumber();
        this.qualification = request.isQualification();
        this.region = request.getRegion();
        this.price =  request.getPrice();
    }
}
