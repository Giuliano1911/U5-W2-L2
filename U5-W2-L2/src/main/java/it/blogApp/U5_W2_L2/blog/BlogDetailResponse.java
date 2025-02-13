package it.blogApp.U5_W2_L2.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.blogApp.U5_W2_L2.author.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogDetailResponse {

    private Long id;

    private String title;

    private String content;

    private String cover;

    private String category;

    private int time;

    @ToString.Exclude
    @JsonIgnoreProperties("blogList")
    private Author author;
}
