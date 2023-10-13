package com.example.kanban.service;

import com.example.kanban.model.Kanban;
import com.example.kanban.model.KanbanDTO;
import com.example.kanban.model.TaskDTO;
import com.example.kanban.repository.KanbanRepository;
import com.example.kanban.model.Task;
import com.example.kanban.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KanbanServiceImpl implements KanbanService {

    private final KanbanRepository kanbanRepository;
    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Kanban> getAllKanbanBoards() {
        List<Kanban> kanbanList = new ArrayList<>();
        kanbanRepository.findAll().forEach(kanbanList::add);
        return kanbanList;
    }

    @Override
    public Optional<Kanban> getKanbanById(Long id) {
        return kanbanRepository.findById(id);
    }

    @Override
    public Optional<Kanban> getKanbanByTitle(String title) {
        return kanbanRepository.findByTitle(title);
    }

    @Override
    public Kanban saveNewKanban(KanbanDTO kanbanDTO) {
        return kanbanRepository.save(convertDTOToKanban(kanbanDTO));
    }

    @Override
    public Kanban updateKanban(Kanban oldKanban, KanbanDTO newKanbanDTO) {
        oldKanban.setTitle(newKanbanDTO.getTitle());
        return kanbanRepository.save(oldKanban);
    }

    @Override
    public void deleteKanban(Kanban kanban) {
        kanbanRepository.delete(kanban);
    }

    @Override
    public Kanban addNewTaskToKanban(Long kanbanId, TaskDTO taskDTO) {
        Optional<Kanban> kanbanOptional = kanbanRepository.findById(kanbanId);
        if (kanbanOptional.isPresent()) {
            Kanban kanban = kanbanOptional.get();
            Task task = convertDTOToTask(taskDTO);
            taskRepository.save(task); // Save the Task in its repository
            kanban.addTask(task);
            kanbanRepository.save(kanban); // Save the Kanban with the new Task
            return kanban;
        } else {
            return null;
        }
    }



    private Kanban convertDTOToKanban(KanbanDTO kanbanDTO){
        Kanban kanban = new Kanban();
        kanban.setId(kanbanDTO.getId());
        kanban.setTitle(kanbanDTO.getTitle());
        return kanban;
    }

    private Task convertDTOToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(UUID.randomUUID());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setColor(taskDTO.getColor());
        task.setStatus(taskDTO.getStatus());
        return task;
    }
}
