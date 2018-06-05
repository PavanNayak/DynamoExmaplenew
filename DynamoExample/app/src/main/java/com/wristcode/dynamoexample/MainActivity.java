package com.wristcode.dynamoexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3DataSource;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.wristcode.dynamoexample.model.Datas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.provider.Contacts.SettingsColumns.KEY;

//import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBClientBuilder;
public class MainActivity extends AppCompatActivity {
    //static AmazonDynamoDBClient client;
    private DynamoDBMapper mapper;
    private static int PRODUCT_ID;
    List<Datas> data;
    Thread t;
    RecyclerView recyclerView;
    GenerateKeys generateKeys;
    CognitoCachingCredentialsProvider credentialsProvider;
    AmazonDynamoDBClient mDynamoDBClient;
   // CustomAdapter adapter;
    Table dbTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        data=new ArrayList<>();

        generateKeys=new GenerateKeys(MainActivity.this);
//
//
//        credentialsProvider = new CognitoCachingCredentialsProvider(
//                getApplicationContext(),
//                "ap-south-1:60314d52-74b2-4a50-92e2-ea4828bc8775", // Identity pool ID
//                Regions.AP_SOUTH_1 // Region
//        );
//        mDynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
//        mDynamoDBClient.setRegion(Region.getRegion(Regions.AP_SOUTH_1));




       // DynamoDBMapper.builder().dynamoDBClient().build();







        //AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
//
//        ScanRequest scanRequest = new ScanRequest()
//                .withTableName("riyaz_dev_submissions_for_review ");
//
//       // ScanResult result = client.scan(scanRequest);
//        for (Map<String, AttributeValue> item : result.getItems()){
//           // printItem(item);
//            Log.d("dynamoresult",item.toString());
//        }





                  //  init();

                   // getAllRows();

//
//
//        Thread thread = new Thread(new Runnable(){
//            public void run() {
//                try {
//
//                    credentialsProvider = new CognitoCachingCredentialsProvider(
//                getApplicationContext(), "ap-south-1:60314d52-74b2-4a50-92e2-ea4828bc8775", Regions.AP_SOUTH_1);
//
//        AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(credentialsProvider);
//        dbTable = Table.loadTable(dbClient, "riyaz_dev_submissions_for_review");
//
//
//        getAll();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        thread.start();
    }


//
//    private class AsyncDownload extends AsyncTask<Void,Void,Void>{
//
//        @Override
//        protected void onPreExecute(){
//
//
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//
//            ManagerClass managerClass=new ManagerClass();
//            managerClass.getcredentials(MainActivity.this);
//            AmazonS3Client amazonS3Client=managerClass.inits3client(MainActivity.this);
//         //   S3ObjectInputStream s3ObjectInputStream=amazonS3Client(Utils.bucket,GenerateKeys.keyholder.get(HoldPosition.position.toString()).getObjectContent();
//
//            return null;
//        }
//    }

//
//
//    public List getAll() {
//        return dbTable.query(new Primitive(credentialsProvider.getCachedIdentityId())).getAllResults();
//    }
//
//
//    private void init() {
//
//
//        CognitoCachingCredentialsProvider provider = new CognitoCachingCredentialsProvider(
//                getApplicationContext(),
//                "ap-south-1:60314d52-74b2-4a50-92e2-ea4828bc8775",
//                Regions.AP_SOUTH_1);
//        mDynamoDBClient = new AmazonDynamoDBClient(provider);
//        mDynamoDBClient.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
//
//        AmazonS3 s3Client = new AmazonS3Client(provider);
//        s3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
//
//
//
//
////
////        client = new AmazonDynamoDBClient();
////        Region usWest2 = Region.getRegion(Regions.AP_SOUTH_1);
////        client.setRegion(usWest2);
////        mapper = new DynamoDBMapper(client);
//    }
//
//
//
//
//
//    private void getAllRows() {
//        ScanRequest scanRequest = new ScanRequest()
//                .withTableName("riyaz_dev_submissions_for_review");
//        try {
//            System.out.println("Scan Request: " + scanRequest);
//            ScanResult scanResponse = mDynamoDBClient.scan(scanRequest);
//            for (Map item : scanResponse.getItems()) {
//                System.out.println(item.get("userId") + " , " +
//                        item.get("title") + " , " +
//                        item.get("timeStamp")+ " , " +
//                        item.get("isReviewed"));
//
//                Toast.makeText(this, item.get("title").toString(), Toast.LENGTH_SHORT).show();
//               // data.add((Datas) item.get("userId"));
//
//            }
//
//            adapter=new CustomAdapter(getApplicationContext(),data);
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//            adapter.notifyDataSetChanged();
//
//            System.out.println("Scan Response: " + scanResponse);
//            System.out.println("Count: " + scanResponse.getCount());
//            System.out.println("Items: " + scanResponse.getItems());
//        } catch (AmazonServiceException e) {
//            e.printStackTrace();
//        } catch (AmazonClientException e) {
//            e.printStackTrace();
//        }
//    }

}
