package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.enums.Telecommuting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import com.giantLink.Hiring.recrutementservice.entities.Campaign;
import com.giantLink.Hiring.recrutementservice.entities.Candidacy;
import com.giantLink.Hiring.recrutementservice.entities.Candidate;
import com.giantLink.Hiring.recrutementservice.entities.Post;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceAlreadyExistsException;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.mappers.CandidacyMapper;
import com.giantLink.Hiring.recrutementservice.mappers.CandidateMapper;
import com.giantLink.Hiring.recrutementservice.mappers.PostMapper;
import com.giantLink.Hiring.recrutementservice.models.requests.PostRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.PostUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidacyResponse;
import com.giantLink.Hiring.recrutementservice.models.response.CandidateResponse;
import com.giantLink.Hiring.recrutementservice.models.response.PostResponse;
import com.giantLink.Hiring.recrutementservice.repositories.CampaignRepository;
import com.giantLink.Hiring.recrutementservice.repositories.CandidacyRepository;
import com.giantLink.Hiring.recrutementservice.repositories.PostRepository;
import com.giantLink.Hiring.recrutementservice.services.PostService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CampaignRepository campaignRepository;
    @Autowired
    CandidacyRepository candidacyRepository;
    @Autowired
    private MessageSource messageSource;

    @Autowired
    CampaignServiceImpl campaignServiceImpl;

    @Override
    public PostResponse add(PostRequest request) {
        if (request.getName() != null && postRepository.findByName(request.getName()).isPresent()) {
            String errorMessage = messageSource.getMessage("post.validation.duplicateName", null, Locale.getDefault());
            throw new ResourceAlreadyExistsException(errorMessage);
        }

        Post post = PostMapper.INSTANCE.requestToEntity(request);
        if (request.getCampaignId() != null) {
            Campaign campaign = campaignRepository.findById(request.getCampaignId())
                    .orElseThrow(() -> new ResourceNotFound("campaign", "id", request.getCampaignId().toString()));
            post.setCampaign(campaign);
        }
        return PostMapper.INSTANCE.entityToResponse(postRepository.save(post));
    }


    public List<PostResponse> getAll() {
        List<Post> posts = postRepository.findAll();
        if (posts.isEmpty()) {
            String message = messageSource.getMessage("posts.notFound.message", null, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        } else {
            return PostMapper.INSTANCE.listToResponseList(posts);
        }
    }

    @Override
    public PostResponse get(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent()){
            String message = messageSource.getMessage("post.notFound.message", new Object[]{"Id", id}, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }
        return PostMapper.INSTANCE.entityToResponse(post.get());
    }

    @Override
    public PostResponse update(PostUpdateRequest request, Long id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Post","Id",id.toString()));

        if(request.getName() != null){
            Optional<Post> postByName = postRepository.findByName(request.getName() );
            if(postByName.isPresent() && !postByName.get().getId().equals(id)) {
                String errorMessage = messageSource.getMessage("post.validation.duplicateName", null, Locale.getDefault());
                throw new ResourceAlreadyExistsException(errorMessage);
            }
        }
        PostMapper.INSTANCE.updateEntity(request, post);

        if (request.getCampaignId() != null) {
            Campaign campaign = campaignRepository.findById(request.getCampaignId())
                    .orElseThrow(() -> new ResourceNotFound("Campaign", "Id", request.getCampaignId().toString()));

            post.setCampaign(campaign);
        }

        Optional<List<Candidacy>> candidacies = candidacyRepository.findByPostId(id);

        post.setCandidacies(candidacies.get());

        postRepository.save(post);

        return PostMapper.INSTANCE.entityToResponse(post);
    }


    @Override
    public List<PostResponse> getByTelecommuting(Telecommuting telecommuting) {
        Optional<List<Post>> posts = postRepository.findByTelecommuting(telecommuting);
        if (posts.isEmpty()) {
            String message = messageSource.getMessage("post.notFound.message", new Object[]{"Telecommuting", telecommuting}, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        } else {
            return PostMapper.INSTANCE.listToResponseList(posts.get());
        }
    }
    @Override
    public PostResponse getByName(String name){
        Optional<Post> post = postRepository.findByName(name);
        if(!post.isPresent()){
            String message = messageSource.getMessage("post.notFound.message", new Object[]{"name", name}, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }else{
            return PostMapper.INSTANCE.entityToResponse(post.get());
        }
    }
    @Override
    public void delete(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(!post.isPresent()){
            String message = messageSource.getMessage("post.notFound.message", new Object[]{"Id", id}, "No Message", LocaleContextHolder.getLocale());
            throw new ResourceNotFound(message);
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<CandidacyResponse> getPotentialCandidacies(Long post_id) {
    	Optional<Post> postOptional = postRepository.findById(post_id);
        if (!postOptional.isPresent()) {
            String errorMessage = messageSource.getMessage("post.validation.notFound", null, Locale.getDefault());
            throw new ResourceNotFound(errorMessage);
        }
        Post post = postOptional.get();
        List<Candidacy> potentialCandidacies = post.getCandidacies();
        String target_keywords = post.getKeyWords();
        for (int i = 0; i < potentialCandidacies.size(); i++) {
            if (hasSameWords(target_keywords, potentialCandidacies.get(i).getQualification().getWording()) == false) {
                potentialCandidacies.remove(i);
            }
        }
        List<CandidacyResponse> candidacyResponses = CandidacyMapper.INSTANCE.listToResponseList(potentialCandidacies);
        return candidacyResponses;
    }

    @Override
    public List<CandidateResponse> getPotentielCandidates(Long post_id) {
    	Optional<Post> postOptional = postRepository.findById(post_id);
        if (!postOptional.isPresent()) {
            String errorMessage = messageSource.getMessage("post.validation.notFound", null, Locale.getDefault());
            throw new ResourceNotFound(errorMessage);
        }
        List<CandidacyResponse> potentialCandidacies = getPotentialCandidacies(post_id);
        List<Candidate> potentialCandidates = new ArrayList<>();
        Optional<Post> findPost = postRepository.findById(post_id);
        if (!findPost.isPresent()) {
            throw new ResourceNotFound("Post Not Found");
        }
        Post post = postOptional.get();
        String candidateMessage =messageSource.getMessage("post.candidateMessage", new Object[] { post.getName() ,post.getTelecommuting()}, Locale.getDefault());
        for (int i = 0; i < potentialCandidacies.size(); i++) {
            Candidate candidate = potentialCandidacies.get(i).getCv().getCandidate();
            candidate.setMessage(candidateMessage);
            potentialCandidates.add(candidate);
        }
        List<CandidateResponse> candidateResponse = CandidateMapper.INSTANCE.listToResponseList(potentialCandidates);
        return candidateResponse;
    }

    public boolean hasSameWords(String keywords, String posteRequirements) {
    	if (keywords == null || posteRequirements == null) {
            return false;
        }
        // Split the strings into words and remove any leading/trailing spaces
        String[] listkeyWords = keywords.trim().split(";");
        String[] postWords2 = posteRequirements.trim().split(";");
        // Check if both arrays have the same length
        if (listkeyWords.length != postWords2.length) {
            return false;
        }
        // Sort the arrays to ensure the words are in the same order
        Arrays.sort(listkeyWords);
        Arrays.sort(postWords2);
        // Compare each word in the arrays
        for (int i = 0; i < listkeyWords.length; i++) {
            if (!listkeyWords[i].equals(postWords2[i])) {
                return false;
            }
        }
        return true;
    }

}
