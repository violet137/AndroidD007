//package com.com.greenacademy.englishlearning.fragment;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.google.android.gms.auth.api.signin.GoogleSignIn;
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
//import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
//import com.google.android.gms.common.SignInButton;
//import com.google.android.gms.common.api.ApiException;
//import com.google.android.gms.tasks.Task;
//import com.greenacademy.englishlearning.R;
//
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//;
//
//public class LoginGooglePlusFragment extends Fragment {
//    private GoogleSignInClient mGoogleSignInClient;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.activity_main, container, false);
//
//        SignInButton signInButton = view.findViewById(R.id.btn_Google);
//
//
//        //goodle plus
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
////        if(account != null)
////        {
////            Toast.makeText(getActivity(), "đã sign in"+account.getEmail(), Toast.LENGTH_SHORT).show();
////        }
//
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//
//        signInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, 200);
//            }
//        });
//
//
//        return view;
//    }
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // kết quả của google
//        if (requestCode == 200) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);// 1 đối tượng liên kết với google
//            handleSignInResult(task);
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
//        try {
//            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
//
//            System.out.println(account.getDisplayName());
//            System.out.println(account.getEmail());
//
//            if (account.getPhotoUrl() != null) {
//                URL link = new URL(account.getPhotoUrl().toString());
//                HttpURLConnection urlConnection = (HttpURLConnection) link.openConnection();
//                urlConnection.connect();
//
//                System.out.println(account.getPhotoUrl());
//            }
//
//
//        } catch (Exception e) {
//            // The ApiException status code indicates the detailed failure reason.
//            // Please refer to the GoogleSignInStatusCodes class reference for more information.
//            Log.w("Google + ", "signInResult:failed code=" + e);
//
//        }
//    }
//}
