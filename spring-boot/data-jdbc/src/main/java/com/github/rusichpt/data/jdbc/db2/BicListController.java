package com.github.rusichpt.data.jdbc.db2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bic-list")
public class BicListController {
    private final BicListRepository bicListRepository;

    @GetMapping("/{id}")
    public BicListEntity getBicListById(@PathVariable Long id) {
        return bicListRepository.findById(id)
                .orElseThrow();
    }
}
