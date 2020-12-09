package br.com.pautas.pautasapp.contract;

public interface IPautasRepositoryRowView {
    void setTitle(String title);
    void setDescription(String description);
    void setDetails(String details);
    void setAuthor(String author);
    void expandCollapse(boolean expandCollapse);
    void notifyItemExpandCollapse(int position);
    void notifyItemChangedStatus(int position);
    void setButtonStatus(int tag, int title);
}
