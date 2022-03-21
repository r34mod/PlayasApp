package com.example.playasapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference_user = database.getReference("Users").child(firebaseUser.getUid());
    DatabaseReference ref_soli_cont = database.getReference("Contador").child(firebaseUser.getUid());
    DatabaseReference ref_estado = database.getReference("Estado").child(firebaseUser.getUid());

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
                        tab.setText("Usuarios");
                        tab.setIcon(R.drawable.ic_users);
                        break;
                    case 1:
                        tab.setText("Chats");
                        tab.setIcon(R.drawable.ic_chat);
                        break;
                    case 2:
                        tab.setText("Solicitudes");
                        tab.setIcon(R.drawable.ic_contacto);
                        final BadgeDrawable badgeDrawable = tab.getOrCreateBadge();
                        badgeDrawable.setBackgroundColor(
                                ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)
                        );
                        badgeDrawable.setVisible(true);
                        //badgeDrawable.setNumber(2);
                        ref_soli_cont.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    Integer count = snapshot.getValue(Integer.class);
                                    badgeDrawable.setVisible(true);
                                    if(count.equals("0")){

                                        badgeDrawable.setVisible(false);
                                    }else{
                                        badgeDrawable.setNumber(count);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;
                    case 3:
                        tab.setText("Llamadas");
                        tab.setIcon(R.drawable.ic_contacting);
                        break;

                }
            }
        });

        tabLayoutMediator.attach();
        view2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                BadgeDrawable badgeDrawable = tabLayout.getTabAt(position).getOrCreateBadge();
                badgeDrawable.setVisible(false);

                if(position==2){
                    contadorcero();
                }
            }
        });

        final FirebaseUser usuarios = FirebaseAuth.getInstance().getCurrentUser();

        usuario();
    }
}