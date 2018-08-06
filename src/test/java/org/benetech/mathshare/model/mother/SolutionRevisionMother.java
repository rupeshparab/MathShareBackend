package org.benetech.mathshare.model.mother;

import org.benetech.mathshare.model.entity.ProblemSolution;
import org.benetech.mathshare.model.entity.SolutionRevision;

public abstract class SolutionRevisionMother {

    public static SolutionRevision validInstance() {
        ProblemSolution problemSolution = ProblemSolutionMother.validInstance();
        return new SolutionRevision(problemSolution);
    }
}
