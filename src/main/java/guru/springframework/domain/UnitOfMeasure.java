package guru.springframework.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Created by maikbartels on 2020.07.23
 */
@Data
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}
