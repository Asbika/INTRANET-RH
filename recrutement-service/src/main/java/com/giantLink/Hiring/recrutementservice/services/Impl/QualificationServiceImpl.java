package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.mappers.QualificationMapper;
import com.giantLink.Hiring.recrutementservice.repositories.CandidacyRepository;
import com.giantLink.Hiring.recrutementservice.repositories.QualificationRepository;
import com.giantLink.Hiring.recrutementservice.entities.Qualification;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceAlreadyExistsException;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.models.requests.QualificationRequest;
import com.giantLink.Hiring.recrutementservice.models.response.QualificationResponse;
import com.giantLink.Hiring.recrutementservice.services.QualificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    QualificationRepository qualificationRepository;
    @Autowired
    CandidacyRepository candidacyRepository;
    @Autowired
    private MessageSource messageSource;

    @Override
    public QualificationResponse add(QualificationRequest request) {
        if (request.getQualificationName() != null && qualificationRepository.findByQualificationName(request.getQualificationName()).isPresent()) {
            String errorMessage = messageSource.getMessage("qualification.validation.duplicateName", null, LocaleContextHolder.getLocale());
            throw new ResourceAlreadyExistsException(errorMessage);
        }

        Qualification qualification = QualificationMapper.INSTANCE.requestToEntity(request);
        qualificationRepository.save(qualification);
        return QualificationMapper.INSTANCE.entityToResponse(qualification);
    }

    @Override
    public List<QualificationResponse> getAll() {
        return QualificationMapper.INSTANCE.listToResponseList(qualificationRepository.findAll());
    }

    @Override
    public QualificationResponse get(Long id) {
        Qualification qualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("qualification", "id", id.toString()));

        return QualificationMapper.INSTANCE.entityToResponse(qualification);
    }

    @Override
    public QualificationResponse update(QualificationRequest request, Long id) {
        Optional<Qualification> qualificationById = qualificationRepository.findById(id);
        if (qualificationById.isPresent()) {
            qualificationById.get().setQualificationName(request.getQualificationName());
            qualificationById.get().setWording(request.getWording());
            Qualification qualification = qualificationRepository.save(qualificationById.get());
            return QualificationMapper.INSTANCE.entityToResponse(qualification);
        } else {
            String errorMessage = messageSource.getMessage("qualification.validation.notFound", null, LocaleContextHolder.getLocale());
            throw new ResourceNotFound(errorMessage);
        }

    }

    @Override
    public void delete(Long id) {
        qualificationRepository.deleteById(id);
    }
}