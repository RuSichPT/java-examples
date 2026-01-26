package com.github.rusichpt.data.jdbc.db1;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nspk-member")
public class NspkMemberController {
    private final NspkMemberRepository nspkMemberRepository;

    @GetMapping("/{id}")
    public NspkMemberEntity getNspkMemberById(@PathVariable Long id) {
        return nspkMemberRepository.findById(id)
                .orElseThrow();
    }
}
