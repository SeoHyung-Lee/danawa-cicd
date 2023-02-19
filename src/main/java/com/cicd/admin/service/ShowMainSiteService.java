package com.cicd.admin.service;

import com.cicd.admin.dto.ShowMainSiteDto;
import com.cicd.admin.entity.ShowMainSiteEntity;
import com.cicd.admin.mapper.ShowMainSiteMapper;
import com.cicd.admin.repository.ShowMainSiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowMainSiteService {

    private final ShowMainSiteRepository showMainSiteRepository;
    private final ShowMainSiteMapper showMainSiteMapper;

    public ShowMainSiteService(ShowMainSiteRepository showMainSiteRepository, ShowMainSiteMapper showMainSiteMapper) {
        this.showMainSiteRepository = showMainSiteRepository;
        this.showMainSiteMapper = showMainSiteMapper;
    }

    public List<ShowMainSiteDto> getShowMainSiteDtos() {
        return showMainSiteRepository.findAll().stream()
                .map(item -> showMainSiteMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public List<ShowMainSiteDto> getShowMainSiteDtosByMain() {
        return showMainSiteRepository.findAll().stream()
                .filter(item -> item.getShowMain())
                .map(item -> showMainSiteMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public ShowMainSiteDto createShowMainSite(ShowMainSiteDto showMainSiteDto) {
        ShowMainSiteEntity saveEntity = showMainSiteRepository.save(showMainSiteMapper.convertEntity(showMainSiteDto));
        return showMainSiteMapper.convertDto(saveEntity);
    }

    public ShowMainSiteDto deleteShowMainSite(Long id) {
        ShowMainSiteEntity entity = showMainSiteRepository.findById(id).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            ShowMainSiteEntity saveEntity = showMainSiteRepository.save(entity);
            return showMainSiteMapper.convertDto(saveEntity);
        } else {
            return null;
        }
    }
}
