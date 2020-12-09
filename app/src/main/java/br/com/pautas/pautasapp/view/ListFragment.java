package br.com.pautas.pautasapp.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.adapter.PautasAdapter;
import br.com.pautas.pautasapp.presenter.PautasPresenter;

public class ListFragment extends Fragment implements PautasPresenter.IReload {
    private static final String STATUS_PARAM = "status_param";

    private String mStatus;
    private PautasAdapter mAdapter;
    private PautasPresenter mPautasPresenter;
    private IFragmentUpdate mIFragmentUpdate;

    public void setIFragmentUpdate(IFragmentUpdate mIFragmentUpdate) {
        this.mIFragmentUpdate = mIFragmentUpdate;
    }

    public static ListFragment newInstance(String status) {
        ListFragment fragment = new ListFragment();

        Bundle args = new Bundle();
        args.putString(STATUS_PARAM, status);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStatus = getArguments().getString(STATUS_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mPautasPresenter = new PautasPresenter(this);
        mPautasPresenter.loadData(mStatus);

        RecyclerView mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(null);

        mAdapter = new PautasAdapter(mPautasPresenter, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public void reloadList() {
        mPautasPresenter.loadData(mStatus);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void reloadStatus(String status) {
        mIFragmentUpdate.fragmentUpdateStatus(status);
    }

    public interface IFragmentUpdate {
        void fragmentUpdateStatus(String status);
    }
}
