package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by maikbartels on 2020.07.23
 */
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
