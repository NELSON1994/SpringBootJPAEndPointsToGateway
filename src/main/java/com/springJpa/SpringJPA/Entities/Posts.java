package com.springJpa.SpringJPA.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * @Aurthor Nelson Mose
 * @Date 2020:07:16
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String descriptions;
    private String content;
    private Date createdDate;
    private Date updateDate;
    private String action;
    private String actionStatus;
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinTable(
            name="post_tags",
            joinColumns = {
                    @JoinColumn(name="post_id",
                            referencedColumnName = "id",
                            nullable = false,
                            updatable = false
                    ),
            },
            inverseJoinColumns = {
                    @JoinColumn(name="tag_id",
                            referencedColumnName = "id",
                            nullable = false,
                            updatable = false
                    )
            }
            )
    private Set<Tag> tags=new HashSet<>();

}
