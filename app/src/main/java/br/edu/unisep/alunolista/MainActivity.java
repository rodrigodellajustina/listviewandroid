package br.edu.unisep.alunolista;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.database.Cursor;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editPesquisa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // comunicação entre o front-end e o back-end (Java)
        ListView lv_alunos = (ListView)findViewById(R.id.lvAlunos);

        editPesquisa = (EditText)findViewById(R.id.edtBuscaAluno);

        // Array List
        ArrayList<String> al_alunos = getBancoDadosAlunos();

        // ArrayAdapter
        final ArrayAdapter<String> aa_alunos = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al_alunos);
        lv_alunos.setAdapter(aa_alunos);


        editPesquisa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                aa_alunos.getFilter().filter(editPesquisa.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private ArrayList<String> getBancoDadosAlunos() {
        ArrayList<String> dadosdosalunos = new ArrayList<String>();

        SQLiteDatabase dbalunocad;
        dbalunocad = openOrCreateDatabase("dbalunocad.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        Cursor cursorAlunos = dbalunocad.rawQuery("select * from tb_aluno", null);

        while(cursorAlunos.moveToNext()){
            dadosdosalunos.add(cursorAlunos.getString(cursorAlunos.getColumnIndex("nome")));
        }

        return dadosdosalunos;

    }

    public void cadastrarNovoAluno(View view){
        Intent int_cadastroaluno = new Intent(this, Main2Activity.class);
        startActivity(int_cadastroaluno);
    }
}
