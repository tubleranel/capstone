package capstone.com.verve.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Posts {
    String saveCurrentDate = "";
    String saveCurrentTime = "";
    String saveCurrentTimePost = "";
    String saveCurrentDatePost = "";


    public void savePosts(final DatabaseReference userRef, final DatabaseReference postRef, FirebaseAuth auth,
                          EditText postTitle, EditText descTitle, final Context context) {

        //FOR UNIQUE NAME
        Calendar calendarDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMMM:dd:yyyy");
        saveCurrentDate = currentDate.format(calendarDate.getTime());

        Calendar calendarTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss:SSS");
        saveCurrentTime = currentTime.format(calendarTime.getTime());

        //FOR POST TIME AND DATE
        Calendar calendarDatePost = Calendar.getInstance();
        SimpleDateFormat currentDatePost = new SimpleDateFormat("MM/dd/yyyy");
        saveCurrentDatePost = currentDatePost.format(calendarDatePost.getTime());

        Calendar calendarTimePost = Calendar.getInstance();
        SimpleDateFormat currentTimePost = new SimpleDateFormat("HH:mm a");
        saveCurrentTimePost =  currentTimePost.format(calendarTimePost.getTime());


        final String currUser = auth.getCurrentUser().getUid();

        final String postTitleString = postTitle.getText().toString();

        final String descTitleString = descTitle.getText().toString();

        final String uniqueUserDate = currUser.concat(saveCurrentDate).concat(saveCurrentTime);

        final String datePost = saveCurrentDatePost;
        final String timePost = saveCurrentTimePost;

        userRef.child(currUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String firstname = dataSnapshot.child("firstname").getValue(String.class);
                    String middlename = dataSnapshot.child("middlename").getValue(String.class);
                    String lastname = dataSnapshot.child("lastname").getValue(String.class);

                    UserPosts userPosts = new UserPosts(datePost, firstname, middlename, lastname, descTitleString, postTitleString, timePost, currUser);

                    postRef.child(uniqueUserDate).setValue(userPosts).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Post Added Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

//                    userPosts.setUserId(currUser);
//                    userPosts.setFirstname(firstname);
//                    userPosts.setMiddlename(middlename);
//                    userPosts.setLastname(lastname);
//                    userPosts.setPostTitle(postTitleString);
//                    userPosts.setPostDescription(descTitleString);


                } else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}