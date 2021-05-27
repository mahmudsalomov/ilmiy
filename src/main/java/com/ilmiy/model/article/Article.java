package com.ilmiy.model.article;

import com.ilmiy.model.user.User;
import com.ilmiy.model.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Article extends AbsEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    private boolean active;
    private Long viewCount;

    @Column(columnDefinition = "text")
    private String author;
}
