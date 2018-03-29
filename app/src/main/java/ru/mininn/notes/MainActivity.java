package ru.mininn.notes;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.mininn.notes.database.NoteDatabase;
import ru.mininn.notes.entity.ImageData;
import ru.mininn.notes.entity.Note;
import ru.mininn.notes.entity.NoteData;
import ru.mininn.notes.usecase.UseCaseHandler;
import ru.mininn.notes.usecase.UseCaseThreadPoolScheduler;
import ru.mininn.notes.usecase.note.GetNotes;
import ru.mininn.notes.usecase.note.SaveNote;

public class MainActivity extends AppCompatActivity {
    private NoteDatabase database ;
    private int name = 0;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = Room.databaseBuilder(this, NoteDatabase.class, "das").build();
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ImageData> imageUris = new ArrayList<>();
                imageUris.add(new ImageData("image " + 0));
                UseCaseHandler useCaseHandler = new UseCaseHandler(new UseCaseThreadPoolScheduler());
                SaveNote saveNote = new SaveNote(database);
                useCaseHandler.execute(saveNote,
                        new SaveNote.RequestValues(new Note(new NoteData("name" + name,
                                "","",""),imageUris)),
                        new SaveNote.UseCaseCallback<SaveNote.ResponseValue>() {

                            @Override
                            public void onSuccess(SaveNote.ResponseValue response) {
                                logAllTasksNames();
                                name++;
                            }

                            @Override
                            public void onError(String message) {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }

    private void logAllTasksNames() {
        UseCaseHandler useCaseHandler = new UseCaseHandler(new UseCaseThreadPoolScheduler());
        GetNotes getNotes = new GetNotes(database);
        useCaseHandler.execute(getNotes,new GetNotes.RequestValues(),
                new GetNotes.UseCaseCallback<GetNotes.ResponseValue>() {

                    @Override
                    public void onSuccess(GetNotes.ResponseValue response) {

                    }

                    @Override
                    public void onError(String message) {

                    }
                });
    }
}
