package lorena.u5_menucamara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class U5_Menucamara extends AppCompatActivity {
    TextView texto;
    Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    private final int REQUEST_CODE_CAMARA=1;
    ImageView mostrarimaxe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u5__menucamara);
        texto = (TextView) findViewById(R.id.lanzarcamara);
        registerForContextMenu(texto);
        mostrarimaxe=(ImageView)findViewById(R.id.foto);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
       if(requestCode==REQUEST_CODE_CAMARA){
           if(resultCode==RESULT_OK){
               if(data==null){
                   Toast.makeText(this,"Non hai imaxe",Toast.LENGTH_SHORT).show();
                   return;
               }

               mostrarimaxe.setImageBitmap((Bitmap)data.getExtras().get("data"));
           }
           else if (resultCode==RESULT_CANCELED){
               Toast.makeText(this,"Foto cancelada",Toast.LENGTH_SHORT).show();

           }
           else Toast.makeText(this,"Fallo na captura da foto",Toast.LENGTH_SHORT).show();
       }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_u5__menucamara, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucontextual, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Ítems premidos sobre o TextView
            // Lanza un Toast coa opción do menú contextual que se seleccinou
            case R.id.menucamara:
                startActivityForResult(i,REQUEST_CODE_CAMARA);
                return true;

            case R.id.menusair:
                finish();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.lanzarcamara1:
                startActivityForResult(i,REQUEST_CODE_CAMARA);
                return true;

            case R.id.itemSair:
                finish();
                return true;

            //noinspection SimplifiableIfStatement
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
