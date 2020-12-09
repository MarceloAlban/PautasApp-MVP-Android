package br.com.pautas.pautasapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import br.com.pautas.pautasapp.R;
import br.com.pautas.pautasapp.contract.IPautasRepositoryRowView;
import br.com.pautas.pautasapp.presenter.PautasPresenter;

public class PautasAdapter extends RecyclerView.Adapter<PautasAdapter.ViewHolder> {
    public static final int BUTTON_FINISH_TAG = 1;
    public static final int BUTTON_REOPEN_TAG = 2;

    private PautasPresenter mPautasPresenter;
    private RecyclerView mRecyclerView;
    private Context mContext;

    public PautasAdapter(PautasPresenter pautasPresenter, Context context) {
        this.mPautasPresenter = pautasPresenter;
        this.mContext = context;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        mPautasPresenter.onBindRowView(position, holder);
    }

    @Override
    public int getItemCount() {
        return mPautasPresenter.getListPautasRowCount();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, IPautasRepositoryRowView {
        AppCompatTextView txtTitle;
        AppCompatTextView txtDescription;
        AppCompatTextView txtDetails;
        AppCompatTextView txtAutor;
        LinearLayout subItem;
        AppCompatButton btnStatus;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtDetails = itemView.findViewById(R.id.txtDetails);
            txtAutor = itemView.findViewById(R.id.txtAutor);
            subItem = itemView.findViewById(R.id.subItem);
            btnStatus = itemView.findViewById(R.id.btnStatus);
            cardView = itemView.findViewById(R.id.cardView);

            btnStatus.setOnClickListener(this);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnStatus) {
                int option = (int) view.getTag();

                switch (option) {
                    case PautasAdapter.BUTTON_FINISH_TAG:
                        mPautasPresenter.finishPauta(getAdapterPosition(), this);
                        break;
                    case PautasAdapter.BUTTON_REOPEN_TAG:
                        mPautasPresenter.reopenPauta(getAdapterPosition(), this);
                        break;
                }
            } else if (view.getId() == R.id.cardView) {
                mPautasPresenter.expandCollapse(mRecyclerView, getAdapterPosition(), this);
            }
        }

        @Override
        public void setTitle(String title) {
            txtTitle.setText(title);
        }

        @Override
        public void setDescription(String description) {
            txtDescription.setText(description);
        }

        @Override
        public void setDetails(String details) {
            txtDetails.setText(details);
        }

        @Override
        public void setAuthor(String author) {
            txtAutor.setText(author);
        }

        @Override
        public void expandCollapse(boolean expandCollapse) {
            subItem.setVisibility(expandCollapse ? View.VISIBLE : View.GONE);
            subItem.setActivated(expandCollapse);
        }

        @Override
        public void notifyItemExpandCollapse(int position) {
            notifyItemChanged(position);
        }

        @Override
        public void setButtonStatus(int tag, int title) {
            btnStatus.setTag(tag);
            btnStatus.setText(mContext.getString(title));
        }

        @Override
        public void notifyItemChangedStatus(int position) {
            notifyItemRemoved(position);
        }
    }
}
