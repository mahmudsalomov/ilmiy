package com.ilmiy.model.article;

import com.ilmiy.model.FileStorage;
import com.ilmiy.model.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ArticleTranslation extends AbsEntity {
    @NotNull
    private String title;

    @Column(columnDefinition = "text")
    private String anons;

    @Column(columnDefinition = "text")
    private String text;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Language language;

    @ManyToMany
    private List<FileStorage> files;
}
