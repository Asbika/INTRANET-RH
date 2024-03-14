package com.giantLink.Hiring.recrutementservice.models;

import com.giantLink.Hiring.recrutementservice.models.requests.PostRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.PostUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.PostResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ModelsTest {

    @Test
    @DisplayName("Tests Post Models getter & setter")
    void testModels() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(PostRequest.class);
        beanTester.testBean(PostRequest.PostRequestBuilder.class);
        beanTester.testBean(PostUpdateRequest.class);
        beanTester.testBean(PostUpdateRequest.PostUpdateRequestBuilder.class);
        beanTester.testBean(PostResponse.class);
        beanTester.testBean(PostResponse.PostResponseBuilder.class);
    }
}

