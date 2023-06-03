package com.temp.chris.services;

import com.temp.chris.models.Post;
import com.temp.chris.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService   {

    private final PostRepository postRepo;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepo = postRepository;
    }


    //Crud Opperations
    //Create
    public boolean savePost(Post post) {
        return postRepo.save(post) != null;
    }


    //Read
    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Post getPostByTitle(String title){
        return postRepo.findByTitle(title).orElse(null);
    }

    public Post getPostById(String _id) {
        return postRepo.findById(_id).orElse(null);
    }


    //Update
    public boolean update(String id, String title , String content , Date date){
        Post post = new Post(id, title, content, date);
        return postRepo.update(post);
    }

    public boolean update(Post post) {
        return postRepo.update(post);
    }


    //Delete
    public boolean deleteAll()  {
        return postRepo.deleteAll();
    }

    public boolean deleteById(String id){
        return postRepo.deleteById(id);
    }

    public void deletePost(Post post) {
        postRepo.delete(post);
    }


    //Other
    public boolean exists(String _id) {
        return postRepo.existsById(_id);
    }
    public int count() {
        return postRepo.count();
    }








}
