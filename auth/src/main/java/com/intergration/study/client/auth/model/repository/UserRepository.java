package com.intergration.study.client.auth.model.repository;

import com.intergration.study.client.auth.model.entity.User;
import java.util.Optional;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
public interface UserRepository {
    Optional<User> findByUsername(String username);
    User save(User user);
}
