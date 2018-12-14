package capstone.com.verve.Presenter;

import android.support.annotation.NonNull;
import android.widget.TextView;
import capstone.com.verve.API.Security;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.scottyab.aescrypt.AESCrypt;
import org.w3c.dom.Text;

import java.security.GeneralSecurityException;

public class UserDetails {

    Users users = new Users();
    Security security = new Security();
    String decryptedFirstName = "";
    String decryptedMiddleName = "";
    String decryptedLastName = "";
    String decryptedEmail = "";
    String decryptedBirthday = "";
    String decryptedAddress = "";
    String decryptedUsername = "";
    String key = security.setSecurityKey();



    public void getUserProfile(DatabaseReference profileReference, final TextView username, final TextView name, final TextView email,
                               final TextView birthDate, final TextView userAddress){

        profileReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String uName = dataSnapshot.child("username").getValue().toString();
                    String firstname = dataSnapshot.child("firstname").getValue().toString();
                    String middlename = dataSnapshot.child("middlename").getValue().toString();
                    String lastname = dataSnapshot.child("lastname").getValue().toString();
                    String emailAddress = dataSnapshot.child("email").getValue().toString();
                    String birthday = dataSnapshot.child("birthday").getValue().toString();
                    String address = dataSnapshot.child("address").getValue().toString();

                    try {
                        decryptedUsername = AESCrypt.decrypt(key, uName);
                        decryptedFirstName = AESCrypt.decrypt(key, firstname);
                        decryptedMiddleName = AESCrypt.decrypt(key, middlename);
                        decryptedLastName = AESCrypt.decrypt(key, lastname);
                        //decryptedEmail = AESCrypt.decrypt(key, emailAddress);
                        decryptedBirthday = AESCrypt.decrypt(key, birthday);
                        decryptedAddress = AESCrypt.decrypt(key, address);


                    } catch (GeneralSecurityException e) {
                        e.printStackTrace();
                    }

                    username.setText(uName);
                    userAddress.setText(address);
                    email.setText(emailAddress);
                    birthDate.setText(birthday);

                    if (middlename.isEmpty()){
                        String nameNoMiddle = firstname.concat(" " + lastname);
                        users.setWholeName(nameNoMiddle);
                        name.setText(users.getWholeName());
                    }else{
                        String nameWithMiddle = firstname.concat(" " + middlename.charAt(0) + ".").concat(" " + lastname);
                        users.setWholeName(nameWithMiddle);
                        name.setText(nameWithMiddle);
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void getUserFullName(DatabaseReference profileReference){


    }
}
