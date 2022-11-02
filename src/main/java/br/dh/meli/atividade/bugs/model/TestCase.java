package br.dh.meli.atividade.bugs.model;

import br.dh.meli.atividade.bugs.dto.TestRequestDto;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "test_case")
@Data

public class TestCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_case")
    private Long id;

    @Column(length = 50, nullable = false)
    private String description;

    @Column(length = 30, nullable = false)
    private boolean tested;

    @Column(length = 5, nullable = false)
    private boolean passed;

    @Column(name = "number_of_tries", length = 15, nullable = false)
    private int numberOfTries;

    @Column(name = "last_update")
//    @Temporal(TemporalType.DATE)
    private LocalDate lastUpdate;

    public void setDados(TestRequestDto testRequestDto){
        this.description = testRequestDto.getDescription();
        this.tested = testRequestDto.isTested();
        this.passed = testRequestDto.isPassed();
        this.numberOfTries = testRequestDto.getNumberOfTries();
    }


    @PrePersist
    public void setup(){
        this.lastUpdate = LocalDate.now();
    }

}
