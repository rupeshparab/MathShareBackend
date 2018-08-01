package org.benetech.mathshare.model.mother;

import org.benetech.mathshare.model.entity.ProblemSolution;
import org.benetech.mathshare.model.entity.SolutionStep;

public abstract class SolutionStepUtils {

    public static final String DEFAULT_STEP_VALUE = "step value";
    public static final Boolean DEFAULT_DELETED_VALUE = false;

    public static SolutionStep createValidInstance() {
        ProblemSolution solution = ProblemSolutionUtils.createValidInstance();
        return new SolutionStep(DEFAULT_STEP_VALUE, solution, DEFAULT_DELETED_VALUE);
    }
}
