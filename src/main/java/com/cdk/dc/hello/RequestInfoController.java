package com.cdk.dc.hello;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class RequestInfoController {

    @CrossOrigin(origins = "*")
    @GetMapping("/requests")
    public ResponseEntity<?> getRequestInfo() throws Exception {
        AmazonDynamoDB requestInfoTable = (new Table()).getTable();
        String tableName = "entityInfo";
        Map attributeNames = new HashMap();
        attributeNames.put("#N", "name");
        attributeNames.put("#D", "date");
        String projectionExpression = "id, #N, address, phoneNumber, #D, storeId, storeName, requestStatus, solutionId, subscriptionId, connectionId, solutionName, developer, email, entityId";
        ScanResult result =  requestInfoTable.scan(new ScanRequest(tableName).withExpressionAttributeNames(attributeNames).withProjectionExpression(projectionExpression));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
