package com.springJpa.SpringJPA.Services;

import com.springJpa.SpringJPA.Constants.Constants;
import com.springJpa.SpringJPA.Entities.Posts;
import com.springJpa.SpringJPA.Entities.Tag;
import com.springJpa.SpringJPA.Repositories.PostsRepositories;
import com.springJpa.SpringJPA.Repositories.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Service
public class TagsService {
    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private PostsRepositories postsRepositories;

    //create and assign to post
    public List<Tag> createTags(List<Tag> tags){
        for (Tag p:tags){
            Date date=new Date();
            p.setAction(Constants.created);
            p.setActionStatus(Constants.UnApproved);
            p.setCreatedDate(date);
            p.setUpdateDate(date);
        }
        List<Tag> tt= tagsRepository.saveAll(tags);
        System.out.println("=============================mmmmmmm=====================" + tt);
        return tt;
    }

    // assign tags to posts
    public Posts assignToPost(Long id, Set<Tag> tags){
        //find post first
        Optional<Posts> p=postsRepositories.findById(id);
        Posts pp=new Posts();
        if (p.isPresent()){
            pp=p.get();
            pp.setTags(tags);
            postsRepositories.save(pp);
            System.out.println("========================kfkfkfkfkfk=============" + pp);
        }
         return pp;
    }

// get all tags
    public List<Tag> findAll(){
        List<Tag> tags=tagsRepository.findAll();
        return tags;
    }
}
