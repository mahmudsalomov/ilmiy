package com.ilmiy.model.article;


import com.ilmiy.model.FileStorage;
import com.ilmiy.model.template.AbsEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book extends AbsEntity {
    @NonNull
    private String name;


    @Column(columnDefinition = "text")
    private String description;

    private String author;

    @OneToOne
    private FileStorage file;


}
