package com.exadel.training.repository.impl;

import com.exadel.training.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by HP on 23.07.2015.
 */
public interface NewsRepository extends JpaRepository<News, Long> {
}