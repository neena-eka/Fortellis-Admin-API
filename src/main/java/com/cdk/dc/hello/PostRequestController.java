package com.cdk.dc.hello;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/client")
public class PostRequestController {

    @CrossOrigin(origins = "*")
    @PostMapping("/add-request")
    public ResponseEntity<?> postRequest(@RequestBody String requestBody) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject requestInfo = (JSONObject) parser.parse(requestBody);
        AmazonDynamoDB requestInfoTable = (new Table()).getTable();
        String tableName = "entityInfo";
        Map item = new HashMap();
        try {
            item.put("id", new AttributeValue(requestInfo.get("id").toString()));
            item.put("entityId", new AttributeValue(requestInfo.get("entityId").toString()));
            item.put("name", new AttributeValue(requestInfo.get("name").toString()));
            item.put("date", new AttributeValue(requestInfo.get("date").toString()));
            item.put("requestStatus", new AttributeValue(requestInfo.get("requestStatus").toString()));
            item.put("address", new AttributeValue(requestInfo.get("address").toString()));
            item.put("phoneNumber", new AttributeValue(requestInfo.get("phoneNumber").toString()));
            item.put("storeName", new AttributeValue(requestInfo.get("storeName").toString()));
            item.put("storeId", new AttributeValue(requestInfo.get("storeId").toString()));
            item.put("solutionId", new AttributeValue(requestInfo.get("solutionId").toString()));
            item.put("solutionName", new AttributeValue(requestInfo.get("solutionName").toString()));
            item.put("developer", new AttributeValue(requestInfo.get("developer").toString()));
            item.put("connectionId", new AttributeValue(requestInfo.get("connectionId").toString()));
            item.put("email", new AttributeValue(requestInfo.get("email").toString()));
            item.put("subscriptionId", new AttributeValue(requestInfo.get("subscriptionId").toString()));

            PutItemResult result = requestInfoTable.putItem(new PutItemRequest().withTableName(tableName).withItem(item));

            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Invalid Request", HttpStatus.BAD_REQUEST);
        }
    }
}
