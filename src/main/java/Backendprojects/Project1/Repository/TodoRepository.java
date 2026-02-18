package Backendprojects.Project1.Repository;

import Backendprojects.Project1.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // Correct method based on entity structure
    List<Todo> findByUser_UserId(Long userId);
}
