package capstone.com.verve.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;
import capstone.com.verve.API.Security;
import capstone.com.verve.View.ForumActivity;
import capstone.com.verve.View.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class Login {
    Users users = new Users();
    String patient = "Patient";
    Boolean emailAddressChecker = false;
    Security sec = new Security();
    String decryptRole = "";


    public void allowUserToLogin(EditText email, EditText password, Context context, FirebaseAuth auth, FirebaseUser user) {
        String emailAdd = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if (emailAdd.isEmpty()) {
            Toast.makeText(context, "Please Enter your email", Toast.LENGTH_LONG).show();
            return;
        }
        if (userPassword.isEmpty()) {
            Toast.makeText(context, "Please Enter you password", Toast.LENGTH_LONG).show();
            return;
        }


        userLogin(emailAdd, userPassword, context, auth, user);

    }


    private void userLogin(String email, String password, final Context context, final FirebaseAuth auth,
                           final FirebaseUser user) {


        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    mRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String role = dataSnapshot.child("role").getValue(String.class);
                                try{
                                    decryptRole = AESCrypt.decrypt(sec.setSecurityKey(), role);
                                } catch (GeneralSecurityException e) {
                                    e.printStackTrace();
                                }
                                if (role.equals(patient)) {
                                    sendPatientToForum(context);
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                } else {
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendPatientToForum(Context context) {
        Intent intent = new Intent(context, ForumActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }



//    private void checkIfEmailIsVerified(Context context, FirebaseAuth auth, FirebaseUser user) {
//        //FirebaseUser user = auth.getCurrentUser();
//        emailAddressChecker = user.isEmailVerified();
//
//        if (emailAddressChecker) {
//            sendPatientToForum(context);
//        } else {
//            Toast.makeText(context, "Please Verify your account first", Toast.LENGTH_SHORT).show();
//            auth.signOut();
//        }
//    }
}


