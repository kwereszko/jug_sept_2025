package com.hlag.jug.service;

import com.hlag.jug.model.Note;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Slf4j
public class NoteService {

	public Note merge(Long id1, Long id2) {
		log.info("Finding notes to merge: {} and {}", id1, id2);
		Note note1 = findNote(id1);
		Note note2 = findNote(id2);
		log.info("Merging notes to merge: {} and {}", id1, id2);
		return mergeAndPersist(note1, note2);
	}

	@Transactional
	public Note findNote(Long id) {
		return Note.findById(id);
	}

	@Transactional
	Note mergeAndPersist(Note note1, Note note2) {
		Note newNote =  new Note(note1.getTitle() + " & " + note2.getTitle(),
				note1.getContent() + "\n" + note2.getContent(),
				note1.getType().ordinal() > note2.getType().ordinal() ? note1.getType() : note2.getType());
		newNote.persist();
		return newNote;
	}

}
