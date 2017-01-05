package laerte.esy.es.cadastrodepessoas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //widgets
    private EditText nomeDivida;
    private EditText valorDivida;
    private Button btnok;


    //lista
    private ListView lvDivida;

    //array adapter
    private ArrayAdapter adapter;

    //objeto
    private Cadastro c;

    //class Cadastro
    private Cadastro cad;

    //ArrayList
    private ArrayList<Cadastro> arrayCadastro;

    private int posSel = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomeDivida = (EditText) findViewById(R.id.t1_nome_divida);
        valorDivida = (EditText) findViewById(R.id.t1_valor_divida);
        btnok = (Button) findViewById(R.id.btn_ok);
        lvDivida = (ListView) findViewById(R.id.lv_dividas);

        arrayCadastro = new ArrayList<Cadastro>();

        adapter = new ArrayAdapter<Cadastro>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                arrayCadastro);
        //Setando o adapter na ListView
        lvDivida.setAdapter(adapter);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = new Cadastro();
                c.setNomeDivida(nomeDivida.getText().toString());
                c.setValorDivida(valorDivida.getText().toString());

                arrayCadastro.add(c);

                adapter.notifyDataSetChanged();

                Toast.makeText(
                        getBaseContext(),
                        "Pessoa cadastrada com sucesso!",
                        Toast.LENGTH_LONG).show();
            }
        });

        lvDivida.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posSel = i;


                Cadastro p = (Cadastro) lvDivida.getItemAtPosition(posSel);

                Intent it = new Intent();
                it.putExtra("p",p);
                startActivity(it);
            }
        });
        lvDivida.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posSel = i;
                AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
                msg.setTitle("Remover?");
                msg.setMessage("Remover Divida");
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        arrayCadastro.remove(posSel);
                        //Notificar o adapter
                        adapter.notifyDataSetChanged();
                        Toast.makeText(
                                getBaseContext(),
                                "Item Removido!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                msg.setNegativeButton("Nao", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(),"item nao removido",Toast.LENGTH_LONG).show();
                    }
                });
                msg.show();
                return true;
            }
        });



    }
}
