package org.benetech.mathshare.repository;

import org.benetech.mathshare.model.entity.Problem;
import org.benetech.mathshare.model.entity.ProblemSet;
import org.benetech.mathshare.model.entity.ProblemSetRevision;
import org.benetech.mathshare.model.entity.ProblemSolution;
import org.benetech.mathshare.model.entity.SolutionRevision;
import org.benetech.mathshare.model.entity.SolutionStep;
import org.benetech.mathshare.model.mother.ProblemMother;
import org.benetech.mathshare.model.mother.ProblemSetMother;
import org.benetech.mathshare.model.mother.ProblemSetRevisionMother;
import org.benetech.mathshare.model.mother.ProblemSolutionMother;
import org.benetech.mathshare.model.mother.SolutionRevisionMother;
import org.benetech.mathshare.model.mother.SolutionStepMother;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
public class SolutionStepRepositoryTest {

    @Autowired
    private SolutionStepRepository solutionStepRepository;

    @Autowired
    private ProblemSetRepository problemSetRepository;

    @Autowired
    private ProblemSetRevisionRepository problemSetRevisionRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private ProblemSolutionRepository problemSolutionRepository;

    @Autowired
    private SolutionRevisionRepository solutionRevisionRepository;

    @Test
    public void shouldSaveSolutionStep() {
        ProblemSet problemSet = problemSetRepository.save(ProblemSetMother.validInstance());
        ProblemSetRevision problemSetRevision = problemSetRevisionRepository.save(ProblemSetRevisionMother.validInstance(problemSet));
        Problem problem = problemRepository.save(ProblemMother.validInstance(problemSetRevision));
        ProblemSolution problemSolution = problemSolutionRepository.save(ProblemSolutionMother.validInstance(problem));
        SolutionRevision solutionRevision = solutionRevisionRepository.save(SolutionRevisionMother.validInstance(problemSolution));
        solutionStepRepository.save(SolutionStepMother.validInstance(solutionRevision));
        SolutionStep solutionStepFromDB = solutionStepRepository.findAllBySolutionRevision(solutionRevision).get(0);
        Assert.assertEquals(SolutionStepMother.DEFAULT_STEP_VALUE, solutionStepFromDB.getStepValue());
        Assert.assertEquals(SolutionStepMother.DEFAULT_DELETED_VALUE, solutionStepFromDB.getDeleted());
    }
}
