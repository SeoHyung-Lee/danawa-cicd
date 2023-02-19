package com.cicd.admin.controller;

import com.cicd.admin.dto.ShowMainSiteDto;
import com.cicd.admin.service.ShowMainSiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/showmainsite")
public class ShowMainSiteController {

    private final ShowMainSiteService showMainSiteService;

    public ShowMainSiteController(ShowMainSiteService showMainSiteService) {
        this.showMainSiteService = showMainSiteService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ShowMainSiteDto> createShowMainSite(@RequestBody ShowMainSiteDto showMainSiteDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(showMainSiteService.createShowMainSite(showMainSiteDto));
    }

    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<ShowMainSiteDto> deleteShowMainSite(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(showMainSiteService.deleteShowMainSite(id));
    }
}
