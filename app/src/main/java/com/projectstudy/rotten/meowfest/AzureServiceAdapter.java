package com.projectstudy.rotten.meowfest;

import android.content.Context;
import com.microsoft.windowsazure.mobileservices.*;

import java.net.MalformedURLException;

public class AzureServiceAdapter {
    private String mobileBackendUrl = "https://meowfest.azurewebsites.net";
    private Context mContext;
    private MobileServiceClient mClient;
    private static AzureServiceAdapter mInstance = null;

    private AzureServiceAdapter(Context context) {
        mContext = context;
        try {
            // Creates Mobile Service client instance with URL and key
            mClient = new MobileServiceClient(
                    mobileBackendUrl,
                    mContext
            );
        } catch (MalformedURLException e) {
            System.out.println("There was an error creating the Mobile Service. Verify the URL");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void Initialize(Context context) {
        if (mInstance == null) {
            mInstance = new AzureServiceAdapter(context);
        } else {
            throw new IllegalStateException("AzureServiceAdapter is already initialized");
        }
    }

    public static AzureServiceAdapter getInstance() {
        if (mInstance == null) {
            throw new IllegalStateException("AzureServiceAdapter is not initialized");
        }
        return mInstance;
    }

    public MobileServiceClient getClient() {
        return mClient;
    }

    // Place any public methods that operate on mClient here.
}
