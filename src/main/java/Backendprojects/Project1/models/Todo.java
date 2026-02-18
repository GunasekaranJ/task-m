package Backendprojects.Project1.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Entity
@Data
public class Todo {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    String title;
    String description;
    boolean isdone;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

}
