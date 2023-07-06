package com.temp.chris.repository;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.temp.chris.models.MongoPost;
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


    public List<MongoPost> findAll() {
        return mongoTemplate.findAll(MongoPost.class);
    }

    public Optional<MongoPost> findById(String _id){
        Query query = new Query(Criteria.where("_id").is(_id));
        return Optional.ofNullable(mongoTemplate.findOne(query , MongoPost.class));
    }

    public Optional<MongoPost> findByTitle(String title){
        Query query = new Query(Criteria.where("title").is(title));
        return Optional.ofNullable(mongoTemplate.findOne(query , MongoPost.class));
    }

    public List<MongoPost> findAllByTitle(String title){
        Query query = new Query(Criteria.where("title").is(title));
        return mongoTemplate.find(query, MongoPost.class);
    }

    public MongoPost save(MongoPost mongoPost) {
        return mongoTemplate.save(mongoPost);
    }

    public void delete(MongoPost entity) {
        Query q = new Query(Criteria.where("_id").is(entity.getId()));
        mongoTemplate.remove(q, MongoPost.class);
    }

    public boolean existsById(String s) {
        Query q = new Query(Criteria.where("_id").is(s));
        return mongoTemplate.exists(q , MongoPost.class);
    }
    public boolean deleteById(String s) {
        Query q = new Query(Criteria.where("_id").is(s));
        DeleteResult res = mongoTemplate.remove(q, MongoPost.class);
        return res.getDeletedCount() > 0;
    }
    public boolean deleteAll() {
        Query q = new Query();
        DeleteResult res = mongoTemplate.remove(q, MongoPost.class);
        return res.getDeletedCount() > 0;
    }

    public boolean update(MongoPost mongoPost){
        Query q = new Query(Criteria.where("_id").is(mongoPost.getId()));
        Update update = new Update()
                .set("title" , mongoPost.getTitle())
                .set("content", mongoPost.getContent())
                .set("date" , mongoPost.getDate());

        UpdateResult  res = mongoTemplate.updateFirst(q , update , MongoPost.class);
        return res.getMatchedCount() > 0;
    }

    public int count() {
        Query q = new Query();
        return ((int) mongoTemplate.count(q, MongoPost.class));
    }
}
