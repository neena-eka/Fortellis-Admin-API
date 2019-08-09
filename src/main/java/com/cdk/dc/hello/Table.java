package com.cdk.dc.hello;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Table {
    private AmazonDynamoDB requestInfoTable;

    public Table() throws Exception {
        requestInfoTable = createTable();
    }

    private AmazonDynamoDB createTable() throws IOException {
        InputStream input = Table.class.getClassLoader().getResourceAsStream("config.properties");
        Properties prop = new Properties();
        prop.load(input);

        AmazonDynamoDBClientBuilder clientBuilder = AmazonDynamoDBClientBuilder.standard();

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(prop.getProperty("aws.accesskey"), prop.getProperty("aws.secretkey"));
        clientBuilder.withRegion(prop.getProperty("aws.region"));

        clientBuilder.setCredentials(new AWSStaticCredentialsProvider(awsCreds));

        requestInfoTable = clientBuilder.build();
        return requestInfoTable;
    }

    public AmazonDynamoDB getTable() {
        return requestInfoTable;
    }

}


