package com.example.board.service;

import com.example.board.model.Member;

public interface MemberService {

    Member findById(Long id);
    Member save(Member member);
    void delete(Long id);

}
