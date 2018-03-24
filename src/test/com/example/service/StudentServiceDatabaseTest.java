package com.example.service;


import com.example.dao.StudentMapper;
import com.example.model.StudentModel;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

public class StudentServiceDatabaseTest {

    private StudentService studentService = new StudentServiceDatabase();

    @Mock
    private StudentMapper studentMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.studentService = new StudentServiceDatabase(this.studentMapper);
    }

    @Test
    public void selectStudent(){
        //Given
        StudentModel studentModel = new StudentModel("1606954975","rama",3.9);
        StudentModel check = new StudentModel("1606954975","rama",3.9);
        BDDMockito.given(studentMapper.selectStudent("1606954975")).willReturn(studentModel);


//        When
        StudentModel test = studentService.selectStudent("1606954975");

//        Then
        assertThat(test, notNullValue());
        assertThat(test, equalTo(check));

    }


    @Test
    public void selectAllStudents(){
//        Given
        List<StudentModel> studentModels = new ArrayList<>();
        StudentModel studentModel = new StudentModel("1608964879", "Joko", 3.4);
        studentModels.add(studentModel);

        List<StudentModel> checks = new ArrayList<>();
        StudentModel check = new StudentModel("1608964879","Joko", 3.4);
        checks.add(check);

        BDDMockito.given(studentMapper.selectAllStudents()).willReturn(studentModels);

//        When
        List<StudentModel> test = studentService.selectAllStudents();

//        Then
        assertThat(test,notNullValue());
        assertThat(test.isEmpty(),equalTo(false));
        assertThat(test.size(), equalTo(1));
        assertThat(test, equalTo(checks));
    }

    @Test
    public void addStudent(){
//        Given
        StudentModel studentModel = new StudentModel("1606978578", "Elon", 4.0);
        StudentModel check = new StudentModel("1606978578", "Elon", 4.0);
        BDDMockito.given(studentService.addStudent(studentModel)).willReturn(true);

//        When
        boolean test = studentService.addStudent(studentModel);

//        Then
        BDDMockito.then(studentMapper).should().addStudent(check);
        assertThat(test, equalTo(true));
    }

    @Test
    public void deleteStudent(){
//        Given
        StudentModel studentModel = new StudentModel("1606957895", "Musk", 3.9);
        StudentModel check = new StudentModel("1606957895", "Musk", 3.9);
        BDDMockito.given(studentService.deleteStudent("1606957895")).willReturn(true);

//        When
        boolean test = studentService.deleteStudent("1606957895");

//        Then
        BDDMockito.then(studentMapper).should().deleteStudent("1606957895");
//        assertThat(test, notNullValue());
        assertThat(test, equalTo(true));
    }

    @Test
    public void updateStudent(){
//        Given
        StudentModel studentmodel = new StudentModel("16069436778", "Jobs", 4.0);
        StudentModel check = new StudentModel("16069436778", "Jobs", 4.0);
        BDDMockito.given(studentService.updateStudent(studentmodel)).willReturn(true);
//        When
        boolean test  = studentService.updateStudent(studentmodel);
//        Then
        BDDMockito.then(studentMapper).should().updateStudent(check);
        assertThat(test,equalTo(true));
    }

}
