package com.example.raphael.tcc.AppUI.ViewPagerFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.raphael.tcc.R;

public class SettingsPage extends Fragment
{
    private Switch bbSwitch; //Will hold the reference to the switch dedicated to BubbleButton
    private Switch notifSwitch; //Will hold the reference to the switch dedicated to SpeedUpNotification

    public static SettingsPage newInstance()
    {
        Bundle args = new Bundle();
        SettingsPage instance = new SettingsPage();
        instance.setArguments(args);
        return instance;
    }

    @Override //Standard method needed but has changes nothing
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflate, ViewGroup container, Bundle savedInstanceState)
    {
        View newView = inflate.inflate(R.layout.settings_page, container, false); //Create fragment
        bbSwitch = newView.findViewById(R.id.BubbleButtonSwitch); //Find the layout of BubbleButton
        notifSwitch = newView.findViewById(R.id.NotificationSwitch); //Find layout of Notification

        bbSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {   //Detects a change in the switch position
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked)
                {
                    Intent intent = new Intent("com.example.raphael.tcc.ENABLE_BUTTON");
                    getContext().sendBroadcast(intent);
                }
                else
                {
                    Intent intent = new Intent("com.example.raphael.tcc.DISABLE_BUTTON");
                    getContext().sendBroadcast(intent);
                }

            }
        });

        notifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {   //Detects a change in the switch position
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked) //True if the switch has been set to 'On' position
                {
                    Intent intent = new Intent("com.example.raphael.tcc.ENABLE_NOTIFICATION");
                    getContext().sendBroadcast(intent);
                }
                else //True if the switch is in the 'Off' position
                {
                    Intent intent = new Intent("com.example.raphael.tcc.DISABLE_NOTIFICATION");
                    getContext().sendBroadcast(intent);
                }
            }
        });

        return newView;
    }
}