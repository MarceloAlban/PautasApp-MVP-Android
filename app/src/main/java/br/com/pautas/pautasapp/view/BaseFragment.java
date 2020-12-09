package br.com.pautas.pautasapp.view;

import android.content.Context;
import androidx.fragment.app.Fragment;
import br.com.pautas.pautasapp.contract.IFragmentActions;

public class BaseFragment extends Fragment {
    IFragmentActions mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentActions) {
            mListener = (IFragmentActions) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IFragmentActions");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
