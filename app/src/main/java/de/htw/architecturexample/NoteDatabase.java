package de.htw.architecturexample;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract NoteDAO noteDAO();
    public static synchronized NoteDatabase getInstance(Context context){
        if (INSTANCE==null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration().addCallback(RoomCallback).build();
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback RoomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                NoteDAO dao = INSTANCE.noteDAO();
                Note note = new Note("First Note", "This is an example note!", 1);
                Note note2 = new Note("Second Note", "This is an example note!", 2);
                Note note3 = new Note("Third Note", "This is an example note!", 3);
                dao.insert(note);
                dao.insert(note2);
                dao.insert(note3);
            });
        }
    };

}
