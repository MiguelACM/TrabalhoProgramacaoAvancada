package com.example.paulo.aplicao_dmuccombd;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.sqlite.DBHelper;
import com.android.sqlite.DadosBD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {

    DBHelper dbHelper;
    ListView listaperguntas;
    ArrayAdapter<String> adapter;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.cursos:
                    MostraDados("SELECT pergunta FROM FAQ");
                    return true;
                case R.id.saidasProfissionais:
                    MostraDados("SELECT resposta FROM FAQ");
                    return true;
                case R.id.instalacoes:
                    return true;
                case R.id.FAQ:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listaperguntas = (ListView) findViewById(R.id.listaPerguntas);
        MostraDados("SELECT texto FROM Texto \n" +
                "WHERE codDescritor = 'Pagina_inicial'");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    public void MostraDados(String query){
        DadosBD databaseAccess = DadosBD.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getDados(query);
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        this.listaperguntas.setAdapter(adapter);
    }


}
