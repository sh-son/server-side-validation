package com.common.service;

import com.common.domain.MemberRepository;
import com.common.dto.MemberRequestDto;
import com.common.dto.MemberResponseDto;
import com.common.exception.ValidCustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MemberService {

    private MemberRepository memberRepository;

    @Transactional
    public Long save(MemberRequestDto memberRequestDto) {
        verifyDuplicateEmail(memberRequestDto.getEmail());
        return memberRepository.save(memberRequestDto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        return memberRepository
                .findAll()
                .stream()
                .map(MemberResponseDto::new)
                .collect(Collectors.toList());
    }

    private void verifyDuplicateEmail(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new ValidCustomException("이미 사용중인 이메일주소입니다.", "email");
        }
    }
}
