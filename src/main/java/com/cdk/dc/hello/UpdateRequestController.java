package com.cdk.dc.hello;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping(value="/admin")

public class UpdateRequestController {

    @CrossOrigin(origins = "*")
    @PatchMapping("/update-request")
    public ResponseEntity<?> updateRequest(@RequestBody String requestBody) throws Exception {
        try {
            JSONParser parser = new JSONParser();
            JSONObject requestInfo = (JSONObject) parser.parse(requestBody);
            AmazonDynamoDB requestInfoTable = (new Table()).getTable();
            String tableName = "entityInfo";
            Map key = new HashMap();
            key.put("id", new AttributeValue(requestInfo.get("id").toString()));
            AttributeValue statusValue = new AttributeValue(requestInfo.get("requestStatus").toString());
            AttributeValue id = new AttributeValue(requestInfo.get("id").toString());
            Map attributeValues = new HashMap();
            attributeValues.put(":r", statusValue);
            attributeValues.put(":id", id);
            UpdateItemRequest request = new UpdateItemRequest().withKey(key).withTableName(tableName).withExpressionAttributeValues(attributeValues).withConditionExpression("id = :id").withUpdateExpression("set requestStatus = :r").withReturnValues("ALL_NEW");
            UpdateItemResult result = requestInfoTable.updateItem(request);

            if (!statusValue.getS().equals("Pending")) {
                JSONObject callback = new JSONObject();
                callback.put("status", statusValue.getS().toLowerCase());
                callback.put("error", "");
                System.out.println(callback.toJSONString());
            }

            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
        }
    }
}
