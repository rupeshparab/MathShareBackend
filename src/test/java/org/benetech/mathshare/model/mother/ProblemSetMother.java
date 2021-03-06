package org.benetech.mathshare.model.mother;

import org.benetech.mathshare.model.entity.ProblemSet;

import java.util.Arrays;

public abstract class ProblemSetMother {

    public static final Long EDIT_CODE = 49L;
    public static final String PALETTE = "Geometry";

    public static ProblemSet validInstance() {
        ProblemSet set = new ProblemSet();
        set.setPalettes(Arrays.asList(PALETTE));
        return set;
    }

    public static ProblemSet withEditCode(long code) {
        ProblemSet result = validInstance();
        result.setEditCode(code);
        return result;
    }

    public static ProblemSet mockInstance() {
        return withEditCode(EDIT_CODE);
    }
}
