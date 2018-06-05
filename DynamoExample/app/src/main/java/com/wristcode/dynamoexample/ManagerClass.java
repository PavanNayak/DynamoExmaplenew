package com.wristcode.dynamoexample;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

public class ManagerClass {

    AmazonS3Client s3Client=null;
    CognitoCachingCredentialsProvider credentialsProvider=null;


    public CognitoCachingCredentialsProvider getcredentials(Context context){

        credentialsProvider = new CognitoCachingCredentialsProvider(
                context,
                "ap-south-1:60314d52-74b2-4a50-92e2-ea4828bc8775", // Identity pool ID
                Regions.AP_SOUTH_1 // Region
        );

        return credentialsProvider;
    }


    public AmazonS3Client inits3client(Context context){

        if(credentialsProvider==null){
            getcredentials(context);
            s3Client=new AmazonS3Client(credentialsProvider);
            s3Client.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        }


        return  s3Client;
    }
}
