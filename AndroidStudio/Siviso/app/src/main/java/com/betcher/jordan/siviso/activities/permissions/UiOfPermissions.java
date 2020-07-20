package com.betcher.jordan.siviso.activities.permissions;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;

import com.betcher.jordan.siviso.Defaults;

public class UiOfPermissions
{
    TextView title;
    Button toggleDetails;
    TextView detailsDisplay;
    Button grantPermission;
    
    public UiOfPermissions(TextView title, Button toggleDetails, TextView detailsDisplay, Button grantPermission)
    {
        this.title = title;
        this.toggleDetails = toggleDetails;
        this.detailsDisplay = detailsDisplay;
        this.grantPermission = grantPermission;
    }
    
    public void init()
    {
        detailsDisplay.setVisibility(TextView.GONE);
    }
    
    public void permissionAccepted()
    {
        title.setTextColor(Defaults.PERMISSIONS_OPEN_SIVISO_ACCEPTED_COLOR);
        grantPermission.setEnabled(false);
    }
    
    public void permissionNotAccepted()
    {
        title.setTextColor(Defaults.PERMISSIONS_OPEN_SIVISO_NOT_ACCEPTED_COLOR);
        grantPermission.setEnabled(true);
    }
    
    public void toggleDetails()
    {
        if(detailsDisplay.getVisibility() == TextView.VISIBLE)
        {
            detailsDisplay.setVisibility(TextView.GONE);
        }
        else
        {
            detailsDisplay.setVisibility(TextView.VISIBLE);
        }
    }
    
    public void remove()
    {
        toggleDetails.setEnabled(false);
        grantPermission.setEnabled(false);
        
        title.setVisibility(TextView.GONE);
        toggleDetails.setVisibility(TextView.GONE);
        detailsDisplay.setVisibility(TextView.GONE);
        grantPermission.setVisibility(TextView.GONE);
    }
    
}
