package com.example.board.controller;

import com.example.board.dto.CreateMemberRequestDTO;
import com.example.board.model.Member;
import com.example.board.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> createUser(@RequestBody CreateMemberRequestDTO requestDTO) {
        Member createdMember = new Member(requestDTO.name());
        memberService.save(createdMember);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

}
