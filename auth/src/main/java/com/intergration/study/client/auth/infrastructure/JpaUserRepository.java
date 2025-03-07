package com.intergration.study.client.auth.infrastructure;

import com.intergration.study.client.auth.model.entity.User;
import com.intergration.study.client.auth.model.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : hanjihoon
 * @Date : 2025. 03. 07.
 */
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {

}
