package org.benetech.mathshare.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.benetech.mathshare.mappers.ProblemMapper;
import org.benetech.mathshare.model.dto.ProblemSetDTO;
import org.benetech.mathshare.model.entity.ProblemSetRevision;
import org.benetech.mathshare.service.ProblemSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/set")
@SuppressFBWarnings(value = "SLF4J_FORMAT_SHOULD_BE_CONST",
        justification = "We need Logger to contain non-constant data to be valuable")
public class ProblemSetController {

    private final Logger logger = LoggerFactory.getLogger(ProblemSetController.class);

    @Autowired
    private ProblemSetService problemSetService;

    @GetMapping("/view/{code}")
    ResponseEntity<ProblemSetDTO> getProblemSet(@PathVariable String code) {
        try {
            ProblemSetDTO body = problemSetService.findProblemsByUrlCode(code);
            if (body != null) {
                return new ResponseEntity<>(body, HttpStatus.OK);
            } else {
                logger.error("ProblemSet with code {} wasn't found", code);
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/default")
    ResponseEntity<String> getDefaultProblemSetCode() {
        try {
            String result = problemSetService.getDefaultProblemSetRevisionCode();
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                logger.error("Default problem set wasn't found");
            }
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(path = "/new")
    ResponseEntity<ProblemSetDTO> createProblemSet(@RequestBody ProblemSetDTO problemSet) {
        try {
            ProblemSetRevision saved = problemSetService.saveNewVersionOfProblemSet(
                    ProblemMapper.INSTANCE.fromDto(problemSet));
            return new ResponseEntity<>(ProblemMapper.INSTANCE.toProblemSetDTO(saved), HttpStatus.CREATED);
        } catch (HttpMessageNotReadableException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
