package Backendprojects.Project1.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
}
