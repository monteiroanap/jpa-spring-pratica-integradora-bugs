package br.dh.meli.atividade.bugs.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor

public class TestRequestDto implements Serializable {

    private long id;
    private String description;
    private boolean tested;
    private boolean passed;
    private int numberOfTries;
}
