package com.example.kanban.repository;

import com.example.kanban.model.Kanban;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KanbanRepository extends MongoRepository<Kanban, Long> {

    Optional<Kanban> findByTitle(String title);
}
