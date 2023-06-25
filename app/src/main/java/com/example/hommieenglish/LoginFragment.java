package com.example.hommieenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hommieenglish.constant.Constant;
import com.example.hommieenglish.dao.UserDao;
import com.example.hommieenglish.db.HommieEnglish;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private HommieEnglish db;
    private UserDao userDao;
    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance o
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
//        db = Room.databaseBuilder(getActivity().getApplicationContext(), HommieEnglish.class, Constant.DATABASE_NAME).build();
//        userDao = db.userDao();
    }

    @Override
    public void onDestroy() {
//        db.close();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View loginView = inflater.inflate(R.layout.fragment_login, container, false);
        Button loginButton = loginView.findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView email = (TextView) loginView.findViewById(R.id.et_email);
                TextView password = (TextView) loginView.findViewById(R.id.et_password);
                if (!email.getText().toString().equals("admin")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                    return;
                } else if (!password.getText().toString().equals("admin")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), MainMenu.class);
                startActivity(intent);
            }
        });
        return loginView;
    }
}