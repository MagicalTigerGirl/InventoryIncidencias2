package com.example.inventoryincidencias.data.repository;

import android.media.metrics.Event;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.inventoryincidencias.data.model.User;
import com.example.inventoryincidencias.ui.IniApplication;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.eventbus.EventBus;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class UserRepository {
    private ArrayList<User> list;
    private static UserRepository instance;
    private FirebaseAuth firebaseAuth;

    private MutableLiveData<User> userMutableLiveData;

    private UserRepository() {
        /*this.list = new ArrayList<>();
        initialice();*/

        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public ArrayList<User> getList() { return list; }

    public void initialice() {
        list.add(new User("mariamolinalorenzo@gmail.com", "26122003Maria*", "María", "Molina Lorenzo", 1));
        list.add(new User("juan.inma.maria@hotmail.com", "26122003", "Juan", "Molina Albalá", 2));
    }

    // Comprobar si existe un usuario dentro de la lista
    public boolean login(String email, String password) {
        /*if (list.contains(new User(email, password)))
            return true;
        else
            return false;*/

        firebaseAuth.signInWithCustomToken(email, password)
                .addOnCompleteListener(IniApplication.getExecutar(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = firebaseAuth.getCurrentUser();
                    userLiveData.postValue(new User(user.getDisplayName(), user.getEmail()));
                    Log.d(TAG, "signInWithEmail:success");
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Event loginEvent = new Event();
                    loginEvent.setEventType(Event.onLoginError);
                    loginEvent.setMessage(task.getException().toString());

                    EventBus.getDefault().post(loginEvent);
                }
            }
        });
    }


}
