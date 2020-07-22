package com.springJpa.SpringJPA.Services;

import com.springJpa.SpringJPA.Constants.Constants;
import com.springJpa.SpringJPA.Entities.Posts;
import com.springJpa.SpringJPA.Entities.Tag;
import com.springJpa.SpringJPA.Repositories.PostsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Service
public class PostsService {
    @Autowired
    private PostsRepositories postsRepositories;

    // create Posts
    public List<Posts> createPost(List<Posts> posts){
        for (Posts p:posts){
            Date date=new Date();
            p.setAction(Constants.created);
            p.setActionStatus(Constants.UnApproved);
            p.setCreatedDate(date);
            p.setUpdateDate(date);
        }
       List<Posts> posts2= postsRepositories.saveAll(posts);
        System.out.println("=============================mmmmmmm=====================" + posts2);
      return  posts2;
    }

    //get all posts
    public List<Posts> fetchAll(){
        List<Posts> posts=postsRepositories.findAll();
        return posts;
    }

    // fetch by id
    public Posts fetchOne(Long id){
        Optional<Posts> p=postsRepositories.findById(id);
        Posts posts=new Posts();
        if (p.isPresent()){
            posts=p.get();
        }
        return posts;
    }
    // find tags for a given post
    public Set<Tag> findTagsForPost(Long id){
        Optional<Posts> posts=postsRepositories.findById(id);
        Set<Tag> posts1 = null;

        if (posts.isPresent()){
            System.out.println("=============================,,...<<<<<<<<<<<<<<<<<========"+ posts.get());
            Posts p=posts.get();
            posts1=p.getTags();
        }
        return posts1;
    }
}
