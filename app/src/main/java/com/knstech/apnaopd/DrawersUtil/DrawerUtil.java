package com.knstech.apnaopd.DrawersUtil;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.knstech.apnaopd.AddNewProfileActivity;
import com.knstech.apnaopd.GenModelClasses.User.UserAuth;
import com.knstech.apnaopd.Utils.AppUtils;
import com.knstech.apnaopd.Doctor.DoctorHomeActivity;
import com.knstech.apnaopd.Patient.HomeActivity;
import com.knstech.apnaopd.Patient.ListOfOrderActivity;
import com.knstech.apnaopd.Patient.PAppointmentViewerActivity;
import com.knstech.apnaopd.Profile.AddressActivity;
import com.knstech.apnaopd.Profile.ProfileActivity;
import com.knstech.apnaopd.R;
import com.knstech.apnaopd.Retailer.RetailerActivity;
import com.knstech.apnaopd.Utils.Connections.RequestGet;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DrawerUtil {

    private static Boolean isDoctor = false;
    private static Boolean isRetailer = false;
    private static Boolean isPathologist = false;

      public static void getDrawer(final Activity activity, Toolbar toolbar) {

          String URL = AppUtils.HOST_ADDRESS+"/api/users/"+ UserAuth.getmUser(activity).getGid();
          RequestGet request = new RequestGet(activity);
          request.getJSONObject(URL, new RequestGet.JSONObjectResponseListener() {
              @Override
              public void onResponse(JSONObject object) {
                  try {

                      isDoctor= object.getBoolean("isDoctor");
                      isRetailer = object.getBoolean("isRetailer");
                      isPathologist = object.getBoolean("isPathologist");

                  } catch (JSONException e) {
                      e.printStackTrace();
                  }

              }
          });


          IProfile patient =  new ProfileDrawerItem()
                  .withName("Patient")
                  .withEmail("patient@apnaopd")
                  .withIcon(R.drawable.p)
                  .withIdentifier(101);

          IProfile doctor =  new ProfileDrawerItem()
                  .withName("Doctor")
                  .withEmail("doctor@apnaopd")
                  .withIcon(R.drawable.d)
                  .withIdentifier(103);

          IProfile retailer =   new ProfileDrawerItem()
                  .withName("Retailer")
                  .withEmail("retailer@apnaopd")
                  .withIcon(R.drawable.r)
                  .withIdentifier(104);

          IProfile pathologist =  new ProfileDrawerItem()
                  .withName("Pathologist")
                  .withEmail("pathologist@apnaopd")
                  .withIcon(R.drawable.pt)
                  .withIdentifier(105);

          IProfile addNewProfile = new ProfileSettingDrawerItem()
                  .withName("Add New Profile")
                  .withIcon(R.drawable.ic_add_black_24dp);


          List<IProfile> iProfiles= new ArrayList<>();
           iProfiles.add(patient);
          if(isDoctor)
            iProfiles.add(doctor);
          if(isRetailer)
              iProfiles.add(retailer);
          if(isPathologist)
              iProfiles.add(pathologist);

          if(!(isDoctor && isRetailer && isPathologist))
              iProfiles.add(addNewProfile);

          IProfile[] iProfiles1 = new IProfile[iProfiles.size()];
          for(int i=0;i< iProfiles.size();i++)
              iProfiles1[i] = iProfiles.get(i);

          AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.color.colorAccentLight)
                .addProfiles(
                           iProfiles1
                )

                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {

                        if(profile.getIdentifier()==102) {
                            activity.startActivity(new Intent(activity, AddNewProfileActivity.class));
                            activity.finish();
                        }
                        else if(profile.getIdentifier()==101)
                        {
                            activity.startActivity(new Intent(activity,HomeActivity.class));
                        }
                        else if(profile.getIdentifier()==103)
                        {
                            activity.startActivity(new Intent(activity,DoctorHomeActivity.class));
                        }
                        else if(profile.getIdentifier()==104)
                        {
                            activity.startActivity(new Intent(activity,RetailerActivity.class));
                        }


                        else
                            Toast.makeText(activity,"Profile is under maintainance, Please check for updates", Toast.LENGTH_LONG).show();
                        return false;
                    }
                })
                .build();

        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("");
        drawerEmptyItem.withEnabled(false);

        PrimaryDrawerItem drawerHome = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName("Home")
                .withIcon(R.drawable.p14)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 1){
                            activity.startActivity(new Intent(activity, HomeActivity.class));
                            activity.finish();
                        }

                        return true;
                    }
                });

        PrimaryDrawerItem drawerProfile = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName("Profile")
                .withIcon(R.drawable.p2)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 2){
                            activity.startActivity(new Intent(activity, ProfileActivity.class));
                            activity.finish();
                        }

                        return true;
                    }
                })
                ;


        PrimaryDrawerItem drawerAddress= new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName("Address")
                .withIcon(R.drawable.p1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 3){
                            activity.startActivity(new Intent(activity,AddressActivity.class));
                            activity.finish();
                        }

                        return false;
                    }
                })
                ;

        PrimaryDrawerItem drawerMyOrders = new PrimaryDrawerItem()
                .withIdentifier(4)
                .withName("My orders")
                .withIcon(R.drawable.p13)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 4){
                            activity.startActivity(new Intent(activity,ListOfOrderActivity.class));
                            activity.finish();

                        }

                        return false;
                    }
                });

        PrimaryDrawerItem drawerQuotations = new PrimaryDrawerItem()
                .withIdentifier(5)
                .withName("Appointments")
                .withIcon(R.drawable.p19).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 5){



                            activity.startActivity(new Intent(activity,PAppointmentViewerActivity.class));
                            activity.finish();
                        }
                        return false;
                    }
                })
                ;
        PrimaryDrawerItem drawerMyHealthRecords = new PrimaryDrawerItem()
                .withIdentifier(6)
                .withName("My Health Records")
                .withIcon(R.drawable.p12).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(drawerItem.getIdentifier() == 6){
                            Toast.makeText(activity, "This page is under maintainance.Please check for updates", Toast.LENGTH_LONG).show();;
                        }

                        return false;
                    }
                })
                ;


        SecondaryDrawerItem drawerItemSettings = new SecondaryDrawerItem().withIdentifier(3)
                .withName("Setings").withIcon(R.drawable.p21);
        SecondaryDrawerItem drawerItemAbout = new SecondaryDrawerItem().withIdentifier(4)
                .withName("About").withIcon(R.drawable.p20);
        SecondaryDrawerItem drawerItemHelp = new SecondaryDrawerItem().withIdentifier(5)
                .withName("Help").withIcon(R.drawable.p5);
        SecondaryDrawerItem drawerItemFeed = new SecondaryDrawerItem().withIdentifier(6)
                .withName("Feedback").withIcon(R.drawable.feed);





        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(activity)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withActionBarDrawerToggle(true)
                .withStickyHeaderShadow(true)
                .withAccountHeader(headerResult)
                .withCloseOnClick(true)
                .withSelectedItem(-1)
                .addDrawerItems(
                        drawerHome,
                        drawerProfile,
                        drawerAddress,
                        drawerMyOrders,
                        drawerQuotations,
                        drawerMyHealthRecords,
                        new DividerDrawerItem(),
                        drawerItemAbout,
                        drawerItemSettings,
                        drawerItemHelp,
                        drawerItemFeed
                )


                .build();

    }
}
