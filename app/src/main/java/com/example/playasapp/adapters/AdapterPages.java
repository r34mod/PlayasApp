package com.example.playasapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.playasapp.fragmentos.Playa;
import com.example.playasapp.fragmentos.all;
import com.example.playasapp.fragmentos.piscinas;

public class AdapterPages extends FragmentStateAdapter {

    public AdapterPages(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch (position){
            case 0:
                return new Playa();
            case 1:
                return new piscinas();
            case 2:
                return new all();
            default:
                return new Playa();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
