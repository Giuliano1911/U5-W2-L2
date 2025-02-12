package it.blogApp.U5_W2_L2.author;

import it.blogApp.U5_W2_L2.blog.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<Blog> blogList;
}
