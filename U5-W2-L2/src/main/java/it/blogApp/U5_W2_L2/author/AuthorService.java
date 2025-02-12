package it.blogApp.U5_W2_L2.author;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import it.blogApp.U5_W2_L2.response.CreateResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorResponse authorResponseFromEntity(Author author) {
        AuthorResponse authorResponse = new AuthorResponse();
        BeanUtils.copyProperties(author, authorResponse);
        return authorResponse;
    }

    public List<AuthorResponse> authorResponseListFromEntityList(List<Author> authors) {
        return authors.stream().map(this::authorResponseFromEntity).toList();
    }

    public List<AuthorResponse> findAll() {
        return authorResponseListFromEntityList(authorRepository.findAll());
    }

    public Author modify(Long id, AuthorRequest authorRequest) {
        Author author = findById(id);
        BeanUtils.copyProperties(authorRequest, author);
        authorRepository.save(author);
        return author;
    }

    public Author authorFromRequest(AuthorRequest authorRequest) {
        Author author = new Author();
        BeanUtils.copyProperties(authorRequest, author);
        return author;
    }

    public CreateResponse save(AuthorRequest request) {
        Author author = authorFromRequest(request);
        authorRepository.save(author);

        CreateResponse response = new CreateResponse();
        BeanUtils.copyProperties(author, response);
        return response;
    }

    @Transactional
    public AuthorDetailResponse findAuthorResponseFromId(Long id) {
        if (!authorRepository.existsById(id))
            throw new EntityNotFoundException("Autore non trovato");

        Author author = authorRepository.findById(id).get();

        AuthorDetailResponse response = new AuthorDetailResponse();
        BeanUtils.copyProperties(findById(id), response);
        response.setBlogList(author.getBlogList());
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
