package com.example.inventoryincidencias.ui.preferences;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

import com.example.inventoryincidencias.R;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class AccountFragment extends PreferenceFragmentCompat {
    private static final String FILE_NAME = "encriptation_shared_pref";
    private static final String KEY_PASSWORD = "key_password";
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.account_preferences, rootKey);
        initPreferenceEmail();
        // Primero se tiene que crear el fichero de encriptación
        initEncriptation();
        initPreferencesPassword();
    }

    /**
     * Método que inicializa el tipo de dato admitido en el EditText, que no esté habilitado, y que cuando
     * el usuario pulsa sobre la preferencia se selccione el texto completo
     */
    private void initPreferenceEmail() {
        EditTextPreference etEmail = getPreferenceManager().findPreference(getString(R.string.key_email));
        etEmail.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setText(new UserPrefManager(getContext()).getUserEmail());
                editText.setEnabled(false);
            }
        });
    }

    private void initPreferencesPassword() {
        EditTextPreference etPassword = getPreferenceManager().findPreference(getString(R.string.key_password));

        // Se va a establecer dos eventos: el de inicialización y el de modificación de la preferencia

        // Inicialización
        etPassword.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                // Se va a inicializar el tipo dato que acepta el campo EDITTEXT android:inputType no existe en las preferencias
                // Recoge el valor del fichero encriptado
                editText.setText(sharedPreferences.getString(KEY_PASSWORD, ""));
                editText.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
                editText.selectAll();
            }
        });

        // Modificación de la preferencia
        etPassword.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                sharedPreferences.edit().putString(KEY_PASSWORD, (String) newValue).commit();
                return true;
            }
        });
    }

    private void initEncriptation() {
        MasterKey mainKey = null;
        try {
            mainKey = new MasterKey.Builder(getContext())
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences
                    .create(
                            getContext(),
                            FILE_NAME,
                            mainKey,
                            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    );
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
