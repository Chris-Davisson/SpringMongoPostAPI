package com.temp.chris.repository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.temp.chris.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    @Autowired
    public PostRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    private MongoTemplate mongoTemplate;


    public List<Post> findAll() {
        return mongoTemplate.findAll(Post.class);
    }

    public Optional<Post> findById(String _id){
        Query query = new Query(Criteria.where("_id").is(_id));
        return Optional.ofNullable(mongoTemplate.findOne(query , Post.class));
    }

    public Optional<Post> findByTitle(String title){
        Query query = new Query(Criteria.where("title").is(title));
        return Optional.ofNullable(mongoTemplate.findOne(query , Post.class));
    }

    public List<Post> findAllByTitle(String title){
        Query query = new Query(Criteria.where("title").is(title));
        return mongoTemplate.find(query,Post.class);
    }

    public Post save(Post post) {
        return mongoTemplate.save(post);
    }

    public void delete(Post entity) {
        Query q = new Query(Criteria.where("_id").is(entity.getId()));
        mongoTemplate.remove(q, Post.class);
    }

    public boolean existsById(String s) {
        Query q = new Query(Criteria.where("_id").is(s));
        return mongoTemplate.exists(q , Post.class);
    }
    public boolean deleteById(String s) {
        Query q = new Query(Criteria.where("_id").is(s));
        DeleteResult res = mongoTemplate.remove(q, Post.class);
        return res.getDeletedCount() > 0;
    }
    public boolean deleteAll() {
        Query q = new Query();
        DeleteResult res = mongoTemplate.remove(q, Post.class);
        return res.getDeletedCount() > 0;
    }

    public boolean update(Post post){
        Query q = new Query(Criteria.where("_id").is(post.getId()));
        Update update = new Update()
                .set("title" , post.getTitle())
                .set("content", post.getContent())
                .set("date" , post.getDate());

        UpdateResult  res = mongoTemplate.updateFirst(q , update , Post.class);
        return res.getMatchedCount() > 0;
    }

    public int count() {
        Query q = new Query();
        return ((int) mongoTemplate.count(q, Post.class));
    }
}
