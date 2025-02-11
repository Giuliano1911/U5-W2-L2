package it.blogApp.U5_W2_L2.runners;

import it.blogApp.U5_W2_L2.author.Author;
import it.blogApp.U5_W2_L2.author.AuthorRepository;
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

    @Override
    public void run(String... args) throws Exception {
        Author author = new Author();
        author.setName("Andrea");
        author.setSurname("Pavoni");
        author.setEmail("KzD0M@example.com");
        author.setBirthDate(LocalDate.now().minusDays(12000));

        authorRepository.save(author);
    }
}
