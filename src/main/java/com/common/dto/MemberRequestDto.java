package com.common.dto;

import com.common.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
public class MemberRequestDto {

    private Long id;

    @NotBlank(message = "이름을 작성해주세요.")
    private String name;

    @NotBlank(message = "전화번호를 작성해주세요.")
    @Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력 가능합니다.")
    private String phoneNumber;

    @NotBlank(message = "메일을 작성해주세요.")
    @Email(message = "메일의 양식을 지켜주세요.")
    private String email;

    public Member toEntity() {
        String[] phones = parsePhone();
        return Member.builder()
                .name(name)
                .phone1(phones[0])
                .phone2(phones[1])
                .phone3(phones[2])
                .email(email)
                .build();
    }

    private String[] parsePhone() {
        String[] phones = new String[3];
        int mid = phoneNumber.length() == 10? 7:8;
        phones[0] = phoneNumber.substring(0,3);
        phones[1] = phoneNumber.substring(4, mid);
        phones[2] = phoneNumber.substring(mid,phoneNumber.length()-1);
        return phones;
    }

}
