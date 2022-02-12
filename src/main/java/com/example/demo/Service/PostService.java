package com.example.demo.Service;


import com.example.demo.Model.Person;
import com.example.demo.Paging.Paged;
import com.example.demo.Paging.Paging;
import com.example.demo.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Paged<Person> getPage(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size, Sort.Direction.ASC,"id");
        Page<Person> postPage = postRepository.findAll(request);
        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
    }
}