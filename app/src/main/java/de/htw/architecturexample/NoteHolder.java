package de.htw.architecturexample;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteHolder extends RecyclerView.ViewHolder {
    private TextView textViewTitle;
    private TextView textViewDescription;
    private TextView textViewPriority;
    public NoteHolder(@NonNull View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.text_view_title);
        textViewDescription = itemView.findViewById(R.id.text_view_description);
        textViewPriority = itemView.findViewById(R.id.text_view_priority);
    }

    public TextView getTextViewTitle() {
        return textViewTitle;
    }

    public TextView getTextViewDescription() {
        return textViewDescription;
    }

    public TextView getTextViewPriority() {
        return textViewPriority;
    }
}
