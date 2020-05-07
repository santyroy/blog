package org.roy.blog.repo;

import org.roy.blog.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {

    public BlogUser findByUsername(String username);
}
