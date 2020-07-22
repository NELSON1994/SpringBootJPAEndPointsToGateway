package com.springJpa.SpringJPA.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Date createdDate;
    private Date updateDate;
    private String action;
    private String actionStatus;

    @ManyToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name="post_tags",
            joinColumns = {
                    @JoinColumn(name="tag_id",
                            referencedColumnName = "id",
                            nullable = false,
                            updatable = false
                    ),
            },
            inverseJoinColumns = {
                    @JoinColumn(name="post_id",
                            referencedColumnName = "id",
                            nullable = false,
                            updatable = false
                    )
            }
    )
    //@JsonManagedReference
    private Set<Posts> posts= new HashSet<>();
}
