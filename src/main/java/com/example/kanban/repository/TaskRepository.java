package com.example.kanban.repository;

import com.example.kanban.model.Task;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends MongoRepository<Task, Long> {

  Optional<Task> findByTitle(String title);
  Optional<Task> findById(UUID id);
}
