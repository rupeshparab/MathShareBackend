package org.benetech.mathshare.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolutionDTO {

    private ProblemDTO problem;

    private String editCode;

    private String shareCode;

    private List<SolutionStepDTO> steps = new ArrayList<>();

    private List<String> palettes;

    public SolutionDTO(ProblemDTO problem, List<SolutionStepDTO> steps, String editCode, List<String> palettes) {
        this.problem = problem;
        this.steps = steps;
        this.editCode = editCode;
        this.palettes = palettes;
    }
}
