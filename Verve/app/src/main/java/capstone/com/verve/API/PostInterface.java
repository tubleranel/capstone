package capstone.com.verve.API;

import android.content.Context;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public interface PostInterface {
    public void savePosts(final DatabaseReference userRef, final DatabaseReference postRef, FirebaseAuth auth,
                          EditText postTitle, EditText descTitle, final Context context);

    public void saveComment(final DatabaseReference userRef, final FirebaseAuth auth, final String postKey,
                            final DatabaseReference postRef, final Context context, EditText comment);
}
