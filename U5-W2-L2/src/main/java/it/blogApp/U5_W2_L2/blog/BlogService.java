package it.blogApp.U5_W2_L2.blog;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Blog modify(Long id, BlogRequest blogRequest) {
        Blog blog = findById(id);
        BeanUtils.copyProperties(blogRequest, blog);
        blogRepository.save(blog);
        return blog;
    }

    public Blog save(BlogRequest blogRequest) {
        Blog blog = new Blog();
        BeanUtils.copyProperties(blogRequest, blog);
        blogRepository.save(blog);
        return blog;
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
}
