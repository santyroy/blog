package org.roy.blog.repo;

import org.roy.blog.model.BlogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private BlogUserRepository blogUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(BlogUserRepository blogUserRepository, PasswordEncoder passwordEncoder) {
        this.blogUserRepository = blogUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.blogUserRepository.deleteAll();

        BlogUser mike = new BlogUser("mike",passwordEncoder.encode("mike123"),new Date());
        BlogUser ana = new BlogUser("ana",passwordEncoder.encode("ana123"),new Date());

        List<BlogUser> users = Arrays.asList(mike, ana);
        blogUserRepository.saveAll(users);
    }
}
