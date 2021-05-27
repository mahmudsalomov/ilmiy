package com.ilmiy.repository;

import com.ilmiy.model.article.ArticleTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleTranslationRepository extends JpaRepository<ArticleTranslation,Long> {
}
