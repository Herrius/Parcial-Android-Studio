package ubaldo_EvalPar.org;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Animacion extends AppCompatActivity {
    ImageView logo,dado,mesa;
    TextView textoLogo;
    Animation animacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion);
        textoLogo=findViewById(R.id.txtLogo);
        logo=findViewById(R.id.logo);
        dado=findViewById(R.id.dado);
        mesa=findViewById(R.id.mesa);
        animacion= AnimationUtils.loadAnimation(this,R.anim.animacion_splash);
        logo.startAnimation(animacion);
        textoLogo.startAnimation(animacion);
        mesa.startAnimation(animacion);
        dado.startAnimation(animacion);
        //animacion
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent tran=new Intent(Animacion.this,MainActivity.class);
                tran.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(tran);
            }
        },3000);
    }
}