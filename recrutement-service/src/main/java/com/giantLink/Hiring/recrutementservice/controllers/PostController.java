package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import com.giantLink.Hiring.recrutementservice.models.requests.PostRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.PostUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;
import com.giantLink.Hiring.recrutementservice.services.PostService;
import jakarta.validation.Valid;
import com.giantLink.Hiring.recrutementservice.models.response.CandidacyResponse;
import com.giantLink.Hiring.recrutementservice.models.response.PostResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping
    @PreAuthorize("hasAuthority('POST_CREATE')")

    public ResponseEntity<PostResponse> add(@RequestBody @Valid PostRequest request) {
        PostResponse response = postService.add(request);
        String successMessage = messageSource.getMessage("post.controller.addSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<PostResponse> get(@PathVariable String name) {
        return new ResponseEntity<>(postService.getByName(name), HttpStatus.CREATED);
    }
    @GetMapping("telecommuting/{telecommuting}")
    public ResponseEntity<List<PostResponse>> getByTelecommuting(@PathVariable Telecommuting telecommuting) {
        return new ResponseEntity<>(postService.getByTelecommuting(telecommuting), HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasAuthority('POST_READ_ALL')")
    public ResponseEntity<List<PostResponse>> get() {
        List<PostResponse> postResponses = postService.getAll();
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('POST_READ_POTENTIAL_CANDIDACIES')")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        PostResponse postResponse = postService.get(id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @GetMapping("get-candidacy/{id_post}")
    @PreAuthorize("hasAuthority('POST_READ_POTENTIAL_CANDIDACIES')")
    public ResponseEntity<List<CandidacyResponse>> getCandidacy(@PathVariable Long id_post) {
        List<CandidacyResponse> candidacyResponses = postService.getPotentialCandidacies(id_post);
        return new ResponseEntity<>(candidacyResponses, HttpStatus.OK);
    }

    @GetMapping("get-candidate/{id_post}")
    @PreAuthorize("hasAuthority('POST_READ')")
    public ResponseEntity<List<CandidateResponse>> getCandidates(@PathVariable Long id_post) {
        List<CandidateResponse> candidateResponses = postService.getPotentielCandidates(id_post);
        return new ResponseEntity<>(candidateResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('POST_DELETE')")
    public ResponseEntity<PostResponse> update(@PathVariable Long id, @RequestBody PostUpdateRequest postUpdateRequest) {
        PostResponse response = postService.update(postUpdateRequest, id);
        String successMessage = messageSource.getMessage("post.controller.updateSuccess", null, LocaleContextHolder.getLocale());
        response.setMessage(successMessage);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('POST_DELETE')")
    public ResponseEntity<SuccessResponse> delete(@PathVariable Long id){
        postService.delete(id);
        SuccessResponse successResponse = SuccessResponse.builder()
                .message(messageSource.getMessage("post.controller.deleteSuccess", null, LocaleContextHolder.getLocale()))
                .build();

        return new ResponseEntity<>(successResponse, HttpStatus.NO_CONTENT);
    }
}
