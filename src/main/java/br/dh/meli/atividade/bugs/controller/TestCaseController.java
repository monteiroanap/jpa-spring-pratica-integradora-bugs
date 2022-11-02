package br.dh.meli.atividade.bugs.controller;

import br.dh.meli.atividade.bugs.dto.TestRequestDto;
import br.dh.meli.atividade.bugs.dto.TestResponseDto;
import br.dh.meli.atividade.bugs.model.TestCase;
import br.dh.meli.atividade.bugs.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {

    @Autowired
    private TestCaseService testCaseService;

   @PostMapping("/new")
    public ResponseEntity<Object> newCaseTest(@RequestBody TestRequestDto testRequestDto) {
        TestCase testCase = testCaseService.newCaseTest(testRequestDto);
        TestResponseDto responseDto = new TestResponseDto(testCase);
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<TestResponseDto> testResponseDtoList = new ArrayList<>();
        List<TestCase> testCases = testCaseService.getAllTestCase();
        testResponseDtoList = testCases.stream().map(TestResponseDto::new).collect(Collectors.toList());
        return new ResponseEntity<>(testResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        TestCase testCase = null;
        try {
            testCase = testCaseService.getById(id);
            TestResponseDto responseDto = new TestResponseDto(testCase);
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTestCaseId(@RequestBody TestRequestDto testRequestDto){
        try {
            TestResponseDto testUpdate = testCaseService.updateTest(testRequestDto);
            return new ResponseEntity<>(testUpdate,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTestCase(@PathVariable long id){
        try {
            testCaseService.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/date")
    public ResponseEntity<Object> testCasesDate(@RequestParam String date){
        List<TestResponseDto> testResponseDtoList = testCaseService.getByDate(date);
        return new ResponseEntity<>(testResponseDtoList,HttpStatus.OK);
    }
}
