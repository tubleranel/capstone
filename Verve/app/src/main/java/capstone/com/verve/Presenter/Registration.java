package capstone.com.verve.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import capstone.com.verve.API.Security;
import capstone.com.verve.View.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class Registration {
    String gender = "";
    Users getInfo = new Users();

    Security sec = new Security();


    String encryptedFirstName = "";
    String encryptedMiddleName = "";
    String encryptedLastName = "";
    String encryptedUsername = "";
    String encryptedMobile = "";
    String encryptedEmail = "";
    String encryptedAddress = "";
    String encryptedBirthdate = "";
    String encryptedGender = "";
    String encryptedRole = "";

    public void registerPatient(EditText firstname, EditText middlename,
                                EditText lastname, EditText username,
                                EditText password, EditText mobile,
                                EditText email, EditText address,
                                EditText birthdate, RadioButton male, RadioButton female,
                                FirebaseAuth auth, Context context, FirebaseUser user) {

        String et_firstname = firstname.getText().toString().trim();
        String et_middlename = middlename.getText().toString().trim();
        String et_lastname = lastname.getText().toString().trim();
        String et_username = username.getText().toString().trim();
        String et_password = password.getText().toString().trim();
        String et_mobile = mobile.getText().toString().trim();
        String et_email = email.getText().toString().trim();
        String et_address = address.getText().toString().trim();
        String et_birthdate = birthdate.getText().toString().trim();
        String rad_male = male.getText().toString().trim();
        String rad_female = female.getText().toString().trim();

        try {
            encryptedFirstName = AESCrypt.encrypt(sec.setSecurityKey(), et_firstname);
            encryptedMiddleName = AESCrypt.encrypt(sec.setSecurityKey(), et_middlename);
            encryptedLastName = AESCrypt.encrypt(sec.setSecurityKey(), et_lastname);
            encryptedUsername = AESCrypt.encrypt(sec.setSecurityKey(), et_username);
            encryptedMobile= AESCrypt.encrypt(sec.setSecurityKey(), et_mobile);
            encryptedEmail = AESCrypt.encrypt(sec.setSecurityKey(), et_email);
            encryptedAddress = AESCrypt.encrypt(sec.setSecurityKey(), et_address);
            encryptedBirthdate = AESCrypt.encrypt(sec.setSecurityKey(), et_birthdate);
            encryptedRole = AESCrypt.encrypt(sec.setSecurityKey(), "Patient");

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        if (et_firstname.isEmpty()) {
            firstname.setError("First Name is Required!");
            firstname.requestFocus();
            return;
        }

        if (et_lastname.isEmpty()) {
            lastname.setError("Last Name is Required!");
            lastname.requestFocus();
            return;
        }

        if (et_username.isEmpty()) {
            username.setError("Username is Required!");
            username.requestFocus();
            return;
        }

        if (et_mobile.length() < 11) {
            mobile.setError("Wrong Format of Phone Number!");
            mobile.requestFocus();
            return;
        }

        if (et_mobile.length() < 11) {
            mobile.setError("Wrong Format of Phone Number!");
            mobile.requestFocus();
            return;
        }

        if (!male.isChecked() && !female.isChecked() == true) {
            Toast.makeText(context, "Please Select a Gender", Toast.LENGTH_LONG).show();
            return;
        }

        getGender(male, female);


        userAndEmailAuth(et_email, et_password, et_firstname, et_middlename,
                et_lastname, et_username, et_mobile, et_address, et_birthdate, getInfo.getGender(),
                "Patient", context, auth, user);
    }

    private void userAndEmailAuth(final String email, String password, final String firstname, final String middlename,
                                  final String lastname, final String username,
                                  final String mobile, final String address,
                                  final String birthdate, final String gender,
                                  final String role,
                                  final Context context, final FirebaseAuth auth, final FirebaseUser user) {


        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Users users = new Users(firstname, middlename, lastname, username, mobile,
                                    email, address, birthdate, gender, role);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(auth.getInstance().getCurrentUser().getUid())
                                    .setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(context, "Registration is Successful!", Toast.LENGTH_LONG).show();
                                        auth.signOut();
                                        sendEmailVerification(user, auth, context);
                                        sendUserToLoginActivity(context);
                                    } else {
                                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {
                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    public void getGender(RadioButton rad_male, RadioButton rad_female) {
        if (rad_male.isChecked()) {
            gender = "Male";
            try {
                encryptedGender = AESCrypt.encrypt(sec.setSecurityKey(), gender);
                getInfo.setGender(gender);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        } else if (rad_female.isChecked()) {
            gender = "Female";
            getInfo.setGender(gender);
            try {
                encryptedGender = AESCrypt.encrypt(sec.setSecurityKey(), gender);
                getInfo.setGender(gender);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }

    }

    private void sendEmailVerification(FirebaseUser user, final FirebaseAuth auth, final Context context) {

        //user = auth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Registration Successful, we have sent you an email. " +
                                "Please check and verify your email account", Toast.LENGTH_LONG).show();
                        auth.signOut();
                    } else {
                        Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        auth.signOut();
                    }
                }
            });
        }

    }

    private void sendUserToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

    }

}
