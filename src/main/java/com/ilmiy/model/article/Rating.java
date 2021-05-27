package com.ilmiy.model.article;

import com.ilmiy.model.user.User;
import com.ilmiy.model.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Rating extends AbsEntity {
    @ManyToOne
    private Article article;
    @ManyToOne
    private User user;
    @NonNull
    private short amount;

}
