package org.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.example.news.NewsType;

@Repository
public interface NewsTypeRepository extends CrudRepository<NewsType, Long> {

}