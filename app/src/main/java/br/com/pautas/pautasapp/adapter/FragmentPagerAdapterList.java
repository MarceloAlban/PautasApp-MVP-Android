package br.com.pautas.pautasapp.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.IPauta;
import br.com.pautas.pautasapp.view.ListFragment;

public class FragmentPagerAdapterList extends FragmentPagerAdapter {

    private Context mContext;
    private ListFragment mFragmentOpen;
    private ListFragment mFragmentClosed;

    public FragmentPagerAdapterList(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;

        mFragmentOpen = ListFragment.newInstance(IPauta.STATUS_OPEN);
        mFragmentClosed = ListFragment.newInstance(IPauta.STATUS_CLOSED);
    }


    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return mFragmentOpen;
        } else if (position == 1) {
            return mFragmentClosed;
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_open);
            case 1:
                return mContext.getString(R.string.fragment_closed);
            default:
                return null;
        }
    }

}