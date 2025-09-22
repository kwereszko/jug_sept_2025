package com.hlag.jug.service;

import com.hlag.jug.model.Note;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NoteService {

	public Note merge(Note note1, Note note2) {
		return new Note(note1.getTitle() + " & " + note2.getTitle(),
				note1.getContent() + "\n" + note2.getContent(),
				note1.getType().ordinal() > note2.getType().ordinal() ? note1.getType() : note2.getType());
	}

}
