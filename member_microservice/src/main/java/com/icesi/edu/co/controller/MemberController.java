package com.icesi.edu.co.controller;

import com.icesi.edu.co.model.Member;
import com.icesi.edu.co.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_TRAINER', 'ROLE_ADMIN')")
    @Operation(summary = "Add a new member", description = "This endpoint allows adding a new member to the system.")
    public Member addMember(
            @Parameter(description = "The member to be added", required = true) @RequestBody Member member) {
        return memberService.addMember(member);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_TRAINER')")
    @Operation(summary = "Get all members", description = "This endpoint allows retrieving all registered members.")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/exist/{id}")
    @Operation(summary = "Get a member by ID", description = "This endpoint allows retrieving a member by its ID.")
    public Boolean existsMember(@Parameter(description = "The ID of the member to be retrieved", required = true) @PathVariable Long id) {
        return memberService.existsMember(id);
    }
}
