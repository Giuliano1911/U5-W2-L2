package it.blogApp.U5_W2_L2.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogResponse {

    private Long id;

    private String title;

    private String content;

    private String cover;

    private String category;

    private int time;
}
