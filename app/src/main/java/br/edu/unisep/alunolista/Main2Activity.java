package br.edu.unisep.alunolista;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import java.util.Locale;
import android.content.ContentValues;

public class Main2Activity extends AppCompatActivity {

    private EditText editNomeAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editNomeAluno = (EditText)findViewById(R.id.edtNomeAluno);


        // Comunicação SQLLite
        SQLiteDatabase dbalunocad;

        // Criando ou abrindo a conexão com o banco de dados
        dbalunocad = openOrCreateDatabase("dbalunocad.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        dbalunocad.setVersion(1);
        dbalunocad.setLocale(Locale.getDefault());

        final String CREATE_TABLES = "create table if not exists tb_aluno (nome varchar(100))";
        // criar a tabela no banco de dados
        dbalunocad.execSQL(CREATE_TABLES);

    }

    public void ArmazenarDadosAluno(View view){
        String novoAluno;

        //  get no nome do aluno;
        novoAluno = editNomeAluno.getText().toString();

        // armazenar
        SQLiteDatabase dbalunocad;

        dbalunocad = openOrCreateDatabase("dbalunocad.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        ContentValues cv_novoAluno = new ContentValues();
        cv_novoAluno.put("nome", novoAluno);

        dbalunocad.insert("tb_aluno", null, cv_novoAluno);

        // depois que inseriu ele mostra a mensagem
        AlertDialog.Builder msg_cadastro = new AlertDialog.Builder(this);
        msg_cadastro.setTitle("Aluno");
        msg_cadastro.setNeutralButton("Ok", null);
        msg_cadastro.setMessage("Cadastrado com sucesso");
        msg_cadastro.show();

        // e retorna para o activity 1 (lista).
        Intent int_lista = new Intent(this, MainActivity.class);
        startActivity(int_lista);


    }


}
