package com.example.playasapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.playasapp.MainActivity;
import com.example.playasapp.fragmentos.Playas;
import com.example.playasapp.fragmentos.all;
import com.example.playasapp.fragmentos.piscinas;

public class AdapterPages extends FragmentStateAdapter {

    public AdapterPages(@NonNull MainActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new all();
            case 1:
                return new piscinas();
            case 2:
                return new Playas();

            default:
                return new all();
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
