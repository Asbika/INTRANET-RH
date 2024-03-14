package com.giantLink.Hiring.recrutementservice.controllers;

import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.models.requests.PostRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.PostUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.PostResponse;
import com.giantLink.Hiring.recrutementservice.models.response.SuccessResponse;
import com.giantLink.Hiring.recrutementservice.services.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PositionControllerTest {

    @InjectMocks
    private PostController postController;
    @Mock
    private PostService postService;
    @Mock
    private MessageSource messageSource;

    @Test
    @DisplayName("Test Success Scenario for Saving new Post")
    void testAddPost() {

        PostRequest postRequest = PostRequest.builder()
                .name("position1")
                .description("description1")
                .build();

        PostResponse savedPost = PostResponse.builder()
                .id(1L)
                .name(postRequest.getName())
                .description(postRequest.getDescription())
                .build();


        Mockito.when(postService.add(postRequest)).thenReturn(savedPost);

        ResponseEntity<PostResponse> responseEntity = postController.add(postRequest);
        Assertions.assertNotNull(responseEntity.getBody().getId());
        assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Not Found Scenario For Save Post")
    void testAddPost_NotFound() {
        // Arrange
        PostRequest postRequest = PostRequest.builder()
                .name("position1")
                .description("description1")
                .build();

        // Mock the service behavior to throw a ResourceNotFound exception when saving
        Mockito.when(postService.add(postRequest))
                .thenThrow(new ResourceNotFound("Resource not found while saving position"));

        // Act and Assert
        ResourceNotFound exception = assertThrows(ResourceNotFound.class, () -> {
            postController.add(postRequest);
        });

        // Verify the exception message if needed
        assertEquals("Resource not found while saving position", exception.getMessage());
    }

    @Test
    @DisplayName("Test Success Scenario for Fetching All Posts")
    void testGetPosts() {
        List<PostResponse> postResponses = new ArrayList<>();
        PostResponse postResponse = PostResponse.builder()
                .id(1L)
                .description("description2")
                .build();
        postResponses.add(postResponse);

        Mockito.when(postService.getAll()).thenReturn(postResponses);
        ResponseEntity<List<PostResponse>> responseEntity = postController.get();
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test Not Found Scenario for Fetching All Posts")
    void testGetPosts_NotFound() {
        // Arrange
        List<PostResponse> emptyList = new ArrayList<>();

        // Mock the service behavior to return an empty list of positions
        Mockito.when(postService.getAll()).thenReturn(emptyList);

        // Act
        ResponseEntity<List<PostResponse>> responseEntity = postController.get();

        // Assert
        assertTrue(responseEntity.getBody().isEmpty());
    }

    @Test
    @DisplayName("Test Not Found Scenario for Updating Post")
    void testUpdatePost_NotFound() {
        // Arrange
        Long positionId = 1L;
        PostUpdateRequest postUpdateRequest = PostUpdateRequest.builder()
                .name("Updated Position")
                .description("Updated Description")
                .build();

        // Mock the service behavior to throw a ResourceNotFound exception when updating
        Mockito.when(postService.update(postUpdateRequest, positionId))
                .thenThrow(new ResourceNotFound("Position not found"));

        // Act and Assert
        ResourceNotFound exception = assertThrows(ResourceNotFound.class, () -> {
            postController.update(positionId, postUpdateRequest);
        });

        // Verify the exception message if needed
        assertEquals("Position not found", exception.getMessage());
    }

    @Test
    @DisplayName("Test Success Scenario for Updating Post")
    void testUpdatePost() {
        // Arrange
        Long postId = 1L;
        PostUpdateRequest postRequest = PostUpdateRequest.builder()
                .name("Updated Position")
                .description("Updated Description")
                .build();

        PostResponse updatedPost = PostResponse.builder()
                .id(postId)
                .name(postRequest.getName())
                .description(postRequest.getDescription())
                .build();

        Mockito.when(postService.update(postRequest, postId)).thenReturn(updatedPost);

        // Act
        ResponseEntity<PostResponse> responseEntity = postController.update(postId, postRequest);

        // Assert
        Assertions.assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

        PostResponse responseBody = responseEntity.getBody();
        assertEquals(postId, responseBody.getId());
        assertEquals(postRequest.getName(), responseBody.getName());
        assertEquals(postRequest.getDescription(), responseBody.getDescription());
    }

    @Test
    @DisplayName("Test Success Scenario for Deleting Post")
    void testDeletePost() {
        // Arrange
        Long postId = 1L;

        // Act
        ResponseEntity<SuccessResponse> responseEntity = postController.delete(postId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());

        // Verify that the positionService.delete method is called with the correct positionId
        Mockito.verify(postService, Mockito.times(1)).delete(postId);
    }

    @Test
    @DisplayName("Test Not Found Scenario for Delete Post")
    void testDeletePost_NotFound() {
        // Arrange
        Long postId = 1L;

        // Mock the service behavior to throw a ResourceNotFound exception when deleting
        Mockito.doThrow(new ResourceNotFound("Position not found while deleting"))
                .when(postService).delete(postId);

        // Act and Assert
        ResourceNotFound exception = assertThrows(ResourceNotFound.class, () -> {
            postController.delete(postId);
        });

        // Verify the exception message if needed
        assertEquals("Position not found while deleting", exception.getMessage());
    }
}