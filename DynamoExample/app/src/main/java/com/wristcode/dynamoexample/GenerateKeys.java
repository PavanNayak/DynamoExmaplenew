package com.wristcode.dynamoexample;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.gson.Gson;
import com.wristcode.dynamoexample.model.Datas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GenerateKeys {
    Context context;

    AmazonDynamoDBClient dynamoDBClient;
    DynamoDBMapper dynamoDBMapper;
    public static ArrayList<HashMap<String,Object>> keyholder=new ArrayList<>();
    List<S3ObjectSummary>  s3ObjectSummaryList;

    GenerateKeys(Context context) {
        this.context = context;



        new downloadkeys().execute();
    }



    private class downloadkeys extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute(){


        }
        @Override
        protected Void doInBackground(Void... voids) {

            ManagerClass managerClass=new ManagerClass();
            managerClass.getcredentials(context);
            AmazonS3Client amazonS3Client=managerClass.inits3client(context);
//            s3ObjectSummaryList=amazonS3Client.listObjects(Utils.bucket).getObjectSummaries();

//            for(S3ObjectSummary summary:s3ObjectSummaryList){
//                 HashMap<String,Object> map=new HashMap<>();
//                 map.put("title",summary.getKey());
//                 keyholder.add(map);
//            }

            CognitoCachingCredentialsProvider provider = new CognitoCachingCredentialsProvider(
                context,
                "ap-south-1:60314d52-74b2-4a50-92e2-ea4828bc8775",
                Regions.AP_SOUTH_1);
            dynamoDBClient = new AmazonDynamoDBClient(provider);
            dynamoDBClient.setRegion(Region.getRegion(Regions.AP_SOUTH_1));

        AmazonS3 s3Client = new AmazonS3Client(provider);
        s3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));




            HashMap<String,Condition> scanFilter=new HashMap<>();
            ScanRequest scanRequest=new ScanRequest("riyaz_dev_submissions_for_review");
            ScanResult scanResult=  dynamoDBClient.scan(scanRequest);
            Log.d("scanresukt",scanResult.toString());
            dynamoDBMapper = DynamoDBMapper.builder()
                    .dynamoDBClient(dynamoDBClient)
                    .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                    .build();

            Datas news = new Datas();

            Condition rangeKeyCondition = new Condition()
                    .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                    .withAttributeValueList(new AttributeValue().withS("title"));

            DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                    .withHashKeyValues("")
                    .withRangeKeyCondition("title", rangeKeyCondition)
                    .withConsistentRead(false);

            PaginatedList<Datas> result = dynamoDBMapper.query(Datas.class, queryExpression);

            Gson gson = new Gson();
            StringBuilder stringBuilder = new StringBuilder();

            // Loop through query results
            for (int i = 0; i < result.size(); i++) {
                String jsonFormOfItem = gson.toJson(result.get(i));
                stringBuilder.append(jsonFormOfItem + "\n\n");
            }

            // Add your code here to deal with the data result
            Log.d("Query result: ", stringBuilder.toString());

            if (result.isEmpty()) {
                // There were no items matching your query.
            }


            return null;
        }
    }



}
