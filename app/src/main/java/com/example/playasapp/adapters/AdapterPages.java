package com.example.playasapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdapterPages extends FragmentStateAdapter {

    public AdapterPages(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0:
                return new playas();
            case 1:
                return new piscinas();
            case 2:
                return new all();
            default:
                return new playas();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
