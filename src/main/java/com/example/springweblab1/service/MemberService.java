package com.example.springweblab1.service;

import com.example.springweblab1.model.Member;
import com.example.springweblab1.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    // The MemberRepository object is automatically created and injected by Spring into the MemberService class because of the @Autowired annotation.
    @Autowired
    MemberRepository memberRepository;

    // This method uses the default save method on the repository object to persist the member in the database.
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    // It uses the findById method on the repository to find a record with the ID of id and returns the object.
    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member getMember(Integer id) {
        return memberRepository.findById(id).get();
    }

    // We can’t simply use a default method for this method and have to add some custom method instead.
    // The controller will call this method with the ID of the member (id) the client wants to update and the data (memberData) to update it with.
    // We update the member instance and call the save method to persist the updated record.
    public Member updateMember(Integer id, Member memberData) {
        Member member = memberRepository.findById(id).get();
        member.setName(memberData.getName());
        member.setEmail(memberData.getEmail());
        return memberRepository.save(member);
    }

    // It removes the member with the ID of id from the database.
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }
}