package ru.oz.mytutors.eldorado.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oz.mytutors.dto.FacetValueDto;
import ru.oz.mytutors.eldorado.model.FacetValue;
import ru.oz.mytutors.eldorado.repository.FacetValueRepository;

import java.util.Map;

@RestController
@Slf4j
public class FacetValueAdminApiController {

    @Autowired
    FacetValueRepository facetValueRepository;

    @RequestMapping(value = "/facet-values/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> partialUpdate(@RequestBody Map<String, Object> facetValueByMap,
                                        @PathVariable("id") String id) {

        try {
            FacetValue facetValue = new FacetValue();
            BeanUtils.populate(facetValue, facetValueByMap);
            facetValueRepository.save(facetValue);
        } catch (Exception e) {
            log.error("can't set properties on the handler", e);
        }

        return ResponseEntity.ok("resource updated");
    }

    @RequestMapping(value = "/facet-values/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> fullUpdate(@RequestBody FacetValueDto facetValue,
                                        @PathVariable("id") String id) {


        return ResponseEntity.ok("resource updated");
    }

}
