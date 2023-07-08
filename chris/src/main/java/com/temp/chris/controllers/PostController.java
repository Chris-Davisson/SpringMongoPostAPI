package com.temp.chris.controllers;

import com.temp.chris.models.Post;
import com.temp.chris.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public String createPost(@RequestBody Post post) {
        return postService.savePost(post) ? "Post Created" : "Post Create Failed";
    }

    @GetMapping("/{_id}")
    public String getPostById(@PathVariable String _id) {
        return postService.getPostById(_id) != null ? "Success" : "Fail";
    }

    @PutMapping("/{_id}")
    public String updatePost(@PathVariable String _id, @RequestBody Post post) {
        Optional<Post> op = Optional.ofNullable(postService.getPostById(post.getId()));

        return op.map(currPost -> {
            currPost.setTitle(post.getTitle());
            currPost.setContent(post.getContent());
            currPost.setDate(post.getDate());
            postService.update(currPost);
            return "Post updated successfully";
        }).orElse("Update failed: Post not found");

    }

    @DeleteMapping("/{_id}")
    public String deletePost(@PathVariable String _id) {
        return postService.deleteById(_id)  ? "Success" : "Fail";
    }

    @DeleteMapping("/all")
    public String deleteAllPosts() {
        boolean res = postService.deleteAll();
        return res ? "All Posts Deleted":"Delete All Failed";

    }

    @GetMapping("/title/ c{title}")
    public String getPostByTitle(@PathVariable String title) {
        return postService.getPostByTitle(title) != null ? "Success" : "Fail";
    }

}
