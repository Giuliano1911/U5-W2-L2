package it.blogApp.U5_W2_L2.runners;

import it.blogApp.U5_W2_L2.author.Author;
import it.blogApp.U5_W2_L2.author.AuthorRepository;
import it.blogApp.U5_W2_L2.blog.Blog;
import it.blogApp.U5_W2_L2.blog.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
@Transactional
public class CreateDBRunner implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BlogRepository blogRepository;

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author();
        author.setName("Andrea");
        author.setSurname("Pavoni");
        author.setEmail("KzD0M@example.com");
        author.setBirthDate(LocalDate.now().minusDays(12000));

        authorRepository.save(author);

        Blog blog = new Blog();
        blog.setTitle("Blog di Andrea");
        blog.setContent("Blog di Andrea");
        blog.setCategory("Ciao");
        blog.setTime(10);
        blog.setAuthor(author);

        blogRepository.save(blog);
    }
}
