package com.example.updateapp;

import static java.lang.Math.log;

import android.os.Bundle;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;

public class MainActivity extends AppCompatActivity {


    private ActivityResultLauncher activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        /*

        Follow us on Facebook Page: Android Squad
        Author: Mi Tayef - Majharul Islam Tayef

        Contact:

        WhatsApp: +880 1622656866

        Linkedin: Majharul Islam Tayef
        Link: https://www.linkedin.com/in/mitayef/

        Facebook: Mi Tayef
        Link: https://www.facebook.com/MiTayef.09

        Facebook Pages: Android Squad
        Link: https://www.facebook.com/AndroidSquadOfficial

        Github: Android Squad
        Link: https://github.com/MiTayef

         */




        checkInAppUpdate();

    } // Close onCreate Method Here


    private void checkInAppUpdate(){
        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());

        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

                appUpdateManager.startUpdateFlowForResult(

                        appUpdateInfo,
                        activityResultLauncher,
                        AppUpdateOptions.newBuilder(AppUpdateType.IMMEDIATE).build());

            }
        });

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartIntentSenderForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() != RESULT_OK) {
                         //   log("Update flow failed! Result code: " + result.getResultCode());
                        }
                    }
                });

    } // Close checkInAppUpdate Method Here


} // Close Public Class