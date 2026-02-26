package com.smartContact.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.xml.transform.sax.SAXResult;
import java.net.http.HttpResponse;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage  {

    private HttpStatus status;
    private String message;

}
