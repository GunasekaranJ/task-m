package Backendprojects.Project1.Service;

import Backendprojects.Project1.Repository.TodoRepository;
import Backendprojects.Project1.Repository.UserRepository;
import Backendprojects.Project1.models.Todo;
import Backendprojects.Project1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(Todo todo, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        todo.setUser(user);
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodos(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return todoRepository.findByUser_UserId(user.getUserId());
    }

    public Todo getTodoById(Long todoId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new RuntimeException("Todo not found"));

        if (!todo.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Access denied");
        }

        return todo;
    }

    public Todo updateTodo(Todo updatedTodo, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Todo existing = getTodoById(updatedTodo.getId(), email);

        existing.setTitle(updatedTodo.getTitle());
        existing.setDescription(updatedTodo.getDescription());
        existing.setIsdone(updatedTodo.isIsdone());

        return todoRepository.save(existing);
    }

    public void deleteTodoById(Long todoId, String email) {
        Todo todo = getTodoById(todoId, email);
        todoRepository.delete(todo);
    }
}