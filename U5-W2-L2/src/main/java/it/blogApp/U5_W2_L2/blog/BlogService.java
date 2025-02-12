package it.blogApp.U5_W2_L2.blog;

import it.blogApp.U5_W2_L2.author.Author;
import it.blogApp.U5_W2_L2.author.AuthorRepository;
import it.blogApp.U5_W2_L2.response.CreateResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final AuthorRepository authorRepository;

    public BlogResponse blogResponseFromEntity(Blog blog) {
        BlogResponse blogResponse = new BlogResponse();
        BeanUtils.copyProperties(blog, blogResponse);
        return blogResponse;
    }

    public List<BlogResponse> blogResponseListFromEntityList(List<Blog> blogs) {
        return blogs.stream().map(this::blogResponseFromEntity).toList();
    }

    public Page<BlogResponse> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable).map(this::blogResponseFromEntity);
    }

    public Blog modify(Long id, BlogRequest blogRequest) {
        Blog blog = findById(id);
        BeanUtils.copyProperties(blogRequest, blog);
        Author author = authorRepository.findById(blogRequest.getAuthorId()).get();
        blog.setAuthor(author);
        blogRepository.save(blog);
        return blog;
    }

    public CreateResponse save(BlogRequest blogRequest) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogRequest, blog);
        Author author = authorRepository.findById(blogRequest.getAuthorId()).get();
        blog.setAuthor(author);
        blogRepository.save(blog);

        CreateResponse response = new CreateResponse();
        BeanUtils.copyProperties(blog, response);
        return response;
    }

    public Blog findById(Long id) {
        if (!blogRepository.existsById(id))
            throw new EntityNotFoundException("Blog non trovato");
        return blogRepository.findById(id).get();
    }

    public void delete(Long id) {
        if (!blogRepository.existsById(id))
            throw new EntityNotFoundException("Blog non trovato");
        blogRepository.deleteById(id);
    }

    @Transactional
    public BlogDetailResponse findBlogResponseFromId(Long id) {
        if (!blogRepository.existsById(id))
            throw new EntityNotFoundException("Autore non trovato");

        Blog blog = blogRepository.findById(id).get();

        BlogDetailResponse response = new BlogDetailResponse();
        BeanUtils.copyProperties(findById(id), response);
        response.setAuthorId(blog.getAuthor().getId());
        return response;
    }
}
