package com.cdk.dc.hello;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/test")

public class DeleteRequestController {
    @DeleteMapping("/remove-request")
    public ResponseEntity<?> deleteRequest(@RequestBody String requestBody) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject requestInfo = (JSONObject) parser.parse(requestBody);
        AmazonDynamoDB requestInfoTable = (new Table()).getTable();
        String tableName = "entityInfo";
        Map key = new HashMap();
        key.put("id", new AttributeValue(requestInfo.get("id").toString()));
        DeleteItemRequest request = new DeleteItemRequest().withKey(key).withTableName(tableName);
        DeleteItemResult result = requestInfoTable.deleteItem(request);

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }
}
