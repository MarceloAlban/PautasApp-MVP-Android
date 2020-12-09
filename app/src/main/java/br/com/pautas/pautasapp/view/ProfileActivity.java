package br.com.pautas.pautasapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.IProfileView;
import br.com.pautas.pautasapp.presenter.ProfilePresenter;

import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity implements IProfileView, View.OnClickListener {
    public static final int REQUEST_PROFILE = 1;
    private ProfilePresenter mProfilePresenter;
    private AppCompatTextView mTxtName;
    private AppCompatTextView mTxtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AppCompatButton btnLogout = findViewById(R.id.btnLogout);
        mTxtName = findViewById(R.id.txtName);
        mTxtEmail = findViewById(R.id.txtEmail);

        btnLogout.setOnClickListener(this);
        mProfilePresenter = new ProfilePresenter(this);
        mProfilePresenter.loadUser();
    }

    @Override
    public void showNameUser(String name) {
        mTxtName.setText(name);
    }

    @Override
    public void showEmailUser(String email) {
        mTxtEmail.setText(email);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogout) {
            mProfilePresenter.logoff();

            setResult(RESULT_OK);
            finish();
        }
    }
}
