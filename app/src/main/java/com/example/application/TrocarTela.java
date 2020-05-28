package com.example.application;

import android.app.Activity;
import android.content.Intent;

public class TrocarTela {

  public void trocar(Activity activity, Class<?> cls){
      Intent it = new Intent(activity,cls);
      activity.startActivity(it);
      activity.finish();
  }

    // Troca de tela e passa o objeto
  public void trocarPassandoObjeto(Activity activity, Class<?> cls, Fato fato, String key ) {
        Intent it = new Intent(activity, cls);
        it.putExtra(key, fato);
        activity.startActivity(it);
        activity.finish();
    }
}
