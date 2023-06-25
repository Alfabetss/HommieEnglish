package com.example.hommieenglish;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hommieenglish.constant.Constant;
import com.example.hommieenglish.dao.UserDao;
import com.example.hommieenglish.db.HommieEnglish;
import com.example.hommieenglish.entity.User;
import com.example.hommieenglish.utils.Password;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private HommieEnglish db;
    private UserDao userDao;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View registerView = inflater.inflate(R.layout.fragment_register, container, false);
        Button registerButton = registerView.findViewById(R.id.btn_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView email = (TextView) registerView.findViewById(R.id.et_register_email);
                TextView password = (TextView) registerView.findViewById(R.id.et_register_password);
                TextView name = (TextView) registerView.findViewById(R.id.et_register_name);
                if (email == null || email.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();
                    return;
                } else if (password == null || password.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                    return;
                } else if (name == null || name.getText().toString().equals("")) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Name", Toast.LENGTH_LONG).show();
                    return;
                }

                User user = userDao.getByEmail(email.getText().toString());
                if (isExists(user)) {
                    Toast.makeText(getActivity().getApplicationContext(), "User Already Exists", Toast.LENGTH_LONG).show();
                    return;
                }

//                User newUser = new User();
//                newUser.email = email.toString();
//                newUser.name = name.toString();
//                newUser.password = Password.encodePassword(password.toString());
//                userDao.insertUser(newUser);

                Toast.makeText(getActivity().getApplicationContext(), "New User Created", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), LoginFragment.class);
                startActivity(intent);
            }
        });
        return registerView;
    }

    private boolean isExists(User user) {
        return user == null || user.equals(null);
    }
}