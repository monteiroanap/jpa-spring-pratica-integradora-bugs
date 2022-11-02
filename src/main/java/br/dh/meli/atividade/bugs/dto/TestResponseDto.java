package br.dh.meli.atividade.bugs.dto;

import br.dh.meli.atividade.bugs.model.TestCase;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TestResponseDto implements Serializable {
    private Long id;
    private String description;
    private boolean tested;
    private boolean passed;
    private int numberOfTries;
    private LocalDate lastUpdate;

    public TestResponseDto(TestCase testCase) {
        this.description = testCase.getDescription();
        this.id = testCase.getId();
        this.tested = testCase.isTested();
        this.passed = testCase.isPassed();
        this.numberOfTries = testCase.getNumberOfTries();
        this.lastUpdate = testCase.getLastUpdate();
    }
}
