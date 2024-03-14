package com.giantLink.Hiring.recrutementservice.Services;
import com.giantLink.Hiring.recrutementservice.entities.Candidacy;
import com.giantLink.Hiring.recrutementservice.entities.Post;
import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import com.giantLink.Hiring.recrutementservice.models.requests.PostRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.PostUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.PostResponse;
import com.giantLink.Hiring.recrutementservice.repositories.CandidacyRepository;
import com.giantLink.Hiring.recrutementservice.repositories.PostRepository;
import com.giantLink.Hiring.recrutementservice.services.Impl.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    private PostServiceImpl postServiceImpl;
    @Mock
    private PostRepository postRepository;
    @Mock
    CandidacyRepository candidacyRepository;
    @Mock
    private MessageSource messageSource;
    private List<PostResponse> postResponses;


    @BeforeEach//////////
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSavePost_Success() {

        PostRequest postRequest = PostRequest.builder()
                .name("pos1")
                .build();

        Post savedPost = Post.builder()
                .name("pos1")
                .id(1L)
                .build();

        PostResponse savedPostResponse = PostResponse.builder()
                .name("pos1")
                .id(1L)
                .build();

        when(postRepository.save(any(Post.class))).thenReturn(savedPost);
        PostResponse result = postServiceImpl.add(postRequest);

        assertEquals(savedPostResponse.getId(), result.getId());
    }

    @Test
    void testGetPosts_Success(){
        List<Post> posts = new ArrayList<>();
        Post post = Post.builder()
                .name("pos1")
                .id(1L)
                .build();

        posts.add(post);

        when(postRepository.findAll()).thenReturn(posts);
        List<PostResponse> posts2;
        posts2 = postServiceImpl.getAll();

        assertEquals(1,posts2.size());

        // Assert the first (and only) position
        PostResponse postResponse = posts2.get(0);
        assertEquals(post.getId(), postResponse.getId());
        assertEquals(post.getName(), postResponse.getName());
    }

    @Test
    void testGetPostById_Success(){

        Long postId = 1L;
        Post expectedPost = new Post();
        expectedPost.setId(postId);

        // Mock the repository behavior
        when(postRepository.findById(postId)).thenReturn(Optional.of(expectedPost));

        PostResponse actualPost = postServiceImpl.get(postId);

        assertNotNull(actualPost);
        assertEquals(expectedPost.getId(),actualPost.getId());
    }

    @Test
    void testUpdate_Success() {
        // Arrange
        Long postId = 1L;
        PostUpdateRequest request =  PostUpdateRequest.builder()
                        .name("Updated Position")
                        .build();
        // Mock the repository behavior to return a position with the given ID
        Post existingPost = Post.builder()
                        .id(postId)
                        .name("Original Position")
                        .build();

        List<Candidacy> candidacies = new ArrayList<>();
        Candidacy candidacy = Candidacy.builder()
                        .applicationName("candidacy name")
                        .build();
        candidacies.add(candidacy);
        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
        when(postRepository.save(any(Post.class))).thenAnswer(invocation -> invocation.getArgument(0)); // Mock the save method to return the input position
        when(candidacyRepository.findByPostId(postId)).thenReturn(Optional.of(candidacies));
        // Act

        PostResponse updatedPostResponse = postServiceImpl.update(request, postId);

        // Assert
        assertNotNull(updatedPostResponse);
        assertEquals(request.getName(), updatedPostResponse.getName());
    }

    @Test
    void testDelete_Success() {
        // Arrange
        Long postId = 1L;

        // Mock the repository behavior to return an existing position
        when(postRepository.findById(postId)).thenReturn(Optional.of(new Post()));

        // Act and Assert
        assertDoesNotThrow(() -> {
            postServiceImpl.delete(postId);
        });

        // Verify that the deleteById method of the repository is called with the correct ID
        verify(postRepository, times(1)).deleteById(postId);
    }

    @Test
    void testGetPostByTelecommuting_Success(){

        Telecommuting positionTelecommuting = Telecommuting.REMOTE;
        Post expectedPost = new Post();
        expectedPost.setTelecommuting(positionTelecommuting);
        List<Post> li = new ArrayList<>();
        li.add(expectedPost);
        // Mock the repository behavior
        when(postRepository.findByTelecommuting(positionTelecommuting)).thenReturn(Optional.of(li));

        List<PostResponse> postResponse = new ArrayList<>();
        postResponse = postServiceImpl.getByTelecommuting(positionTelecommuting);

        assertEquals(1,postResponse.size());

        // Assert the first (and only) position
        PostResponse positionResponse2 = postResponse.get(0);
        assertEquals(expectedPost.getId(), positionResponse2.getId());
        assertEquals(expectedPost.getTelecommuting(), positionResponse2.getTelecommuting());

    }

    @Test
    void testGetPostByName_Success(){

        String name = "pos1";
        Post expectedPost = new Post();
        expectedPost.setName(name);

        when(postRepository.findByName(name)).thenReturn(Optional.of(expectedPost));

        PostResponse postResponse;
        postResponse = postServiceImpl.getByName(name);

        assertEquals(expectedPost.getId(), postResponse.getId());
        assertEquals(expectedPost.getTelecommuting(), postResponse.getTelecommuting());
    }
}
