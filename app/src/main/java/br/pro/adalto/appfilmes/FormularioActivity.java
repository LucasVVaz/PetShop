package br.pro.adalto.appfilmes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNomeCao;
    private EditText etNomeDono;
    private EditText etEndereco;
    private Spinner spIdadeCao;
    private Button btnSalvar;
    private String acao;
    private PetShop petShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNomeCao = findViewById( R.id.etNomeCao );
        etNomeDono = findViewById( R.id.etNomeDono );
        etEndereco = findViewById( R.id.etEndereco );
        spIdadeCao = findViewById( R.id.spIdadeCao );
        btnSalvar = findViewById( R.id.btnSalvar );

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

    }

    private void carregarFormulario(){
        int idPetShop = getIntent().getIntExtra("idPetShop", 0);
        if( idPetShop != 0) {
            petShop = PetShopDAO.getPetById(this, idPetShop);
            etNomeCao.setText( petShop.nomeCao);
            etNomeDono.setText( petShop.nomeDono);
            etEndereco.setText( petShop.endereco );

            String[] arrayAno = getResources().getStringArray(R.array.arrayIdadeCao);
            for(int i = 1; i < arrayAno.length ; i++){
                if( Integer.valueOf( arrayAno[i] ) == petShop.getIdadeCao()){
                    spIdadeCao.setSelection( i );
                }
            }
        }
    }

    private void salvar(){
        if( spIdadeCao.getSelectedItemPosition() == 0 || etNomeCao.getText().toString().isEmpty()|| etNomeDono.getText().toString().isEmpty() || etEndereco.getText().toString().isEmpty() ) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon(android.R.drawable.ic_input_delete);
            alerta.setTitle(R.string.txtAtencao);
            alerta.setMessage("Preencha todos os campos!!!");
            alerta.setNeutralButton(" Clik aqui, Entendi!!", null);
            alerta.show();
        }else{

            if (acao.equals("novo")) {
                petShop = new PetShop();
                AlertDialog.Builder alerta = new AlertDialog.Builder(this);
                alerta.setIcon(android.R.drawable.ic_input_delete);
                alerta.setTitle(R.string.txtAtencao);
                Intent intent = new Intent(FormularioActivity.this, MainActivity.class);
                startActivity( intent );
            }

            petShop.nomeCao = etNomeCao.getText().toString();
            petShop.nomeDono = etNomeDono.getText().toString();
            petShop.endereco = etEndereco.getText().toString();
            petShop.setIdadeCao( Integer.valueOf( spIdadeCao.getSelectedItem().toString()  ) );

            if( acao.equals("editar")){
                PetShopDAO.editar(petShop, this);
                finish();
            }else {
                PetShopDAO.inserir(petShop, this);
                etNomeCao.setText("");
                etNomeDono.setText("");
                etEndereco.setText("");
                spIdadeCao.setSelection(0);
            }
        }
    }
}