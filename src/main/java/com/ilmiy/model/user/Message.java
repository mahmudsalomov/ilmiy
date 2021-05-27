package com.ilmiy.model.user;

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
public class Message extends AbsEntity {

    @NonNull
    private String message;
    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private boolean read;


}
