package Backendprojects.Project1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Backendprojects.Project1.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
