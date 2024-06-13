package com.project.repository;

import com.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fajaryudi
 * @created 2024/06/13 - 10.17
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
