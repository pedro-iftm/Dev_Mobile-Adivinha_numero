package phs.com.adivinhanumero;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    private Button btn_reset;
    private Button btn_tentativa;
    private EditText numero_usuario;
    private TextView tv_qtd_tentativas;
    private TextView tv_resultado;

    private int random_number;
    private int qtdTentativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        btnClick();
    }
    private void resultado() {
        qtdTentativas++;
        tv_qtd_tentativas.setText(Integer.toString(qtdTentativas));

        if (random_number == Integer.parseInt(numero_usuario.getText().toString()))
        {
            tv_resultado.setText("Acertou");
            btn_tentativa.setEnabled(false);

            Toast.makeText(this, "Acertou", Toast.LENGTH_SHORT).show();
        }
        else if (random_number > Integer.parseInt(numero_usuario.getText().toString()))
        {
            tv_resultado.setText("Tente um número maior");
        }
        else if (random_number < Integer.parseInt(numero_usuario.getText().toString()))
        {
            tv_resultado.setText("Tente um número menor");
        }
    }

    private void resetar() {
        btn_tentativa.setEnabled(true);
        numero_usuario.setText("");
        qtdTentativas = 0;
        tv_resultado.setText("");
        tv_qtd_tentativas.setText("0");
    }

    private void initViews() {
        Random random = new Random();
        random_number = random.nextInt(100);

        btn_reset = findViewById(R.id.btn_reset);
        btn_tentativa = findViewById(R.id.btn_tentativa);
        numero_usuario = findViewById(R.id.et_numero);
        tv_qtd_tentativas = findViewById(R.id.tv_qtd_tentativas);
        tv_resultado = findViewById(R.id.tv_resultado);
    }

    public void btnClick () {
        numero_usuario.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.KEYCODE_ENTER:
                        resultado();
                }
                return false;
            }
        });

        btn_tentativa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado();
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetar();
            }
        });
    }
}
