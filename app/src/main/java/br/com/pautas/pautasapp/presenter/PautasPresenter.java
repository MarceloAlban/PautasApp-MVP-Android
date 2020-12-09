package br.com.pautas.pautasapp.presenter;

import android.transition.ChangeBounds;
import android.transition.TransitionManager;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.adapter.PautasAdapter;
import br.com.pautas.pautasapp.contract.IPauta;
import br.com.pautas.pautasapp.contract.IPautasRepositoryRowView;
import br.com.pautas.pautasapp.model.Pauta;

public class PautasPresenter {
    private List<Pauta> mListPautas;
    private IPauta mIPauta;
    private int mExpandedPosition;
    private int mLastExpandedPosition;
    private IReload mIReload;

    public PautasPresenter(IReload reload) {
        this.mListPautas = new ArrayList<>();
        this.mIPauta = new Pauta();
        this.mIReload = reload;
    }

    public void loadData(String status) {
        this.mExpandedPosition = -1;
        this.mLastExpandedPosition = -1;
        this.mListPautas = mIPauta.getAll(status);
    }

    public int getListPautasRowCount() {
        return mListPautas.size();
    }

    public void createPauta(String title, String description, String details, String author){
        this.mIPauta.insert(
                new Pauta(title, description, details, author, IPauta.STATUS_OPEN)
        );
    }

    public void onBindRowView(int position, IPautasRepositoryRowView iview) {
        Pauta pauta = mListPautas.get(position);
        iview.setTitle(pauta.getTitle());
        iview.setDescription(pauta.getDescription());
        iview.setAuthor(pauta.getAuthor());
        iview.setDetails(pauta.getDetails());

        String status = pauta.getStatus() != null ? pauta.getStatus() : "";

        switch (status) {
            case "A":
                iview.setButtonStatus(PautasAdapter.BUTTON_FINISH_TAG, R.string.status_finish);
                break;
            case "F":
                iview.setButtonStatus(PautasAdapter.BUTTON_REOPEN_TAG, R.string.status_reopen);
                break;
        }

        boolean expand = position == mExpandedPosition;

        iview.expandCollapse(expand);

        if (expand)
            mLastExpandedPosition = position;
    }

    public void expandCollapse(RecyclerView recyclerView, int position, IPautasRepositoryRowView iview) {
        mExpandedPosition = (position == mExpandedPosition) ? -1 : position;

        ChangeBounds transition = new ChangeBounds();
        transition.setDuration(125);

        iview.notifyItemExpandCollapse(mLastExpandedPosition);
        iview.notifyItemExpandCollapse(position);

        TransitionManager.beginDelayedTransition(recyclerView, transition);
    }

    public void finishPauta(int position, IPautasRepositoryRowView iview) {
        Pauta pauta = mListPautas.get(position);
        pauta.setStatus(IPauta.STATUS_CLOSED);

        mIPauta.update(pauta);

        mListPautas.remove(position);
        iview.notifyItemChangedStatus(position);

        this.mIReload.reloadStatus(IPauta.STATUS_OPEN);
    }

    public void reopenPauta(int position, IPautasRepositoryRowView iview) {
        Pauta pauta = mListPautas.get(position);
        pauta.setStatus(IPauta.STATUS_OPEN);

        mIPauta.update(pauta);

        mListPautas.remove(position);
        iview.notifyItemChangedStatus(position);

        this.mIReload.reloadStatus(IPauta.STATUS_CLOSED);
    }

    public interface IReload {
        void reloadStatus(String status);
    }
}
