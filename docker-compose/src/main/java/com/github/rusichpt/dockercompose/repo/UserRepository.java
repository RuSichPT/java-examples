package com.github.rusichpt.dockercompose.repo;

import com.github.rusichpt.dockercompose.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
