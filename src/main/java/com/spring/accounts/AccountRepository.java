package com.spring.accounts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by David on 2016-07-26.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
