package org.benetech.mathshare.model.mother;

import org.benetech.mathshare.model.entity.ProblemSet;
import org.benetech.mathshare.model.entity.ProblemSetRevision;

public abstract class ProblemSetRevisionMother {

    public static final String VALID_CODE = "4H53SD";

    public static final String INVALID_CODE = "RH%5TD";

    public static ProblemSetRevision validInstance() {
        ProblemSet problemSet = ProblemSetMother.validInstance();
        return validInstance(problemSet);
    }

    public static ProblemSetRevision validInstance(ProblemSet problemSet) {
        return new ProblemSetRevision(problemSet);
    }
}
