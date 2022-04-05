package com.example.playasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.playasapp.adapters.AdapterPages;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference_user = database.getReference("Users").child(firebaseUser.getUid());
    DatabaseReference ref_beach = database.getReference("Beach").child(firebaseUser.getUid());
    DatabaseReference ref_pools = database.getReference("Pool").child(firebaseUser.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 view2 = findViewById(R.id.viewPage);
        view2.setAdapter(new AdapterPages(this));

        final TabLayout tabLayout = findViewById(R.id.tabla);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, view2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Playas");
                        tab.setIcon(R.drawable.ic_beach);
                        break;
                    case 1:
                        tab.setText("Playas");
                        tab.setIcon(R.drawable.ic_swimming_pool);
                        break;
                    case 2:
                        tab.setText("Todo");
                        tab.setIcon(R.drawable.ic_swim);
                        break;


                }
            }
        });


    }
}