package com.springJpa.SpringJPA.Controllers;

import com.springJpa.SpringJPA.Entities.Posts;
import com.springJpa.SpringJPA.Entities.Tag;
import com.springJpa.SpringJPA.Services.TagsService;
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
public class TagsController {
    @Autowired
    private TagsService tagsService;

    // create Tags
    @PostMapping("/tags")
    private ResponseEntity<List<Tag>> createManyTags(@RequestBody List<Tag> tags){
        List<Tag> tags1=tagsService.createTags(tags);
        return ResponseEntity.status(HttpStatus.CREATED).body(tags1);

    }
    @PutMapping("/tags/{id}")
    private ResponseEntity<Posts> assign(@PathVariable Long id, @RequestBody Set<Tag> tags){
        Posts posts=tagsService.assignToPost(id,tags);
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    // find all tags
    @GetMapping("/tags")
    private ResponseEntity<List<Tag>> fetchAllTags(){
        List<Tag> tags=tagsService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(tags);
    }

}
