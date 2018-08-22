package com.com.greenacademy.englishlearning.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.com.greenacademy.englishlearning.AsyncTask.LoginAsyncTask;
import com.com.greenacademy.englishlearning.Interface.Supporter;
import com.com.greenacademy.englishlearning.Model.FbUser;
//import com.com.greenacademy.englishlearning.fragment.LoginGooglePlusFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.greenacademy.englishlearning.R;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Supporter {
    LoginButton loginButton;
    private static final String EMAIL = "email";
    private static final int RC_SIGN_IN = 1594;
    CallbackManager callbackManager;
    FbUser fbUser = new FbUser();

    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;

    EditText txtUserName, txtPassword;
    Button btnGo,btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.greenacademy.englishlearning.R.layout.activity_main);

        txtUserName = findViewById(R.id.txt_User);
        txtPassword = findViewById(R.id.txt_Password);
        btnGo = findViewById(R.id.btn_Go);
        btnCreate  = findViewById(R.id.btn_Creat);

        btnGo.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        //khoi tao fb sdk
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        //dang ky su kien


        callbackManager = CallbackManager.Factory.create();

        loginButton = findViewById(R.id.btn_Facebook);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                // Application code
                                try {
                                    String email = object.getString("email");
                                    fbUser.setEmail(email);
                                    String name = object.getString("name");
                                    fbUser.setName(name);
                                    Profile profile = Profile.getCurrentProfile();
                                    fbUser.setUrlAvarta(profile.getProfilePictureUri(100, 100).toString());
                                    System.out.println(fbUser.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        System.out.println(fbUser.toString());

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            rollBack();
        }
///////////////////////////////////////////////////////////////////////////////////////////////
        // login with GOOGLE

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Set the dimensions of the sign-in button.
        signInButton = findViewById(R.id.btn_Google);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(this);

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ssad", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    private void rollBack() {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Profile profile = Profile.getCurrentProfile();
                        if (profile != null) {
                            System.out.println(profile.getName());
                            System.out.println(profile.getProfilePictureUri(100, 100).toString());
                            System.out.println(profile.getMiddleName());
                        }

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    public void getGoogleUser() {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            System.out.println(personId);
            System.out.println(personEmail);
            System.out.println(personName);
            System.out.println(personPhoto.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Google:
                signIn();
                break;
            case R.id.btn_Google_SignOut:
                signOut();
                break;
            case  R.id.btn_Go:
                LoginAsyncTask loginAsyncTask = new LoginAsyncTask(this);
                loginAsyncTask.execute(txtUserName.getText().toString(), txtPassword.getText().toString());
                break;
            case R.id.btn_Creat:
                Intent i = new Intent(this,SignUpActivity.class);
                startActivity(i);
                break;
        }
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }

    @Override
    public void showToast(Boolean aBoolean) {
        Toast.makeText(getApplicationContext(), "Connect: " + aBoolean, Toast.LENGTH_LONG).show();
    }
}
