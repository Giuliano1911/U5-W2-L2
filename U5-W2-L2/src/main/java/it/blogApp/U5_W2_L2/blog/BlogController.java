package it.blogApp.U5_W2_L2.blog;

import it.blogApp.U5_W2_L2.response.CreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<BlogResponse> findAll(@RequestParam int page, @RequestParam int recordPerPagina, @RequestParam String sortBy) {
        Pageable pageable = PageRequest.of(page, recordPerPagina, Sort.by(sortBy));
        return blogService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BlogDetailResponse findById(@PathVariable Long id) {
        return blogService.findBlogResponseFromId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateResponse save(@RequestBody BlogRequest blogRequest) {
        return blogService.save(blogRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Blog modify(@PathVariable Long id, @RequestBody BlogRequest blogRequest) {
        return blogService.modify(id, blogRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        blogService.delete(id);
    }
}
