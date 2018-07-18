package me.mehdi.transitionresources;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button login;
Button signup;
Scene mainScene;
Scene loginScene;
Scene signupScene;
TransitionSet transitionSet;
TransitionManager transitionMgr;
ViewGroup root;
Context context = this;

//Add this boolean field to track transition
    boolean transitionStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        root = findViewById(R.id.root);
        mainScene = Scene.getSceneForLayout(root, R.layout.activity_main, context);
        loginScene = Scene.getSceneForLayout(root, R.layout.layout_login, context);
        signupScene = Scene.getSceneForLayout(root, R.layout.layout_signup, context);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        transitionSet = (TransitionSet) TransitionInflater.from(context).inflateTransition(R.transition.mytransitions);
        transitionMgr = TransitionInflater.from(context).inflateTransitionManager(R.transition.transition_mgr, root);
    }

    //Change onClick as follows
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.login:
                if(!transitionStarted) {
                    transitionMgr.transitionTo(loginScene);
                    transitionStarted = true;
                }
                break;
            case R.id.signup:
                if(!transitionStarted) {
                    transitionMgr.transitionTo(signupScene);
                    transitionStarted = true;
                }
                break;
        }
    }

    //override onBackPressed and add transition code to it

    @Override
    public void onBackPressed() {
        if(transitionStarted) {
            transitionMgr.transitionTo(mainScene);
            transitionStarted = false;

            //Note: We need to find buttons and set onClickListeners again
            findViewById(R.id.login).setOnClickListener(this);
            findViewById(R.id.signup).setOnClickListener(this);
        }
        else {
            super.onBackPressed();
        }
    }
}
