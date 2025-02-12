package it.blogApp.U5_W2_L2.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.blogApp.U5_W2_L2.blog.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDetailResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private String avatar;
    @ToString.Exclude
    @JsonIgnoreProperties("author")
    private List<Blog> blogList;
}
