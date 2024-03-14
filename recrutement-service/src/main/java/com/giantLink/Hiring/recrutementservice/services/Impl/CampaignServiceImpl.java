package com.giantLink.Hiring.recrutementservice.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.giantLink.Hiring.recrutementservice.entities.Campaign;
import com.giantLink.Hiring.recrutementservice.exceptions.ResourceNotFound;
import com.giantLink.Hiring.recrutementservice.mappers.CampaignMapper;
import com.giantLink.Hiring.recrutementservice.models.requests.CampaignRequest;
import com.giantLink.Hiring.recrutementservice.models.requests.CampaignUpdateRequest;
import com.giantLink.Hiring.recrutementservice.models.response.CampaignResponse;
import com.giantLink.Hiring.recrutementservice.repositories.CampaignRepository;
import com.giantLink.Hiring.recrutementservice.services.CampaignService;

import java.util.List;
@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private CampaignRepository campaignRepository;

    @Override
    public CampaignResponse add(CampaignRequest request) {
        Campaign campaign = CampaignMapper.INSTANCE.requestToEntity(request);
        campaignRepository.save(campaign);
        return CampaignMapper.INSTANCE.entityToResponse(campaign);
    }

    @Override

    public List<CampaignResponse> getAll() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return CampaignMapper.INSTANCE.listToResponseList(campaigns);
    }

    @Override
    public List<CampaignResponse> get(String name) {
        List<Campaign> campaigns = campaignRepository.findByName(name);
        if (campaigns.isEmpty()) {
            String errorMessage = messageSource.getMessage("error.resource.notfound",
                    new Object[]{"This name of campaign", "name", name}, LocaleContextHolder.getLocale());
            throw new ResourceNotFound(errorMessage);
        } else {
            return CampaignMapper.INSTANCE.listToResponseList(campaigns);
        }
    }

    @Override
    public CampaignResponse get(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = messageSource.getMessage("error.resource.notfound",
                            new Object[]{"This Campaign id", "id", id.toString()}, LocaleContextHolder.getLocale());
                    return new ResourceNotFound(errorMessage);
                });

        return CampaignMapper.INSTANCE.entityToResponse(campaign);
    }

    @Override
    public CampaignResponse update(CampaignUpdateRequest request, Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = messageSource.getMessage("error.resource.notfound",
                            new Object[]{"This campaign", "id", id.toString()}, LocaleContextHolder.getLocale());
                    return new ResourceNotFound(errorMessage);
                });
        CampaignMapper.INSTANCE.updateEntity(request, campaign);
        campaignRepository.save(campaign);
        return CampaignMapper.INSTANCE.entityToResponse(campaign);
    }

    @Override
    public void delete(Long id) {
        Campaign campaign = campaignRepository.findById(id)
                .orElseThrow(() -> {
                    String errorMessage = messageSource.getMessage("error.resource.notfound",
                            new Object[]{"This campaign", "id", id.toString()}, LocaleContextHolder.getLocale());
                    return new ResourceNotFound(errorMessage);
                });

        campaignRepository.delete(campaign);
    }
}
