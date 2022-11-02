package br.dh.meli.atividade.bugs.repository;

import br.dh.meli.atividade.bugs.dto.TestResponseDto;
import br.dh.meli.atividade.bugs.model.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface ITestCaseRepository extends JpaRepository<TestCase, Long> {
    List<TestCase> findByLastUpdate(LocalDate date);
}
