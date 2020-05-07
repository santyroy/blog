package org.roy.blog.controller;

import org.roy.blog.model.Blog;
import org.roy.blog.model.BlogUser;
import org.roy.blog.repo.BlogRepository;
import org.roy.blog.repo.BlogUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogUserRepository blogUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static BlogUser principalBlogUser = null;

    @RequestMapping("/")
    public ModelAndView showHomePage() {
        List<Blog> blogs = blogRepository.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("data_blog", blogs);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/newBlog")
    public ModelAndView showNewPostPage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("blog", new Blog());
        mv.setViewName("new-blog");
        return mv;
    }

    @PostMapping("/newBlog/processNewBlog")
    public ModelAndView processNewPost(@ModelAttribute Blog blog, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        Blog currentBlog = new Blog(blog.getMessage(),new Date(),principalBlogUser);
        if(bindingResult.hasErrors()) {
            mv.setViewName("redirect:/newBlog");
            return mv;
        }
        System.out.println(currentBlog.toString());
        blogRepository.save(currentBlog);
        mv.setViewName("redirect:/");
        return mv;
    }

    @RequestMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("blogUser", new BlogUser());
        mv.setViewName("register");
        return mv;
    }

    @PostMapping("/register/processRegistration")
    public ModelAndView processRegistration(@ModelAttribute("blogUser") BlogUser blogUser, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        BlogUser currentUser = new BlogUser(blogUser.getUsername(), passwordEncoder.encode(blogUser.getPassword()), blogUser.getDob());
        System.out.println(currentUser.toString());
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR" + bindingResult.getFieldError());
            mv.setViewName("redirect:/register");
            return mv;
        }
        blogUserRepository.save(currentUser);
        principalBlogUser = currentUser;
        mv.setViewName("redirect:/");
        return mv;
    }
}
