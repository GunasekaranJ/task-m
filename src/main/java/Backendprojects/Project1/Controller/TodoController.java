    package Backendprojects.Project1.Controller;

    import Backendprojects.Project1.JwtFilter;
    import Backendprojects.Project1.Service.TodoService;
    import Backendprojects.Project1.models.Todo;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/todo")
    public class TodoController {

        @Autowired
        private TodoService todoService;

        private String getUserEmail() {
            return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        @PostMapping("/create")
        public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth == null) {
                throw new RuntimeException("Unauthorized");
            }
            String email = getUserEmail();
            return new ResponseEntity<>(
                    todoService.createTodo(todo, email),
                    HttpStatus.CREATED
            );
        }

        @GetMapping("/all")
        public ResponseEntity<List<Todo>> getAllTodos() {
            return ResponseEntity.ok(todoService.getAllTodos(getUserEmail()));
        }

        @GetMapping("/{id}")
        public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
            return ResponseEntity.ok(todoService.getTodoById(id, getUserEmail()));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
            todo.setId(id);
            return ResponseEntity.ok(todoService.updateTodo(todo, getUserEmail()));
        }

        @PutMapping("/{id}/status")
        public ResponseEntity<Todo> updateStatus(@PathVariable Long id, @RequestBody Todo body) {
            Todo todo = todoService.getTodoById(id, getUserEmail());
            todo.setIsdone(body.isIsdone());
            return ResponseEntity.ok(todoService.updateTodo(todo, getUserEmail()));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
            todoService.deleteTodoById(id, getUserEmail());
            return ResponseEntity.noContent().build();
        }
    }