package capstone.com.verve.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import capstone.com.verve.API.FirebaseConnection;
import capstone.com.verve.Presenter.Login;
import capstone.com.verve.Presenter.Posts;
import capstone.com.verve.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    Login login = new Login();
    EditText etUsername, etPassword;
    FirebaseAuth auth = null;
    
    FirebaseUser user;

    FirebaseConnection firebaseConnection = new FirebaseConnection();

    Boolean emailChecker = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etxt_username);
        etPassword = findViewById(R.id.etxt_password);

        user = firebaseConnection.getFirebaseUser();

        auth = firebaseConnection.getFirebaseAuth();

        if (user != null) {
            // User is signed in
            Intent i = new Intent(LoginActivity.this, ForumActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out

        }

        setHintTextColor();
    }

    public void option(View v) {
        Intent i = null, chooser = null;

        if (v.getId() == R.id.click_signup) {
            i = new Intent(this, RegisterPatientActivity.class);
            startActivity(i);
        }

        if (v.getId() == R.id.click_forgotpass) {
            i = new Intent(this, ForgotPasswordActivity.class);
            startActivity(i);
        }

        if (v.getId() == R.id.btn_login) {

            login.allowUserToLogin(etUsername, etPassword, LoginActivity.this, auth, user);
        }
    }

    private void setHintTextColor() {
        EditText etUsername = findViewById(R.id.etxt_username);
        EditText etPassword = findViewById(R.id.etxt_password);

        etUsername.setHintTextColor(getResources().getColor(R.color.Pale));
        etPassword.setHintTextColor(getResources().getColor(R.color.Pale));
    }
//    private void DisplayUserPosts(){
//        FirebaseRecyclerAdapter<Posts, >
//    }

}
