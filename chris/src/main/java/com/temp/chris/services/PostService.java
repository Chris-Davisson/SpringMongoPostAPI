package com.temp.chris.services;

import com.temp.chris.models.MongoPost;
import com.temp.chris.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean savePost(MongoPost mongoPost) {
        return postRepo.save(mongoPost) != null;
    }


    //Read
    public List<MongoPost> getAllPosts() {
        return postRepo.findAll();
    }

    public MongoPost getPostByTitle(String title){
        return postRepo.findByTitle(title).orElse(null);
    }

    public MongoPost getPostById(String _id) {
        return postRepo.findById(_id).orElse(null);
    }


    //Update
    public boolean update(String id, String title , String content , Date date){
        MongoPost mongoPost = new MongoPost(id, title, content, date);
        return postRepo.update(mongoPost);
    }

    public boolean update(MongoPost mongoPost) {
        return postRepo.update(mongoPost);
    }


    //Delete
    public boolean deleteAll()  {
        return postRepo.deleteAll();
    }

    public boolean deleteById(String id){
        return postRepo.deleteById(id);
    }

    public void deletePost(MongoPost mongoPost) {
        postRepo.delete(mongoPost);
    }


    //Other
    public boolean exists(String _id) {
        return postRepo.existsById(_id);
    }
    public int count() {
        return postRepo.count();
    }








}
