package com.ilmiy.model.user;

import com.ilmiy.model.article.Article;
import com.ilmiy.model.article.Category;
import com.ilmiy.model.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comment extends AbsEntity {

    @NonNull
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Comment parent;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Comment> children;

}
