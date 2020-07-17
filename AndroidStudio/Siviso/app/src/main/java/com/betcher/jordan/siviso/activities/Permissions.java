package com.betcher.jordan.siviso.activities;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.betcher.jordan.siviso.Defaults;
import com.betcher.jordan.siviso.R;
import com.betcher.jordan.siviso.activities.methods.CancelActivity;

public class Permissions extends AppCompatActivity
{
    PermissionsRow fineLocation;
    //PermissionsRow notificationPolicy;
    
    boolean granted_fineLocation;
    boolean granted_notificationPolicy;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
    
        granted_fineLocation = ActivityCompat
                                .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                               PackageManager.PERMISSION_GRANTED;
        
        NotificationManager notificationManager =
            (NotificationManager) this.getSystemService(
            Context.NOTIFICATION_SERVICE);
        granted_notificationPolicy =
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && notificationManager.isNotificationPolicyAccessGranted();
        
        fineLocation = createPermissionRowFineLocation(granted_fineLocation);
        
        ifDone();
    }
    
    private void ifDone()
    {
        if(true == granted_fineLocation)
        {
            CancelActivity.run(this);
        }
    }
    
    private PermissionsRow createPermissionRowFineLocation(
    boolean granted_fineLocation)
    {
        Button toggleDetails = findViewById(R.id.button_fineLocation_details);
        TextView detailsDisplay = findViewById(R.id.textView_fineLocation_details);
        Button grantPermission = findViewById(R.id.button_fineLocation_accept);
        PermissionsRow fineLocation = new PermissionsRow(toggleDetails, detailsDisplay, grantPermission, granted_fineLocation);
        return fineLocation;
    }
    
    public void clickFineLocationDetails(View view)
    {
        fineLocation.toggleDetails();
    }
    
    public void clickFineLocationAccept(View view)
    {
        ActivityCompat.requestPermissions(this, new String[]
                                          {Manifest.permission.ACCESS_FINE_LOCATION},
                                          Defaults.REQUEST_LOCATION_PERMISSION
                                         );
        
        granted_fineLocation = ActivityCompat
                               .checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                               PackageManager.PERMISSION_GRANTED;
        
        fineLocation.permission(granted_fineLocation);
        ifDone();
    }
    
    
    class PermissionsRow
    {
        Button toggleDetails;
        TextView detailsDisplay;
        Button grantPermission;
        
        public PermissionsRow(Button toggleDetails, TextView detailsDisplay, Button grantPermission, boolean granted)
        {
            this.toggleDetails = toggleDetails;
            this.detailsDisplay = detailsDisplay;
            this.grantPermission = grantPermission;
            permission(granted);
        }
    
        public void toggleDetails()
        {
            if(detailsDisplay.getVisibility() == TextView.VISIBLE)
            {
                detailsDisplay.setVisibility(TextView.INVISIBLE);
            }
            else
            {
                detailsDisplay.setVisibility(TextView.VISIBLE);
            }
        }
        
        public void permission(boolean granted)
        {
            toggleDetails.setEnabled(!granted);
            detailsDisplay.setVisibility(TextView.INVISIBLE);
            grantPermission.setEnabled(!granted);
        }
    }
}