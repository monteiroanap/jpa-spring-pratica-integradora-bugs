package br.dh.meli.atividade.bugs.service;

import br.dh.meli.atividade.bugs.dto.TestRequestDto;
import br.dh.meli.atividade.bugs.dto.TestResponseDto;
import br.dh.meli.atividade.bugs.model.TestCase;
import br.dh.meli.atividade.bugs.repository.ITestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TestCaseService {

    @Autowired
    private ITestCaseRepository testCaseRepository;

    public TestCase newCaseTest(TestRequestDto testRequestDto) {
        TestCase testCase = new TestCase();
        testCase.setDescription(testRequestDto.getDescription());
        testCase.setTested(testRequestDto.isTested());
        testCase.setPassed(testRequestDto.isPassed());
        testCase.setNumberOfTries(testRequestDto.getNumberOfTries());
        return testCaseRepository.save(testCase);

    }
    public List<TestCase> getAllTestCase() {
        return testCaseRepository.findAll();
    }
    public TestCase getById( long id) throws Exception {
        Optional<TestCase> testCaseOptional = testCaseRepository.findById(id);
        if(testCaseOptional.isPresent()){
            return testCaseOptional.get();
        }
        throw new Exception("id do testCase nao encontrado");
    }

    public TestResponseDto updateTest(TestRequestDto testRequestDto) throws Exception{
        TestCase testCase= getById(testRequestDto.getId());
        testCase.setDados(testRequestDto);
        testCaseRepository.save(testCase);
        TestResponseDto responseDto = new TestResponseDto(testCase);
        return responseDto;
    }

    public void delete(long id) throws Exception {
        TestCase testCase = getById(id);
        testCaseRepository.delete(testCase);
    }

    public List<TestResponseDto> getByDate(LocalDate date) {
        List<TestCase> testCaseList = testCaseRepository.findByLastUpdate(date);
        return testCaseList.stream().map(TestResponseDto::new).collect(Collectors.toList()); // NEW: metodo chamado é o construtor
    }
}
