package com.ilmiy.model.article;

import com.ilmiy.model.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category extends AbsEntity {
    private String name;

    @ManyToOne
    private Category parent;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Category> children;
}
