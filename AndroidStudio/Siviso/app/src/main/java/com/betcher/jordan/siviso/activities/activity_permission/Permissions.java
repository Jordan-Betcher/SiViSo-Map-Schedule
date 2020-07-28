package com.betcher.jordan.siviso.activities.activity_permission;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.betcher.jordan.siviso.R;

public class Permissions extends AppCompatActivity
{
    PermissionFineLocation fineLocation;
    UiOfPermissions uiOfFineLocationUI;
    
    PermissionNotificationPolicy notificationPolicy;
    UiOfPermissions uiOfNotificationPolicy;
    
    Button openSiviso;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
    
        uiOfFineLocationUI = createUiOfFineLocation();
        uiOfFineLocationUI.init();
        fineLocation = new PermissionFineLocation(uiOfFineLocationUI);
        fineLocation.initUI(this);
    
    
        uiOfNotificationPolicy = createUiOfNotificationPolicy();
        uiOfNotificationPolicy.init();
        notificationPolicy = new PermissionNotificationPolicy(uiOfNotificationPolicy);
        notificationPolicy.initUI(this);
    
        openSiviso = findViewById(R.id.button_OpenSiviso);
        
        if(allPermissionsGranted())
        {
            this.finish();
        }
        else
        {
            openSiviso.setEnabled(false);
        }
    }
    
    private boolean allPermissionsGranted()
    {
        if(true == fineLocation.isGranted(this) && true == notificationPolicy.isGranted(this))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    private UiOfPermissions createUiOfFineLocation()
    {
        TextView title = findViewById(R.id.textView_fineLocation);
        Button toggleDetails = findViewById(R.id.button_fineLocation_details);
        TextView detailsDisplay = findViewById(R.id.textView_fineLocation_details);
        Button grantPermission = findViewById(R.id.button_fineLocation_accept);
        UiOfPermissions uiOfFineLocationUI = new UiOfPermissions(title, toggleDetails, detailsDisplay, grantPermission);
        return uiOfFineLocationUI;
    }
    
    private UiOfPermissions createUiOfNotificationPolicy()
    {
        TextView title = findViewById(R.id.textView_notificationPolicy);
        Button toggleDetails = findViewById(R.id.button_notificationPolicy_details);
        TextView detailsDisplay = findViewById(R.id.textView_notificationPolicy_details);
        Button grantPermission = findViewById(R.id.button_notificationPolicy_accept);
        UiOfPermissions uiOfNotificationPolicy = new UiOfPermissions(title, toggleDetails, detailsDisplay, grantPermission);
        return uiOfNotificationPolicy;
    }
    
    public void clickFineLocationDetails(View view)
    {
        uiOfFineLocationUI.toggleDetails();
    }
    
    public void clickFineLocationAccept(View view)
    {
        fineLocation.run(this);
        
        ifDone();
    }
    
    private void ifDone()
    {
        if(allPermissionsGranted())
        {
            openSiviso.setEnabled(true);
        }
    }
    
    public void clickNotificationPolicyDetails(View view)
    {
        uiOfNotificationPolicy.toggleDetails();
    }
    
    public void clickNotificationPolicyAccept(View view)
    {
        notificationPolicy.run(this);
        
        ifDone();
    }
    
    
    public void clickOpenSiviso(View view)
    {
        if(allPermissionsGranted())
        {
            this.finish();
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode,
                                     int resultCode,
                                     Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        refresh();
    }
    
    @Override
    public void onRequestPermissionsResult(
    int requestCode, @NonNull String[] permissions,
    @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions,
                                         grantResults);
        refresh();
    }
    
    public void refresh()
    {
        fineLocation.refreshUI(this);
        notificationPolicy.refreshUI(this);
        ifDone();
    }
}