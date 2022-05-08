package com.example.playasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.playasapp.InfoView.OnBoardAdap;
import com.example.playasapp.InfoView.OnBoardItem;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase InfoActivity donde nos muestra como utilizar la app mediante el uso de Fragmentos
 *
 */

public class InfoActivity extends AppCompatActivity {

    private OnBoardAdap onboardApp;
    private LinearLayout layoutBoard;
    private MaterialButton materialButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        layoutBoard = findViewById(R.id.layoutOnBoard);
        materialButton = findViewById(R.id.buttonOnboard);

        setupOnboardItems();

        final ViewPager2 onBoardView = findViewById(R.id.onboardView);
        onBoardView.setAdapter(onboardApp);

        setOnboardIndicador();

        setCurrentIndicador(0);

        onBoardView.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicador(position);
            }
        });

        materialButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(onBoardView.getCurrentItem()+1<onboardApp.getItemCount()){
                    onBoardView.setCurrentItem(onBoardView.getCurrentItem()+1);
                }else{
                    startActivity(new Intent(getApplicationContext(), Ajustes.class));
                    finish();
                }
            }
        });





    }

    /**
     * Funcion donde creamos cada objeto con su imagen y los datos a mostrar por fragmento al usuario
     *
     * Se introducen en un arraylist de tipo OnBoardItem
     *
     */

    private void setupOnboardItems(){
        List<OnBoardItem> onboardItems = new ArrayList<>();
        OnBoardItem itemBoardPlaya = new OnBoardItem();
        itemBoardPlaya.setTitulo("Consulta tu chapuzon favorito");
        itemBoardPlaya.setDescripcion("Puedes visualizar las playas, piscinas, lagos... mejores de tu zona \uD83D\uDCAC");
        itemBoardPlaya.setImagen(R.drawable.beach);


        OnBoardItem itemBoardPiscina = new OnBoardItem();
        itemBoardPiscina.setTitulo("Consulta tu chapuzon favorito");
        itemBoardPiscina.setDescripcion("Puedes visualizar las playas, piscinas, lagos... mejores de tu zona \uD83D\uDCAC");
        itemBoardPiscina.setImagen(R.drawable.pool);


        OnBoardItem itemBoardAll = new OnBoardItem();
        itemBoardAll.setTitulo("Localiza las playas");
        itemBoardAll.setDescripcion("Puedes visualizar las playas, piscinas, lagos... mejores de tu zona \uD83D\uDCAC");
        itemBoardAll.setImagen(R.drawable.unknown);


        OnBoardItem itemBoardLast = new OnBoardItem();
        itemBoardLast.setTitulo("Consulta tu chapuzon favorito");
        itemBoardLast.setDescripcion("Puedes visualizar las playas, piscinas, lagos... mejores de tu zona \uD83D\uDCAC");
        itemBoardLast.setImagen(R.drawable.beach);

        onboardItems.add(itemBoardPlaya);
        onboardItems.add(itemBoardPiscina);
        onboardItems.add(itemBoardAll);
        onboardItems.add(itemBoardLast);

        onboardApp = new OnBoardAdap(onboardItems);


    }

    /**
     *
     * Funcion para indicar el numero de fragmentos que hay en la actividad.
     * Se mostrara con un icono de concha marina
     *
     */

    private void setOnboardIndicador(){
        ImageView[] indicador = new ImageView[onboardApp.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );

        layoutParams.setMargins(8,0,8,0);
        for(int i = 0; i<indicador.length;i++){
            indicador[i] = new ImageView(getApplicationContext());
            indicador[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.ic_cascara
            ));
            indicador[i].setLayoutParams(layoutParams);
            layoutBoard.addView(indicador[i]);
        }
    }

    /**
     *
     * Funcion para mostrar un icono de cangrejo en la posicion de fragmento que este el usuario
     *
     *
     * @param index = numero de fragmento situado
     */

    private void setCurrentIndicador(int index){
        int count = layoutBoard.getChildCount();
        for(int i = 0; i<count; i++){
            ImageView imageView = (ImageView)layoutBoard.getChildAt(i);
            if(i==index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.ic_cangrejo__1_)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),
                                R.drawable.ic_cascara)
                );
            }
        }

        if(index == onboardApp.getItemCount()-1){
            materialButton.setText("Inicio");
        }else{
            materialButton.setText("Siguiente");
        }
    }

}