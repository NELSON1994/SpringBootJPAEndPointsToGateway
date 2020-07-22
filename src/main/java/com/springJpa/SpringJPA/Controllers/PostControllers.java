package com.springJpa.SpringJPA.Controllers;

import com.springJpa.SpringJPA.Entities.Posts;
import com.springJpa.SpringJPA.Entities.Tag;
import com.springJpa.SpringJPA.Repositories.PostsRepositories;
import com.springJpa.SpringJPA.Services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@RestController
public class PostControllers {
    @Autowired
    private PostsService postsService;
    // create list of posts

    @PostMapping("/posts")
    public ResponseEntity<List<Posts>> create(@RequestBody List<Posts> posts){
        List<Posts> posts1= postsService.createPost(posts);
        return ResponseEntity.status(HttpStatus.CREATED).body(posts1);
    }
  @GetMapping("/posts")
    public ResponseEntity<List<Posts>> fetchAll(){
        List<Posts> posts=postsService.fetchAll();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    @GetMapping("/posts/{id}")
    public ResponseEntity<Posts> fetchOne(@PathVariable Long id){
        Posts posts=postsService.fetchOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    @GetMapping("/posts/{id}/tags")
    public ResponseEntity<Set<Tag>> getTagsForPost(@PathVariable Long id){
        Set<Tag> tags=postsService.findTagsForPost(id);
        System.out.println("====================================TAGS=========================" +tags);
        return ResponseEntity.status(HttpStatus.OK).body(tags);
    }

}
