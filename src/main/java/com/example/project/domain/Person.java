package com.example.project.domain;


import com.example.project.controller.dto.PersonDto;
import com.example.project.domain.dto.Birthday;
import lombok.*;
import org.springframework.util.StringUtils;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor//아무런 파라미터를 가지지 않은 생성자를 생성해줌
@AllArgsConstructor
@RequiredArgsConstructor
@Data  //@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.를 한번에 선언해줌

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NonNull
    @Min(1)
    private int age;


    private String hobby;

    @NonNull
    @Column(nullable =false)
    private String bloodType;

    private String address;
    @Embedded
    @Valid
    private Birthday birthday;

    private String job;

    @ToString.Exclude //출력값에서 보이지 않도록 설정하는
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true )
    @ToString.Exclude
    private Block block;

    public void set(PersonDto personDto){
        if(personDto.getAge() !=0){
            this.setAge(personDto.getAge());
        }
        if(!StringUtils.isEmpty(personDto.getHobby())){
            this.setHobby(personDto.getHobby());
        }
        if(!StringUtils.isEmpty(personDto.getBloodType())){
            this.setBloodType(personDto.getBloodType());
        }
        if(!StringUtils.isEmpty(personDto.getAddress())){
            this.setAddress(personDto.getAddress());
        }
        if(!StringUtils.isEmpty(personDto.getJob())){
            this.setJob(personDto.getJob());
        }
        if(!StringUtils.isEmpty(personDto.getPhoneNumber())){
            this.setJob(personDto.getJob());
        }
    }

}
//orphanRemoval:관련 entity의 relation이 사라질 때,entity를 함께 삭제 해줌
//cascade
//관련된 entity의 영속성을 함께 관리할 수 있도록 해줌
//CascadeType.PERSIST:insert할 경우 관련 entity도 함께 insert함
//CascadeType.MERGE:update할 경우 관련 entity도 함께 update함
//CascadeType.REMOVE:delete할 경우 관련 entity도 함께 delete함
//CascadeType.ALL:모든 케이스에 대해 영속성을 함께 관리함