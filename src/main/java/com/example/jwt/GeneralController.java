package com.example.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/all")
    public String getAll() {
        return "Some dummy test text";
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

}
