package capstone.com.verve.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.Toast;
import capstone.com.verve.API.PostInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Comments implements PostInterface {
    String commentDate = "";
    String commentTime = "";
    String commentDatePost = "";
    String commentTimePost = "";
    String commentUniqueKey = "";


    //@Override
    public void savePosts(DatabaseReference userRef, DatabaseReference postRef, FirebaseAuth auth, EditText postTitle, EditText descTitle, Context context) {

    }

    //@Override
    public void saveComment(final DatabaseReference userRef, final FirebaseAuth auth, final String postKey,
                            final DatabaseReference postRef, final Context context, EditText comment) {
        Calendar calendarDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMMM:dd:yyyy");
        commentDate = currentDate.format(calendarDate.getTime());

        Calendar calendarTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss:SSS");
        commentTime = currentTime.format(calendarTime.getTime());

        //FOR POST TIME AND DATE
        Calendar calendarDatePost = Calendar.getInstance();
        SimpleDateFormat currentDatePost = new SimpleDateFormat("MM/dd/yyyy");
        commentDatePost = currentDatePost.format(calendarDatePost.getTime());

        Calendar calendarTimePost = Calendar.getInstance();
        SimpleDateFormat currentTimePost = new SimpleDateFormat("HH:mm a");
        commentTimePost = currentTimePost.format(calendarTimePost.getTime());

        final String commentPost = comment.getText().toString();


        String currUser = auth.getCurrentUser().getUid();

        commentUniqueKey = currUser.concat(commentDate).concat(commentTime);

        userRef.child(currUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String firstname = dataSnapshot.child("firstname").getValue(String.class);
                    String middlename = dataSnapshot.child("middlename").getValue(String.class);
                    String lastname = dataSnapshot.child("lastname").getValue(String.class);

                    UserComments userComments = new UserComments(firstname, middlename, lastname, commentDatePost,
                            commentTimePost, commentPost);

                    postRef.child(postKey).child("Comments").child(commentUniqueKey).setValue(userComments).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Comment Added Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
