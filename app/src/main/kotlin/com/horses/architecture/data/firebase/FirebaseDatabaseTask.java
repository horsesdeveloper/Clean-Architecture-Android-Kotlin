package com.horses.architecture.data.firebase;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author @briansalvattore on 19/10/2017
 */

@SuppressWarnings("unused")
public class FirebaseDatabaseTask {

    private FirebaseDatabase firebaseDatabase;

    private FirebaseDatabaseTask() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public static FirebaseDatabaseTask getInstance() {
        return new FirebaseDatabaseTask();
    }

    public TaskReference getReference() {
        return new TaskReference();
    }

    public class TaskReference {
        private List<String> children;
        private String key = null;

        private TaskReference() {
            children = new ArrayList<>();
        }

        public TaskReference child(String child) {
            children.add(child);
            return this;
        }

        public TaskReference push() {
            DatabaseReference ref = firebaseDatabase.getReference().push();
            key = ref.getKey();
            children.add(key);
            return this;
        }

        public String getKey() {
            if (key == null) throw new NullPointerException("key only works for push");
            return key;
        }

        public Task<DataSnapshot> addListenerForSingleValueEvent() {

            DatabaseReference reference = firebaseDatabase.getReference().child(TextUtils.join("/", children));

            TaskCompletionSource<DataSnapshot> sourceDbTask = new TaskCompletionSource<>();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    sourceDbTask.setResult(dataSnapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    sourceDbTask.setException(databaseError.toException());
                }
            });

            return sourceDbTask.getTask();
        }

        public Task<Void> setValue(@NotNull Object object) {
            return firebaseDatabase.getReference().child(TextUtils.join("/", children)).setValue(object);
        }

        public void addCounter() {
            firebaseDatabase.getReference().child(TextUtils.join("/", children))
                    .runTransaction(new Transaction.Handler() {

                        private static final String TAG = "Task Transaction";

                        @NonNull
                        @Override
                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                            Integer counter = mutableData.getValue(Integer.class);

                            if (counter == null) {
                                counter = 0;
                            }

                            counter = counter + 1;

                            mutableData.setValue(counter);

                            return Transaction.success(mutableData);
                        }

                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                            Log.d(TAG, "onComplete() called");
                        }
                    });
        }
    }
}
