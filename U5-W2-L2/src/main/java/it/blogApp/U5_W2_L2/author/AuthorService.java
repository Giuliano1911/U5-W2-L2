package it.blogApp.U5_W2_L2.author;

import it.blogApp.U5_W2_L2.mail.EmailService;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import it.blogApp.U5_W2_L2.response.CreateResponse;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final EmailService emailService;

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

    public CreateResponse save(@Valid AuthorRequest request) {
        Author author = authorFromRequest(request);
        authorRepository.save(author);

        CreateResponse response = new CreateResponse();
        BeanUtils.copyProperties(author, response);

        try {
            emailService.sendEmail(author.getEmail(), "Benvenuto", "Benvenuto " + " "
                    + author.getEmail());
        } catch (MessagingException e) {
            System.out.println("Errore invio email");
        }

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
