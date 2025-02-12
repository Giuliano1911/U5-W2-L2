package it.blogApp.U5_W2_L2.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.blogApp.U5_W2_L2.author.Author;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String title;

    private String content;

    private String cover;

    private String category;

    private int time;

    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties("blogList")
    private Author author;
}
