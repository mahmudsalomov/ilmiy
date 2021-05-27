package com.ilmiy.model;

import com.ilmiy.model.others.FileStorageStatus;
import com.ilmiy.model.template.AbsEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStorage extends AbsEntity {
    private String name;
    private String extension;
    private long fileSize;
    private String hashId;
    private String contentType;
    private String uploadPath;
    @Enumerated(EnumType.STRING)
    private FileStorageStatus fileStorageStatus;
}
