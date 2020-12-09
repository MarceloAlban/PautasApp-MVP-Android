package br.com.pautas.pautasapp.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.IProfileView;
import br.com.pautas.pautasapp.presenter.PautasPresenter;
import br.com.pautas.pautasapp.presenter.ProfilePresenter;

public class CreatePautaActivity extends AppCompatActivity implements IProfileView, TextWatcher, View.OnClickListener {
    public static final int REQUEST_CREATE_PAUTA = 2;
    private TextInputLayout mTxtInpTitulo;
    private TextInputLayout mTxtInpDescription;
    private TextInputLayout mTxtInpDetails;
    private TextInputLayout mTxtInpAuthor;
    private AppCompatButton mBtnCreate;
    private PautasPresenter mPautasPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pauta);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTxtInpTitulo = findViewById(R.id.txtInpTitulo);
        mTxtInpDescription = findViewById(R.id.txtInpDescription);
        mTxtInpDetails = findViewById(R.id.txtInpDetails);
        mTxtInpAuthor = findViewById(R.id.txtInpAuthor);
        mBtnCreate = findViewById(R.id.btnCreate);
        mBtnCreate.setEnabled(false);
        mBtnCreate.setOnClickListener(this);

        mTxtInpTitulo.getEditText().addTextChangedListener(this);
        mTxtInpDescription.getEditText().addTextChangedListener(this);
        mTxtInpDetails.getEditText().addTextChangedListener(this);
        mTxtInpAuthor.getEditText().addTextChangedListener(this);

        mPautasPresenter = new PautasPresenter(null);
        ProfilePresenter profilePresenter = new ProfilePresenter(this);
        profilePresenter.loadUser();
    }

    @Override
    public void showNameUser(String name) {
        mTxtInpAuthor.getEditText().setText(name);
    }

    @Override
    public void showEmailUser(String email) {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        mBtnCreate.setEnabled(
                !mTxtInpTitulo.getEditText().getText().toString().equals("") &&
                        !mTxtInpDescription.getEditText().getText().toString().equals("") &&
                        !mTxtInpDetails.getEditText().getText().toString().equals("") &&
                        !mTxtInpAuthor.getEditText().getText().toString().equals("")
        );
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCreate) {
            mPautasPresenter.createPauta(
                    mTxtInpTitulo.getEditText().getText().toString(),
                    mTxtInpDescription.getEditText().getText().toString(),
                    mTxtInpDetails.getEditText().getText().toString(),
                    mTxtInpAuthor.getEditText().getText().toString()
            );

            setResult(RESULT_OK);
            finish();
        }
    }
}
