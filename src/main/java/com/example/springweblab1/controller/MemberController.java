package com.example.springweblab1.controller;

import com.example.springweblab1.model.Member;
import com.example.springweblab1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

// The @RestController combines the following annotations:
 // @Controller: Indicates to Spring that the class represents a controller.
 // @ResponseBody: Configures Spring to return JSON response from controller methods instead of view templates which is the default.

// @RequestMapping
 // This annotation defines the base URL for the API.
 // If our base URL is http://www.example.org and we use /api as the value for @RequestMapping, it would create the base API as http://www.example.org/api.

// @RequestBody
// This annotation allows the method to accept JSON formatted client data and convert it to a Java object.
// For example, the JSON object mentioned above would be mapped to a Member object with the name and email attributes set to the values in the JSON.
@RestController
@RequestMapping("/api")
public class MemberController {

    // MemberService Injection
    // The MemberService object is injected automatically by Spring into the controller, so we can use it in the class.
    @Autowired
    MemberService memberService;

    // This method is creating the /api/members endpoint for the POST HTTP method and persisting the data sent from the client.
    // The @PostMapping annotation is a shorthand for the @RequestMapping(value="/members", method=RequestMethod.POST) annotation.
    // Both of these annotations define the endpoint path as /api/members
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member) {
        Member newMember = memberService.createMember(member);
        // Notice that we are returning a ResponseEntity instead of a Member object like earlier.
        // The ResponseEntity allows us to modify response information (status code, headers) before sending them back to the client.
        return ResponseEntity.ok(newMember);
    }

    // The @GetMapping is a shorthand for the @RequestMapping(value="/members", method=RequestMethod.GET) annotation.
    // Both of these annotations define the endpoint path as /api/members.
    // The readMembers method calls the memberService to retrieve all the member records from the database and returns a JSON
    @GetMapping("/members")
    public List<Member> readMembers() {
        return memberService.getMembers();
    }

    // The {memberId} in the @GetMapping value is a dynamic value which takes on the value of the client’s request path parameter.
    // For example, the following URL would assign the value of 1 to memberId and id:
    // http://localhost:8080/api/members/1
    // The @PathVariable annotation maps the dynamic path value to the method parameter id.
    @GetMapping("/members/{memberId}")
    public Member readMember(@PathVariable(value = "memberId") Integer id) {
        return memberService.getMember(id);
    }

    // The @PutMapping is a shorthand for the @RequestMapping(value="/members/{memberId}", method=RequestMethod.PUT).
    // This method is using @PathVariable to get the ID of the record that needs to be updated and @RequestBody to get the data sent by the client.
    @PutMapping("/members/{memberId}")
    public Member updateMember(@PathVariable(value = "memberId") Integer id, @RequestBody Member memberData) {
        return memberService.updateMember(id, memberData);
    }

    // The @DeleteMapping is a shorthand for the @RequestMapping(value="/members/{memberId}", method=RequestMethod.DELETE) annotation.
    // This method calls the deleteMember method on the memberService class which removes the record with the given ID from the database.
    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable(value = "memberId") Integer id) {
        memberService.deleteMember(id);
    }
}
