package com.casa.samala.repository;

import com.casa.samala.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * UserRepository
 *
 * @author Ahmad Isyfalana Amin
 * @version $Id: UserRepository.java, v 0.1 2024-01-05  22.39 Ahmad Isyfalana Amin Exp $
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);

}