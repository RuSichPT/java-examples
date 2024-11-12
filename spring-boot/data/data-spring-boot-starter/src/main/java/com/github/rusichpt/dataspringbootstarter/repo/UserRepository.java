package com.github.rusichpt.dataspringbootstarter.repo;

import com.github.rusichpt.dataspringbootstarter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
