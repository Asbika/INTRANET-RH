package com.giantLink.Hiring.recrutementservice.services.Impl;

import com.giantLink.Hiring.recrutementservice.models.requests.RegionRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.RegionUpdateRequest;
import com.giantLink.Hiring.recrutementservice.entities.Region;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceAlreadyExistsException;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.mappers.RegionMapper;
import com.giantLink.Hiring.recrutementservice.models.response.RegionResponse;
import com.giantLink.Hiring.recrutementservice.repositories.RegionRepository;
import com.giantLink.Hiring.recrutementservice.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionRepository regionRepository;
    
    @Autowired
    MessageSource messageSource;

    @Override
    public RegionResponse add(RegionRequest request) {
        if (request.getName() != null && regionRepository.findByName(request.getName()).isPresent()) {
            String errorMessage = messageSource.getMessage("region.validation.nameAlreadyExists", null, LocaleContextHolder.getLocale());
            throw new ResourceAlreadyExistsException(errorMessage);
        }
        Region region = RegionMapper.INSTANCE.requestToEntity(request);
        return RegionMapper.INSTANCE.entityToResponse(regionRepository.save(region));
    }

    @Override
    public List<RegionResponse> getAll() {
        return RegionMapper.INSTANCE.listToResponseList(regionRepository.findAll());
    }

    @Override
    public RegionResponse get(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("region.validation.notFound", null, LocaleContextHolder.getLocale())));

        return RegionMapper.INSTANCE.entityToResponse(region);
    }

    @Override
    public RegionResponse update(RegionUpdateRequest request, Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("region.validation.notFound", null, LocaleContextHolder.getLocale())));

        if (region.getName() != null){
            Optional<Region> regionByName = regionRepository.findByName(region.getName());
            if (regionByName.isPresent() && !regionByName.get().getId().equals(id)) {
                String errorMessage = messageSource.getMessage("region.validation.nameAlreadyExists", null, LocaleContextHolder.getLocale());
                throw new ResourceAlreadyExistsException(errorMessage);
            }
        }

        RegionMapper.INSTANCE.updateEntity(request, region);
        regionRepository.save(region);

        return RegionMapper.INSTANCE.entityToResponse(region);
    }

    @Override
    public void delete(Long id) {
        regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(messageSource.getMessage("region.validation.notFound", null, LocaleContextHolder.getLocale())));

        regionRepository.deleteById(id);
    }
}
