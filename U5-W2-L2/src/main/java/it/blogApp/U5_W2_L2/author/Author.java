package it.blogApp.U5_W2_L2.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.blogApp.U5_W2_L2.blog.Blog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String name;

    private String surname;

    private String email;

    private LocalDate birthDate;

    private String avatar = "ciao";

    @OneToMany
    @ToString.Exclude
    @JsonIgnoreProperties("author")
    private List<Blog> blogList = new ArrayList<>();
}
