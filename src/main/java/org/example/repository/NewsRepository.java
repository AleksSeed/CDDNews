package org.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.example.news.News;

public interface NewsRepository extends CrudRepository<News, Long> {

}
