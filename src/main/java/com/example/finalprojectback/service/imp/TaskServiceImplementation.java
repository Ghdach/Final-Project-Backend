package com.example.finalprojectback.service.imp;

import com.example.finalprojectback.entity.Task;
import com.example.finalprojectback.repository.TaskRepository;
import com.example.finalprojectback.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existedTask = getTaskById(id);
        existedTask.setTitle(task.getTitle());
        existedTask.setContent(task.getContent());
        return taskRepository.save(existedTask);    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id);
        }

        taskRepository.deleteById(id);
    }
    /*
    *     public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    public Task updateTask(Long id, Task task) {
        Task existedTask = getTaskById(id);
        existedTask.setTitle(task.getTitle());
        existedTask.setContent(task.getContent());
        return taskRepository.save(existedTask);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id);
        }

        taskRepository.deleteById(id);

    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
    }*/
}

