package com.knstech.apnaopd;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.knstech.apnaopd.Patient.HomeActivity;
import com.knstech.apnaopd.Retailer.RetailerActivity;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class DrawerUtil {
    public static void getDrawer(final Activity activity, Toolbar toolbar) {

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.m1)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Doctor")
                                .withEmail("doctor@gmail.com")
                                .withIcon(R.drawable.ic_launcher_foreground)
                                .withIdentifier(101)   )
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Retailer")
                                .withEmail("retailer@gmail.com")
                                .withIcon(R.drawable.ic_launcher_foreground)
                                .withIdentifier(102)    )

                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Patient")
                                .withEmail("patient@gmail.com")
                                .withIdentifier(103)
                                .withIcon(R.drawable.m1))
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName("Pathologists")
                                .withEmail("pathologist@gmail.com")
                                .withIdentifier(104)
                                .withIcon(R.drawable.google))

                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        if(profile.getIdentifier()==102)
                            activity.startActivity(new Intent(activity,RetailerActivity.class));
                        else
                            Toast.makeText(activity, profile.toString(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();


        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        PrimaryDrawerItem drawerItemManagePlayers = new PrimaryDrawerItem().withIdentifier(1)
                .withName("Home").withIcon(R.drawable.google);
        PrimaryDrawerItem drawerItemManagePlayersTournaments = new PrimaryDrawerItem()
                .withIdentifier(2).withName("Profile").withIcon(R.drawable.google);


        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName("Setings").withIcon(R.drawable.google);
        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4)
                .withName("About").withIcon(R.drawable.google);
        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(5)
                .withName("Help").withIcon(R.drawable.google);
        SecondaryDrawerItem drawerItemDonate = new SecondaryDrawerItem().withIdentifier(6)
                .withName("Donate").withIcon(R.drawable.google);





        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withAccountHeader(headerResult)
                
                .withActionBarDrawerToggle(true)
                .withActionBarDrawerToggleAnimated(true)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        drawerItemManagePlayers,
                        drawerItemManagePlayersTournaments,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        drawerItemSettings,
                        drawerItemHelp,
                        drawerItemDonate
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem.getIdentifier() == 2 && !(activity instanceof HomeActivity)) {
                            // load tournament screen
                            Intent intent = new Intent(activity, HomeActivity.class);
                            view.getContext().startActivity(intent);
                        }
                        return true;
                    }
                })
                .build();
    }
}
