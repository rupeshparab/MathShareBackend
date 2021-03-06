package org.benetech.mathshare.mappers;

import org.benetech.mathshare.model.dto.ProblemDTO;
import org.benetech.mathshare.model.dto.ProblemSetDTO;
import org.benetech.mathshare.model.entity.Problem;
import org.benetech.mathshare.model.entity.ProblemSet;
import org.benetech.mathshare.model.entity.ProblemSetRevision;
import org.benetech.mathshare.model.entity.Scratchpad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProblemMapper {

    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    @Mappings({
            @Mapping(source = "scratchpad", target = "scratchpad", qualifiedByName = "fromScratchpad"),
            @Mapping(source = "problemText", target = "text"),
            @Mapping(source = "problemSetRevision.shareCode", target = "problemSetRevisionShareCode",
                    qualifiedByName = "toCode")})
    ProblemDTO toDto(Problem problem);

    @Mappings({
            @Mapping(source = "scratchpad", target = "scratchpad", qualifiedByName = "toScratchpad"),
            @Mapping(source = "text", target = "problemText"),
            @Mapping(source = "problemSetRevisionShareCode", target = "problemSetRevision.shareCode",
                    qualifiedByName = "fromCode")})
    Problem fromDto(ProblemDTO problem);

    @Mapping(source = "editCode", target = "editCode", qualifiedByName = "fromCode")
    ProblemSet fromDto(ProblemSetDTO problemSet);

    @Mapping(source = "editCode", target = "editCode", qualifiedByName = "toCode")
    ProblemSetDTO toDto(ProblemSet problemSet);

    @Mappings({
            @Mapping(source = "problems", target = "problems", qualifiedByName = "sortProblems"),
            @Mapping(source = "shareCode", target = "shareCode", qualifiedByName = "toCode"),
            @Mapping(source = "problemSet.editCode", target = "editCode", qualifiedByName = "toCode")})
    ProblemSetDTO toProblemSetDTO(ProblemSetRevision revision);

    @Named("toCode")
    default String toCode(Long shareCode) {
        return MapperUtils.toCode(shareCode);
    }

    @Named("fromCode")
    default Long fromCode(String editCode) {
        return MapperUtils.fromCode(editCode);
    }

    @Named("fromScratchpad")
    default String fromScratchpad(Scratchpad scratchpad) {
        return MapperUtils.fromScratchpad(scratchpad);
    }

    @Named("toScratchpad")
    default Scratchpad toScratchpad(String content) {
        return MapperUtils.toScratchpad(content);
    }

    @Named("sortProblems")
    default List<ProblemDTO> sortProblems(List<Problem> problems) {
        return problems.stream().sorted(Comparator.comparing(Problem::getPosition,
                Comparator.nullsFirst(Comparator.naturalOrder())))
                .map(ProblemMapper.INSTANCE::toDto).collect(Collectors.toList());
    }
}
