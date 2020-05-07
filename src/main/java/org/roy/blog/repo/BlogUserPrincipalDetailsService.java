package org.roy.blog.repo;

import org.roy.blog.model.BlogUser;
import org.roy.blog.model.BlogUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BlogUserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private BlogUserRepository blogUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        BlogUser user = blogUserRepository.findByUsername(s);

        BlogUserPrincipal blogUserPrincipal = new BlogUserPrincipal(user);

        return blogUserPrincipal;
    }
}
