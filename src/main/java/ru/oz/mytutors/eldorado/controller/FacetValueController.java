package ru.oz.mytutors.eldorado.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oz.mytutors.dto.FacetValueDto;
import ru.oz.mytutors.eldorado.model.AttributeValue;
import ru.oz.mytutors.eldorado.model.FacetValue;
import ru.oz.mytutors.eldorado.model.RangedAttributeValue;
import ru.oz.mytutors.eldorado.repository.AttributeValueRepository;
import ru.oz.mytutors.eldorado.repository.FacetValueRepository;
import ru.oz.mytutors.eldorado.repository.RangedAttributeValueRepository;

import java.util.List;

@Slf4j
@RestController
public class FacetValueController {

    @Autowired
    FacetValueRepository facetValueRepository;

    @Autowired
    RangedAttributeValueRepository rangedAttributeValueRepository;

    @Autowired
    AttributeValueRepository attributeValueRepository;

    @RequestMapping(value = "/facetvalue/{id}")
    public ResponseEntity<FacetValue> getFacetValueById(@PathVariable("id") long id) {
        return new ResponseEntity(facetValueRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/facetvalue/{id}", method = RequestMethod.PUT)
    public ResponseEntity<FacetValue> updateFacetValue(@PathVariable("id") long id, @RequestBody FacetValueDto dto) {
        log.info("Updating facetValue {}", id);

        FacetValue currentFV = facetValueRepository.findOne(id);

        if (currentFV == null) {
            log.warn("FacetValue with id {} not found", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        List<AttributeValue> attributeValues = attributeValueRepository.findAll(dto.getAttributeValuesIds());
        RangedAttributeValue rangedAttributeValue = rangedAttributeValueRepository.findOne(dto.getRangedAttributeValueId());
        facetValueRepository.updateAttributeValues(currentFV, attributeValues, rangedAttributeValue);

        return new ResponseEntity(currentFV, HttpStatus.OK);
    }

}
