package com.sce.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sce.app.entity.PersonEntity;
import com.sce.app.service.PersonService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.sce.app.common.ValuesCommon.EXPECTED_ID_1;
import static com.sce.app.common.ValuesCommon.EXPECTED_ID_2;
import static com.sce.app.common.ValuesCommon.EXPECTED_NAME_IVAN;
import static com.sce.app.common.ValuesCommon.getPerson_Ivan;
import static com.sce.app.common.ValuesCommon.getPerson_Oleg;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    public void testSavePersonEntity() throws Exception {

        PersonEntity person = getPerson_Ivan();
        person.setId(EXPECTED_ID_1);

        String inputInJson = this.mapToJson(person);
        String URI = "/add";
        Mockito.when(personService.createPerson(Mockito.any(PersonEntity.class))).thenReturn(person);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept("application/json").content(inputInJson)
                .contentType("application/json");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        String outputInJson = mockHttpServletResponse.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.CREATED.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void testGetPersonEntity() throws Exception {

        PersonEntity person = getPerson_Ivan();
        person.setId(EXPECTED_ID_1);

        String URI = "/get/1";

        Mockito.when(personService.getPersonById(Mockito.anyInt())).thenReturn(person);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept("application/json")
                .contentType("application/json");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(person);
        String outputInJson = mvcResult.getResponse().getContentAsString();

        assertThat(outputInJson).isEqualTo(expectedJson);
        assertEquals(HttpStatus.FOUND.value(), mvcResult.getResponse().getStatus());

    }

    @Test
    public void getAllPersonEntity() throws Exception {

        PersonEntity person1 = getPerson_Ivan();
        person1.setId(EXPECTED_ID_1);
        PersonEntity person2 = getPerson_Oleg();
        person2.setId(EXPECTED_ID_2);

        List<PersonEntity> listperson = new ArrayList<>();
        listperson.add(person1);
        listperson.add(person2);

        String URI = "/get";

        Mockito.when(personService.getAllPersons()).thenReturn(listperson);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept("application/json")
                .contentType("application/json");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(listperson);
        String outputInJson = mvcResult.getResponse().getContentAsString();

        assertThat(outputInJson).isEqualTo(expectedJson);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

    }

    @Test
    public void testUpdatePersonEntity() throws Exception {

        PersonEntity person = getPerson_Oleg();
        person.setId(EXPECTED_ID_2);

        String URI = "/update/1";
        String inputInJson = this.mapToJson(person);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept("application/json").content(inputInJson)
                .contentType("application/json");
        person.setName(EXPECTED_NAME_IVAN);

        Mockito.when(personService.updatePersonById(Mockito.any(PersonEntity.class), Mockito.anyInt())).thenReturn(person);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(person);
        String outputInJson = mvcResult.getResponse().getContentAsString();
        assertThat(expectedJson).isEqualTo(outputInJson);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeletePersonEntity() throws Exception {

        PersonEntity person = getPerson_Oleg();
        person.setId(EXPECTED_ID_2);
        String URI = "/delete/1";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).contentType("text/plain");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);

    }
}
