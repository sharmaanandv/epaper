package com.iapps.epaper

import com.fasterxml.jackson.databind.ObjectMapper
import com.iapps.epaper.dto.EpaperDTO
import io.restassured.RestAssured
import io.restassured.config.EncoderConfig
import io.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static io.restassured.RestAssured.given

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("local")
class Epaper extends Specification {

    @LocalServerPort
    private int localServerPort

    @Autowired
    private ObjectMapper objectMapper

    def setup() {
        RestAssured.port = localServerPort
    }

    def cleanup() {
        RestAssured.reset()
    }

    def "Upload Sample Epaper XML Persist Result and Retrieve Same Result"() {
        given:
        File file = new ClassPathResource("epaper_valid_sample.xml").getFile()
        expect:
        def responseFromUpload = given()
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.XML)))
                .contentType("multipart/form-data")
                .multiPart("file", file)
                .post("/iapps/v1/epaper/upload")
                .then().statusCode(HttpStatus.CREATED.value()).extract().response()

        responseFromUpload.asString() == "1"

        EpaperDTO dto = EpaperDTO.builder().id(1).build();
        def responseFromGetAll = given()
                .contentType("application/json")
                .when()
                .body(objectMapper.writeValueAsString(dto))
                .post("iapps/v1/epaper/getAll")
                .then().statusCode(HttpStatus.OK.value()).extract().response()

        new org.json.JSONObject(responseFromGetAll.asString()).get("numberOfElements").toString() == "1"
    }


    def "Upload Invalid Sample Epaper XML Expect Error"() {
        given:
        File file = new ClassPathResource("epaper_invalid_sample.xml").getFile()
        expect:
        def response = given()
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.XML)))
                .contentType("multipart/form-data")
                .multiPart("file", file)
                .post("/iapps/v1/epaper/upload")
                .then().statusCode(HttpStatus.NOT_FOUND.value()).extract().response()

        response.asString().toString().equals("{\"code\":1001,\"message\":\"XML is not valid\"}")
    }

}
