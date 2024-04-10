package com.gitgub.rusichpt.dataspringbootstarter.repo;

import com.gitgub.rusichpt.dataspringbootstarter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
