    package Backendprojects.Project1.Controller;

    import Backendprojects.Project1.Service.TodoService;
    import Backendprojects.Project1.models.Todo;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/todo")
    public class TodoController {

        @Autowired
        private TodoService todoService;

         @GetMapping("/{id}")
         ResponseEntity<Todo> getTodoById(@PathVariable Long id){
             try {
                 Todo createdTodo= todoService.getTodoById(id);
                 return new ResponseEntity<>(createdTodo, HttpStatus.OK);
             }
             catch(RuntimeException ex){
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }

         }

         @GetMapping("/alltodo")
         ResponseEntity<List<Todo>> getAllTodos(){
             return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
         }

         @PostMapping("/create")
         ResponseEntity<Todo> createUser(@RequestBody Todo todo) {
            return new ResponseEntity<>(todoService.createTodo(todo), HttpStatus.CREATED);
         }

         @PutMapping
        ResponseEntity<Todo> updateTodo(@RequestBody Todo todo){
             return new ResponseEntity<>(todoService.updateTodo(todo), HttpStatus.OK);
         }

         @DeleteMapping("/{id}")
        void  deleteTodoById(@PathVariable Long id){
             todoService.deleteTodoById(id);
         }

         @DeleteMapping("/deleteall")
        void  deleteAllTodo(){
            todoService.deleteAllTodo();
        }
    }
