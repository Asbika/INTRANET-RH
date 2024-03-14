package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.mappers.CandidacyMapper;
import com.giantLink.Hiring.recrutementservice.repositories.CandidacyRepository;
import com.giantLink.Hiring.recrutementservice.repositories.QualificationRepository;
import lombok.RequiredArgsConstructor;
import com.giantLink.Hiring.recrutementservice.entities.Candidacy;
import com.giantLink.Hiring.recrutementservice.entities.Cv;
import com.giantLink.Hiring.recrutementservice.entities.Post;
import com.giantLink.Hiring.recrutementservice.entities.Qualification;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.models.requests.CandidacyRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CandidacyResponse;
import com.giantLink.Hiring.recrutementservice.repositories.CvRepository;
import com.giantLink.Hiring.recrutementservice.repositories.PostRepository;
import com.giantLink.Hiring.recrutementservice.services.CandidacyService;

import jakarta.transaction.Transactional;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidacyServiceImpl implements CandidacyService {

    private final CandidacyRepository candidacyRepository;
    private final QualificationRepository qualificationRepository;
    private final CvRepository cvRepository;
    private final PostRepository postRepository;
    private final MessageSource messageSource;

    @Override
    @Transactional
    public CandidacyResponse add(CandidacyRequest request) {
        Candidacy candidacy = CandidacyMapper.INSTANCE.requestToEntity(request);

        // Check if the required IDs exist
        Cv cv = cvRepository.findById(request.getCvId())
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("candidacy.cv.not.found", null, Locale.getDefault())));
        candidacy.setCv(cv);

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("candidacy.post.not.found", null, Locale.getDefault())));
        candidacy.setPost(post);

        Qualification qualification = qualificationRepository.findById(request.getQualificationId())
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("candidacy.qualification.not.found", null, Locale.getDefault())));
        candidacy.setQualification(qualification);

        candidacyRepository.save(candidacy);
        CandidacyResponse candidacyResponse = CandidacyMapper.INSTANCE.entityToResponse(candidacy);
        return candidacyResponse;
    }

    @Override
    public List<CandidacyResponse> getAll() {
        return CandidacyMapper.INSTANCE.listToResponseList(candidacyRepository.findAll());
    }

    @Override
    public CandidacyResponse get(Long id) {
        return CandidacyMapper.INSTANCE.entityToResponse(candidacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("candidacy.not.found", null, Locale.getDefault()))));
    }

    @Override
    @Transactional
    public CandidacyResponse update(CandidacyRequest request, Long id) {
        Optional<Candidacy> findCandidacy = candidacyRepository.findById(id);
        Optional<Qualification> findQualification = qualificationRepository.findById(request.getQualificationId());
        
        if (!findCandidacy.isPresent()) {
            throw new ResourceNotFound(messageSource.getMessage("candidacy.not.found", null, Locale.getDefault()));
        }
        if (!findQualification.isPresent()) {
            throw new ResourceNotFound(messageSource.getMessage("candidacy.qualification.not.found", null, Locale.getDefault()));
        }
        
        Candidacy candidacy = findCandidacy.get();

        // Check if the required IDs exist
        Cv cvById = cvRepository.findById(request.getCvId())
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("candidacy.cv.not.found", null, Locale.getDefault())));
        candidacy.setCv(cvById);

        Qualification qualification = findQualification.get();
        candidacy.setApplicationName(request.getApplicationName());
        candidacy.setQualification(qualification);

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("candidacy.post.not.found", null, Locale.getDefault())));
        candidacy.setPost(post);

        CandidacyResponse candidacyResponse = CandidacyMapper.INSTANCE.entityToResponse(candidacy);
        return candidacyResponse;
    }

    @Override
    @Transactional
    public void delete(Long id) {
    	CandidacyMapper.INSTANCE.entityToResponse(candidacyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("candidacy.not.found", null, Locale.getDefault()))));
        candidacyRepository.deleteById(id);
    }
}
