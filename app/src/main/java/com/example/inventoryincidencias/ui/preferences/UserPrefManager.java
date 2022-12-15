package com.example.inventoryincidencias.ui.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.NetworkOnMainThreadException;

/**
 * Esta clase se encarga de leer/escribir cualquier informacion del usuairo como preferencias del sistema.
 * Cualquier Activity o Fragment que necesite información del usuario utilizará una instancia de esta clase.
 */
public class UserPrefManager {
    private final static String PREFS_NAME = "userpref";
    private final static String KEY_EMAIL = "email";

    private Context context;

    public UserPrefManager(Context context) {
        this.context = context;
    }

    /**
     * Una persona hace login cuando me da el email
     */
    public void login(String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_EMAIL, email);

        // Hay que lamar al método commit para hacer los cambios permanentes en el fichero XML
        editor.commit();
    }

    public boolean isUserLogin() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return !sharedPreferences.getString(KEY_EMAIL, "").isEmpty();                    // El segundo parámetro es el valor por defecto si no existe el primer parámetro
    }

    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_EMAIL);
        editor.commit();
    }
    public String getUserEmail() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

}
