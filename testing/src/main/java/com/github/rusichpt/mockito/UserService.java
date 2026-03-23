package com.github.rusichpt.mockito;


import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;

    public User saveUser(User user) {
        return repo.save(user);
    }

    public User findUser(Long id) {
        return repo.findById(id)
                .orElse(null);
    }

    public List<User> findUsers() {
        return repo.findAll();
    }

    public User findUserByName(String name) {
        return repo.findByName(name)
                .orElse(null);
    }

    public void deleteUser(Long id) {
        repo.delete(id);
    }
}
