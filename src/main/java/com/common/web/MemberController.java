package com.common.web;

import com.common.dto.MemberRequestDto;
import com.common.dto.MemberResponseDto;
import com.common.dto.ValidTestDto;
import com.common.exception.ValidCustomException;
import com.common.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class MemberController {

    private MemberService memberService;

    @PostMapping("/member")
    public Long saveMember(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        return memberService.save(memberRequestDto);
    }

    @GetMapping("/members")
    public List<MemberResponseDto> findAll() {
        return memberService.findAll();
    }

/*    @PostMapping("/test")
    public ValidTestDto validTest(@Valid ValidTestDto validTestDto) {
        return validTestDto;
    }*/

}
