package de.htw.architecturexample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<Note>> allNotes;
    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDAO();
        allNotes = noteDAO.getAll();
    }
    public void insert(Note note){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDAO.insert(note);
        });
    }
    public void update(Note note){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDAO.update(note);
        });
    }
    public void delete(Note note){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDAO.delete(note);
        });
    }
    public void deleteAll(){
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDAO.deleteAll();
        });
    }
    public LiveData<List<Note>> getAll(){
        return allNotes;
    }
}
