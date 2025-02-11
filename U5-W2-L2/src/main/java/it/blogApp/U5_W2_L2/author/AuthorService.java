package it.blogApp.U5_W2_L2.author;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import it.blogApp.U5_W2_L2.response.CreateResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author modify(Long id, AuthorRequest authorRequest) {
        Author author = findById(id);
        BeanUtils.copyProperties(authorRequest, author);
        authorRepository.save(author);
        return author;
    }

    public CreateResponse save(AuthorRequest request) {
        Author author = new Author();
        BeanUtils.copyProperties(request, author);
        authorRepository.save(author);

        CreateResponse response = new CreateResponse();
        BeanUtils.copyProperties(author, response);
        return response;
    }

    public Author findById(Long id) {
        if (!authorRepository.existsById(id))
            throw new EntityNotFoundException("Autore non trovato");

        return authorRepository.findById(id).get();
    }

    public void delete(Long id) {
        if (!authorRepository.existsById(id))
            throw new EntityNotFoundException("Autore non trovato");
        authorRepository.deleteById(id);
    }
}
