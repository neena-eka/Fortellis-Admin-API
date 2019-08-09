package com.cdk.dc.hello;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Scope("cucumber-glue")
@Getter
@Setter
public class World {

  private ResponseEntity response;

  public ResponseEntity getResponse() {
    return response;
  }

  public void setResponse(ResponseEntity responseEntity) {
    response = responseEntity;
  }
}
